package developers.hub.com.thelearningapp;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import developers.hub.com.thelearningapp.Helper.DataModel;
import retrofit2.http.Url;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    private int RC_SIGN_IN = 1;
    private FirebaseAuth authFirebase;
    private FlipperLayout flipperLayout;
    private Button courseAct;
    private CardView b1;
    int i = 0;


    // Bottom Navigation

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    return true;
                case R.id.navigation_search:
                    bang();
                    return true;
                case R.id.navigation_bookmark:
                    Intent intent1 = new Intent(getApplicationContext(), Forum.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_profile:
                    Intent intent2 = new Intent(getApplicationContext(), ProfileViewNav.class);
                    startActivity(intent2);
                    return true;

            }
            return false;
        }
    };

    boolean backPressToExit = false;

    @Override
    public void onBackPressed() {


        if (backPressToExit) {

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


    // Course Activity

    // begComp
    public void compBeg(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 1);
        startActivity(intent);


    }

    public void intermediateComp(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 2);
        startActivity(intent);



    }

    public void compAdv(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 3);
        startActivity(intent);



    }

    public void andBeg(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 4);
        startActivity(intent);


    }

    public void andInt(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 5);
        startActivity(intent);

    }

    public void andAdv(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 6);
        startActivity(intent);

    }

    public void webBeg(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 7);
        startActivity(intent);


    }

    public void webInt(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 8);
        startActivity(intent);


    }

    public void webAdv(View view) {

        Intent intent = new Intent(getApplicationContext(), Open_Course_Activity.class);
        intent.putExtra("list", 9);
        startActivity(intent);

    }


    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom Navigation

        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_explore
        );


        // Messy

        // Initialize
        flipperLayout = (FlipperLayout) findViewById(R.id.flipperImage);
        b1 = (CardView) findViewById(R.id.Competitve);


        // Flipper Image
        try {
            setLayout();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        authFirebase = FirebaseAuth.getInstance();
   /*     if (authFirebase.getCurrentUser() != null) {
            // already signed in

        } else {
            // not signed in


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.FirebaseLoginTheme)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                                    new AuthUI.IdpConfig.AnonymousBuilder().build()))
                            .build(),
                    RC_SIGN_IN);

        }
        */

    }

    // Flipper


    String[] courses = {"https://sye7272.000webhostapp.com", "https://www.udemy.com/java-the-complete-java-developer-course/", "https://www.udemy.com/complete-android-n-developer-course/",

            "https://www.udemy.com/java-enterprise-application-development-with-java-ee-8/", "https://www.udemy.com/complete-python-3-masterclass-journey/"
    };

    private void setLayout() throws MalformedURLException {

        int url[] = new int[]{

                R.mipmap.flipper_guide, R.mipmap.flipper_java, R.mipmap.flipper_android, R.mipmap.flipper_web, R.mipmap.flipper_python
                // url of images

        };

        for (i = 0; i < url.length; i++) {

            final FlipperView view = new FlipperView(getBaseContext());
            view.setImageDrawable(url[i])
                    .setDescription("Hot Courses");
            flipperLayout.addFlipperView(view);


            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {


                    if (flipperView.getImageRes() == R.mipmap.flipper_guide) {

                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, courses[0]);
                        startActivity(intent);

                    } else if (flipperView.getImageRes() == R.mipmap.flipper_java) {

                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, courses[1]);
                        startActivity(intent);

                    } else if (flipperView.getImageRes() == R.mipmap.flipper_android) {

                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, courses[2]);
                        startActivity(intent);

                    } else if (flipperView.getImageRes() == R.mipmap.flipper_web) {

                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, courses[3]);
                        startActivity(intent);

                    } else if (flipperView.getImageRes() == R.mipmap.flipper_python) {

                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, courses[4]);
                        startActivity(intent);
                    }


                }
            });
        }


    }

    public void logout() {
        authFirebase.signOut();

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.FirebaseLoginTheme)
                        .setLogo(R.mipmap.icomain)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.AnonymousBuilder().build()))
                        .build(),
                RC_SIGN_IN);

    }

    public void bang() {

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RC_SIGN_IN) {

            if (resultCode == RESULT_OK)
                Toast.makeText(this, "Sign in", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(this, "Sign in Cancel", Toast.LENGTH_SHORT).show();
                finish();
            }
        }


    }
}


// C5:CD:6F:0A:79:80:C8:97:74:21:0C:EE:8F:3F:84:61:65:E1:36:A7