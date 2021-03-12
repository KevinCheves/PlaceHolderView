package com.appmovil.placeholderview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Animate(Animate.CARD_TOP_IN_DESC)
@Layout(R.layout.volumenes_item)
public class clvolumen {

    @View(R.id.imagecover)
    public ImageView imageView;
    @View(R.id.txtTitle)
    public TextView title;
    @View(R.id.txtVol)
    public TextView vol;
    @View(R.id.txtNum)
    public TextView num;
    @View(R.id.txtPublished)
    public TextView published;
    @Position
    int position;
    public String mUlr,mtitle, mvol, mnum, mpubli, mNameclick;
    public Context mContext;
    public PlaceHolderView mPlaceHolderView;

    public clvolumen(Context context, PlaceHolderView placeHolderView, String ulr, String title, String vol, String num, String publicad, String nameclick) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mUlr = ulr;
        mtitle = title;
        mvol = vol;
        mnum = num;
        mpubli = publicad;
        mNameclick = nameclick;
        //mNameclick = nameclick.replace("Revista ", "").toLowerCase();
    }


    @Resolve
    protected void onResolved() {
        Glide.with(mContext).load(mUlr).into(imageView);
        title.setText("Titulo: "+mtitle);
        vol.setText("Volumen: "+mvol);
        num.setText("Número: "+mnum);
        published.setText("Publicación: "+mpubli);
    }
    @Click(R.id.imageview)
    protected void onImageViewClick(){
        Intent intent = new Intent(mContext, VolumenesActivity.class);
        Bundle b = new Bundle();
        b.putString("usr", "");
        intent.putExtras(b);
        mContext.startActivity(intent);
    }

    @LongClick(R.id.imageview)
    protected void onLongClick(){
        mPlaceHolderView.removeView(this);
    }

}
