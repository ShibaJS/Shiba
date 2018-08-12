grammar Shiba;

view:
	identifier (
		LeftBrace ((property | view) Comma?)* RightBrace
		| Arrow (value | property)
	)?;

property: identifier Assign value;

value:
	basicValue
	| array
	| map
	| functionCall
	| shibaExtension
	| view;

map: LeftBracket (property Comma?)* RightBracket;

basicValue: String | Number | Boolean | Null | Identifier;

functionCall: Identifier LeftParen (value Comma?)* RightParen;

shibaExtension: '$' Identifier basicValue;

array: LeftBracket (value Comma?)* RightBracket;

identifier: (Identifier Colon)? Identifier;

Null: 'null';
String: '"' (~'"')* '"';
Boolean: 'true' | 'false';
Identifier: ([a-z] | [A-Z] | '_')+ (
		[a-z]
		| [A-Z]
		| [0-9]
		| '_'
		| '.'
	)*;
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