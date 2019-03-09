package developers.hub.com.thelearningapp.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpConnectionHelper {

    public HttpConnectionHelper(){}



    public static String get(String url) throws IOException {

        StringBuffer response = new StringBuffer();

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json;charset-utf-8");

        int responseCode = con.getResponseCode();

        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(

                    con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        return response.toString();

    }


    public static String send(final String url, final String post) throws IOException {

        StringBuffer response = new StringBuffer();
          final String POST_PARAMS  = post;


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");


        // For POST only - START
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true); // to post
        con.setDoInput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println(" yasir POST Response Code :: " + responseCode);


  //      if (responseCode == HttpURLConnection.HTTP_OK) { //success

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();

      //  } else {
        //    System.out.println("POST request not worked");
       // }

      //  return response.toString();

    }



}
