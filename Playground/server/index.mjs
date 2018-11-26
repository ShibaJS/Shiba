import Koa from 'koa';
import { readFile, watchFile } from 'fs';
import Router from 'koa-router';
import { promisify } from 'util';
import EventEmitter from 'events'
import { PassThrough } from 'stream';

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
    const stream = new PassThrough();
    
    ctx.set('Content-Type', 'text/event-stream');
    ctx.set('Cache-Control', 'no-cache');
    ctx.set('Connection', 'keep-alive');
    ctx.status = 200;

    Stream.on("layout_changed", function (event, data) {
        console.log("layout_changed");
        console.log(event);
        console.log(data);
        stream.write("event: " + String(event) + "\n" + "data: " + data.replace(/\r?\n|\r/g,"") + "\n\n");
    });

    Stream.on("data_changed", function (event, data) {
        console.log("data_changed");
        console.log(event);
        console.log(data);
        stream.write("event: " + String(event) + "\n" + "data: " + data.replace(/\r?\n|\r/g,"") + "\n\n");
    });

    ctx.type = 'text/event-stream';
    ctx.body = stream;
    
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
