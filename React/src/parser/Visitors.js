import {BasicValue, Property, ShibaArray, ShibaExtension, ShibaFunction, ShibaToken, View} from "../types";
import {ShibaValueType} from "../types/BasicValue";
import {ShibaParser} from "./ShibaParser";

const Visitors = {
    ViewContextVisitor: function (tree) {
        const viewName = this.IdentifierContextVisitor(tree.identifier());
        const view = new View(viewName);
        const self = this;
        if (tree.property()) {
            view.properties.push(tree.property().map((value, index, array) => {
                return self.PropertyContextVisitor(value);
            }))
        }

        if (tree.view()) {
            view.children.push(tree.view().map((value, index, array) => {
                return self.ViewContextVisitor(value);
            }))
        }

        if (tree.value()) {
            view.defaultValue = this.ValueContextVisitor(tree.value());
        }

        return view;
    },

    IdentifierContextVisitor: function (tree) {
        switch (tree.Identifier().length) {
            case 1:
                return new ShibaToken("", tree.Identifier()[0].getText());
            case 2:
                return new ShibaToken(tree.Identifier()[0].getText(), tree.Identifier()[1].getText());
            default:
                throw new SyntaxError('view name can not be null at line:' + tree.start.line+ ' column:'+ tree.start.startIndex);
        }
    },

    ValueContextVisitor: function (tree) {
        let type = tree.children[0].constructor.name;
        return this[type + "Visitor"](tree.children[0]);
    },

    BasicValueContextVisitor: function (tree) {
        let type = ShibaValueType.Token;
        let targetValue = undefined;
        let token = tree.getChild(0);

        switch (token.symbol.type) {
            case ShibaParser.String:
                type = ShibaValueType.String;
                targetValue = token.getText().replace(/^"+|"+$/g, '');
                break;
            case ShibaParser.Number:
                type = ShibaValueType.Number;
                targetValue = parseFloat(token.getText());
                break;
            case ShibaParser.Boolean:
                type = ShibaValueType.Boolean;
                targetValue = token.getText() === 'true';
                break;
            case ShibaParser.Null:
                type = ShibaValueType.Null;
                targetValue = undefined;
                break;
            case ShibaParser.Identifier:
                type = ShibaValueType.Token;
                targetValue = token.getText();
                break;
        }
        return new BasicValue(type, targetValue);
    },

    PropertyContextVisitor: function (tree) {
      let name = this.IdentifierContextVisitor(tree.identifier());
      let value = this.ValueContextVisitor(tree.value());
      return new Property(name, value)
    },

    ArrayContextVisitor: function (tree) {
        let self = this;
        return tree.value().map((value, index, array) => {
            let type = value.constructor.name;
            return self[type + "Visitor"](value);
        }).filter((value, index, array) => {
            return value !== null && value !== undefined;
        });
    },

    MapContextVisitor: function (tree) {
        const self = this;
        return tree.property().map((value, index, array) => {
            return self.PropertyContextVisitor(value);
        }).filter((value, index, array) => {
            return value !== null && value !== undefined;
        })
    },

    FunctionCallContextVisitor: function (tree) {
        const self = this;
        let name = tree.Identifier().getText();
        const shibaFunction = new ShibaFunction(name);
        if (tree.value()) {
            shibaFunction.parameter.push(tree.value().map((value, index, array) => {
                let type = value.constructor.name;
                return self[type + "Visitor"](value);
            }).filter((value, index, array) => {
                return value !== null && value !== undefined;
            }))
        }
        return shibaFunction;
    },

    ShibaExtensionContextVisitor: function (tree) {
        let name = tree.Identifier().getText();
        let value = this.BasicValueContextVisitor(tree.basicValue());
        return new ShibaExtension(name, value);
    }
};

export default Visitors