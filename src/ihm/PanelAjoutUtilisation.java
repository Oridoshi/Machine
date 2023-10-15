package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controleur.Controleur;

public class PanelAjoutUtilisation extends JPanel implements ActionListener
{
	private JDialog dial;
	private JTextField textFieldNcli;
	private JTextField textFieldType;
	private Controleur ctrl;

	public PanelAjoutUtilisation(Frame mere, Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.dial = new JDialog(mere, "Ajout d'un tuple dans une table", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(300, 200);
		this.dial.setLocationRelativeTo(mere);
		
		// Ajout du Button Ajouter
		JButton ajout = new JButton("Ajouter");
		ajout.addActionListener(this);
		
		this.dial.add(ajout, BorderLayout.SOUTH);
		
		// Créez et ajoutez votre panelFormulaire au JDialog
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// JLabel et JTextField pour NOM
		JLabel labelNom = new JLabel("NCLI : ");
		this.textFieldNcli = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(labelNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(textFieldNcli, gbc);

		// JLabel et JTextField pour PRENOM
		JLabel labelPrenom = new JLabel("TYPE : ");
		this.textFieldType = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(labelPrenom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(textFieldType, gbc);

		this.dial.add(panelFormulaire, BorderLayout.CENTER);

		this.dial.setResizable(false);
		this.dial.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.ajouterUtilisation(Integer.parseInt(this.textFieldNcli.getText()), Integer.parseInt(this.textFieldType.getText()));
		this.dial.dispose();
	}
}
