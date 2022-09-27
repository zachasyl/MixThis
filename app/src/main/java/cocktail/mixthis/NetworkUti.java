package cocktail.mixthis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * This class helps convert the input stream to a string using stringBuilder.
 */
public class NetworkUti {

    public static String convertStreamToString(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while((len=bufferedReader.readLine())!=null){
                stringBuilder.append(len);
            }
            bufferedReader.close();
            return stringBuilder.toString().replace(",", ",\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * Handles get request.
     */
    public static String httpResponse(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        conn.connect();

        // inputstream is the HTTP response code for the URL with url
        InputStream inputStream = conn.getInputStream();
        //convert InputStream object into a String.
        String resp = NetworkUti.convertStreamToString(inputStream);

        return resp;
    }
}