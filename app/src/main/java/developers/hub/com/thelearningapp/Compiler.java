package developers.hub.com.thelearningapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import developers.hub.com.thelearningapp.Helper.HttpConnectionHelper;

public class Compiler extends AppCompatActivity {


    EditText input;
    EditText code;


    public void runCode(View view){

        Toast.makeText(this,  input.getText().toString(), Toast.LENGTH_LONG).show();

        new Mcoder(code,input).execute("https://api.judge0.com/submissions?wait=true");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);

        input = (EditText) findViewById(R.id.compilerInput);
        code = (EditText) findViewById(R.id.compilerCode);
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



    public Mcoder(EditText codersCode,EditText codersInput){

        codeEditText = codersCode;
        inputEditText = codersInput;

    }


    @Override
    protected String doInBackground(String... strings) {

        final String code = codeEditText.getText().toString();
        final String input = inputEditText.getText().toString();
        final String url = "https://api.judge0.com/submissions?wait=true";
        final String langId = "4";
        final String output = "hello";




        try {
    //     String op = HttpConnectionHelper.get("sdfghj");

            String s = "hello";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("source_code",code);

            jsonObject.put("language_id",langId);

            jsonObject.put("number_of_runs","1");


            jsonObject.put("stdin",input);

            jsonObject.put("expected_output",output);

            jsonObject.put("cpu_time_limit","2");

            jsonObject.put("cpu_extra_time","0.5");

            jsonObject.put("wall_time_limit","5");

            jsonObject.put("memory_limit","128000");

            jsonObject.put("stack_limit","64000");

            jsonObject.put("max_processes_and_or_threads","30");

            jsonObject.put("enable_per_process_and_thread_time_limit",false);

            jsonObject.put("enable_per_process_and_thread_memory_limit",true);

            jsonObject.put("max_file_size","1024");

         //   System.out.println("yasir "+ jsonObject.toString());


            String post = HttpConnectionHelper.send(url,jsonObject.toString());






        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  null;
    }
}

