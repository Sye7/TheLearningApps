package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import developers.hub.com.thelearningapp.Helper.home;

public class HomeActivity extends AppCompatActivity {


    private WebView webView;
   // private Button navigationButton;
    private ImageView machineL;
    private ImageView processResult;
    public TextView testing;

    public Bitmap answerSheeet;
    String yourResult = "";



    public void tabbed(View view){

        Intent intent = new Intent(getApplicationContext(),CodingQuestions.class);
        startActivity(intent);
    }

    // Bottom Navigation

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_search:
                 //   bang();
                    return true;
                case R.id.navigation_bookmark:
                    Intent intent1 = new Intent(getApplicationContext(),Forum.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_profile:
                    Intent intent2 = new Intent(getApplicationContext(),ProfileViewNav.class);
                    startActivity(intent2);
                    return true;

            }
            return false;
        }
    };



// competition

    BottomNavigationView navigation;



    boolean  backPressToExit = false;

    @Override
    public void onBackPressed() {


        if(backPressToExit)
        {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        Toast.makeText(this, "Back Again to Exit", Toast.LENGTH_SHORT).show();

        this.backPressToExit = true;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPressToExit = false;
            }
        }, 2000);



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Bottom Navigation

        navigation = (BottomNavigationView) findViewById(R.id.bottomHome_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);


        View view = navigation.findViewById(R.id.navigation_search);
        view.performClick();


        // Init

      //  navigationButton = (Button) findViewById(R.id.navButton);
        machineL = (ImageView) findViewById(R.id.machineL);
        processResult = (ImageView) findViewById(R.id.processResult);


        webView = (WebView) findViewById(R.id.quiz);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.pskills.org/onlinetest.jsp");

    }

    public void screenShot(View view){

        answerSheeet = ScreenShot.takeScreenShot(webView);
    }

    public void processResult(View view){


        if(answerSheeet == null){

            Toast.makeText(this, "Take Snap first", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(answerSheeet);
            FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                    .getOnDeviceTextRecognizer();


            // pass the image

            Task<FirebaseVisionText> result =
                    detector.processImage(image)
                            .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                                @Override
                                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                    // Task completed successfully
                                    // ...
                                    yourResult = (firebaseVisionText.getText());
                                    yourResult = yourResult.toLowerCase();
                                }
                            })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Task failed with an exception
                                            // ...
                                            Toast.makeText(HomeActivity.this, "Better luck next Time", Toast.LENGTH_SHORT).show();
                                        }
                                    });



            if(yourResult.contains("poor")) {


                ImageView imageView = (ImageView) findViewById(R.id.congratsPic);
                Glide.with(this).load(R.drawable.failed).into(imageView);

                Toast.makeText(this, "Poor Result Work Hard", Toast.LENGTH_SHORT).show();
            }

            else if(yourResult.contains("bad")){

                ImageView imageView = (ImageView) findViewById(R.id.congratsPic);
                Glide.with(this).load(R.drawable.failed).into(imageView);

                Toast.makeText(this, "Failed, Work from scratch ", Toast.LENGTH_SHORT).show();
            }

            else if(yourResult.contains("excelent")) {

                ImageView imageView = (ImageView) findViewById(R.id.congratsPic);
                Glide.with(this).load(R.drawable.congrats).into(imageView);
                Toast.makeText(this, "Very Good Ready to go to next phase", Toast.LENGTH_SHORT).show();
            }

            else if(yourResult.contains("average")){

                ImageView imageView = (ImageView) findViewById(R.id.congratsPic);
                Glide.with(this).load(R.drawable.congrats).into(imageView);

                Toast.makeText(this, "Average Need for Dedication", Toast.LENGTH_SHORT).show();
            }

            else if(yourResult.contains("good")) {

                ImageView imageView = (ImageView) findViewById(R.id.congratsPic);
                Glide.with(this).load(R.drawable.congrats).into(imageView);
                Toast.makeText(this, "Good Keep it up", Toast.LENGTH_SHORT).show();
            }

        }


    }

    public void navigation(View view ){

        Intent intent = new Intent(getApplicationContext(), home.class);
        startActivity(intent);
    }
}


class ScreenShot{

    public static Bitmap takeScreenShot(View view){

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);

        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takeScreenShotOfRootView(View v){
        return takeScreenShot(v.getRootView());
    }

}