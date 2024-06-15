package pl.nexucode;

import netscape.javascript.JSObject;
import pl.nexucode.geters.BansHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("x-backend-Api - sprawdzam czy server odpowiada");
        String urlString = "http://moodhc.pl:8090/bans";

        try {
            // Tworzymy URL
            URL url = new URL(urlString);
            // Otwieramy połączenie
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Ustawiamy metodę na GET
            connection.setRequestMethod("GET");

            // Ustawiamy timeout na odpowiedź (opcjonalnie)
            connection.setConnectTimeout(5000); // 5 sekund
            connection.setReadTimeout(5000); // 5 sekund

            // Wysyłamy żądanie i otrzymujemy kod odpowiedzi
            int responseCode = connection.getResponseCode();

            // Sprawdzamy kod odpowiedzi
            if (responseCode == HttpURLConnection.HTTP_OK) { // kod 200 oznacza sukces
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("x-backend-Api - server odpowiada");
            } else {
                System.out.println("GET request not worked, Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
