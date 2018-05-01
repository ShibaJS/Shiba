grammar Shiba;

root
   : obj
   ;

obj
   : TOKEN ('{' ((pair | obj) (','? (pair | obj))*)? '}')?
   ;

pair
   : TOKEN ':' value
   ;

value
   : STRING
   | NUMBER
   | BOOLEAN
   | 'null'
   | TOKEN
   | percent
   | thickness
   | binding
   | native
   | dic
   | jsonpath
   ;

STRING
   : '"' (~'"')* '"'
   ;

BOOLEAN
   : 'true'
   | 'false'
   ;

TOKEN
   : ([a-z] | [A-Z] | '_')+ ([a-z] | [A-Z] | [0-9] | '_' | '.')*
   ;

NUMBER
   : '-'? INT ('.' [0-9] +)? EXP?
   ;

percent
   : NUMBER '%'
   ;

thickness
   : NUMBER
   | NUMBER ',' NUMBER
   | NUMBER ',' NUMBER ',' NUMBER ',' NUMBER
   ;

comput
   : func
   | value
   ;

func
   : TOKEN '(' comput (',' value)? ')'
   ;

binding
   : '$bind' comput
   ;

native
   : '$res' comput
   ;

jsonpath
   : '$json' comput
   ;

dic
   : '[' pair (','? pair)* ']'
   ;

fragment INT
   : '0' | [1-9] [0-9]*
   ;

fragment EXP
   : [Ee] [+\-]? INT
   ;

Hws: [ \t] -> skip;
Vws: [\r\n\f] -> skip;
DocComment: '/**' .*? ('*/' | EOF) -> skip;
BlockComment: '/*'  .*? ('*/' | EOF) -> skip;
LineComment: '//' ~[\r\n]* -> skip;
LineCommentExt: '//' ~'\n'* ( '\n' Hws* '//' ~'\n'* )* -> skip;