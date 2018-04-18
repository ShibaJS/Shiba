grammar Shiba;

root
   : namespaces obj
   ;

namespaces
   : NAMESPACE*
   ;

NAMESPACE
   : '@' TOKEN ('.' TOKEN)*
   ;

obj
   : TOKEN ('{' pair (','? (pair | obj))* '}')?
   ;

pair
   : TOKEN ':' value
   ;

value
   : STRING
   | NUMBER
   | BOOLEAN
   | 'null'
   ;

STRING
   : '"' (~'"')* '"'
   ;

BOOLEAN
   : 'true'
   | 'false'
   ;

TOKEN
   : ([a-z] | [A-Z] | '_')+ ([a-z] | [A-Z] | [0-9] | '_')*
   ;

NUMBER
   : '-'? INT ('.' [0-9] +)? EXP?
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