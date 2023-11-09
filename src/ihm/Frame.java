package ihm;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controleur.Controleur;

import ihm.classPerso.*;

public class Frame extends JFrame implements ActionListener
{
	private final int TAILLE_HAUTEUR = 100;
	private final int TAILLE_LARGEUR = 50;

	private Controleur ctrl;
	private JButton boutonAjouterClient;
	private JPanel panelCentre;
	private JButton boutonAjouterUtilisation;
	private String panCharger = "";

	private BtnGrpPerso grpTable;

	public Frame(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Gestion Machine");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setIconImage(new ImageIcon(getClass().getResource("/icon/icon.png")).getImage());

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();

		int largeurEcran = tailleEcran.width;
		int hauteurEcran = tailleEcran.height;

		this.setSize(largeurEcran - TAILLE_LARGEUR, hauteurEcran - TAILLE_HAUTEUR);
		this.setLocation(TAILLE_LARGEUR/2, TAILLE_HAUTEUR/2);

		JPanel panNord = new JPanel();
		panNord.setBackground(new Color(31, 31, 31));
		panNord.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.grpTable = new BtnGrpPerso();
		for (String table : ctrl.getTable())
		{
			BtnPerso tmp = new BtnPerso(table);
			tmp.addActionListener(this);

			this.grpTable.add(tmp);
		}
		panNord.add(this.grpTable.ajouterFrame());

		JPanel panSud = new JPanel();
		panSud.setBackground(new Color(31, 31, 31));
		panSud.setLayout(new FlowLayout(FlowLayout.LEFT));
 
		this.boutonAjouterClient = new JButton("Ajouter/Mise a jour Client");
		this.boutonAjouterClient.setForeground(new Color(156, 220, 254));
		this.boutonAjouterClient.setBackground(new Color(51, 51, 51));
		this.boutonAjouterClient.setBorderPainted(false);
		this.boutonAjouterClient.setFocusPainted(false);
		panSud.add(this.boutonAjouterClient);

		this.boutonAjouterUtilisation = new JButton("Ajouter Utilisation");
		this.boutonAjouterUtilisation.setForeground(new Color(156, 220, 254));
		this.boutonAjouterUtilisation.setBackground(new Color(51, 51, 51));
		this.boutonAjouterUtilisation.setBorderPainted(false);
		this.boutonAjouterUtilisation.setFocusPainted(false);
		panSud.add(this.boutonAjouterUtilisation);

		this.boutonAjouterClient.addActionListener(this);
		this.boutonAjouterUtilisation.addActionListener(this);

		JPanel panelOption = new JPanel();
		panelOption.setBackground(new Color(31, 31, 31));
		BtnGrpPerso grpOption = new BtnGrpPerso();
		grpOption.add(new BtnIconPerso("/img/table/table_(2).png", "/img/table/table_(1).png", "/img/table/table_(3).png", this, "table"));
		grpOption.add(new BtnIconPerso("/img/eco/eco_(2).png"    , "/img/eco/eco_(1).png"    , "/img/eco/eco_(3).png", this, "eco"));
		panelOption.add(grpOption.ajouterFrame());
		this.add(panelOption, BorderLayout.WEST);

		this.add(panNord, BorderLayout.NORTH);
		this.add(panSud , BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() 
		{
			public void windowClosing(java.awt.event.WindowEvent windowEvent) 
			{
				ctrl.fermerConnexion();
				System.out.println("APP FERMER");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(panCharger.equals("table"))
		{
			if(e.getSource() == this.boutonAjouterClient)
				new PanelAjoutCli(this, this.ctrl);
			else
				if(e.getSource() == this.boutonAjouterUtilisation)
					new PanelAjoutUtilisation(this, this.ctrl);
				else
				{
					this.getContentPane().remove(this.panelCentre);

					this.panelCentre = new PanelInfoTable(ctrl, ((AbstractButton) e.getSource()).getText());

					this.add(this.panelCentre, BorderLayout.CENTER);
					this.revalidate();
					this.repaint();
				}
		}
	}

	public void chargerPanel(String pan)
	{
		if(this.panelCentre != null)
			this.getContentPane().remove(this.panelCentre);

		if(pan.equals("table"))
			try
			{
				this.panelCentre = new PanelInfoTable(this.ctrl, "client");
				this.add(panelCentre, BorderLayout.CENTER);
				this.panCharger = pan;
				this.grpTable.btnSelect(this.grpTable.getBouttonPerso("client"));
			}
			catch (Exception e)
			{
				System.out.println("Aucune donné dans la table client");
			}
		else
			try
			{
				this.panelCentre = new PanelCompte(this.ctrl);
				this.panCharger = pan;
				this.grpTable.btnSelect(null);
			}
			catch (Exception e)
			{
				System.out.println("Aucune donné dans les tables");
			}
		
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}

	public void majTable()
	{
		((PanelInfoTable) this.panelCentre).updateTable();
	}

}
