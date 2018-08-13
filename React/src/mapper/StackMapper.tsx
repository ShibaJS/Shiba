import * as React from "react";
import {IViewProp} from "./IViewProp";
import Mappers from "./Mappers";

export default class StackMapper extends React.Component<IViewProp, any> {
    public render() {
        return (
            <div>
                {this.getChilds()}
            </div>
        )
    }

    private getChilds(): any {
        return this.props.view.children.map(value => {
            Mappers[value.viewName.value]({
                dataContext: this.props.dataContext,
                view: value
            })
        })
    }
}