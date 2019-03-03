package developers.hub.com.thelearningapp.ModelYoutube;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import developers.hub.com.thelearningapp.R;
import developers.hub.com.thelearningapp.VideoDetailsU2;

public class CustomAdapterYoutube extends BaseAdapter {


    Activity myActivity;
    ArrayList<YoutubeVideoDetails> detailsArrayList;
    LayoutInflater inflater;

    public CustomAdapterYoutube(Activity activity, ArrayList<YoutubeVideoDetails> arrayList){

        this.myActivity = activity;
        this.detailsArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.detailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.detailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){

            inflater = this.myActivity.getLayoutInflater();
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_adaptera_item,null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewThumbnails);
        TextView textView = (TextView) convertView.findViewById(R.id.titleYoutube);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.root);

        final YoutubeVideoDetails videoDetails = (YoutubeVideoDetails) this.detailsArrayList.get(position);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(myActivity, VideoDetailsU2.class);
                i.putExtra("videoId",videoDetails.getVideoId());
                myActivity.startActivity(i);
            }
        });






        //   Glide.get().load(videoDetails.getVideoUrl()).into(imageView);

        Glide.with(myActivity).load(videoDetails.videoUrl).thumbnail(0.3f).into(imageView);
        textView.setText(videoDetails.getVideotitle());


        return convertView;
    }


}
