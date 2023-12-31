package metier;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;

	public class UpdateChecker
	{
		private String currentVersion;
		private String availableVersion;

		public UpdateChecker()
		{
			try {
				// URL du fichier de configuration JSON
				String configUrl = "https://raw.githubusercontent.com/Oridoshi/Machine/main/data/update.json";

				// Récupérer le contenu du fichier JSON
				String jsonContent = fetchJson(configUrl);

				// Analyser le contenu JSON
				JSONObject jsonObject = new JSONObject(jsonContent);

				// Extraire la version actuelle de l'application
				this.currentVersion = (new JSONObject(lireFichier("/update.json"))).getString("version");

				// Extraire la version disponible dans le fichier JSON
				this.availableVersion = jsonObject.getString("version");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static String fetchJson(String url) throws IOException
		{
			HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			try (Scanner scanner = new Scanner(connection.getInputStream()))
			{
				scanner.useDelimiter("\\A");
				return scanner.hasNext() ? scanner.next() : "";
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

		public boolean isNewVersionAvailable()
		{
			return !this.currentVersion.equals(this.availableVersion);
		}

		public void update()
		{
			System.out.println("Mise à jour en cours...");
			System.out.println("Version actuelle : " + this.currentVersion);
			System.out.println("Version disponible : " + this.availableVersion);
			new GitHubFileDownloader();
		}
	}
