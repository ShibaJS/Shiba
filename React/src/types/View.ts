import {Property} from "./Property";
import {ShibaToken} from "./ShibaToken";

export class View {
    public readonly children: View[] = [];
    public defaultValue: any = null;
    public readonly viewName: ShibaToken;
    public readonly properties: Property[] = [];

    constructor(viewName: ShibaToken) {
        this.viewName = viewName;
    }
}