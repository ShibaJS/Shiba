// Generated from Shiba.g4 by ANTLR 4.7.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var ShibaListener = require('./ShibaListener').ShibaListener;
var grammarFileName = "Shiba.g4";

var serializedATN = ["\u0003\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964",
    "\u0003\u0019l\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0005\u0002\u0019\n\u0002\u0003\u0002\u0005\u0002\u001c\n\u0002",
    "\u0007\u0002\u001e\n\u0002\f\u0002\u000e\u0002!\u000b\u0002\u0003\u0002",
    "\u0003\u0002\u0003\u0002\u0003\u0002\u0005\u0002\'\n\u0002\u0005\u0002",
    ")\n\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004",
    "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0005\u0004",
    "5\n\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005:\n\u0005\u0007",
    "\u0005<\n\u0005\f\u0005\u000e\u0005?\u000b\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0006\u0003\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0005\u0007I\n\u0007\u0007\u0007K\n\u0007\f\u0007\u000e\u0007N\u000b",
    "\u0007\u0003\u0007\u0003\u0007\u0003\b\u0003\b\u0003\b\u0005\bU\n\b",
    "\u0003\b\u0005\bX\n\b\u0003\t\u0003\t\u0003\t\u0005\t]\n\t\u0007\t_",
    "\n\t\f\t\u000e\tb\u000b\t\u0003\t\u0003\t\u0003\n\u0003\n\u0005\nh\n",
    "\n\u0003\n\u0003\n\u0003\n\u0002\u0002\u000b\u0002\u0004\u0006\b\n\f",
    "\u000e\u0010\u0012\u0002\u0003\u0003\u0002\u0005\t\u0002v\u0002\u0014",
    "\u0003\u0002\u0002\u0002\u0004*\u0003\u0002\u0002\u0002\u00064\u0003",
    "\u0002\u0002\u0002\b6\u0003\u0002\u0002\u0002\nB\u0003\u0002\u0002\u0002",
    "\fD\u0003\u0002\u0002\u0002\u000eQ\u0003\u0002\u0002\u0002\u0010Y\u0003",
    "\u0002\u0002\u0002\u0012g\u0003\u0002\u0002\u0002\u0014(\u0005\u0012",
    "\n\u0002\u0015\u001f\u0007\u0012\u0002\u0002\u0016\u0019\u0005\u0004",
    "\u0003\u0002\u0017\u0019\u0005\u0002\u0002\u0002\u0018\u0016\u0003\u0002",
    "\u0002\u0002\u0018\u0017\u0003\u0002\u0002\u0002\u0019\u001b\u0003\u0002",
    "\u0002\u0002\u001a\u001c\u0007\u000b\u0002\u0002\u001b\u001a\u0003\u0002",
    "\u0002\u0002\u001b\u001c\u0003\u0002\u0002\u0002\u001c\u001e\u0003\u0002",
    "\u0002\u0002\u001d\u0018\u0003\u0002\u0002\u0002\u001e!\u0003\u0002",
    "\u0002\u0002\u001f\u001d\u0003\u0002\u0002\u0002\u001f \u0003\u0002",
    "\u0002\u0002 \"\u0003\u0002\u0002\u0002!\u001f\u0003\u0002\u0002\u0002",
    "\")\u0007\u0013\u0002\u0002#&\u0007\n\u0002\u0002$\'\u0005\u0006\u0004",
    "\u0002%\'\u0005\u0004\u0003\u0002&$\u0003\u0002\u0002\u0002&%\u0003",
    "\u0002\u0002\u0002\')\u0003\u0002\u0002\u0002(\u0015\u0003\u0002\u0002",
    "\u0002(#\u0003\u0002\u0002\u0002()\u0003\u0002\u0002\u0002)\u0003\u0003",
    "\u0002\u0002\u0002*+\u0005\u0012\n\u0002+,\u0007\r\u0002\u0002,-\u0005",
    "\u0006\u0004\u0002-\u0005\u0003\u0002\u0002\u0002.5\u0005\n\u0006\u0002",
    "/5\u0005\u0010\t\u000205\u0005\b\u0005\u000215\u0005\f\u0007\u00022",
    "5\u0005\u000e\b\u000235\u0005\u0002\u0002\u00024.\u0003\u0002\u0002",
    "\u00024/\u0003\u0002\u0002\u000240\u0003\u0002\u0002\u000241\u0003\u0002",
    "\u0002\u000242\u0003\u0002\u0002\u000243\u0003\u0002\u0002\u00025\u0007",
    "\u0003\u0002\u0002\u00026=\u0007\u0010\u0002\u000279\u0005\u0004\u0003",
    "\u00028:\u0007\u000b\u0002\u000298\u0003\u0002\u0002\u00029:\u0003\u0002",
    "\u0002\u0002:<\u0003\u0002\u0002\u0002;7\u0003\u0002\u0002\u0002<?\u0003",
    "\u0002\u0002\u0002=;\u0003\u0002\u0002\u0002=>\u0003\u0002\u0002\u0002",
    ">@\u0003\u0002\u0002\u0002?=\u0003\u0002\u0002\u0002@A\u0007\u0011\u0002",
    "\u0002A\t\u0003\u0002\u0002\u0002BC\t\u0002\u0002\u0002C\u000b\u0003",
    "\u0002\u0002\u0002DE\u0007\b\u0002\u0002EL\u0007\u000e\u0002\u0002F",
    "H\u0005\u0006\u0004\u0002GI\u0007\u000b\u0002\u0002HG\u0003\u0002\u0002",
    "\u0002HI\u0003\u0002\u0002\u0002IK\u0003\u0002\u0002\u0002JF\u0003\u0002",
    "\u0002\u0002KN\u0003\u0002\u0002\u0002LJ\u0003\u0002\u0002\u0002LM\u0003",
    "\u0002\u0002\u0002MO\u0003\u0002\u0002\u0002NL\u0003\u0002\u0002\u0002",
    "OP\u0007\u000f\u0002\u0002P\r\u0003\u0002\u0002\u0002QR\u0007\u0003",
    "\u0002\u0002RT\u0007\b\u0002\u0002SU\u0005\n\u0006\u0002TS\u0003\u0002",
    "\u0002\u0002TU\u0003\u0002\u0002\u0002UW\u0003\u0002\u0002\u0002VX\u0007",
    "\u0004\u0002\u0002WV\u0003\u0002\u0002\u0002WX\u0003\u0002\u0002\u0002",
    "X\u000f\u0003\u0002\u0002\u0002Y`\u0007\u0010\u0002\u0002Z\\\u0005\u0006",
    "\u0004\u0002[]\u0007\u000b\u0002\u0002\\[\u0003\u0002\u0002\u0002\\",
    "]\u0003\u0002\u0002\u0002]_\u0003\u0002\u0002\u0002^Z\u0003\u0002\u0002",
    "\u0002_b\u0003\u0002\u0002\u0002`^\u0003\u0002\u0002\u0002`a\u0003\u0002",
    "\u0002\u0002ac\u0003\u0002\u0002\u0002b`\u0003\u0002\u0002\u0002cd\u0007",
    "\u0011\u0002\u0002d\u0011\u0003\u0002\u0002\u0002ef\u0007\b\u0002\u0002",
    "fh\u0007\f\u0002\u0002ge\u0003\u0002\u0002\u0002gh\u0003\u0002\u0002",
    "\u0002hi\u0003\u0002\u0002\u0002ij\u0007\b\u0002\u0002j\u0013\u0003",
    "\u0002\u0002\u0002\u0011\u0018\u001b\u001f&(49=HLTW\\`g"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'$'", null, "'null'", null, null, null, null, 
                     "'->'", "','", "':'", "'='", "'('", "')'", "'['", "']'", 
                     "'{'", "'}'" ];

