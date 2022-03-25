package fr.enst.pact42.sportbox;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
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
    private JSONObject lastResult;

    public AsyncHttps(AppCompatActivity activity){
        this.activity=activity;
    }

    public JSONObject getLastResult(){
        return lastResult;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        publishProgress(1);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        URL url = null;
        HttpURLConnection urlConnection = null;
        JSONObject result=null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
            publishProgress(2);
            result = readStream(in); // Read stream


        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
        finally { if (urlConnection != null) { urlConnection.disconnect();}  }

        publishProgress(4);


        lastResult=result;
        return result;
    }

    public JSONObject readStream (InputStream in) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(in),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line+ "\n");
        }
        in.close();
        String s = sb.toString();
        int start=s.indexOf("["), end=s.indexOf("]");
        String s1= s.substring(0, start)+""+s.substring(end+1);
        String data= s.substring(start, end+1);

        JSONObject result=null;
        try {
            result= new JSONObject (s1);
            result.remove("data");
            result.put("data",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
