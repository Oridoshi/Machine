package metier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubFileDownloader {

	public GitHubFileDownloader()
	{
		String githubUrl = "https://github.com/Oridoshi/Machine/raw/main/Machine.jar";
		String destinationPath = "Machine.jar";

		try
		{
			URL url = new URL(githubUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setInstanceFollowRedirects(true);

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				try (FileOutputStream outputStream = new FileOutputStream(destinationPath))
				{
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = connection.getInputStream().read(buffer)) != -1)
					{
						outputStream.write(buffer, 0, bytesRead);
					}
				}

				System.out.println("Nouvelle version téléchargée avec succès : " + destinationPath);
			} else {
				System.err.println("Erreur lors du téléchargement : " + responseCode + " " + connection.getResponseMessage());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}