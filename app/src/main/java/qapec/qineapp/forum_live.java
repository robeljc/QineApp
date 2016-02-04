package qapec.qineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class forum_live extends AppCompatActivity {
    public static final String FORUM_FILENAME = "forum_posts.txt";
    String topic1_str = "Ideal Educational Environment";
    String topic2_str = "Major Challenges in Secondary Education Quality";
    String topic3_str = "Ensuring Secondary Education Quality: Students";
    String topic_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_live);
    }

    public void send_clicked(View view) {
        EditText post = (EditText)findViewById(R.id.posttext);
        String postString = post.getText().toString();
        if (postString.length() > 0 && topic_str.length() > 0) {
            //add to notes
            try {
                PrintStream out = new PrintStream(openFileOutput(FORUM_FILENAME, MODE_APPEND));
                out.println(topic_str + ":" + postString);
                out.close();
                Toast.makeText(this, "Thanks for participating!", Toast.LENGTH_SHORT).show();

                //shutting down this activity
                Intent intent = new Intent();
                intent.putExtra("Topic", topic_str);
                intent.putExtra("Post", topic_str);
                setResult(RESULT_OK, intent);
                finish();


            } catch (IOException e) {
                Log.wtf("adding forum failed", e);
            }
        }else{
            Toast.makeText(this, "Please write a text and choose the appropriate topic.", Toast.LENGTH_SHORT).show();
        }


    }
    //modifies the global variable of topic
    public void topic_clicked(View view) {
        int id = view.getId();
        if (id == R.id.topic1){
            topic_str = topic1_str;
        }else if (id == R.id.topic2){
            topic_str = topic2_str;
        }else if (id == R.id.topic3) {
            topic_str = topic3_str;
        }
    }
}
