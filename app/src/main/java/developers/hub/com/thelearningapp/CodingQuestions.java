package developers.hub.com.thelearningapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import developers.hub.com.thelearningapp.Helper.CodingQuestionDataModel;

public class CodingQuestions extends AppCompatActivity {
    RelativeLayout relativeLayout;

   static ArrayList<CodingQuestionDataModel> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding_questions);



        questions = new ArrayList<>();

        questions.add(new CodingQuestionDataModel("Archie and his Homework",
                "Archie was given a homework by his teacher. He is given a fraction and he needs to write it in its reduced form. Archie is not good at Maths. Help Archie finish his homework.", 30,
                "2 integers separated by space denoting the numerator and denominator respectively.",
                "Print the reduced form of the fraction",
                "1 ≤ N ≤ D ≤ 1000","20 40","1 2"));

        questions.add(new CodingQuestionDataModel("The Homework",
                "As the students were not focusing much on the lecture. The professor became very angry and decided to give a homework to all the students. So some of the students started trying to convince the professor not to do so. So the professor gives them a K digits and asks the students to make the largest number possible from these digits. If they able to do it, they don't need to do the homework. So you being the smartest student decided to solve the problem.", 30,
                "The first line of input consists of single integer T denoting the number of test cases. The first line of each test case has a single integer K. The second line of each test case consists of K space separated integers denoting K digits.",
                "Print the largest number possible using these digits.",
                "1<=T<=100. 1<=K<=100. 0<=Digits<=9.","1\n" +
                "5\n" + "1 4 5 9 2","95421"));

        questions.add(new CodingQuestionDataModel("Divide The Array",
                "Cody loves even numbers. He was bored and decided to print all the even numbers at the even indexes of an array(1-indexed). He wants your help to complete this task.",
                30,
                "The first line of the input contains an integer N denoting the size of an array. The second line contains N-space separated integers denoting the array.",
                "Print all the numbers separated by space which satisfies the condition. ",
                "1<=N<=10^3. 1<=Array Elements<=10^5.",
                "6\n" + "2 3 5 4 7 8",
                "4 8"));

        questions.add(new CodingQuestionDataModel("First and the Last Digit",
                "Ron is weak in mathematics.Knowing this fact John challenges him to find the sum of first and last digit of a given number. Help Ron to find the sum of first and last digit of a number.",
                30,
                "The first line contains an integer T denoting total number of test cases. Then in the following T lines, each line contains an integer N.",
                "For each test case, print the sum of first and last digit of N.",
                "1<=T<=100. 10<=N<=100000.",
                "3\n" + "1234\n" + "85694\n" + "234",
                "5\n" + "12\n" + "6"));

        questions.add(new CodingQuestionDataModel("Total Expenses",
                "Joey went for shopping. The shopkeeper gives a discount of 10% if the total expense exceeds 1000 . Given total expense as input, write a program to calculate the total money Joey has to give to the shopkeeper after the discount, if discount is applicable.",
                30,
                "The first line contains an integer T, total number of test cases. The next T lines consist of an integer each representing total cost.",
                "Print the total money Joey has to pay after the discount. The precision must be upto 2 decimal places.",
                "1 ≤ T ≤ 100 1 ≤ total_cost ≤ 100000",
                "2\n" +
                        "900\n" +
                        "1100",
                "900.00\n" +
                        "990.00"));



        QuestionAdapter  adapter = new QuestionAdapter(this,questions);

        relativeLayout = (RelativeLayout) findViewById(R.id.courseRel);

        // Recycler View

        RecyclerView recList = (RecyclerView) findViewById(R.id.questionList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);


        recList.setItemAnimator(new DefaultItemAnimator());
        recList.setAdapter(adapter);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

    }



}
