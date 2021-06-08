package main.ReversePolishNotation;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.decimal.CalcEngine;
import main.decimal.UserInterface;

public class UserInterfaceRPN extends UserInterface {
	
	public static JPanel buttonPanel = new JPanel(new GridLayout(6, 5));
	
	CalcEngineStr cStr;
	
	protected JTextField result;

	public UserInterfaceRPN(CalcEngineStr engine) {
		super(engine);
		cStr = engine;
	}
	
	public void makeFrame() {
		frame = new JFrame(calc.getTitle());
		
		JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);
        
        addButton(buttonPanel, "=");
        
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        result = new JTextField();
        contentPane.add(result, BorderLayout.SOUTH);
        //contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
	}
	
	public void actionPerformed() {
		
	}

}
