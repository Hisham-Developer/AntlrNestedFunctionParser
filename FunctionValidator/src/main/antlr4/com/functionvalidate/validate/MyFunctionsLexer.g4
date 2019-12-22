lexer grammar MyFunctionsLexer;
FUNCTION: 'Functions';
NAME: [A-Za-z0-9]+;
DOT: '.';
COMMA: ',';
L_BRACKET: '(';
R_BRACKET: ')';
WS : [ \t\r\n]+ -> skip;