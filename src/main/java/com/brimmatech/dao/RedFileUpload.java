package com.brimmatech.dao;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RedFileUpload {

    public static void main(String[] args) {
        try {
            String inputFile = "C:\\Users\\asvin\\Desktop\\Brimmatech.pdf";
            InputStream inputStream = new FileInputStream(inputFile);
            long fileSize = new File(inputFile).length();
            byte[] allBytes = new byte[(int) fileSize];
            int bytesRead = inputStream.read(allBytes);

            System.out.println("fileSize: " + fileSize + ", bytesRead: " + bytesRead);
            String s = new String(allBytes);

            URL url = new URL("http://localhost:8080/Redfile/testing");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileContentBytes", s);
            jsonObject.put("contentType", "application/pdf");
            connection.connect();

            String data = jsonObject.toString();
            byte[] b = new byte[13 * 1024];
            b = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(b);
            stream.flush();
            System.out.println(stream);
            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

            // StringBuilder builder = new StringBuilder();
//            Scanner scanner = new Scanner((InputStream) connection.getContent());
//            while (scanner.hasNext()) {
//                builder.append(scanner.nextLine());
//            }
//            scanner.close();
//            System.out.println(builder);
//            JSONObject jsonObject1 = new JSONObject(builder.toString());
//            String folderid = jsonObject1.getString("id");
//            System.out.println(folderid);
            connection.disconnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
