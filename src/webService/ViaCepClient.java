package webService;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author rafael
 */
public class ViaCepClient {

    public ViaCep buscarCep(String cep) {
        String urlViaCep = "http://viacep.com.br/ws/" + cep + "/json/";
        ViaCep viaCep = null;
        try {
            URL url = new URL(urlViaCep);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                Gson gson = new Gson();
                viaCep = gson.fromJson(response.toString(), ViaCep.class);
                conn.disconnect();
                return viaCep;
            }    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar CEP.\nVerifique a conexão com a internet!");
        }
        return viaCep;
    }
}
