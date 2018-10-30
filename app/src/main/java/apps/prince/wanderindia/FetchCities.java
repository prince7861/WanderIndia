package apps.prince.wanderindia;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchCities extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {
    String data="";

        try {
            URL url=new URL("https://api.myjson.com/bins/gxya4");
            HttpURLConnection urlcon= (HttpURLConnection)url.openConnection();
            InputStream inputStream=urlcon.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                data=data+line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
