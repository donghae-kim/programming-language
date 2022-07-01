
import java.util.Scanner;

public class Java20182586
{
     public final static Scanner input= new Scanner(System.in);
     static char token;
     
    public static char gettoken()		//�Է¹��� token Ȯ�� ����ε� ��ū�̸� nextchar�� ���� �ܾ token���� ����
    {
          return token =nextChar(input);        
    }
    public final static char nextChar(Scanner scanner)	//���� �ܾ token���� ����� �Լ�
    {
        scanner.useDelimiter("");	// ""�������� ������ ��������
        char t=scanner.next().charAt(0);
        scanner.reset();
        return t;
    }
    public static void del_space() {	//����, \t �� �������ִ� �Լ�
        while (true) {
            if (token == ' ' || token == '\t')
                token = gettoken();
            else
                break;
        }
    }
    // <expr> -> <term> {+ <term> | - <term>}
    public static int expr()
    {
    	del_space();	//�� �� ���� ����
       int result = term();		// <expr> -> <term> �κ�
       del_space();
   	while ((token == '+') || (token == '-')) {		// < expr > ->{+<term> | -<term>} �κ�
       switch (token) {
           case '+':
               gettoken();
               result += term();
               break;
           case '-':
               gettoken();
               result -= term();
               break;
           		
       }
   	}
       return result;
    }
    
    // <term> -> <factor> {* <factor> | / <factor>}
    public static int term() {
    	del_space();
    	int result = factor();	//<term> -> <factor> �κ�
    	del_space();
    	while ((token == '*') || (token == '/')) {	 //<term> ->{* <factor> | / <factor>} �κ�
        switch (token) {
            case '*':
                gettoken();
                result *= factor();
                break;
            case '/':
                gettoken();
                result /= factor();
                break;
        	}
    	}
		return result;
	}
    
    //<factor> -> [ - ]( <number> | (<expr>) )
	public static int factor() {
		del_space();
		int result=0;
		int flag =0;

		if(token == '-') {	//<factor> -> [ - ] -�ϰ�� flag�� 1������ �ؼ� ���� -�� ����
			gettoken();
			flag=1;
		}
		if(token =='(') {	//<factor> -> ( <number> | (<expr>) )
			gettoken();
			result = expr();
			if(flag==1) {		//������ [-] ���� ��� -���� ����
				result =(-result);
			}
			if(token==')'){
				gettoken();
			}
			else {
				System.out.printf("Syntax error!!");
	       	 	System.exit(1);
			}
				
		}
		else if(Character.isDigit(token)==true) {	// �����ϰ��

			result = number();
			if(flag==1) {			//������ [-] ���� ��� -���� ����	
				result =(-result);	
			}
		}
		else {
			System.out.printf("Syntax error!!");
       	 	System.exit(1);
		}
		return result;
	}
	
	// <number> -> <digit> {<digit>}
	public static int number() {
		del_space();
		int result = digit();		// <number> -> <digit> �κ�
		while(Character.isDigit(token)) {		// <number> ->{<digit>} �κ� 
			result *=10;			// �ڸ����� �÷����鼭 ��� ���ϰ� ����
			int tmp = digit();
			result += tmp;
		}
		return result;
	}
	
	// 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 
	public static int digit() {
		int result=0;

		switch (token) {
        case '0':
            gettoken();
            result+=0;
            break;
        case '1':
            gettoken();

            result+=1;
            break;
        case '2':
            gettoken();
            result+=2;
            break;
        case '3':
            gettoken();
            result+=3;
            break;
        case '4':
            gettoken();
            result+=4;
            break;
        case '5':
            gettoken();
            result+=5;
            break;
        case '6':
            gettoken();
            result+=6;
            break;
        case '7':
            gettoken();
            result+=7;
            break;
        case '8':
            gettoken();
            result+=8;
            break;
        case '9':
            gettoken();
            result+=9;
            break;
            
           default:
                 System.err.println("Syntax error!!");
               break;
    }
		return result;
	}
	
	
	public static void main(String[] args)
    {
       while(true)
       {
          System.out.print(">> ");
          System.out.flush();
          gettoken();
          int result = expr();
          gettoken();
          
          if(token == '\n') {		//���� �ý� result ���
        	  System.out.printf("%d\n",result);
          }
          else {
        	 System.out.printf("Syntax error!!");		//�ƴϸ� error���
        	 System.exit(1);
          }
          
          
       	}
        
    }
    
}
