import { isString } from "util";
import registerGlobalFunction from "./registerGlobalFunction";
import {IShibaExtension} from "./types";

export function binding<T>(target: string, converter?: string | ((value: T) => any)): IShibaExtension {
    return extension("bind", target, converter);
}

export function extension<T>(type: string, target: string, converter?: string | ((value: T) => any)): IShibaExtension {
    let converterName = "";
    if (converter instanceof Function) {
        converterName = registerGlobalFunction(converter);
    } else if (isString(converter)) {
        converterName = converter;
    }
    return {
        className: "IShibaExtension",
        scriptName: converterName,
        target,
        type,
    };
}
