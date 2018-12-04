package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				System.out.println(state);
				addContent();
				state = "choice";
				text.setText("Hey !");
				System.out.println(state);
			}
		});
	}
}