var symbolicNames = [ null, null, "Script", "Null", "String", "Boolean", 
                      "Identifier", "Number", "Arrow", "Comma", "Colon", 
                      "Assign", "LeftParen", "RightParen", "LeftBracket", 
                      "RightBracket", "LeftBrace", "RightBrace", "Hws", 
                      "Vws", "DocComment", "BlockComment", "LineComment", 
                      "LineCommentExt" ];

var ruleNames =  [ "view", "property", "value", "map", "basicValue", "functionCall", 
                   "shibaExtension", "array", "identifier" ];

function ShibaParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

ShibaParser.prototype = Object.create(antlr4.Parser.prototype);
ShibaParser.prototype.constructor = ShibaParser;

Object.defineProperty(ShibaParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

ShibaParser.EOF = antlr4.Token.EOF;
ShibaParser.T__0 = 1;
ShibaParser.Script = 2;
ShibaParser.Null = 3;
ShibaParser.String = 4;
ShibaParser.Boolean = 5;
ShibaParser.Identifier = 6;
ShibaParser.Number = 7;
ShibaParser.Arrow = 8;
ShibaParser.Comma = 9;
ShibaParser.Colon = 10;
ShibaParser.Assign = 11;
ShibaParser.LeftParen = 12;
ShibaParser.RightParen = 13;
ShibaParser.LeftBracket = 14;
ShibaParser.RightBracket = 15;
ShibaParser.LeftBrace = 16;
ShibaParser.RightBrace = 17;
ShibaParser.Hws = 18;
ShibaParser.Vws = 19;
ShibaParser.DocComment = 20;
ShibaParser.BlockComment = 21;
ShibaParser.LineComment = 22;
ShibaParser.LineCommentExt = 23;

ShibaParser.RULE_view = 0;
ShibaParser.RULE_property = 1;
ShibaParser.RULE_value = 2;
ShibaParser.RULE_map = 3;
ShibaParser.RULE_basicValue = 4;
ShibaParser.RULE_functionCall = 5;
ShibaParser.RULE_shibaExtension = 6;
ShibaParser.RULE_array = 7;
ShibaParser.RULE_identifier = 8;

function ViewContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_view;
    return this;
}

ViewContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ViewContext.prototype.constructor = ViewContext;

ViewContext.prototype.identifier = function() {
    return this.getTypedRuleContext(IdentifierContext,0);
};

ViewContext.prototype.LeftBrace = function() {
    return this.getToken(ShibaParser.LeftBrace, 0);
};

ViewContext.prototype.RightBrace = function() {
    return this.getToken(ShibaParser.RightBrace, 0);
};

ViewContext.prototype.Arrow = function() {
    return this.getToken(ShibaParser.Arrow, 0);
};

ViewContext.prototype.value = function() {
    return this.getTypedRuleContext(ValueContext,0);
};

ViewContext.prototype.property = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(PropertyContext);
    } else {
        return this.getTypedRuleContext(PropertyContext,i);
    }
};

ViewContext.prototype.view = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ViewContext);
    } else {
        return this.getTypedRuleContext(ViewContext,i);
    }
};

ViewContext.prototype.Comma = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(ShibaParser.Comma);
    } else {
        return this.getToken(ShibaParser.Comma, i);
    }
};


ViewContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterView(this);
	}
};

ViewContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitView(this);
	}
};




ShibaParser.ViewContext = ViewContext;

ShibaParser.prototype.view = function() {

    var localctx = new ViewContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, ShibaParser.RULE_view);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 18;
        this.identifier();
        this.state = 38;
        this._errHandler.sync(this);
        switch (this._input.LA(1)) {
        case ShibaParser.LeftBrace:
        	this.state = 19;
        	this.match(ShibaParser.LeftBrace);
        	this.state = 29;
        	this._errHandler.sync(this);
        	_la = this._input.LA(1);
        	while(_la===ShibaParser.Identifier) {
        	    this.state = 22;
        	    this._errHandler.sync(this);
        	    var la_ = this._interp.adaptivePredict(this._input,0,this._ctx);
        	    switch(la_) {
        	    case 1:
        	        this.state = 20;
        	        this.property();
        	        break;

        	    case 2:
        	        this.state = 21;
        	        this.view();
        	        break;

        	    }
        	    this.state = 25;
        	    this._errHandler.sync(this);
        	    _la = this._input.LA(1);
        	    if(_la===ShibaParser.Comma) {
        	        this.state = 24;
        	        this.match(ShibaParser.Comma);
        	    }

        	    this.state = 31;
        	    this._errHandler.sync(this);
        	    _la = this._input.LA(1);
        	}
        	this.state = 32;
        	this.match(ShibaParser.RightBrace);
        	break;
        case ShibaParser.Arrow:
        	this.state = 33;
        	this.match(ShibaParser.Arrow);
        	this.state = 36;
        	this._errHandler.sync(this);
        	var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        	switch(la_) {
        	case 1:
        	    this.state = 34;
        	    this.value();
        	    break;

        	case 2:
        	    this.state = 35;
        	    this.property();
        	    break;

        	}
        	break;
        case ShibaParser.T__0:
        case ShibaParser.Null:
        case ShibaParser.String:
        case ShibaParser.Boolean:
        case ShibaParser.Identifier:
        case ShibaParser.Number:
        case ShibaParser.Comma:
        case ShibaParser.RightParen:
        case ShibaParser.LeftBracket:
        case ShibaParser.RightBracket:
        case ShibaParser.RightBrace:
        	break;
        default:
        	break;
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function PropertyContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_property;
    return this;
}

PropertyContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
PropertyContext.prototype.constructor = PropertyContext;

PropertyContext.prototype.identifier = function() {
    return this.getTypedRuleContext(IdentifierContext,0);
};

PropertyContext.prototype.Assign = function() {
    return this.getToken(ShibaParser.Assign, 0);
};

PropertyContext.prototype.value = function() {
    return this.getTypedRuleContext(ValueContext,0);
};

PropertyContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterProperty(this);
	}
};

PropertyContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitProperty(this);
	}
};




ShibaParser.PropertyContext = PropertyContext;

ShibaParser.prototype.property = function() {

    var localctx = new PropertyContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, ShibaParser.RULE_property);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 40;
        this.identifier();
        this.state = 41;
        this.match(ShibaParser.Assign);
        this.state = 42;
        this.value();
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ValueContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_value;
    return this;
}

ValueContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ValueContext.prototype.constructor = ValueContext;

ValueContext.prototype.basicValue = function() {
    return this.getTypedRuleContext(BasicValueContext,0);
};

ValueContext.prototype.array = function() {
    return this.getTypedRuleContext(ArrayContext,0);
};

ValueContext.prototype.map = function() {
    return this.getTypedRuleContext(MapContext,0);
};

ValueContext.prototype.functionCall = function() {
    return this.getTypedRuleContext(FunctionCallContext,0);
};

ValueContext.prototype.shibaExtension = function() {
    return this.getTypedRuleContext(ShibaExtensionContext,0);
};

ValueContext.prototype.view = function() {
    return this.getTypedRuleContext(ViewContext,0);
};

ValueContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterValue(this);
	}
};

ValueContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitValue(this);
	}
};




ShibaParser.ValueContext = ValueContext;

ShibaParser.prototype.value = function() {

    var localctx = new ValueContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, ShibaParser.RULE_value);
    try {
        this.state = 50;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,5,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 44;
            this.basicValue();
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 45;
            this.array();
            break;

        case 3:
            this.enterOuterAlt(localctx, 3);
            this.state = 46;
            this.map();
            break;

        case 4:
            this.enterOuterAlt(localctx, 4);
            this.state = 47;
            this.functionCall();
            break;

        case 5:
            this.enterOuterAlt(localctx, 5);
            this.state = 48;
            this.shibaExtension();
            break;

        case 6:
            this.enterOuterAlt(localctx, 6);
            this.state = 49;
            this.view();
            break;

        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function MapContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_map;
    return this;
}

MapContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
MapContext.prototype.constructor = MapContext;

MapContext.prototype.LeftBracket = function() {
    return this.getToken(ShibaParser.LeftBracket, 0);
};

MapContext.prototype.RightBracket = function() {
    return this.getToken(ShibaParser.RightBracket, 0);
};

