#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

char token;
int expr(void);                 // <expr> -> <term> {+ <term> | - <term>}
int term(void);                 // <term> -> <factor> {* <factor> | / <factor>}
int factor(void);               // <factor> -> [ - ]( <number> | (<expr>) )
int number(void);               // <number> -> <digit> {<digit>}
int digit(void);                // 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 


void error(void)                //������ ǥ�����ִ� �Լ�
{
    fprintf(stderr, "Syntax error!!\n");
    exit(1);
}

void token_match(char Token)    //�Է¹��� token�� ����ε� ��ū���� Ȯ��
{                               //����ε� ��ū�̸� getchar�� ���� �ܾ token���� ����
    if (token == Token) {
        token = getchar();
    }
    else {
        error();
    }
}

void del_space() {              //����, \t �� �������ִ� �Լ�
    while (1) {
        if (token == ' ' || token == '\t')
            token = getchar();
        else
            break;
    }
}
int main()
{
    int result;
    while (1) {
        printf(">> ");
        token = getchar();
        result = expr();
        if (token == '\n')      //token�� ����(������)�� �ȴٸ� result�� ������
            printf("%d\n", result);
        else
            error();
    }
}


// <expr> -> <term> {+ <term> | - <term>}
int expr(void)
{
    del_space();            //�� �� ���� ����
    int temp = term();      // <expr> -> <term> �κ�
    del_space();
    while ((token == '+') || (token == '-'))    //< expr > ->{+<term> | -<term>} �κ�
        switch (token)
        {
        case '+':
            token_match('+');
            temp += term();
            break;

        case '-':
            token_match('-');
            temp -= term();
            break;
        }

    return temp;
}

//<term> -> <factor> {* <factor> | / <factor>}
int term(void)
{
    del_space();
    int temp = factor();    //<term> -> <factor> �κ�
    del_space();
    while ((token == '*') || (token == '/')) //<term> ->{* <factor> | / <factor>} �κ�
        switch (token)
        {
        case '*':
            token_match('*');
            temp *= factor();
            break;

        case '/':
            token_match('/');
            temp /= term();
            break;
        }
    return temp;
}

//<factor> -> [ - ]( <number> | (<expr>) )
int factor(void)
{
    del_space();
    int temp;
    int flag = 0;
    if (token == '-') {     //<factor> -> [ - ] -�ϰ�� flag�� 1������ �ؼ� ���� -�� ����
        token_match('-');
        flag = 1;
    }   
    if (token == '(') {     //<factor> -> ( <number> | (<expr>) )
        token_match('(');
        temp = expr();
        if (flag == 1) {    //������ [-] ���� ��� -���� ����
            temp = (-temp);
        }
        token_match(')');
    }
    else if (isdigit(token)) {  // �����ϰ��
        temp = number();
        if (flag == 1) {    //������ [-] ���� ��� -���� ����
            temp = (-temp);
        }
    }
    else error();

    return temp;
}

// <number> -> <digit> {<digit>}
int number(void) {
    del_space();
    int temp = digit();     // <number> -> <digit> �κ�
    while (isdigit(token)) {    // <number> ->{<digit>} �κ� 
        temp *= 10;             // �ڸ����� �÷����鼭 ��� ���ϰ� ����
        int tmp = digit();
        temp += tmp;
    }
    return temp;
}

// 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 
int digit(void) {
    int temp = 0;

    switch (token)
    {
    case '0':
        token_match('0');
        temp += 0;
        break;

    case '1':
        token_match('1');
        temp += 1;
        break;
    case '2':
        token_match('2');
        temp += 2;
        break;

    case '3':
        token_match('3');
        temp += 3;
        break;
    case '4':
        token_match('4');
        temp += 4;
        break;

    case '5':
        token_match('5');
        temp += 5;
        break;
    case '6':
        token_match('6');
        temp += 6;
        break;

    case '7':
        token_match('7');
        temp += 7;
        break;
    case '8':
        token_match('8');
        temp += 8;
        break;

    case '9':
        token_match('9');
        temp += 9;
        break;
    }
    return temp;
}
