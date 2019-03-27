package za.co.kiekerweb.learnnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class finalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);
        Bundle final_bundle = getIntent().getExtras();

        int score = final_bundle.getInt("finalScore");
        double score_calc = (double) score;

        int numberTries = final_bundle.getInt("numberTries");
        double numberTries_calc = (double) numberTries;

        double percentage = (score_calc/numberTries_calc)*100.0;
        String encouragemessage;
        if (percentage >= 80)
        {
            encouragemessage = "Great Job";
        }
        else if (percentage >= 60)
        {
            encouragemessage = "You did okay, try memorizing those notes!";
        }
        else
        {
            encouragemessage = "Not good enough, keep trying harder";
        }
        TextView textView = (TextView)findViewById(R.id.textView4);
        TextView percentageView = (TextView)findViewById(R.id.textView5);
        TextView triesView = (TextView)findViewById(R.id.textView6);
        TextView encourageView = (TextView)findViewById(R.id.textView7);
        percentageView.setText("That's " + Double.toString(Math.round(percentage)) + "%");
        textView.setText("Score: " + Integer.toString(score));
        triesView.setText("Number of questions answered: " + Integer.toString(numberTries));


        encourageView.setText(encouragemessage);
    }
}
