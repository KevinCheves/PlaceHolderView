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

public class VolumenesActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumenes);

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        String b = bundle.getString("id");
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id=" +bundle.getString("id"),
                datos, VolumenesActivity.this,VolumenesActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        PlaceHolderView mGalleryView = (PlaceHolderView)findViewById(R.id.galleryViewvol);
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject comment=  JSONlista.getJSONObject(i);
            mGalleryView
                    .addView(new clvolumen(this.getApplicationContext(), mGalleryView,comment.getString("cover").toString(), comment.getString("title").toString(),comment.getString("volume").toString(),comment.getString("number").toString(), comment.getString("date_published").toString(),comment.getString("issue_id").toString()));
        }
    }
}