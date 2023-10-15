package ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;

public class PanelCompte extends JPanel
{

	public PanelCompte(Controleur ctrl)
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants


		/*------Clacul------*/
		
		int nbType1 = ctrl.getNbType(1);
		int nbType2 = ctrl.getNbType(2);

		int argentGenType2 = nbType2 * 2;

		int argentGenTot = nbType1 + argentGenType2;

		/*------Clacul------*/


		JLabel labelNbType1 = new JLabel("Nombre de machine de type 1 : ");
		JLabel NbMachineType1 = new JLabel(String.format("%3d", nbType1));
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(labelNbType1, gbc);
		gbc.gridx = 1;
		this.add(NbMachineType1, gbc);

		gbc.gridx = 2;
		this.add(new JLabel("    |    "), gbc);

		JLabel labelNbType2 = new JLabel("Nombre de machine de type 2 : ");
		JLabel NbMachineType2 = new JLabel(String.format("%3d", nbType2));
		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(labelNbType2, gbc);
		gbc.gridx = 4;
		this.add(NbMachineType2, gbc);


		JLabel labelArgType1 = new JLabel("Argent généré type 1 : ");
		JLabel ArgMachineType1 = new JLabel(String.format("%3d", nbType1));
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(labelArgType1, gbc);
		gbc.gridx = 1;
		this.add(ArgMachineType1, gbc);

		gbc.gridx = 2;
		this.add(new JLabel("    |    "), gbc);

		JLabel labelArgType2 = new JLabel("Argent généré type 2 : ");
		JLabel ArgMachineType2 = new JLabel(String.format("%3d", argentGenType2));
		gbc.gridx = 3;
		gbc.gridy = 1;
		this.add(labelArgType2, gbc);
		gbc.gridx = 4;
		this.add(ArgMachineType2, gbc);


		gbc.gridy = 2;
		this.add(new JLabel(""), gbc);


		JLabel labelTotGen = new JLabel("Total Argent généré : ");
		JLabel TotGen = new JLabel(String.format("%3d", argentGenTot));
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(labelTotGen, gbc);
		gbc.gridx = 1;
		this.add(TotGen, gbc);
	}
}
