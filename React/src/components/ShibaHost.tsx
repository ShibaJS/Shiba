import * as React from "react";
import Mappers from "../mapper/Mappers";
import ShibaParserWrapper from "../parser/ShibaParserWrapper";

interface IProp {
    layout: string,
    dataContext?: any
}

export default class ShibaHost extends React.Component<IProp, any> {
    constructor(props: IProp) {
        super(props);
    }

    public render() {
        return (
            <div>
                {this.nativeRender()}
            </div>
        )
    }

    private nativeRender(): any {
        const layout = ShibaParserWrapper.parse(this.props.layout);
        return Mappers[this.props.layout]({
            dataContext: this.props.dataContext,
            view: layout
        })
    }
}