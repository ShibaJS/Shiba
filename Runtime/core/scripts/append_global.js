const fs = require("fs");
const globalFile = fs.readFileSync("./src/global.d.ts");
fs.appendFileSync("./lib/index.d.ts", globalFile);
