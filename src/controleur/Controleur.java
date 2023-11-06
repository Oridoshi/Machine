package controleur;
import ihm.Frame;
import ihm.FrameMaj;
import metier.*;

/**
 * Controleur
 */
public class Controleur
{
	private CoeurGest gestionaire;

	public Controleur()
	{
		UpdateChecker verifUpdate = new UpdateChecker();

		if (verifUpdate.isNewVersionAvailable())
		{
			FrameMaj frame = new FrameMaj();
			verifUpdate.update();
			frame.updateFini();
		}
		else
		{
			this.gestionaire = new CoeurGest();
			this.gestionaire.supCli(12);
			new Frame(this);
		}
	}
	public static void main(String[] args)
	{
		new Controleur();
	}

// VOIR POUR FAIT UN HISTORIQUE AVEC TRIGGER	

	public void fermerConnexion()
	{
		this.gestionaire.fermerConnexion();
	}

	public String[] getTable()
	{
		return gestionaire.getTable();
	}
	public void ajouterCli(String nom, String prenom)
	{
		this.gestionaire.ajouterCli(nom, prenom);
	}

	public int getNbTuple(String table)
	{
		return gestionaire.getNbTuple(table);
	}

	public int getNbCol(String table)
	{
		return this.gestionaire.getNbCol(table);
	}

	public String[] getNomCol(String table)
	{
		return gestionaire.getNomCol(table);
	}
	public void ajouterArgentCli(int ncli, int montant)
	{
		this.gestionaire.ajouterArgentCli(ncli, montant);
	}

	public String getTupleTable(String table)
	{
		String sRet = null;

		switch (table)
		{
			case "client"      -> sRet = gestionaire.getTupleTableClient();
			case "type"        -> sRet = gestionaire.getTupleTableType();
			case "utilisation" -> sRet = gestionaire.getTupleTableUtilisation();
		}

		return sRet;
	}

	public void ajouterUtilisation(int ncli, int type)
	{
		this.gestionaire.ajouterUtilisation(ncli, type);
	}

	public int getNbType(int type)
	{
		return this.gestionaire.getNbType(type);
	}
}