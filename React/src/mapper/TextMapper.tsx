import {string} from "prop-types";
import * as React from "react";
import {IViewProp} from "./IViewProp";


export default class TextMapper extends React.Component<IViewProp, any> {
    public render() {
        return (
            <span>{this.getText()}</span>
        )
    }

    private getText(): any {
        if (this.props.view.defaultValue instanceof string) {
            return this.props.view.defaultValue;
        }
        return this.props.view.properties.filter(value => value.name.value === "text")[0].value;
    }
}