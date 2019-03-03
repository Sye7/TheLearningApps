package developers.hub.com.thelearningapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import developers.hub.com.thelearningapp.Helper.HttpConnectionHelper;

public class Compiler extends AppCompatActivity {


    EditText input;
    EditText code;


    public void runCode(View view){

        Snackbar.make(view, input.getText().toString(),Snackbar.LENGTH_LONG);
        Toast.makeText(this,  input.getText().toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);

        input = (EditText) findViewById(R.id.compilerInput);
        code = (EditText) findViewById(R.id.compilerCode);
        String s = "import java.util.*;\n" +
                "\n" +
                "public class CareerAssistant\n" +
                "{\n" +
                " \n" +
                "    public static void main(String[] args)\n" +
                "     {   \n" +
                "\n" +
                "       Scanner scan = new Scanner(System.in);\n" +
                "       String value = scan.nextLine();\n" +
                "       System.out.println(value);\n" +
                "     } \t\n" +
                "}";

        code.setText(s);

        Mcoder mCoder = new Mcoder();

        String str = String.valueOf(mCoder.execute("dfgh"));

    }




}

class Mcoder extends android.os.AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {


        try {
         String op = HttpConnectionHelper.get("sdfghj");
         String post = HttpConnectionHelper.send("DSgfhj");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }
}

