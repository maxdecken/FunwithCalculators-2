package main.ReversePolishNotation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import main.decimal.CalcEngine;
import main.decimal.UserInterface;

public class UserInterfaceRPN extends UserInterface {
	
	public static JPanel buttonPanel = new JPanel(new GridLayout(6, 5));
	
	CalcEngineStr cStr;
	
	protected JTextField result;
	JToggleButton toggleButton;

	public UserInterfaceRPN(CalcEngineStr engine) {
		super(engine);
		cStr = engine;
		calc.setMode(10);
	}
	
	public void makeFrame() {
		frame = new JFrame(calc.getTitle());
		
		JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
        toggleButton = new JToggleButton("DEC");

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);
        
        addButton(buttonPanel, "=");
        addButton(buttonPanel, "C");
        buttonPanel.add(toggleButton);
        toggleButton.addActionListener(this);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(new JLabel("Result:"));
        result = new JTextField();
        buttonPanel.add(result);
        
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
	}
	
	public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if(command.equals("=")) {
        	String text = display.getText();
        	String result = cStr.evaluateIntFix(text);
        	redisplay(result);
        } else if(command.equals("C")) {
        	redisplay("");
        }else if (command.equals("DEC")) {
            toggleButton.setText("HEX");
            calc.setMode(16);
        }
        else if (command.equals("HEX")) {
            toggleButton.setText("DEC");
            calc.setMode(10);
        }
	}
	
	/**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    public void redisplay(String text)
    {
    	if(cStr.getMode() == 10) {
    		result.setText("" + text);
    		display.setText("" + text);
    	}else {
    		int textValue = Integer.parseInt(text);
    		String textNew = Integer.toHexString(textValue);
    		result.setText("" + text);
    		display.setText("" + text);
    	}
    }

}
