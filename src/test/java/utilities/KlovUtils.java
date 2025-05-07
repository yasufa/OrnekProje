package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class KlovUtils {


    public static String getProperty(String key) throws IOException {
        Properties properties =new Properties();

        FileInputStream fileInputStream = new FileInputStream("src/test/resources/klovReport.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public static int getPropertyInt(String key) throws IOException {
        Properties properties =new Properties();

        FileInputStream fileInputStream = new FileInputStream("src/test/resources/klovReport.properties");
        properties.load(fileInputStream);
        String text = properties.getProperty(key);
        int intText = Integer.parseInt(text);
        return intText;
    }

    public static void setProperty(String key, String value) throws IOException {
        Properties properties = new Properties();

        // Önce mevcut verileri oku
        FileInputStream in = new FileInputStream("src/test/resources/config.properties");
        properties.load(in);
        in.close();

        // Yeni key-value çiftini ekle ya da güncelle
        properties.setProperty(key, value);

        // Dosyayı tekrar yaz
        FileOutputStream out = new FileOutputStream("src/test/resources/config.properties");
        OutputStreamWriter writer = new OutputStreamWriter(out, java.nio.charset.StandardCharsets.UTF_8);
        properties.store(writer, "config.properties dosyası güncellendi");
        out.close();
    }
}
