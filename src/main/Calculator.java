package main;

import main.ReversePolishNotation.CalcEngineStr;
import main.ReversePolishNotation.UserInterfaceRPN;
import main.hex.CalcEngineHex;
import main.hex.UserInterfaceHex;

public class Calculator {

    public static void main(String args []) {

        /**
         * Create a new Calculator and combine the engine with the interface.
         */
        CalcEngineStr calcEngine = new CalcEngineStr();
        UserInterfaceRPN userInterface = new UserInterfaceRPN(calcEngine);
        userInterface.setVisible(true);

    }
}
