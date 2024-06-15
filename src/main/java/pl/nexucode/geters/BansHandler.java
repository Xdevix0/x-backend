package pl.nexucode.geters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BansHandler {
    public String getBans(String nick) throws IOException {
        String url = "http://moodhc.pl:8090/bans?nick=" + nick;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        // Opcje metody GET
        httpClient.setRequestMethod("GET");

        int responseCode = httpClient.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                System.out.println("Response JSON: " + response.toString());
                return response.toString();
            }
        } else {
            System.out.println("GET request not worked");
            return null;
        }
    }

    public static void main(String[] args) {
        BansHandler bansHandler = new BansHandler();
        try {
            String response = bansHandler.getBans("xevix_");
            if (response != null) {
                System.out.println("Final Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
