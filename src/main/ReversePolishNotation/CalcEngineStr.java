package main.ReversePolishNotation;

import java.util.ArrayList;
import java.util.regex.Pattern;

import main.decimal.CalcEngine;
import main.postFix.StackAsList;

public class CalcEngineStr extends CalcEngine {

	public CalcEngineStr() {
		super();
	}
	
	/**
	 * Method that is used by the UserIterfaceRPN
	 * @param inFix the String you want to calculate
	 * @return the result
	 */
	public String evaluateIntFix(String inFix) {
		if(mode == 16) {
			inFix = splitStringHex(inFix);
		}else {
			inFix = splitStringDec(inFix);
		}
		String result = evaluatePostFix(infixToPostfix(inFix));
		return result;
	}
	
	/**
     * Converts given infix expression to postfix expression
     * Patter: Only numbers and operators separated with space
     * @param tokens
     * @return
     */
	private String infixToPostfix (String tokens) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = tokens.split(Pattern.quote(" "));
        String result = "";

        for (int i = 0; i < t.length; i++) {

            if(t[i].equals("(")) {
                stack.push(t[i]);
            }
            else if(t[i].equals(")")) {
                String latest = stack.pop();
                while(!latest.equals("(")){
                    result = result + " " + latest;
                    latest = stack.pop();
                }
            }else if (t[i].equals("+") || t[i].equals("-") || t[i].equals("*") || t[i].equals("/") || t[i].equals("^")) {

              String top = stack.pop();
                while (calculatePrecedence(t[i], top)) {
                    result = result + " " + top;
                    top = stack.pop();
                }
                stack.push(top);
                stack.push(t[i]);

            } else {
                result = result + " " + t[i];
            }
        }

        String last = stack.pop();

        while (last != null) {
            result = result + " " + last;
            last = stack.pop();
        }

        return result;
    }
	
	  /**
     * Evaluate the value of given postfix expression
     * Patter: Only numbers and operators separated with space
     * @param pfx
     * @return
     */
    private String evaluatePostFix (String pfx) {

        StackAsList<String> stack = new StackAsList<>();
        String[] t = pfx.split(Pattern.quote(" "));

        for (int i = 0; i < t.length; i++) {

            int rhs;
            int lhs;

            if (t[i].equals("+") || t[i].equals("-") || t[i].equals("*") || t[i].equals("/") || t[i].equals("^")) {

                rhs = Integer.parseInt(stack.pop());
                lhs = Integer.parseInt(stack.pop());

                if(t[i].equals("+")) {
                    stack.push(((lhs + rhs)+ ""));
                }

                if(t[i].equals("-")) {
                    stack.push(((lhs - rhs)+ ""));
                }

                if(t[i].equals("*")) {
                    stack.push(((lhs * rhs)+ ""));
                }

                if(t[i].equals("/")) {
                    stack.push(((lhs / rhs)+ ""));
                }

                if(t[i].equals("^")) {
                    int intResult = (int) Math.pow(lhs, rhs);
                    stack.push(((intResult)+ ""));
                }

            } else {
                stack.push(t[i]);
            }

        }
        return stack.pop();
    }
    
	/**
     * Calculate the precedence between the given operators
     * @param newV
     * @param oldV
     * @return true, you can put it inside the stack
     * @return false, you have to pop the last element from stack
     */
    private boolean calculatePrecedence(String newV, String oldV) {

        if(newV != null && oldV != null) {

            //Wenn letzter Operator "(", diesen dann ignorieren
            if(oldV.equals("(")) {return false;}

            //Gleichheitsfall
            if(newV.equals(oldV)) {
                return true;
            }
            //positiver fall wenn man drauf packen darf
            else if((newV.equals("+") || newV.equals("-")) && (!newV.equals("+") || !newV.equals("-"))) {
                return true;
            }

        }

        return false;
    }
    
    /**
     * Spilt the String, for DEC input
     * @param line the line you want to split
     * @return a string with blanks
     */
    private String splitStringDec(String line) {
    	String[] str = line.split("");
        String s = "";
        String p = "";
        for (int i = 0; i < str.length; i++) {
        	 if(Pattern.compile("[0-9]").matcher(str[i]).matches() && Pattern.compile("[0-9]").matcher(p).matches()) {
        		s = s + str[i];
        	 }else {
        		 s = s + " " + str[i];
             }
             p = str[i];
        }
        return s;
    }
    
    /**
     * Spilt the String, for HEX input
     * @param line the line you want to split
     * @return a string with blanks
     */
    private String splitStringHex(String line) {
    	String[] str = line.split("");
        ArrayList<String> result = new ArrayList();
        int i = 0;
        while(i < str.length) {
	        if(Pattern.compile("[0-9a-fA-F]").matcher(str[i]).matches())
	        {
	        	int number = Integer.parseInt(str[i], 16);
	        	i++;
	        	if(i < str.length) {
		        	while(Pattern.compile("[1-9a-fA-F]").matcher(str[i]).matches() && i < str.length) {
		        		number = Integer.parseInt(str[i], 16)  * 16 + number;
		        		i++;
		        	}
	        	}
	        	result.add(number + "");
	        }else {
	        	result.add(str[i]);
	        	i++;
	        }
        }
        
        String s = "";
        for(String elm : result) {
        	s = s + " " + elm;
        }
        return s;
    }
    
    /**
     * Returns the current mode, HEX or DEC
     * @return the current mode (10/16)
     */
    public int getMode() {
    	return mode;
    }
}
