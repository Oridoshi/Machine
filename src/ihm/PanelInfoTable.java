package ihm;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controleur.Controleur;
import ihm.classPerso.ModelAffichageTable;
import ihm.classPerso.ModelLignePerso;

public class PanelInfoTable extends JPanel
{
	private Controleur ctrl;
	private JTable     tableDonne;
	private String     table;

	public PanelInfoTable(Controleur ctrl, String table)
	{
		this.ctrl = ctrl;
		this.table = table;

		this.setLayout(new BorderLayout());

		JScrollPane spDonneTable;

		this.tableDonne = new JTable( new ModelAffichageTable(this.ctrl, table));
		this.tableDonne.setFillsViewportHeight(true);
		this.tableDonne.setRowHeight(25);
		this.tableDonne.setDefaultRenderer(Object.class, new ModelLignePerso());

		spDonneTable = new JScrollPane(this.tableDonne);

		this.add(spDonneTable, BorderLayout.CENTER);
	}

	public void updateTable()
	{
		this.tableDonne.setModel(new ModelAffichageTable(this.ctrl, this.table));
	}
}
