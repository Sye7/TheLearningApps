package developers.hub.com.thelearningapp;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONException;
import org.json.JSONObject;

import developers.hub.com.thelearningapp.Helper.HttpHelper;
import mbanje.kurt.fabbutton.FabButton;

public class Editor extends AppCompatActivity {

    FabButton fab;
    RecyclerView rv;
    Spinner spin;
    SlidingUpPanelLayout slidingUpPanelLayout;
    View output;
    EditText editText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editText = findViewById(R.id.edittext);
        spin = findViewById(R.id.spinner);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv = findViewById(R.id.recycleview);
        rv.setLayoutManager(lm);

        String operators[] = {"TAB", "<", ">", "{", "}", "(", ")", "\"", ":", "\\", "=", "'", "&", "|", " "};

        CustomAdapter adapter = new CustomAdapter(this, operators, editText);

        rv.setAdapter(adapter);

        final Switch s = findViewById(R.id.custominput);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(Editor.this);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s.setChecked(false);
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s.setChecked(false);
                    }
                });

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog, null);
                dialog.setView(view);
                dialog.show();

            }
        });

        slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        output = findViewById(R.id.output);

        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

            }
        });

        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    fab.setIcon(R.drawable.code, R.drawable.ic_fab_complete);
                }

            }
        });

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.showProgress(true);
                fab.setEnabled(false);

                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

                String source = editText.getText().toString();

                if (!source.isEmpty()) {
                    JSONObject json = convertToJson(source, spin.getSelectedItemPosition());

                    TextView output = findViewById(R.id.compile_output);
                    TextView time = findViewById(R.id.time);
                    output.setText("Compiling ...");
                    time.setText("");

                    new Run(json.toString(), output, time, fab).execute();
                } else {
                    Toast.makeText(Editor.this, "Field is Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {

        sharedPreferences = getSharedPreferences("sample", MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("TAG", ""));
        editText.setSelection(editText.getText().length());

        super.onResume();
    }

    @Override
    protected void onPause() {

        if (editText.getText().length() > 0) {

            String s = sharedPreferences.getString(editText.getText().toString(), null);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("TAG", editText.getText().toString());
            editor.apply();

            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);

        }

        super.onPause();
    }

    public JSONObject convertToJson(String source, int id) {

        String languages[] = {"1", "3", "4", "10", "16", "18", "19", "20", "21", "22", "23", "25", "26", "29", "31", "32", "33", "34", "38", "42"};

        JSONObject json = new JSONObject();
        try {
            json.put("source_code", source);
            json.put("language_id", languages[id]);
            json.put("stdin", "");
            json.put("expected_output", "");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void Clear(View v) {

        final EditText editText = findViewById(R.id.edittext);
        editText.setText("");

        final String s;

        if (spin.getSelectedItemPosition() == 0)
            s = "";
        else if (spin.getSelectedItemPosition() == 1)
            s = "";
        else if (spin.getSelectedItemPosition() == 2)
            s = "#include<stdio.h>\nint main()\n{\n    printf(\"Hello, World\");\n    return 0;\n}";
        else if (spin.getSelectedItemPosition() == 3)
            s = "#include<bits/stdc++.h>\nusing namespace std;\nint main()\n{\n    cout<<\"Hello World\";\n}";
        else if (spin.getSelectedItemPosition() == 4)
            s = "";
        else if (spin.getSelectedItemPosition() == 5)
            s = "";
        else if (spin.getSelectedItemPosition() == 6)
            s = "";
        else if (spin.getSelectedItemPosition() == 7)
            s = "";
        else if (spin.getSelectedItemPosition() == 8)
            s = "";
        else if (spin.getSelectedItemPosition() == 9)
            s = "";
        else if (spin.getSelectedItemPosition() == 10)
            s = "";
        else if (spin.getSelectedItemPosition() == 11)
            s = "";
        else if (spin.getSelectedItemPosition() == 12)
            s = "";
        else if (spin.getSelectedItemPosition() == 13)
            s = "";
        else if (spin.getSelectedItemPosition() == 14)
            s = "";
        else if (spin.getSelectedItemPosition() == 15)
            s = "";
        else if (spin.getSelectedItemPosition() == 16)
            s = "";
        else if (spin.getSelectedItemPosition() == 17)
            s = "";
        else if (spin.getSelectedItemPosition() == 18)
            s = "";
        else
            s = getString(R.string.java);

        Snackbar.make(v, "Do you want to add template code?", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editText.setText(s);
                        editText.setSelection(editText.getText().length());
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.save) {

            final AlertDialog.Builder dialog = new AlertDialog.Builder(Editor.this);

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.save, null);
            dialog.setView(view);

            final EditText edit = findViewById(R.id.file_name);

            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    String code = editText.getText().toString();

                    if (!code.trim().equals("")) {

                        try {

                            File path = new File(getFilesDir(), "MatCode");
                            path.mkdir();

                            File file = new File(path, "s2.java");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write(code);
                            writer.close();

                            Toast.makeText(Editor.this, "File Successfully saved", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            });

            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            dialog.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

class Run extends AsyncTask<Void, Void, Void> {

    FabButton fab;
    String data;
    TextView textOut, time;
    String output, t;

    public Run(String data, TextView textOut, TextView time, FabButton fab) {
        this.data = data;
        this.textOut = textOut;
        this.fab = fab;
        this.time = time;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            HttpHelper helper = new HttpHelper();

            output = helper.Post("https://api.judge0.com/submissions?wait=true", data);

            JSONObject json = new JSONObject(output);
            output = json.getString("stdout");
            t = json.getString("time");

            if (output.equals("null")) {
                output = json.getString("compile_output");
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        fab.hideProgressOnComplete(true);
        textOut.setText(output);
        time.setText("Time - " + t + " sec");
        fab.onProgressCompleted();
        fab.setEnabled(true);

        super.onPostExecute(aVoid);
    }
}