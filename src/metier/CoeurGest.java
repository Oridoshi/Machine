package metier;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CoeurGest
{
	private static final String urlBaseDeDonné = "jdbc:mysql://localhost:3306/machine";
	private static final String utilisateur    = "root";
	private static final String motDePasse     = "22555225Tt.";
	private Connection connexion;

	public CoeurGest()
	{
		try
		{
			this.connexion = DriverManager.getConnection(urlBaseDeDonné, utilisateur, motDePasse);
			System.out.println("DEBUG : CONNECTION ETABLI");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/* REQUETE SUR LA TABLE CLIENT */

	public int getNbTuple(String table)
	{
		int nbTuple = 0;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT COUNT(*) FROM " + table;

			ResultSet resultSet = statement.executeQuery(sqlQuery);

			resultSet.next();
			nbTuple = Integer.parseInt(resultSet.getString(1));

			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return nbTuple;
	}

	public int getNbCol(String table)
	{
		int nbCol = 0;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT COUNT(*) " +
			                  "FROM information_schema.columns " +
			                  "WHERE table_schema = 'machine' " +
			                  "AND table_name = '" + table + "';";

			ResultSet resultSet = statement.executeQuery(sqlQuery);

			resultSet.next();
			nbCol = Integer.parseInt(resultSet.getString(1));

			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return nbCol;
	}

	public String[] getNomCol(String table)
	{
		String[] tabNomCol = null;

		try {
			Statement statementRes = connexion.createStatement();
			String sqlQuery = "SELECT column_name " +
			                  "FROM information_schema.columns " +
			                  "WHERE table_schema = 'machine' " +
			                  "AND table_name = '" + table + "'" +
			                  "ORDER BY ordinal_position;";

			ResultSet resultSet = statementRes.executeQuery(sqlQuery);

			tabNomCol = new String[this.getNbCol(table)];
			for(int cpt = 0;resultSet.next(); cpt++)
			{
				tabNomCol[cpt] = resultSet.getString(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return tabNomCol;
	}



	public void ajouterCli(String nom, String prenom)
	{
		try
		{
			PreparedStatement statement = connexion.prepareStatement("Insert into Client (nom, prenom) values ('" + nom + "','" + prenom + "')");
			statement.execute();
			System.out.println("DEBUG : AJOUT D'UN CLIENT REUSIT");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void supCli(int ncli)
	{
		try
		{
			Statement statementRes = connexion.createStatement();
			String sqlQuery = "SELECT * FROM Client where ncli = " + ncli; //DEMANDE

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statementRes.executeQuery(sqlQuery);

			if(resultSet.next() != false)
			{
				PreparedStatement statement = connexion.prepareStatement("Delete from Client where ncli = " + ncli);
				statement.execute();
				System.out.println("DEBUG : SUPPRESSION D'UN CLIENT REUSIT");
			}
			else
			{
				System.out.println("DEBUG : LE CLIENT N'EXISTE PAS");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void ajouterArgentCli(int ncli, int montant)
	{
		try
		{
			PreparedStatement statement = connexion.prepareStatement("Update Client set argent = argent + " + montant + " where ncli = " + ncli);
			statement.execute();
			System.out.println("DEBUG : AJOUT D'ARGENT AU CLIENT REUSIT");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public String[] getTable()
	{
		String[] tabTable = null;

		try
		{
			Statement statementRes = connexion.createStatement();
			String sqlQuery = "SELECT table_name " +
							  "FROM information_schema.tables " +
							  "WHERE table_schema = DATABASE() ";

			ResultSet resultSet = statementRes.executeQuery(sqlQuery);

			tabTable = new String[3];
			for(int cpt = 0;resultSet.next(); cpt++)
			{
				tabTable[cpt] = resultSet.getString(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return tabTable;
	}

	public String getTupleTableClient()
	{
		String sRet = null;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT * FROM Client"; //DEMANDE

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery(sqlQuery);


			while (resultSet.next()) {
				// Traitez les résultats ici :
				int id        = resultSet.getInt   ("ncli");
				String nom    = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				int argent    = resultSet.getInt   ("argent");

				if(sRet == null)
					sRet  = id + "," + nom + "," + prenom + "," + argent;
				else
					sRet += ":" + id + "," + nom + "," + prenom + "," + argent;
			}

			resultSet.close();
			/*FIN RESULTAT DEMANDE*/

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return sRet;
	}



	public String getTupleTableType()
	{
		String sRet = null;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT * FROM type"; //DEMANDE

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery(sqlQuery);


			while (resultSet.next()) {
				// Traitez les résultats ici :
				int    id   = resultSet.getInt   ("nt");
				String lib  = resultSet.getString("lib");
				int    prix = resultSet.getInt   ("prix");

				if(sRet == null)
					sRet  = id + "," + lib + "," + prix ;
				else
					sRet += ":" + id + "," + lib + "," + prix ;
			}

			resultSet.close();
			/*FIN RESULTAT DEMANDE*/

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return sRet;
	}

	public String getTupleTableUtilisation()
	{
		String sRet = null;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT * FROM utilisation"; //DEMANDE

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery(sqlQuery);


			while (resultSet.next()) {
				// Traitez les résultats ici :
				int    id   = resultSet.getInt   ("idUti");
				int    ncli = resultSet.getInt   ("ncli");
				int    nt   = resultSet.getInt   ("nt");
				String date = resultSet.getString("dateUti");
				

				if(sRet == null)
					sRet  = id + "," + ncli + "," + nt + "," + date ;
				else
					sRet += ":" + id + "," + ncli + "," + nt + "," + date ;
			}

			resultSet.close();
			/*FIN RESULTAT DEMANDE*/

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return sRet;
	}

	/* REQUETE SUR LA TABLE UTILISATION */

	public String dateDuJour()
	{
		// Obtenez une instance de GregorianCalendar
		GregorianCalendar calendar = new GregorianCalendar();

		// Obtenez la date du jour
		int jour = calendar.get(Calendar.DAY_OF_MONTH);
		int mois = calendar.get(Calendar.MONTH) + 1; // Les mois sont indexés à partir de 0, alors ajoutez 1
		int annee = calendar.get(Calendar.YEAR);

		// Formattez la date au format AAAA-MM-JJ
		String dateDuJour = String.format("%04d-%02d-%02d", annee, mois, jour);

		return dateDuJour;
	}

	public void ajouterUtilisation(int ncli, int type)
	{
		try
		{
			PreparedStatement statement = connexion.prepareStatement("Insert into Utilisation (ncli, nt, dateUti) values (" + ncli + "," + type + ",'" + dateDuJour() + "')");
			statement.execute();
			System.out.println("DEBUG : AJOUT UTILISATION REUSIT");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void fermerConnexion()
	{
		try
		{
			this.connexion.close();
			System.out.println("DEBUG : DECONNECTION REUSIT");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public int getNbType(int type)
	{
		int nbCol = 0;

		try {
			Statement statement = connexion.createStatement();
			String sqlQuery = "SELECT COUNT(*) " +
			                  "FROM utilisation " +
			                  "WHERE nt = " + type + ";";

			ResultSet resultSet = statement.executeQuery(sqlQuery);

			resultSet.next();
			nbCol = Integer.parseInt(resultSet.getString(1));

			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return nbCol;
	}

	// private void supDonneTable()
	// {
	// 	try
	// 	{
	// 		//Désactiver temporairement toutes les contraintes de clé étrangère sur une table
	// 		PreparedStatement statement = connexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
	// 		statement.execute();


	// 		Statement statementRes = connexion.createStatement();
	// 		String sqlQuery = "SELECT table_name " +
	// 						  "FROM information_schema.tables " +
	// 						  "WHERE table_schema = DATABASE() ";

	// 		/*RESULTAT DEMANDE*/
	// 		ResultSet resultSet = statementRes.executeQuery(sqlQuery);

	// 		while (resultSet.next())
	// 		{
	// 			statement = connexion.prepareStatement("DELETE FROM " + resultSet.getString(1));
	// 			statement.execute();
	// 			System.out.println("DEBUG : SUPPRESSION TABLE " + tableName + " REUSIT");
	// 		}

	// 		resultSet.close();
	// 		/*FIN RESULTAT DEMANDE*/

	// 		//Réactivez les contraintes de clé étrangère
	// 		statement = connexion.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
	// 		statement.execute();
	// 	}
	// 	catch (SQLException e)
	// 	{
	// 		e.printStackTrace();
	// 	}
	// }


	// public static void main(String[] args) {
	// 	CoeurGest reset = new CoeurGest();

	// 	reset.supDonneTable();
	// 	reset.fermerConnexion();
	// }


	//quelle est l'inconvégniant du second choix au premiere ( demande au voisine état ( doit être sur que chaque voisine a envoyer sont état )/ donne au voisine sont état )
}
