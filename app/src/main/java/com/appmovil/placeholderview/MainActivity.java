package com.appmovil.placeholderview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appmovil.placeholderview.WebService.Asynchtask;
import com.appmovil.placeholderview.WebService.WebService;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php",
               datos, MainActivity.this,MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        PlaceHolderView mGalleryView = (PlaceHolderView)findViewById(R.id.galleryView);
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject comment=  JSONlista.getJSONObject(i);
            mGalleryView
                    .addView(new GalleryImage(this.getApplicationContext(), mGalleryView, comment.getString("portada").toString(),comment.getString("name").toString(),comment.getString("description").toString(), comment.getString("journal_id").toString()));
        }
    }
}