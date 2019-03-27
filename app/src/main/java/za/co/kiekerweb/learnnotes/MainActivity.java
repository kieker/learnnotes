package za.co.kiekerweb.learnnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public String set_level(View v)
    {
        String level = "beginner";
        switch (v.getId()) {
            case R.id.beginner:
                level = "beginner";
                break;
            case R.id.advanced:
                level = "advanced";
                break;
        }
        return level;
    }

    public void passData(View view)
    {
        Intent passdata_intent = new Intent(this,shownotes.class);
        String level = set_level(view);
        passdata_intent.putExtra("level",  level);
        startActivity(passdata_intent);

    }

}
