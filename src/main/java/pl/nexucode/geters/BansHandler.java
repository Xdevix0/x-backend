package pl.nexucode.geters;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BansHandler {
    public JSONObject getBans(String nick) throws IOException {
        String url = "http://localhost:8080/bans?nick=" + nick;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        // Opcje metody GET
        httpClient.setRequestMethod("GET");

        int responseCode = httpClient.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Konwertujemy odpowiedź do JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Wyciągamy dane z JSON
            System.out.println("Response JSON: " + jsonResponse.toString());

            // Przykład jak wyciągnąć poszczególne dane z JSON (jeśli istnieją)
            if (jsonResponse.has("reason")) {
                String reason = jsonResponse.getString("reason");
                System.out.println("Reason: " + reason);
            }
            if (jsonResponse.has("administration")) {
                String administration = jsonResponse.getString("administration");
                System.out.println("Administration: " + administration);
            }

            return jsonResponse;
        } else {
            System.out.println("GET request not worked");
            return null;
        }
    }

    public static void main(String[] args) {
        BansHandler bansHandler = new BansHandler();
        try {
            JSONObject response = bansHandler.getBans("xevix_");
            if (response != null) {
                System.out.println("Final Response: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
