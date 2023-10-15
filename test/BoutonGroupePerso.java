import javax.swing.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BoutonGroupePerso extends JToggleButton implements ActionListener
{
	private LinkedList<JButton> groupe;

	public BoutonGroupePerso()
	{
		super();

		this.groupe = new LinkedList<JButton>();

		this.addActionListener(this);
	}

	public void add(JButton nouvJButton)
	{
		this.groupe.add(nouvJButton);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (JButton button : groupe) 
		{
			if(e.getSource() == button)
				button.selected();
		}
	}
}