package za.co.kiekerweb.learnnotes;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class shownotes extends AppCompatActivity {
    ArrayList<Note> notelist = new ArrayList<>();
    ArrayList<Note> wronglist  = new ArrayList<>();
    String name = "";
    String pos = "";
    int Score = 0;
    int numberTries = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownotes);

        Bundle bundle = getIntent().getExtras();
        String level = bundle.getString("level");
        get_json(level);
        start();


        final TextView sTextField = (TextView)findViewById(R.id.textView3);
        sTextField.setText("0");



    }
    public void get_json(String level)
    {
        String json;
        try{
            InputStream inputstr = getAssets().open("notes.json");
            int size = inputstr.available();
            byte[] buffer = new byte[size];
            inputstr.read(buffer);
            inputstr.close();

            json = new String(buffer, "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray Jarray = obj.getJSONArray("notes");

            //JSONArray jsonArray = new JSONArray(json);
            System.out.println("Json array is " + Jarray);

            for (int i = 0; i < Jarray.length(); i++)
            {
                JSONObject row = Jarray.getJSONObject(i);

                if (row.getString("level").equals(level))
                {
                    JSONArray notes = row.getJSONArray("data");
                    for (int j = 0; j < notes.length(); j++)
                    {
                        JSONObject note = notes.getJSONObject(j);
                        String p_pos = note.getString("pos");
                        String p_name =  note.getString("name");
                        String p_image = note.getString("image");
                        notelist.add(new Note(p_name,p_image,p_pos));
                    }

                }
            }


        }catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }


    }
    public void start()
    {

        final Note theNote = chooseRandom();
        ImageView initialImage = (ImageView)findViewById(R.id.imageView3);
        String imageSrc = theNote.getImage();
        int drawableId = getResources().getIdentifier(imageSrc, "drawable",getPackageName());
        initialImage.setImageResource(drawableId);

        startCountdown();

        Button submitButton = (Button)findViewById(R.id.button10);

        submitButton.setOnClickListener(new View.OnClickListener(){
            Note currentNote = theNote;
            @Override
            public void onClick(View view)
            {
                verifyNote(currentNote);
                currentNote = chooseRandom();
                changeImage(currentNote);
                numberTries++;
            }
        });


    }
    public void startCountdown()
    {
        final TextView mTextField = (TextView)findViewById(R.id.textView2);
        new CountDownTimer(30000,1000)
        {
            public void onTick(long millisUntilFinished)
            {

                mTextField.setText("Time: "+ millisUntilFinished / 1000);

            }
            public void onFinish(){
                mTextField.setText("ok");
                Intent lastintent = new Intent(shownotes.this,finalScreen.class);
                lastintent.putExtra( "finalScore",Score);
                lastintent.putExtra("numberTries",numberTries);
                startActivity(lastintent);
                finish();
            }
        }.start();
    }
    public Note chooseRandom()
    {
        Random rand = new Random();
        Note randomNote = notelist.get(rand.nextInt(notelist.size()));
        //Toast.makeText(getApplicationContext(),randomNote.getName(), Toast.LENGTH_LONG).show();
        return randomNote;
    }
    public void changeImage(Note note)
    {
        ImageView theImage = (ImageView)findViewById(R.id.imageView3);
        String imagesrc = note.getImage();
        int drawableId = getResources().getIdentifier(imagesrc, "drawable",getPackageName());
        theImage.setImageResource(drawableId);

    }
    public void assignNoteName(View v)
    {

        switch (v.getId())
        {
            case R.id.A:
                name = "A";
                break;
            case R.id.B:
                name = "B";
                break;
            case R.id.C:
                name = "C";
                break;
            case R.id.D:
                name = "C";
                break;
            case R.id.E:
                name = "E";
                break;
            case R.id.F:
                name = "F";
                break;
            case R.id.G:
                name = "G";
                break;
        }

    }

    public boolean verifyNote(Note note)
    {

       //EditText posfield = (EditText)findViewById(R.id.posfield);
       // String pos = posfield.getText().toString();
        String pos = "";
        String pos1 = "";
        String pos2 = "";
        String pos3 = "";

        CheckBox zeroButton = (CheckBox)findViewById(R.id.checkBox0);
        CheckBox firstButton = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox secButton = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox thirdButton = (CheckBox)findViewById(R.id.checkBox3);

        if(firstButton.isChecked())
        {
            pos1 = "1";
        }
        if(secButton.isChecked())
        {
            pos2 = "2";
        }
        if(thirdButton.isChecked())
        {
            pos3 = "3";
        }

        pos = pos1 + pos2 + pos3;

        if (zeroButton.isChecked())
        {
            pos = "0";
        }
        Log.d("posValue", pos);
        if (pos.equals(note.getPos()))
        {
            Log.d("notever", "Notes are equal");
        }
        else
        {
            Log.d("notever", "Notes aren't equal" + pos + " " + note.getPos());
        }
        final TextView sTextField = (TextView)findViewById(R.id.textView3);

        boolean noteSuccess;
        if (name.equals(note.getName()) && pos.equals(note.getPos()))
        {
            noteSuccess = true;
            IncreaseScore(sTextField);
        }
        else
        {
            Log.d("noteVerify", "notes aren't equal: Note - " + note.getName() + " " + note.getPos() + " Selected: " + pos + " " + name);
            noteSuccess = false;
        }
        return noteSuccess;
    }
    public void IncreaseScore( TextView sTextField)
    {
        Score++;
        sTextField.setText("Score: " + Integer.toString(Score));

    }
    public void noteSelect(View v)
    {
        CheckBox firstButton = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox secButton = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox thirdButton = (CheckBox)findViewById(R.id.checkBox3);

        firstButton.setChecked(false);
        secButton.setChecked(false);
        thirdButton.setChecked(false);
    }
    public void noteDeselect(View v)
    {
        CheckBox zeroButton = (CheckBox)findViewById(R.id.checkBox0);
        zeroButton.setChecked(false);
    }

}
