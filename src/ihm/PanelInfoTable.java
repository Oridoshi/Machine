package ihm;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controleur.Controleur;

public class PanelInfoTable extends JPanel
{
	private Controleur ctrl;
	private JTable     tableDonne;

	public PanelInfoTable(Controleur ctrl, String table)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JScrollPane spDonneTable;

		this.tableDonne = new JTable( new ModelAffichageTable(this.ctrl, table));
		this.tableDonne.setFillsViewportHeight(true);
		this.tableDonne.setRowHeight(25);

		spDonneTable = new JScrollPane(this.tableDonne);

		this.add(spDonneTable, BorderLayout.CENTER);
	}
}