MapContext.prototype.property = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(PropertyContext);
    } else {
        return this.getTypedRuleContext(PropertyContext,i);
    }
};

MapContext.prototype.Comma = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(ShibaParser.Comma);
    } else {
        return this.getToken(ShibaParser.Comma, i);
    }
};


MapContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterMap(this);
	}
};

MapContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitMap(this);
	}
};




ShibaParser.MapContext = MapContext;

ShibaParser.prototype.map = function() {

    var localctx = new MapContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, ShibaParser.RULE_map);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 52;
        this.match(ShibaParser.LeftBracket);
        this.state = 59;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===ShibaParser.Identifier) {
            this.state = 53;
            this.property();
            this.state = 55;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if(_la===ShibaParser.Comma) {
                this.state = 54;
                this.match(ShibaParser.Comma);
            }

            this.state = 61;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 62;
        this.match(ShibaParser.RightBracket);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function BasicValueContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_basicValue;
    return this;
}

BasicValueContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BasicValueContext.prototype.constructor = BasicValueContext;

BasicValueContext.prototype.String = function() {
    return this.getToken(ShibaParser.String, 0);
};

BasicValueContext.prototype.Number = function() {
    return this.getToken(ShibaParser.Number, 0);
};

BasicValueContext.prototype.Boolean = function() {
    return this.getToken(ShibaParser.Boolean, 0);
};

BasicValueContext.prototype.Null = function() {
    return this.getToken(ShibaParser.Null, 0);
};

BasicValueContext.prototype.Identifier = function() {
    return this.getToken(ShibaParser.Identifier, 0);
};

BasicValueContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterBasicValue(this);
	}
};

BasicValueContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitBasicValue(this);
	}
};




ShibaParser.BasicValueContext = BasicValueContext;

ShibaParser.prototype.basicValue = function() {

    var localctx = new BasicValueContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, ShibaParser.RULE_basicValue);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 64;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << ShibaParser.Null) | (1 << ShibaParser.String) | (1 << ShibaParser.Boolean) | (1 << ShibaParser.Identifier) | (1 << ShibaParser.Number))) !== 0))) {
        this._errHandler.recoverInline(this);
        }
        else {
        	this._errHandler.reportMatch(this);
            this.consume();
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function FunctionCallContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_functionCall;
    return this;
}

FunctionCallContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
FunctionCallContext.prototype.constructor = FunctionCallContext;

FunctionCallContext.prototype.Identifier = function() {
    return this.getToken(ShibaParser.Identifier, 0);
};

FunctionCallContext.prototype.LeftParen = function() {
    return this.getToken(ShibaParser.LeftParen, 0);
};

FunctionCallContext.prototype.RightParen = function() {
    return this.getToken(ShibaParser.RightParen, 0);
};

FunctionCallContext.prototype.value = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ValueContext);
    } else {
        return this.getTypedRuleContext(ValueContext,i);
    }
};

FunctionCallContext.prototype.Comma = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(ShibaParser.Comma);
    } else {
        return this.getToken(ShibaParser.Comma, i);
    }
};


FunctionCallContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterFunctionCall(this);
	}
};

FunctionCallContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitFunctionCall(this);
	}
};




ShibaParser.FunctionCallContext = FunctionCallContext;

ShibaParser.prototype.functionCall = function() {

    var localctx = new FunctionCallContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, ShibaParser.RULE_functionCall);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 66;
        this.match(ShibaParser.Identifier);
        this.state = 67;
        this.match(ShibaParser.LeftParen);
        this.state = 74;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << ShibaParser.T__0) | (1 << ShibaParser.Null) | (1 << ShibaParser.String) | (1 << ShibaParser.Boolean) | (1 << ShibaParser.Identifier) | (1 << ShibaParser.Number) | (1 << ShibaParser.LeftBracket))) !== 0)) {
            this.state = 68;
            this.value();
            this.state = 70;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if(_la===ShibaParser.Comma) {
                this.state = 69;
                this.match(ShibaParser.Comma);
            }

            this.state = 76;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 77;
        this.match(ShibaParser.RightParen);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ShibaExtensionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_shibaExtension;
    return this;
}

ShibaExtensionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ShibaExtensionContext.prototype.constructor = ShibaExtensionContext;

ShibaExtensionContext.prototype.Identifier = function() {
    return this.getToken(ShibaParser.Identifier, 0);
};

ShibaExtensionContext.prototype.basicValue = function() {
    return this.getTypedRuleContext(BasicValueContext,0);
};

ShibaExtensionContext.prototype.Script = function() {
    return this.getToken(ShibaParser.Script, 0);
};

ShibaExtensionContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterShibaExtension(this);
	}
};

ShibaExtensionContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitShibaExtension(this);
	}
};




ShibaParser.ShibaExtensionContext = ShibaExtensionContext;

ShibaParser.prototype.shibaExtension = function() {

    var localctx = new ShibaExtensionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, ShibaParser.RULE_shibaExtension);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 79;
        this.match(ShibaParser.T__0);
        this.state = 80;
        this.match(ShibaParser.Identifier);
        this.state = 82;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,10,this._ctx);
        if(la_===1) {
            this.state = 81;
            this.basicValue();

        }
        this.state = 85;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        if(_la===ShibaParser.Script) {
            this.state = 84;
            this.match(ShibaParser.Script);
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ArrayContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_array;
    return this;
}

ArrayContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ArrayContext.prototype.constructor = ArrayContext;

ArrayContext.prototype.LeftBracket = function() {
    return this.getToken(ShibaParser.LeftBracket, 0);
};

ArrayContext.prototype.RightBracket = function() {
    return this.getToken(ShibaParser.RightBracket, 0);
};

ArrayContext.prototype.value = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ValueContext);
    } else {
        return this.getTypedRuleContext(ValueContext,i);
    }
};

ArrayContext.prototype.Comma = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(ShibaParser.Comma);
    } else {
        return this.getToken(ShibaParser.Comma, i);
    }
};


ArrayContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterArray(this);
	}
};

ArrayContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitArray(this);
	}
};




ShibaParser.ArrayContext = ArrayContext;

ShibaParser.prototype.array = function() {

    var localctx = new ArrayContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, ShibaParser.RULE_array);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 87;
        this.match(ShibaParser.LeftBracket);
        this.state = 94;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << ShibaParser.T__0) | (1 << ShibaParser.Null) | (1 << ShibaParser.String) | (1 << ShibaParser.Boolean) | (1 << ShibaParser.Identifier) | (1 << ShibaParser.Number) | (1 << ShibaParser.LeftBracket))) !== 0)) {
            this.state = 88;
            this.value();
            this.state = 90;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if(_la===ShibaParser.Comma) {
                this.state = 89;
                this.match(ShibaParser.Comma);
            }

            this.state = 96;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 97;
        this.match(ShibaParser.RightBracket);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function IdentifierContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = ShibaParser.RULE_identifier;
    return this;
}

IdentifierContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IdentifierContext.prototype.constructor = IdentifierContext;

IdentifierContext.prototype.Identifier = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(ShibaParser.Identifier);
    } else {
        return this.getToken(ShibaParser.Identifier, i);
    }
};


IdentifierContext.prototype.Colon = function() {
    return this.getToken(ShibaParser.Colon, 0);
};

IdentifierContext.prototype.enterRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.enterIdentifier(this);
	}
};

IdentifierContext.prototype.exitRule = function(listener) {
    if(listener instanceof ShibaListener ) {
        listener.exitIdentifier(this);
	}
};




ShibaParser.IdentifierContext = IdentifierContext;

ShibaParser.prototype.identifier = function() {

    var localctx = new IdentifierContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, ShibaParser.RULE_identifier);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 101;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,14,this._ctx);
        if(la_===1) {
            this.state = 99;
            this.match(ShibaParser.Identifier);
            this.state = 100;
            this.match(ShibaParser.Colon);

        }
        this.state = 103;
        this.match(ShibaParser.Identifier);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};


exports.ShibaParser = ShibaParser;
