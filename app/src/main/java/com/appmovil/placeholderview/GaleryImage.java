package com.appmovil.placeholderview;

import android.content.Context;
import android.widget.ImageView;
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
    @Position
    int position;
    public ImageView imageView;
    public TextView txtname;
    public String mName;
    public String mAbbreviation;
    public String mUlr;
    public Context mContext;
    public PlaceHolderView mPlaceHolderView;

    public GalleryImage(Context context, PlaceHolderView placeHolderView, String ulr, String name, String abbreviation) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mUlr = ulr;
    }


    @Resolve
    protected void onResolved() {
        Glide.with(mContext).load(mUlr).into(imageView);

    }

    @LongClick(R.id.imageview)
    protected void onLongClick(){
        mPlaceHolderView.removeView(this);
    }

}