"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
require("../globals");
var ShibaApp_1 = __importDefault(require("./ShibaApp"));
var Shiba = new ShibaApp_1.default();
global.Shiba = Shiba;
exports.default = Shiba;
