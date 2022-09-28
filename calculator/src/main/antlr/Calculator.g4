grammar Calculator;

expression
    : operands+=term (operators += add_op operands += term)*
    ;

add_op
    : '+' | '-'
    ;

term
    : operands+=factor (operators += mul_op operands += factor)*
    ;

mul_op
    : '*' | '/'
    ;

factor
    : (neg='-' | '+')? (num=number | '(' expr=expression ')')
    ;

number
    : dec=DEC_NUMBER | hex=HEX_NUMBER
    ;

WS
    : [ \r\n\t]+ -> skip
    ;

DEC_NUMBER: DEC_DIGIT_WO_ZERO DEC_DIGIT*;
HEX_NUMBER: '0x' HEX_DIGIT+;

fragment DEC_DIGIT_WO_ZERO: '1'..'9';
fragment DEC_DIGIT: '0' | DEC_DIGIT_WO_ZERO;

fragment HEX_DIGITS: HEX_DIGIT+;
fragment HEX_DIGIT: DEC_DIGIT | 'a'..'f' | 'A'..'F';