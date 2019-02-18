import EventEmitter from "events";
import express from "express";
import { readFile, watchFile } from "fs";
import { promisify } from "util";

const layoutPath = "./layout.xml";
const dataPath = "./data.json";
const readFileAsync = promisify(readFile);
const Stream = new EventEmitter();

watchFile(layoutPath, async (curr, prev) => {
    const content = await readFileAsync(layoutPath, "utf8");
    Stream.emit("layout_changed", "layout_changed", content);
});

watchFile(dataPath, async (curr, prev) => {
    const content = await readFileAsync(dataPath, "utf8");
    Stream.emit("data_changed", "data_changed", content);
});

const app = express();

app.get("/streaming", async (req, res) => {
    res.writeHead(200, {
        "Cache-Control": "no-cache",
        "Connection": "keep-alive",
        "Content-Type": "text/event-stream",
    });

    Stream.on("layout_changed", (event, data) => {
        res.write("event: " + String(event) + "\n" + "data: " + data.replace(/\r?\n|\r/g, "") + "\n\n");
    });

    Stream.on("data_changed", (event, data) => {
        res.write("event: " + String(event) + "\n" + "data: " + data.replace(/\r?\n|\r/g, "") + "\n\n");
    });

    Stream.emit("data_changed", "data_changed", await readFileAsync(dataPath, "utf8"));
    Stream.emit("layout_changed", "layout_changed", await readFileAsync(layoutPath, "utf8"));
});

app.listen(5000);
