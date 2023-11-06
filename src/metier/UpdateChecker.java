package metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStream;

public class UpdateChecker {
    public static void main(String[] args) {
        try {
            // URL du fichier de configuration
            String configUrl = "https://github.com/Oridoshi/Machine/raw/main/data/update.json";
            
            // Récupération du fichier de configuration
            JSONObject config = fetchConfig(configUrl);

            if (config != null) {
                String currentVersion = "1.0.0"; // Version actuelle de votre application

                String latestVersion = (String) config.get("version");
                String updateUrl = (String) config.get("updateUrl");

                if (isUpdateAvailable(currentVersion, latestVersion)) {
                    System.out.println("Une nouvelle version est disponible : " + latestVersion);
                    // Vous pouvez ici déclencher la mise à jour en téléchargeant le nouveau fichier JAR depuis updateUrl.
                } else {
                    System.out.println("Vous utilisez déjà la dernière version.");
                }
            } else {
                System.out.println("Impossible de récupérer le fichier de configuration.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Vérifie si une mise à jour est disponible
    private static boolean isUpdateAvailable(String currentVersion, String latestVersion) {
        // Vous pouvez implémenter votre propre logique de comparaison de versions ici.
        // Par exemple, en convertissant les versions en objets Version et en les comparant.
        return !currentVersion.equals(latestVersion);
    }

    // Récupère le fichier de configuration depuis l'URL
    private static JSONObject fetchConfig(String url) {
        try {
            URL configURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) configURL.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                JSONParser parser = new JSONParser();
                return (JSONObject) parser.parse(response.toString());
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
