import crypto from "crypto";

// tslint:disable-next-line:ban-types
export default function registerGlobalFunction(func: Function): string {
    const hash = "_" + crypto.createHash("sha256").update(func.toString()).digest("hex");
    const g: any = global;
    g[hash] = func;
    return hash;
}
