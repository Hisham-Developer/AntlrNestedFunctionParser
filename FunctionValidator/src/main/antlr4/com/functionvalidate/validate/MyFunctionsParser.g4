parser grammar MyFunctionsParser;
options { tokenVocab=MyFunctionsLexer; }
functions : function* EOF;
//function : FUNCTION '.' NAME '(' (function | argument (',' argument)*) ')';
function : FUNCTION '.' NAME '(' (argument (',' argument)*)? ')';
argument: (NAME | function );