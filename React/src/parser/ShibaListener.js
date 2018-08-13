// Generated from Shiba.g4 by ANTLR 4.7.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by ShibaParser.
function ShibaListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

ShibaListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
ShibaListener.prototype.constructor = ShibaListener;

// Enter a parse tree produced by ShibaParser#view.
ShibaListener.prototype.enterView = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#view.
ShibaListener.prototype.exitView = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#property.
ShibaListener.prototype.enterProperty = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#property.
ShibaListener.prototype.exitProperty = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#value.
ShibaListener.prototype.enterValue = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#value.
ShibaListener.prototype.exitValue = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#map.
ShibaListener.prototype.enterMap = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#map.
ShibaListener.prototype.exitMap = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#basicValue.
ShibaListener.prototype.enterBasicValue = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#basicValue.
ShibaListener.prototype.exitBasicValue = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#functionCall.
ShibaListener.prototype.enterFunctionCall = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#functionCall.
ShibaListener.prototype.exitFunctionCall = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#shibaExtension.
ShibaListener.prototype.enterShibaExtension = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#shibaExtension.
ShibaListener.prototype.exitShibaExtension = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#array.
ShibaListener.prototype.enterArray = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#array.
ShibaListener.prototype.exitArray = function(ctx) {
};


// Enter a parse tree produced by ShibaParser#identifier.
ShibaListener.prototype.enterIdentifier = function(ctx) {
};

// Exit a parse tree produced by ShibaParser#identifier.
ShibaListener.prototype.exitIdentifier = function(ctx) {
};



exports.ShibaListener = ShibaListener;