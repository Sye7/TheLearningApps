package developers.hub.com.thelearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import developers.hub.com.thelearningapp.Helper.ForumDataModel;
import developers.hub.com.thelearningapp.Helper.HttpConnectionHelper;
import developers.hub.com.thelearningapp.Helper.ProfileInfo;

public class Compiler extends AppCompatActivity
       {

    String language[] = {"C", "C++", "PYTHON", "JAVA", "RUBY"};


    EditText input;
    EditText code;
    Spinner spinner;
    String lang = "4";
    TextView opC;
    TextView timeC;
    TextView desC;

    TextView outputDekho;

    LinearLayout outputLayout;




    public void runCode(View view){

        Toast.makeText(this,  input.getText().toString(), Toast.LENGTH_LONG).show();

        new Mcoder(code,input,lang, outputLayout, opC,timeC,desC,op ).execute("https://api.judge0.com/submissions?wait=true");


    }

    public void dekhoOutput(View view){

        if(tog)
        {
            outputLayout.setVisibility(View.VISIBLE);
            input.setVisibility(View.INVISIBLE);
            code.setVisibility(View.INVISIBLE);

            tog = false;
        }
        else
        {
            outputLayout.setVisibility(View.INVISIBLE);
            input.setVisibility(View.VISIBLE);
            code.setVisibility(View.VISIBLE);

            tog = true;
        }

    }

    static  boolean tog = true;

    String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);


        Intent intent = getIntent();
         op = intent.getStringExtra("output");

        opC = (TextView) findViewById(R.id.outputConsole);
        timeC = (TextView) findViewById(R.id.timeConsole);
        desC = (TextView) findViewById(R.id.descriptionConsole);
        outputDekho = (TextView) findViewById(R.id.outputDekho);

        spinner = (Spinner) findViewById(R.id.spinner);


        input = (EditText) findViewById(R.id.compilerInput);
        code = (EditText) findViewById(R.id.compilerCode);
        outputLayout = (LinearLayout) findViewById(R.id.layoutOutputCoder);
        outputLayout.setVisibility(View.INVISIBLE);


        // creating array adapter

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,language);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lang =  (position == 0)? "4" : (position == 1)? "11": (position == 2)?"34": (position == 3)? "27": "38";


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String s = "#include <stdio.h>\n" +
                "int main()\n" +
                "{\n" +
                "   // printf() displays the string inside quotation\n" +
                "   printf(\"hello\");\n" +
                "   return 0;\n" +
                "}";

        code.setText(s);



    }



}

class Mcoder extends android.os.AsyncTask<String,String,String> {

     EditText codeEditText;
    EditText inputEditText;
    String langId;
    LinearLayout outputLayout;
    TextView opC;
    TextView timeC;
    TextView desC;
    String op;



    public Mcoder(EditText codersCode, EditText codersInput, String langI, LinearLayout layout, TextView op, TextView time, TextView des, String outputE) {

        codeEditText = codersCode;
        inputEditText = codersInput;
        langId = langI;
         outputLayout = layout;
         opC = op;
         timeC = time;
         desC = des;
         this.op = outputE;


    }


    @Override
    protected String doInBackground(String... strings) {

        final String code = codeEditText.getText().toString();
        final String input = inputEditText.getText().toString();
        final String url = "https://api.judge0.com/submissions?wait=true";
        final String langIdJson = langId;
        final String expectedOutput = op;


        try {
            //     String op = HttpConnectionHelper.get("sdfghj");

            String s = "hello";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("source_code", code);

            jsonObject.put("language_id", langIdJson);

            jsonObject.put("number_of_runs", "1");


            jsonObject.put("stdin", input);

            jsonObject.put("expected_output", expectedOutput);

            jsonObject.put("cpu_time_limit", "2");

            jsonObject.put("cpu_extra_time", "0.5");

            jsonObject.put("wall_time_limit", "5");

            jsonObject.put("memory_limit", "128000");

            jsonObject.put("stack_limit", "64000");

            jsonObject.put("max_processes_and_or_threads", "30");

            jsonObject.put("enable_per_process_and_thread_time_limit", false);

            jsonObject.put("enable_per_process_and_thread_memory_limit", true);

            jsonObject.put("max_file_size", "1024");

            //   System.out.println("yasir "+ jsonObject.toString());


            String post = HttpConnectionHelper.send(url, jsonObject.toString());


            getOutput(post);




        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    String outputConsole = "";
    String timeConsloe = "";
    String descriptionConsole = "";

    private void getOutput(String post) {


        try {


            JSONObject jsonObject = new JSONObject(post);

            outputConsole = jsonObject.getString("stdout");

            timeConsloe = jsonObject.getString("time");

            JSONObject desJson = jsonObject.getJSONObject("status");


            descriptionConsole = desJson.getString("description");
            System.out.print("yasir 5" );

              System.out.print("yasir  ali"+ outputConsole +timeConsloe +descriptionConsole);




        } catch (JSONException e) {

            System.out.print("yasir ali" +e.getMessage());

            e.printStackTrace();
        }

    }


    @Override
    protected void onPostExecute(String s) {

        opC.setText(outputConsole);
        timeC.setText(timeConsloe);
        desC.setText(descriptionConsole);

    }
}


