package developers.hub.com.thelearningapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import developers.hub.com.thelearningapp.Helper.CodingQuestionDataModel;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ContactViewHolder> {

    private List<CodingQuestionDataModel> questionList;
    private Context myActivity;

    public QuestionAdapter(Context context, List<CodingQuestionDataModel> questionList) {

        this.questionList = questionList;
        myActivity = context;
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, final int i) {
        final CodingQuestionDataModel ci = questionList.get(i);
        String titleQuestion = ci.getqDesc();
        titleQuestion = titleQuestion.substring(0,60);
        titleQuestion = titleQuestion +"....";
        contactViewHolder.qDesc.setText(titleQuestion);
        contactViewHolder.qName.setText(ci.getqName());
        contactViewHolder.solved.setText("Solved By :"+ci.getSolvedBy());


        contactViewHolder.codingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(myActivity,QuestionOpener.class);
                intent.putExtra("index",i);
                myActivity.startActivity(intent);


            }
        });
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.coding_questions_cardview, viewGroup, false);

        return new ContactViewHolder(itemView);
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        public TextView qName;
        public TextView qDesc;
        public TextView solved;
        public CardView codingCard;


        public ContactViewHolder(View v) {
            super(v);
            qName = (TextView) v.findViewById(R.id.head);
            qDesc = (TextView) v.findViewById(R.id.question);
            solved = (TextView) v.findViewById(R.id.solved);
            codingCard = (CardView) v.findViewById(R.id.codingCardView);


        }
    }

}
