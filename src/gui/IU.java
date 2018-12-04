package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class IU extends JFrame{
	
	static String[] panels = {"addCustomer","addCarDriver", "addDriver", "setDriverStatus", "moveCar", "moveCustomer", "displayState"};
	static Integer[] textAreasNumbers = {2, 3, 3, 3, 3, 3, 0};
	static ArrayList<JTextField> textAreas = new ArrayList<JTextField>();
	ArrayList<JPanel> realpanels = new ArrayList<JPanel>();
	ArrayList<ArrayList<JTextField>> realfields = new ArrayList<ArrayList<JTextField>>();
	ArrayList<JButton> realbuttons = new ArrayList<JButton>();
	ArrayList<JButton> choicebuttons = new ArrayList<JButton>();

	protected JButton setup;
	protected JTextField nStandard;
	protected JTextField nBerline;
	protected JTextField nVan;
	protected JTextField nCustomer;
	String state = "setup";
	CardLayout card = new CardLayout(); 
	JPanel pane = new JPanel();
	
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
			
			JPanel setuppane = new JPanel();
			nStandard = new JTextField(20);
			nBerline = new JTextField(20);
			nVan = new JTextField(20);
			nCustomer = new JTextField(20);
			setup = new JButton("Setup");
			setuppane.add(new JLabel("Welcome on you myUber platform !"));
			setuppane.add(nStandard);
			setuppane.add(nBerline);
			setuppane.add(nVan);
			setuppane.add(nCustomer);
			setuppane.add(setup);
			
			JPanel choicepane = new JPanel();
			for (String s : panels) {
				choicebuttons.add(new JButton(s));
			}
			for (JButton b : choicebuttons) {
				choicepane.add(b, b.getText());
			}
			
			for (int i=0;i<panels.length;i++) {
				realpanels.add(new JPanel());
				realfields.add(new ArrayList<JTextField>());
				realbuttons.add(new JButton(panels[i]));
				for (int j=0;j<textAreasNumbers[i];j++) {
					realfields.get(realfields.size()-1).add(new JTextField(20));
				}
				realpanels.get(realpanels.size()-1).add(realbuttons.get(realbuttons.size()-1));
				for (int j=0;j<textAreasNumbers[i];j++) {
					realpanels.get(realpanels.size()-1).add(realfields.get(realfields.size()-1).get(j));
				}
			}
			
			
			/* JPanel addCustomerpane = new JPanel();
			addCustomerExe = new JButton("Add customer");
			addCustomerName = new JTextField(20);
			addCustomerSurname = new JTextField(20);
			addCustomerpane.add(addCustomerName);
			addCustomerpane.add(addCustomerSurname);
			addCustomerpane.add(addCustomerExe);
			
			JPanel addCarDriverpane = new JPanel();
			addCarDriverExe = new JButton("Add car driver");
			addCarDriverName = new JTextField(20);
			addCarDriverSurname = new JTextField(20);
			addCarDriverCarType = new JTextField(20);
			addCarDriverpane.add(addCustomerName);
			addCarDriverpane.add(addCustomerSurname);
			addCarDriverpane.add(addCustomerExe); */
			
	        pane.setLayout(card);  
	        pane.add(setuppane, "Setup pane");  
	        pane.add(choicepane, "Choice pane");
	        for (int i=0;i<panels.length;i++) {
	        	pane.add(realpanels.get(i), panels[i]);
	        }
	        //pane.add(addCustomerpane, "addCustomer pane");
			
			add(pane);
	}
	
	public void addListener() {}

}
