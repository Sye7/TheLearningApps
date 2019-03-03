package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;
import java.util.Objects;

import developers.hub.com.thelearningapp.Helper.ProfileInfo;

public class profile extends AppCompatActivity {

    final int RC_PHOTO_PICKER = 1;
    static   Uri downloadUri;
    private DatabaseReference mMessageDatabaseReferenceUser;

    private StorageReference mChatPhotoStorageRef;
    private FirebaseStorage mFirebaseStorage;

    private String mUsername;
    private FirebaseAuth authFirebase;
    Uri selectedImageUri;

    String name = "mhsadgh";
    String dob ="123";
    String college ="sahdg";
    String year="sda";
    String city= "qwdbnb";
    int point = 0;

    EditText editTextName;
    EditText editTextDob;
    EditText editTextCollege;
    EditText editTextyear;
    EditText editTextCity;
    ImageButton dp;
    ProgressBar progressBar;


    //on CLick

    String alreadyExists = "catchMeIfYouCan";

    public void submit(final View view){


        if(selectedImageUri == null){

            Snackbar.make(view,"Please select DP first",Snackbar.LENGTH_LONG).show();
            return;
        }

        Snackbar.make(view,"Please wait while data is uploading",Snackbar.LENGTH_LONG).show();

        name = editTextName.getText().toString();
        dob = editTextDob.getText().toString();
        college = editTextCollege.getText().toString();
        year = editTextyear.getText().toString();
        city = editTextCity.getText().toString();

       String uid = FirebaseAuth.getInstance().getUid();

        DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("profile/");

        temp.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if( dataSnapshot.exists()) {

                    alreadyExists = "nonono";
                    return;
                }
                else{
                    alreadyExists = "pikapika";
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



       if(alreadyExists.equals("pikapika")){

            // upload


            //Get a reference to store file at image_photos/<FileName>
            final StorageReference photoRef = mChatPhotoStorageRef.child(selectedImageUri.getLastPathSegment());


            //Upload file to firebase storage
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            // got image url
                            downloadUri = uri;

                            ProfileInfo profileInfo =
                                    new ProfileInfo(name, dob, college, year, city, downloadUri.toString(), 0, FirebaseAuth.getInstance().getCurrentUser().getUid(), 1000);
                            mMessageDatabaseReferenceUser.push().setValue(profileInfo);
                          Snackbar.make(view,"Done :)",Snackbar.LENGTH_SHORT).show();

                        }


                    });


                }
            });


            progressBar.setVisibility(View.GONE);
        }

        else{
            Snackbar.make(view, "Sorry Already Registered", Snackbar.LENGTH_SHORT).show();

        }

        alreadyExists = "catchMeIfYouCan";

   }

    private int RC_SIGN_IN = 1;


    public void logout()
    {
        FirebaseAuth.getInstance().signOut();

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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_coding_lecture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mainLogOut) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
        String recomnd = "";
        recomnd  = intent.getStringExtra("message");

        if(recomnd == null)
            recomnd = "yas";

        if(recomnd.equals("yes")){
            Toast.makeText(this, "Register First", Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);

        // Init
        authFirebase = FirebaseAuth.getInstance();
        mUsername = "ANOMINOUS";

        progressBar = (ProgressBar) findViewById(R.id.progressProfile);
        mMessageDatabaseReferenceUser =FirebaseDatabase.getInstance().getReference().child("profile");
        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotoStorageRef = mFirebaseStorage.getReference().child("image_photos");

        dp = (ImageButton) findViewById(R.id.imageButton);
        editTextName = (EditText) findViewById(R.id.name);
        editTextCity = (EditText) findViewById(R.id.city);
        editTextCollege = (EditText) findViewById(R.id.college);
        editTextDob = (EditText) findViewById(R.id.dob);
        editTextyear = (EditText) findViewById(R.id.year);





        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // TODO: Fire an intent to show an image picker
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Complete Action Using"),RC_PHOTO_PICKER);


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){

             selectedImageUri = data.getData();

        }


    }
}
