import Event from "./Event";
import { IShibaComponentLifeCycle } from "./IShibaComponentLifeCycle";

export default abstract class ShibaComponent
    implements
    ProxyHandler<any>,
    IShibaComponentLifeCycle {

    public dataContextPropertyChanged = new Event<string>();
    public dataContextChanged = new Event<any>();

    public get dataContext(): any {
        return this._dataContext;
    }

    public set dataContext(v: any) {
        this._dataContext = new Proxy(v, this);
        this.dataContextChanged.invoke(this, this._dataContext);
    }

    // tslint:disable-next-line:variable-name
    private _dataContext: any;

    public constructor() {
        this.dataContext = this;
    }

    public set(target: any, property: PropertyKey, value: any, receiver: any): boolean {
        target[property] = value;
        if (typeof property === "string") {
            this.dataContextPropertyChanged.invoke(target, property);
        }
        return true;
    }

    public abstract view(): string;
}
