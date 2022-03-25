package fr.enst.pact42.sportbox;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHttps extends AsyncTask<String,Integer, JSONObject> {

    private AppCompatActivity activity;

    public AsyncHttps(AppCompatActivity activity){
        this.activity=activity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        publishProgress(1);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        String stringResult = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            publishProgress(2);
            stringResult = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
        finally { if (urlConnection != null) { urlConnection.disconnect();}  }

        publishProgress(4);

        return null;
    }

    public String readStream (InputStream in){
        return "OK";
    }
}
