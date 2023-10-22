package ihm.classPerso;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

public class BtnGrpPerso
{
	private LinkedList<BtnPerso> groupe;

	public BtnGrpPerso()
	{
		super();

		this.groupe = new LinkedList<BtnPerso>();
	}

	public void add(BtnPerso nouvBtnPerso)
	{
		this.groupe.add(nouvBtnPerso);
		nouvBtnPerso.setGroupe(this);
	}

	public void btnSelect(BtnPerso btnSelect)
	{
		for (BtnPerso button : groupe) 
		{
			if(btnSelect == button)
			{
				button.setSelected(true);
				button.miseAJourStyle();
			}
			else
			{
				button.setSelected(false);
				button.miseAJourStyle();
			}
		}
	}

	public BtnPerso getBouttonPerso(String nom)
	{
		for (BtnPerso button : groupe) 
		{
			if(nom.equals(button.getText()))
				return button;
		}
		return null;
	}

	public JPanel ajouterFrame()
	{
		JPanel JPaneRet = new JPanel();
		JPaneRet.setBackground(new Color(0, 0, 0, 0));

		if(groupe.get(0) instanceof BtnIconPerso)
			JPaneRet.setLayout(new GridLayout(groupe.size(), 1, 5, 5));

		for (BtnPerso btnPerso : groupe)
		{
			JPaneRet.add(btnPerso);
		}

		return JPaneRet;
	}
}