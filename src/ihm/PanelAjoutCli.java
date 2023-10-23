package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controleur.Controleur;

public class PanelAjoutCli extends JPanel implements ActionListener, KeyListener
{
	private JDialog dial;
	private JTextField textFieldPrenom;
	private JTextField textFieldNom;
	private JButton ajout;
	private boolean ajoutClient;
	private JButton ajCli;
	private JButton misCli;
	private JPanel panelFormulaire;
	private Controleur ctrl;
	private JTextField textFieldMontant;
	private JTextField textFieldNcli;
	private Frame mere;

	public PanelAjoutCli(Frame mere, Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.dial = new JDialog(mere, "Ajout d'un tuple dans une table", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(300, 200);
		this.dial.setLocationRelativeTo(mere);
		
		// Ajout du Button Ajouter
		this.ajout = new JButton("Ajouter");
		ajout.addActionListener(this);
		
		// Ajout des Button pour le choix d'action
		this.ajCli  = new JButton("Ajout Client");
		this.ajCli .addActionListener(this);
		this.misCli = new JButton("Mise à jour Client");
		this.misCli.addActionListener(this);
		
		JPanel panelAction = new JPanel();
		panelAction.setLayout(new GridLayout(1, 2));
		panelAction.add(this.ajCli);
		panelAction.add(this.misCli);
		
		this.dial.add(this.ajout, BorderLayout.SOUTH);
		this.dial.add(panelAction, BorderLayout.NORTH);
		
		this.panelFormulaireClient();

		this.dial.setResizable(false);
		this.dial.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.ajout)
		{
			if(this.ajoutClient == true)
				this.ctrl.ajouterCli(this.textFieldNom.getText(), this.textFieldPrenom.getText());
			else
				this.ctrl.ajouterArgentCli(Integer.parseInt(this.textFieldNcli.getText()), Integer.parseInt(this.textFieldMontant.getText()));

			this.mere.majTable();
			this.dial.dispose();
		}
		else
			if(e.getSource() == this.ajCli)
				this.panelFormulaireClient();
			else
				this.panelMiseAJourClient();
	}

	private void panelFormulaireClient()
	{
		if(this.panelFormulaire != null)
			this.dial.getContentPane().remove(this.panelFormulaire);

		this.ajoutClient = true;

		// Créez et ajoutez votre panelFormulaire au JDialog
		this.panelFormulaire = new JPanel();
		this.panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// JLabel et JTextField pour NOM
		JLabel labelNom = new JLabel("NOM : ");
		this.textFieldNom = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.panelFormulaire.add(labelNom, gbc);
		gbc.gridx = 1;
		this.panelFormulaire.add(textFieldNom, gbc);

		// JLabel et JTextField pour PRENOM
		JLabel labelPrenom = new JLabel("PRENOM : ");
		this.textFieldPrenom = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panelFormulaire.add(labelPrenom, gbc);
		gbc.gridx = 1;
		this.panelFormulaire.add(textFieldPrenom, gbc);

		this.textFieldPrenom.addKeyListener(this);
		this.textFieldNom.addKeyListener(this);

		this.panelFormulaire.revalidate();
		this.panelFormulaire.repaint();

		this.dial.add(this.panelFormulaire, BorderLayout.CENTER);
		this.dial.revalidate();
		this.dial.repaint();
	}

	private void panelMiseAJourClient()
	{
		this.dial.getContentPane().remove(this.panelFormulaire);

		this.ajoutClient = false;

		// Créez et ajoutez votre panelFormulaire au JDialog
		this.panelFormulaire = new JPanel();
		this.panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// JLabel et JTextField pour Ncli
		JLabel labelNcli = new JLabel("Numéro Client : ");
		this.textFieldNcli = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.panelFormulaire.add(labelNcli, gbc);
		gbc.gridx = 1;
		this.panelFormulaire.add(textFieldNcli, gbc);

		// JLabel et JTextField pour PRENOM
		JLabel labelMontant = new JLabel("Montant : ");
		this.textFieldMontant = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panelFormulaire.add(labelMontant, gbc);
		gbc.gridx = 1;
		this.panelFormulaire.add(textFieldMontant, gbc);

		this.textFieldMontant.addKeyListener(this);
		this.textFieldNcli.addKeyListener(this);

		this.panelFormulaire.revalidate();
		this.panelFormulaire.repaint();

		this.dial.add(this.panelFormulaire, BorderLayout.CENTER);
		this.dial.revalidate();
		this.dial.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
			if(this.ajoutClient == true)
				if(this.textFieldNom.getText().length() > 0 && this.textFieldPrenom.getText().length() > 0)
					this.ctrl.ajouterCli(this.textFieldNom.getText(), this.textFieldPrenom.getText());
			else
				if(this.textFieldNom.getText().length() > 0 && this.textFieldPrenom.getText().length() > 0)
					this.ctrl.ajouterArgentCli(Integer.parseInt(this.textFieldNcli.getText()), Integer.parseInt(this.textFieldMontant.getText()));

		this.mere.majTable();
		this.dial.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}
