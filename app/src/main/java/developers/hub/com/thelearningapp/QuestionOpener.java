package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import developers.hub.com.thelearningapp.Helper.CodingQuestionDataModel;


public class QuestionOpener extends AppCompatActivity {


    public TextView t1;
    public TextView t2;
    public TextView t3;
    public TextView t4;
    public TextView t5;
    public TextView t6;
    public TextView t7;


    int index = 0;

    public void compiler(View view){

        Intent intent = new Intent(getApplicationContext(), Compiler.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_opener);


        Intent intent = getIntent();
        index = intent.getIntExtra("index",0);

        t1 = (TextView) findViewById(R.id.question_heading);
        t2 = (TextView) findViewById(R.id.question_desc);
        t3 = (TextView) findViewById(R.id.inputdesc);
        t4 = (TextView) findViewById(R.id.outputdesc);
        t5 = (TextView) findViewById(R.id.cons_desc);
        t6 = (TextView) findViewById(R.id.sampleinput_desc);
        t7 = (TextView) findViewById(R.id.sampleOutput_desc);

        ArrayList al = CodingQuestions.questions;

        CodingQuestionDataModel dm = CodingQuestions.questions.get(index);

        t1.setText(dm.getqName());
        t2.setText(dm.getqDesc());
        t3.setText(dm.getInputDesc());
        t4.setText(dm.getOutputDesc());
        t5.setText(dm.getConstraints());
        t6.setText(dm.getSampleInput());
        t7.setText(dm.getSampleOutput());

    }
}
