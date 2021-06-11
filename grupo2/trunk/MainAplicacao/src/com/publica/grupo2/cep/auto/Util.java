package com.publica.grupo2.cep.auto;
import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }
}