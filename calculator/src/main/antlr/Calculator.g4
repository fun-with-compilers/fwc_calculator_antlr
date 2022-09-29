grammar Calculator;

expression
    : operands+=term (operators += ('+' | '-') operands += term)*;

term
    : operands+=factor (operators += ('*' |'/') operands += factor)*;

factor
    : (neg='-' | '+')? (num=number | '(' expr=expression ')');

number
    : dec=DEC_NUMBER | hex=HEX_NUMBER;

WS: [ \r\n\t]+ -> skip;
DEC_NUMBER: DEC_DIGIT+;
HEX_NUMBER: '0x' HEX_DIGIT+;
fragment DEC_DIGIT: '0'..'9';
fragment HEX_DIGITS: HEX_DIGIT+;
fragment HEX_DIGIT: DEC_DIGIT | 'a'..'f' | 'A'..'F';