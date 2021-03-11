package com.appmovil.placeholderview;

import android.content.Context;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

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

    @View(R.id.mycard_view)
    CardView cardView;

    @View(R.id.imagView)
    ImageView imageView;

    @Position
    int position;

    private Context context;
    private String url;

    public GalleryImage(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    /*
     * This method is called when the view is rendered
     * onResolved method could be named anything, Example: onAttach
     */
    @Resolve
    public void onResolved() {
        // do something here
        // example: load imageView with url image
    }

    /*
     * This method is called when the view holder is recycled
     * and used to display view for the next data set
     */
    @Recycle
    public void onRecycled(){
        // do something here
        // Example: clear some references used by earlier rendering
    }

    /*
     * This method is called when the view with id image_view is clicked.
     * onImageViewClick method could be named anything.
     */
    @Click(R.id.imagView)
    public void onImageViewClick(){
        // do something
    }

    @LongClick(R.id.imagView)
    public void onImageViewLongClick() {
        // do something
    }

}