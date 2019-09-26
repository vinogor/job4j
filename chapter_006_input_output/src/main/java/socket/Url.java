package socket;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

// URL является подмножеством URI.
// Класс URI инкапсулирует универсалъный идентификатор ресурса

class Url {
    public static void main(String[] args) throws IOException {
//        URL hp = new URL("http://www.HerbSchildt.com/Articles");
        URL hp = new URL("http://ya.ru");
        System.out.println("Пpoтoкoл:" + hp.getProtocol());
        System.out.println("Пopт:" + hp.getPort());
        System.out.println("Xocт:" + hp.getHost());
        System.out.println("Фaйл:" + hp.getFile());
        System.out.println("Пoлнaя форма:" + hp.toExternalForm());
        System.out.println();

        // Класс URLConnection является классом общего назначения и предназначен
        // для доступа к атрибутам удаленного ресурса.
        URLConnection urlConnection = hp.openConnection();
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry entry : headerFields.entrySet()) {
            System.out.println("ключ " + entry.getKey() + ", значение " + entry.getValue());
        }

    }
}