package com.appmovil.placeholderview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Recycle;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Animate(Animate.CARD_TOP_IN_DESC)
@Layout(R.layout.image_item)

 class GalleryImage {
    @View(R.id.imageview)
    public ImageView imageView;
    @View(R.id.txtname)
    public TextView name;
    @View(R.id.txtdescription)
    public TextView description;
    @View(R.id.txtid)
    public TextView id;
    @Position
    int position;
    public String mUlr,mName,mDescription,mNameclick, mId;
    public Context mContext;
    public PlaceHolderView mPlaceHolderView;

    public GalleryImage(Context context, PlaceHolderView placeHolderView, String ulr, String name1, String description1, String issue_id) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mUlr = ulr;
        mName=name1;
        mDescription = description1;
        mId = issue_id;
    }

    @Resolve
    protected void onResolved() {
        Glide.with(mContext).load(mUlr).into(imageView);
        name.setText(mName);
        description.setText(mDescription);
        id.setText(mId);
    }
    @Click(R.id.imageview)
    protected void onImageViewClick(){
       Intent intent = new Intent(mContext, VolumenesActivity.class);
        Bundle b = new Bundle();
        b.putString("id", id.getText().toString());
        intent.putExtras(b);
        mContext.startActivity(intent);
    }

    @LongClick(R.id.imageview)
    protected void onLongClick(){
        mPlaceHolderView.removeView(this);
    }

}