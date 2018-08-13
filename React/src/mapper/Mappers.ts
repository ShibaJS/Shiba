import * as React from "react";
import InputMapper from "./InputMapper";
import {IViewProp} from "./IViewProp";
import StackMapper from "./StackMapper";
import TextMapper from "./TextMapper";


const Mappers = {
    "input": (props: IViewProp) => React.createElement(InputMapper, props),
    "stack": (props: IViewProp) => React.createElement(StackMapper, props),
    "text": (props: IViewProp) => React.createElement(TextMapper, props),
};

export default Mappers;