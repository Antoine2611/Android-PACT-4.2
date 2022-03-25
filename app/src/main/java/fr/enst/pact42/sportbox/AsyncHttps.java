package fr.enst.pact42.sportbox;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            Log.i("info1",stringResult);
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
        finally { if (urlConnection != null) { urlConnection.disconnect();}  }

        publishProgress(4);

        JSONObject result=null;
        try{
            result=new JSONObject (stringResult);
        }catch (Exception e){ e.printStackTrace();}

        return result;
    }

    public String readStream (InputStream in) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

}
