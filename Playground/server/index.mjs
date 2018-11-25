import Koa from 'koa';
import { readFile, watchFile } from 'fs';
import Router from 'koa-router';
import { promisify } from 'util';
import EventEmitter from 'events'

const layoutPath = './layout.sb';
const dataPath = './data.json';
const readFileAsync = promisify(readFile)
const Stream = new EventEmitter();

watchFile(layoutPath, async (curr, prev) => {
    const content = await readFileAsync(layoutPath, "utf8");
    Stream.emit("layout_changed", "layout_changed", content);
});

watchFile(dataPath, async (curr, prev) => {
    const content = await readFileAsync(dataPath, "utf8");
    Stream.emit("data_changed", "data_changed", content);
})


const app = new Koa();
var router = new Router();


//logger
app.use(async (ctx, next) => {
    await next();
    console.log(`${ctx.method} ${ctx.url}`);
    console.log(ctx.body);
});

router.get('/streaming', async (ctx, next) => {
    
    ctx.set('Content-Type', 'text/event-stream');
    ctx.set('Cache-Control', 'no-cache');
    ctx.set('Connection', 'keep-alive');
    ctx.status = 200;

    Stream.on("layout_changed", function (event, data) {
        console.log("layout_changed");
        console.log(event);
        console.log(data);
        ctx.body = ("event: " + String(event) + "\n" + "data: " + data.replace(/(\r\n\t|\n|\r\t)/gm,"") + "\n\n");
    });

    Stream.on("data_changed", function (event, data) {
        console.log("data_changed");
        console.log(event);
        console.log(data);
        ctx.body = ("event: " + String(event) + "\n" + "data: " + data.replace(/(\r\n\t|\n|\r\t)/gm,"") + "\n\n");
    });

    
    Stream.emit("data_changed", "data_changed", await readFileAsync(dataPath, "utf8"));
    Stream.emit("layout_changed", "layout_changed", await readFileAsync(layoutPath, "utf8"));
})

router.get('/layout', async (ctx, next) => {
    ctx.body = await readFileAsync('./layout.sb', "utf8");
});

router.get('/json', async (ctx, next) => {
    const content = JSON.parse(await readFileAsync(dataPath, "utf8"));
    ctx.body = content;
})


app.use(router.routes())
app.use(router.allowedMethods());

app.listen(5000);
