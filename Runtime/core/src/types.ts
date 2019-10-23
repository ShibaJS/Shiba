export interface IView {
    className: "IView";
    name: string;
    children?: IView[];
    properties: IProperty[];
}

export enum ValueType {
    Extension,
    Boolean,
    Number,
    String,
    Null,
    Custom,
}

export interface IShibaExtension {
    className: "IShibaExtension";
    type: string;
    target: string;
    scriptName?: string;
}

export interface IProperty {
    className: "IProperty";
    name: string;
    value: any;
    valueType: ValueType;
}
