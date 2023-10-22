package ihm.classPerso;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BtnPerso extends JButton implements ActionListener, MouseListener
{
	private boolean     selected;
	protected BtnGrpPerso groupe;

	public BtnPerso(String nom)
	{
		super(nom);

		this.selected = false;

		this.miseAJourStyle();

		setBorderPainted(false);
		setFocusPainted(false);

		this.addActionListener(this);
		this.addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e){}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// System.out.println("DEBUG : ENTRE");
		this.setForeground(new Color(215, 215, 215));
		this.setBackground(new Color(51, 51, 51));
	}

	@Override
	public void mouseExited(MouseEvent e) 
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
}
