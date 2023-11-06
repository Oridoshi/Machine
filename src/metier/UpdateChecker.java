package metier;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {
    public static void main(String[] args) {
        try {
            // URL du fichier de configuration JSON
            String configUrl = "https://github.com/Oridoshi/Machine/blob/main/data/update.json";

            // Récupérer le contenu du fichier JSON
            String jsonContent = fetchJson(configUrl);

            // Analyser le contenu JSON
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Extraire la version actuelle de l'application
            String currentVersion = "1.0.0"; // Vous pouvez obtenir la version actuelle de votre application ici

            // Extraire la version disponible dans le fichier JSON
            String availableVersion = jsonObject.getString("version");

            // Comparer les versions
            if (isNewVersionAvailable(currentVersion, availableVersion)) {
                System.out.println("Une nouvelle version (" + availableVersion + ") est disponible.");
                // Vous pouvez ici déclencher le processus de mise à jour.
            } else {
                System.out.println("Votre application est à jour.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fetchJson(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    private static boolean isNewVersionAvailable(String currentVersion, String availableVersion) {
        // Vous pouvez personnaliser cette logique de comparaison selon vos besoins.
        // Par exemple, vous pouvez diviser les versions en parties (majeure, mineure, correctif) et les comparer individuellement.
        return !currentVersion.equals(availableVersion);
    }
}
