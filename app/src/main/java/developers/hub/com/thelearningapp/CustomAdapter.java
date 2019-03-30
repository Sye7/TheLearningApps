package developers.hub.com.thelearningapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    EditText edit;
    private LayoutInflater inflater;
    private Context context;
    String operators[];

    public  CustomAdapter(Context context,String s[],EditText editText){
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.operators=s;
        edit=editText;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.operators,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String s = operators[position];

        holder.textView.setText(s);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = edit.getText().toString();
                int pos = edit.getSelectionStart();

                String s1 = s.substring(0,pos);

                if(operators[position].equals("TAB"))
                    s1 += "\t\t";
                else
                    s1 += operators[position];

                s1 = s1.concat(s.substring(pos,s.length()));

                edit.setText(s1);
                edit.setSelection(pos+1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return operators.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View view){
            super(view);
            textView=view.findViewById(R.id.text);
        }
    }
}
