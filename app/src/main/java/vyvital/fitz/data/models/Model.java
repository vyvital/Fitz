package vyvital.fitz.data.models;

import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vyvital.fitz.BuilderActivity;
import vyvital.fitz.MainActivity;
import vyvital.fitz.data.GetRequestListener;


abstract public class Model {

    static String baseUrl = "http://localhost:8081/rest/v2";

    protected static String modelName = "";

    public void add(final String query, final GetRequestListener listener){

        final String url = baseUrl+"/"+modelName+"/"+query;

        new Thread(){

            @Override
            public void run() {

                try {

                    listener.onResult(new JSONArray(get(url)));

                }catch(Exception e){
                    listener.onFailed();
                }
            }
        };
    }

    public static void listModel(final String query, final GetRequestListener listener){

        final String url = baseUrl+"/"+"user_workouts"+"?token=assssss";

        new Thread(){

            @Override
            public void run() {

                try {
                    //listener.onResult(new JSONArray(get(url).replace("\"","\'")));
                    listener.onResult(new JSONArray("[{‘user_id':1,'token':'assssss','id':1,'name':'fun','level':'hard','type':'strength','owner_id':1,'days':3,'ts':'2018-02-12T20:37:03.000Z'},{'user_id':1,'token':'assssss','id':3,'name':'fun','level':'hard','type':'strength','owner_id':1,'days':3,'ts':'2018-02-12T20:37:03.000Z'}]"));
                }catch(Exception e){
                    listener.onFailed();
                }
            }
        }.start();
    }

    private static String get(String urlString) throws MalformedURLException, IOException{

        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        return sb.toString();
        //System.out.println("JSON: " + jsonString);

        //listener.onResult(new JSONArray(jsonString));
    }
}
