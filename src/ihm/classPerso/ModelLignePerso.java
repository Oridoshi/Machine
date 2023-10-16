package ihm.classPerso;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ModelLignePerso extends DefaultTableCellRenderer
{
	private static final Color COULEUR_FOND = new Color(60, 60, 60); // Couleur pour les lignes impaires
	private static final Color COULEUR_FONT = new Color(255, 255, 255); // Couleur pour les textes impaires


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Si la ligne est impaire, définissez la couleur de fond personnalisée
		if (row % 2 != 0)
		{
			component.setBackground(COULEUR_FOND);
			component.setForeground(COULEUR_FONT);
		}
		else
		{
			// Sinon, réinitialisez la couleur de fond par défaut
			component.setBackground(table.getBackground());
			component.setForeground(table.getForeground());
		}

		return component;
	}
}
