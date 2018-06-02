package hu.peti.fbstat;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by petib on 2018. 06. 02..
 */

public class HTTPCalls extends AsyncTask<String, Void, Boolean> {

    public static String token;
    public static String userID = "";




    public static void getID(){
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL("http://152.66.177.107:5000/get");
            Log.d("MSG","getID() method starts");

            conn = (HttpURLConnection) url
                    .openConnection();
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Authorization", token);

            InputStream in = conn.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            Log.d("MSG","getID() method reads");
            while (data != -1) {
                char current = (char) data;
                userID += current;
                data = isw.read();
            }
            Log.d("MSG","userID = " + userID);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }



    public static void authUser() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Login.username = Login.usernameText.getText().toString();
                    Login.password = Login.passwordText.getText().toString();

                    URL url = new URL("http://152.66.177.107:5000/auth");
                    Log.d("MSG","authUser() method starts");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    //conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("username", Login.username);
                    jsonParam.put("password", Login.password);

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();


                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject temp = new JSONObject(response.toString());
                    token = "JWT " + temp.getString("access_token");

                    Log.d("MSG", "authUser() token = " + token);

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        return null;
    }
}
