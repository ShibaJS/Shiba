"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ShibaApp = /** @class */ (function () {
    function ShibaApp() {
        this.components = [];
    }
    ShibaApp.prototype.addComponent = function (component) {
        this.components.push(component);
    };
    return ShibaApp;
}());
exports.default = ShibaApp;
