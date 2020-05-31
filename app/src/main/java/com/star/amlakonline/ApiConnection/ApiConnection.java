package com.star.amlakonline.ApiConnection;

import android.os.AsyncTask;
import android.util.Log;

import com.star.amlakonline.MainActivity;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import static android.content.ContentValues.TAG;

public class ApiConnection extends AsyncTask<String,Integer,String> {
    private String result;

    public String getResult() {
        return result;
    }

    @Override
    protected String doInBackground(String... strings) {
        String targetURL = strings[0];
        String urlParameters = strings[1];
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        values[0]++;
        Log.d(TAG, "process thread: "+values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        s = StringEscapeUtils.unescapeJava(s);
        result = s;
    }
}
