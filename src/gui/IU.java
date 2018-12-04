package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class IU extends JFrame{
	
	protected JButton setup;
	protected JTextField text;
	String state = "setup";
	
	public IU() {
		super();
		addContent();
		addListener();
		this.setTitle("My Uber");
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void addContent() {
		System.out.println(state);
		if (this.state.equals("setup")) {
			JPanel pane = new JPanel();
			pane.setLayout(new FlowLayout());
			pane.setBorder(new MatteBorder(2,2,2,2, Color.ORANGE));
			
			text = new JTextField(20);
			setup = new JButton("Setup");
			pane.add(setup);
			add(pane);
			add(text);
			setLayout(new FlowLayout());
		}
	}
	
	public void addListener() {}

}
