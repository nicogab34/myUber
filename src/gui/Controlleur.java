package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import myUber.MyUber;

public class Controlleur extends IU{
	MyUber platform;
	
	public Controlleur(MyUber platform) {
		super();
		this.platform = platform;
	}
	
	@Override
	public void addListener() {
		setup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      card.show(pane,"Choice pane");
			      platform.setup(nStandard.getText(), nBerline.getText(), nVan.getText(), nCustomer.getText());
			}
		});
		
		for (JButton b : choicebuttons) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				      card.show(pane,b.getText());
				}
			});
		}
		
		for (int i=0;i<panels.length;i++) {
			final int k = i;
			realbuttons.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					card.show(pane,"Choice pane");
					if (panels[k].equals("addCustomer")){
						platform.addCustomer(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText());
					}
					else if (panels[k].equals("addCarDriver")){
						platform.addCarDriver(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText(), realfields.get(k).get(2).getText());
					}
					else if (panels[k].equals("addDriver")){
						platform.addDriver(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText(), realfields.get(k).get(2).getText());
					}
					else if (panels[k].equals("setDriverStatus")){
						platform.setDriverStatus(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText(), realfields.get(k).get(2).getText());
					}
					else if (panels[k].equals("moveCar")){
						platform.moveCar(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText(), realfields.get(k).get(2).getText());
					}
					else if (panels[k].equals("moveCustomer")){
						platform.moveCustomer(realfields.get(k).get(0).getText(), realfields.get(k).get(1).getText(), realfields.get(k).get(2).getText());
					}
					else if (panels[k].equals("displayState")){
						platform.displayState();
					}
				}
			});
		}
		/* addCustomerExe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      card.show(pane,"Choice pane");
			      platform.addCustomer(addCustomerName.getText(), addCustomerSurname.getText());
			}
		}); */
	}
}
