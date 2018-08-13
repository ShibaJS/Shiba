import {CommonTokenStream, InputStream} from "antlr4";
import {ShibaLexer} from "./ShibaLexer";
import {ShibaParser} from "./ShibaParser";
import Visitors from "./Visitors";

function parseGrammarTree(input) {
    const chars = new InputStream(input);
    const lexer = new ShibaLexer(chars);
    const tokens = new CommonTokenStream(lexer);
    const parser = new ShibaParser(tokens);
    parser.buildParseTrees = true;
    return parser.view();
}


const ShibaParserWrapper = {
    parse: function (input) {
        const tree = parseGrammarTree(input);
        return Visitors.ViewContextVisitor(tree);
    }
};

export default ShibaParserWrapper;