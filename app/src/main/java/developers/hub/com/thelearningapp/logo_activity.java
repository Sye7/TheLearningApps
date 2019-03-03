package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class logo_activity extends AppCompatActivity {

    int count =0;

    private ImageView bg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_activity);


        final Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        count++;

                        if(count >= 2) {

                            T.cancel();
                            Intent intent = new Intent(getApplicationContext(),PageSliderIntro.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }, 1000, 1000);




    }
}
