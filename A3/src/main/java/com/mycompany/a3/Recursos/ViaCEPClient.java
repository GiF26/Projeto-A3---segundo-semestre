package com.mycompany.a3.Recursos;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Giovana Ferreira
 */
public class ViaCEPClient {
    public static Endereco buscarEnderecoPorCEP(String cep) throws Exception {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("CEP não encontrado");
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        
        conn.disconnect();
        return new Gson().fromJson(response.toString(), Endereco.class);
    }
}

