package ihm.classPerso;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BtnPerso extends JButton implements ActionListener
{
	private boolean     selected;
	protected BtnGrpPerso groupe;

	public BtnPerso(String nom)
	{
		super(nom);

		this.selected = false;

		this.miseAJourStyle();

		this.addActionListener(this);
	}

	protected BtnPerso()
	{
		super();

		this.selected = false;

		this.miseAJourStyle();

		this.addActionListener(this);
	}

	public boolean isSelected()
	{
		return this.selected;
	}

	public void setSelected(boolean nouvelEtat)
	{
		this.selected = nouvelEtat;
	}

	public void miseAJourStyle()
	{
		if(selected)
		{
			this.setForeground(new Color(156, 220, 254));
			this.setBackground(new Color(51, 51, 51));
		}
		else
		{
			this.setForeground(new Color(134, 134, 134));
			this.setBackground(new Color(51, 51, 51));
		}
	}

	protected void setGroupe(BtnGrpPerso btnGrpPerso)
	{
		this.groupe = btnGrpPerso;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.groupe.btnSelect(this);
	}
}
