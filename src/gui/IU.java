package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class IU extends JFrame{
	
	static String[] panels = {"addCustomer","addCarDriver", "addDriver", "setDriverStatus", "moveCar", "moveCustomer", "displayState","ask4price","simRide", "displayDrivers", "displayCustomers", "totalCashed"};
	static String[][] textAreasNames = {{"name","surname"},{"name","surname","car type"},{"name","surname", "car ID"},{"name","surname","status"},{"car ID","x","y"},{"Customer ID","x","y"},{},{"customer ID", "x", "y", "time"}, {"customer ID", "x", "y", "time","ride type", "driver mark"}, {"sort policy"},{"sort policy"},{}};
	static ArrayList<JTextField> textAreas = new ArrayList<JTextField>();
	ArrayList<JPanel> globalpanels = new ArrayList<JPanel>();
	ArrayList<ArrayList<JPanel>> realpanels = new ArrayList<ArrayList<JPanel>>();
	ArrayList<ArrayList<JTextField>> realfields = new ArrayList<ArrayList<JTextField>>();
	ArrayList<ArrayList<JLabel>> reallabels = new ArrayList<ArrayList<JLabel>>();
	ArrayList<JButton> realbuttons = new ArrayList<JButton>();
	ArrayList<JButton> choicebuttons = new ArrayList<JButton>();

	protected JButton setup;
	protected JTextField nStandard;
	protected JTextField nBerline;
	protected JTextField nVan;
	protected JTextField nCustomer;
	CardLayout card = new CardLayout(); 
	JPanel pane = new JPanel();
	ArrayList<JButton> backs = new ArrayList<JButton>();
	
	public IU() {
		super();
		addContent();
		addListener();
		this.setTitle("My Uber");
		this.pack();
		this.setSize(300,300);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void addContent() {
			JPanel setuppane = new JPanel();
			JPanel nS = new JPanel();
			JPanel nB = new JPanel();
			JPanel nV = new JPanel();
			JPanel nC = new JPanel();
			nStandard = new JTextField(18-26*2/5);
			nBerline = new JTextField(18-25*2/5);
			nVan = new JTextField(18-22*2/5);
			nCustomer = new JTextField(18-27*2/5);
			setup = new JButton("Setup");
			nS.add(new JLabel("number of standard cars : "));
			nS.add(nStandard);
			nB.add(new JLabel("number of berline cars : "));
			nB.add(nBerline);
			nV.add(new JLabel("number of vans : "));
			nV.add(nVan);
			nC.add(new JLabel("number of customers : "));
			nC.add(nCustomer);
			setuppane.add(new JLabel("Welcome on you myUber platform !"));
			setuppane.add(nS);
			setuppane.add(nB);
			setuppane.add(nV);
			setuppane.add(nC);
			setuppane.add(setup);
			
			JPanel choicepane = new JPanel();
			for (String s : panels) {
				choicebuttons.add(new JButton(s));
			}
			for (JButton b : choicebuttons) {
				choicepane.add(b, b.getText());
			}
			
			for (int i=0;i<panels.length;i++) {
				globalpanels.add(new JPanel());
				backs.add(new JButton("Back"));
				globalpanels.get(globalpanels.size()-1).add(backs.get(backs.size()-1));
				globalpanels.get(globalpanels.size()-1).add(new JLabel("Enter the relevant parameters to use "+panels[i]));
				realpanels.add(new ArrayList<JPanel>());
				realfields.add(new ArrayList<JTextField>());
				reallabels.add(new ArrayList<JLabel>());
				realbuttons.add(new JButton(panels[i]));
				for (int j=0;j<textAreasNames[i].length;j++) {
					realfields.get(realfields.size()-1).add(new JTextField(18-textAreasNames[i][j].length()*2/5));
					realfields.get(realfields.size()-1).get(realfields.get(realfields.size()-1).size()-1).setMinimumSize(new Dimension(150,40));
					reallabels.get(reallabels.size()-1).add(new JLabel(textAreasNames[i][j]+" : "));
				}
				for (int j=0;j<textAreasNames[i].length;j++) {
					realpanels.get(realpanels.size()-1).add(new JPanel());
					realpanels.get(realpanels.size()-1).get(realpanels.get(realpanels.size()-1).size()-1).add(reallabels.get(reallabels.size()-1).get(j));
					realpanels.get(realpanels.size()-1).get(realpanels.get(realpanels.size()-1).size()-1).add(realfields.get(realfields.size()-1).get(j));
				}
				for (JPanel p : realpanels.get(realpanels.size()-1)) {
					globalpanels.get(globalpanels.size()-1).add(p);
				}
				globalpanels.get(globalpanels.size()-1).add(realbuttons.get(realbuttons.size()-1));
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
	        	pane.add(globalpanels.get(i), panels[i]);
	        }
			add(pane);
	}
	
	public void addListener() {}

}
