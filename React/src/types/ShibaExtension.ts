import {BasicValue} from "./BasicValue";

export class ShibaExtension {
    public readonly type: string;
    public readonly value: BasicValue;
    
    constructor(type: string, value: BasicValue) {
        this.type = type;
        this.value = value;
    }
}
