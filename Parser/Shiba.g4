grammar Shiba;

root
   : obj
   ;

obj
   : TOKEN ('{' ((pair | obj) (','? (pair | obj))*)? '}')?
   ;

pair
   : TOKEN (':' | '=') value
   ;

value
   : staticvalue
   | binding
   | resource
   | jsonpath
   | calc
   | dic
   ;

staticvalue
   : STRING
   | NUMBER
   | BOOLEAN
   | 'null'
   | TOKEN
   | percent
   | thickness
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

calc
   : '$calc' func
   ;
   
func
   : TOKEN '(' paramter (',' paramter)* ')'
   ;

paramter
   : value
   | func
   ;

binding
   : '$bind' staticvalue
   ;

resource
   : '$res' staticvalue
   ;

jsonpath
   : '$json' staticvalue
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