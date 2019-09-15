package infixpostfix;
import java.util.Stack;
public class Postfix {

    static int precedence(char ch) 
    { 
        switch (ch) 
        { 
        case '+': 
        case '-': 
            return 1; 
       
        case '*': 
        case '/': 
            return 2; 
       
        case '^': 
            return 3; 
        } 
        return -1; 
    } 
   
    static String postFix(String result) 
    { 
   
        String postfix = new String(""); 
          
        // initializing empty stack 
        Stack<Character> operatorStack = new Stack<>(); 
          
        for (int i = 0; i<result.length(); ++i) 
        { 
            char c = result.charAt(i); 
              
             // If the scanned character is an operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                postfix += c; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (c == '(') 
                operatorStack.push(c); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') 
            { 
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') 
                	postfix += operatorStack.pop(); 
                  
                if (!operatorStack.isEmpty() && operatorStack.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                    operatorStack.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!operatorStack.isEmpty() && precedence(c) 
                		<= precedence(operatorStack.peek())){ 
                    if(operatorStack.peek() == '(') 
                        return "Invalid Expression"; 
                    postfix += operatorStack.pop(); 
             } 
                operatorStack.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!operatorStack.isEmpty()){ 
            if(operatorStack.peek() == '(') 
                return "Invalid Expression"; 
            postfix += operatorStack.pop(); 
         } 
        return postfix; 
    } 
     
    public static void main(String[] args)  
    { 
        String result = "(a-b*c)/(d*e*f+g)"; 
        System.out.println(postFix(result)); 
    } 
} 


