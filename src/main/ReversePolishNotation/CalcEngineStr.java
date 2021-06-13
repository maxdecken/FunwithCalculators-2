package main.ReversePolishNotation;

import java.util.regex.Pattern;

import main.decimal.CalcEngine;
import main.postFix.StackAsList;

public class CalcEngineStr extends CalcEngine {

	public CalcEngineStr() {
		super();
	}
	
	public String evaluateIntFix(String inFix) {
		inFix = splitString(inFix);
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
    
    private String splitString(String line) {
    	String[] str = line.split("");
        String s = "";
        String p = "";
        /*for (int i = 0; i < str.length; i++){
            if(Pattern.compile("[1-9a-fA-F]").matcher(str[i]).matches() && Pattern.compile("[1-9a-fA-F]").matcher(p).matches()){
            	int number;
            	if(Pattern.compile("[a-fA-F]").matcher(str[i]).matches()){
                     number = Integer.parseInt(str[i], 16);
            	 }else {
            		 number = Integer.parseInt(str[i]);
            	 }
            	if(mode == 16) {
            		number = number * 16 + Integer.parseInt(p);
            		s = s + number + "";
            	}else {
            		s = s + str[i];
            	}
            }else {
                s = s + " " + str[i];
            }
            p = str[i];
        }*/
        int number = 0;
        for (int i = 0; i < str.length; i++) {
        	 if(Pattern.compile("[1-9a-fA-F]").matcher(str[i]).matches() && Pattern.compile("[1-9a-fA-F]").matcher(p).matches()) {
        		 if(mode == 16) {
        			 number = Integer.parseInt(str[i], 16) * 16 + number;
        		 }else {
        			 s = s + str[i];
        		 }
        	 }else {
        		 if(mode == 16) {
        			 number = Integer.parseInt(str[i], 16) * 16 + number;
        		 }else {
        			 s = s + " " + str[i];
        		 }
             }
             p = str[i];
        }
        return s;
    }
    
    public int getMode() {
    	return mode;
    }
}
