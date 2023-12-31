package metier;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.json.JSONObject;

public class CoeurGest
{
	private static final String urlBaseDeDonné = "jdbc:mysql://localhost:3306/machine";
	private static final String utilisateur    = "root";
	private Connection connexion;

	public CoeurGest()
	{
		try
		{
			this.connexion = DriverManager.getConnection(urlBaseDeDonné, utilisateur, new JSONObject(lireFichier("/mdp.json")).getString("mdp"));
			System.out.println("DEBUG : CONNECTION ETABLI");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("DEBUG : FICHIER JSON INTROUVABLE");
		}
	}

	private String lireFichier(String nomFichier) throws IOException
	{
		InputStream ips = this.getClass().getResourceAsStream(nomFichier);
		try (Scanner scanner = new Scanner(ips, "UTF-8"))
		{
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}

	/* REQUETE SUR LA TABLE CLIENT */

	public int getNbTuple(String table)
	{
		int nbTuple = 0;

		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT COUNT(*) FROM " + table);

			ResultSet resultSet = statement.executeQuery();

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
			String sqlQuery = "SELECT COUNT(*) " +
			                  "FROM information_schema.columns " +
			                  "WHERE table_schema = 'machine' " +
			                  "AND table_name = '" + table + "';";
			PreparedStatement statement = connexion.prepareStatement(sqlQuery);

			ResultSet resultSet = statement.executeQuery();

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
			String sqlQuery = "SELECT column_name " +
			                  "FROM information_schema.columns " +
			                  "WHERE table_schema = 'machine' " +
			                  "AND table_name = '" + table + "'" +
			                  "ORDER BY ordinal_position;";
			PreparedStatement statementRes = connexion.prepareStatement(sqlQuery);

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
			PreparedStatement statementRes = connexion.prepareStatement("SELECT * FROM Client where ncli = " + ncli);

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statementRes.executeQuery();

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
			String sqlQuery = "SELECT table_name " +
							  "FROM information_schema.tables " +
							  "WHERE table_schema = DATABASE() ";
			PreparedStatement statementRes = connexion.prepareStatement(sqlQuery);

			ResultSet resultSet = statementRes.executeQuery();

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
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Client");

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery();


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
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM type");

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery();


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
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM utilisation");

			/*RESULTAT DEMANDE*/
			ResultSet resultSet = statement.executeQuery();


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
			String sqlQuery = "SELECT COUNT(*) " +
			                  "FROM utilisation " +
			                  "WHERE nt = " + type + ";";
			PreparedStatement statement = connexion.prepareStatement(sqlQuery);

			ResultSet resultSet = statement.executeQuery();

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


	// 		PreparedStatement statementRes = connexion.createStatement();
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

	// 	// reset.supDonneTable();
	// 	// reset.supCli(13);
	// 	reset.fermerConnexion();
	// }
}
