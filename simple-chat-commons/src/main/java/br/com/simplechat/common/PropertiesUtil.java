package br.com.simplechat.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties loadProperties(String path) throws IOException{
        InputStream inputStream = PropertiesUtil.class.getResourceAsStream(path);
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            System.out.println("Erro ao acessar arquivo properties, ou arquivo não encontrado.");
            throw new IOException(e);
        }
    }

}
