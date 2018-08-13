import {ShibaToken} from "./ShibaToken";

export class Property {
    public readonly name: ShibaToken;
    public readonly value: any;

    constructor(name: ShibaToken, value: any) {
        this.name = name;
        this.value = value;
    }


}