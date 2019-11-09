import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {
	JFrame frame;
	JPanel pan_button = new JPanel();
	JPanel pan_screem = new JPanel();
	JPanel store_screem = new JPanel();
	JPanel store_button = new JPanel();
	
	JTextField [] editText = new JTextField[2];
	JTextField [] uneditText = new JTextField[3];
	JButton[] oper_button = new JButton[5];
	
//	JLabel test = new JLabel("test");
	
	public Calculator() {
//pan设置布局
		pan_button.setLayout(new GridLayout(1,5,1,1));
		pan_screem.setLayout(new GridLayout(1,5,1,1));
//大布局添加pan		
		store_screem.add(pan_screem);
		store_button.add(pan_button);


//pan添加组件
		//screem

		for(int i = 0; i < uneditText.length; i ++) {
			uneditText[i] = new JTextField();
			uneditText[i].setEditable(false);
			uneditText[i].setHorizontalAlignment(JTextField.CENTER);
		}
		
		for(int i = 0; i < editText.length; i ++) {
			editText[i] = new JTextField();
			editText[i].setHorizontalAlignment(JTextField.CENTER);
		}
		
		uneditText[1].setText("=");
		
		pan_screem.add(editText[0]);
		pan_screem.add(uneditText[0]);
		pan_screem.add(editText[1]);
		pan_screem.add(uneditText[1]);
		pan_screem.add(uneditText[2]);		
		pan_screem.setPreferredSize(new Dimension(400,60));
		
		//buttom
		pan_button.setPreferredSize(new Dimension(400,60));
		

		oper_button[0] = new JButton("+");
		oper_button[1] = new JButton("-");
		oper_button[2] = new JButton("*");
		oper_button[3] = new JButton("/");
		oper_button[4] = new JButton("OK");
		
		for(int i = 0; i < 5; i ++) {
			pan_button.add(oper_button[i]);
//			oper_button[i].setSize(50,50);
		}

		
//frame设置布局		
		
		frame = new JFrame("Calculator");
		frame.setLocation(600,400);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(store_button, BorderLayout.CENTER);
		frame.getContentPane().add(store_screem, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
//启动点击事件抓捕
		ClickEvent();
	}
	
	public void ClickEvent() {
		class ClickActionListen implements ActionListener {
			JButton tempButton = new JButton();
			public void actionPerformed(ActionEvent e){
				tempButton = (JButton)e.getSource();	
				if (tempButton.getText() != "OK") {
					uneditText[0].setText(tempButton.getText());
				}
				else if (tempButton.getText() == "OK") {
					String LeftOperNum = editText[0].getText();
					String operSign = uneditText[0].getText();
					String rightOperNum = editText[1].getText();
					uneditText[2].setText(GetResult(LeftOperNum,rightOperNum,operSign));
				}
			}
		}
		ClickActionListen CAI = new ClickActionListen();
		for(int i = 0; i < oper_button.length; i ++) {
			oper_button[i].addActionListener(CAI);
		}
		
	}
	
	public String GetResult(String LeftOperNum, String rightOperNum, String operSign) {
		double rightOperNumF;
		double LeftOperNumF;
		try{
			rightOperNumF = Double.valueOf(rightOperNum);
			LeftOperNumF = Double.valueOf(LeftOperNum);
		} catch(Exception e){
			return "number only";
		}
		if (operSign.equals("+")) {
			String Result = String.valueOf(LeftOperNumF + rightOperNumF);
			return Result;
		}
		if (operSign.equals("-")) {
			String Result = String.valueOf(LeftOperNumF - rightOperNumF);
			return Result;
		}
		if (operSign.equals("*")) {
			String Result = String.valueOf(LeftOperNumF * rightOperNumF);
			return Result;
		}
		if (operSign.equals("/")) {
			if (rightOperNumF == 0.0) {
				return "error!";
			}
			double ResultD = LeftOperNumF / rightOperNumF;
			ResultD = (double)Math.round(ResultD*1000)/1000;
			String Result = String.valueOf(ResultD);
			return Result;
		}
		
		else {
			return "operator sign error!";
		}

	}
	
	
	
	public static void main(String[] arg){
		Calculator c1 = new Calculator();
	}
}
