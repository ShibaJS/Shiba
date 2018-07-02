grammar Shiba;

view:
	token (
		(
			LeftBrace (
				(property | view) (Comma? (property | view))*
			)? RightBrace
		)
		| Arrow (value | property)
	)?;

property: token Assign value;

value: basicValue | array | object | function | shibaExtension;

object: LeftBrace (property (Comma? property)*)? RightBrace;

basicValue: String | Number | Boolean | Null | Token;

function: Token LeftParen paramter (Comma paramter)* RightParen;

paramter: value | function;

shibaExtension: '$' Token basicValue;

array: LeftBracket value (Comma? value)* RightBracket;

token: (Token Colon)? Token;

Null: 'null';
String: '"' (~'"')* '"';
Boolean: 'true' | 'false';
Token: ([a-z] | [A-Z] | '_')+ ([a-z] | [A-Z] | [0-9] | '_' | '.')*;
Number: '-'? INT ('.' [0-9]+)? EXP?;
Arrow: '->';
Comma: ',';
Colon: ':';
Assign: '=';
LeftParen: '(';
RightParen: ')';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';

fragment INT: '0' | [1-9] [0-9]*;
fragment EXP: [Ee] [+\-]? INT;

Hws: [ \t] -> skip;
Vws: [\r\n\f] -> skip;
DocComment: '/**' .*? ('*/' | EOF) -> skip;
BlockComment: '/*' .*? ('*/' | EOF) -> skip;
LineComment: '//' ~[\r\n]* -> skip;
LineCommentExt: '//' ~'\n'* ( '\n' Hws* '//' ~'\n'*)* -> skip;