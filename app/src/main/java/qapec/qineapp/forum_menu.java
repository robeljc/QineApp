package qapec.qineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

public class forum_menu extends AppCompatActivity {
    private static final int REQ_CODE_LIVE = 123;
    //a map from topic to posts
    private HashMap<String, ArrayList<String>> post_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_menu);
        get_posts();
    }

    //gets all recent posts by the user
    private void get_posts() {
        //scan the file form live scanning
        post_map = new HashMap<>();
        try {
            Scanner scan2 = new Scanner(
                    openFileInput(forum_live.FORUM_FILENAME));
            readPostsFromFile(scan2);
        } catch (Exception e) {
            Log.wtf("no new words", e);
        }


    }

    private void readPostsFromFile(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] pieces = line.split(":");
            if (pieces.length >= 2) {
                ArrayList<String> topic_posts = post_map.get(pieces[0]);
                if (topic_posts.isEmpty()){
                    topic_posts = new ArrayList<>();
                }
                topic_posts.add(pieces[1]);
                post_map.put(pieces[0], topic_posts);

            }
        }
    }
    public void about_clicked(View view) {
            Intent about = new Intent(this, About.class);
            startActivity(about);
    }
    public void part_clicked(View view) {
        Intent live = new Intent(this, forum_live.class);
        startActivityForResult(live, REQ_CODE_LIVE);
        update_posts();
    }

    //update recent posts after new participation (needs to be fixeds)
    private void update_posts() {
        get_posts();
        String update = "";
        for (String key: post_map.keySet()){
            update += key;
            ArrayList<String> str = post_map.get(key);
            update += " : \n" + str;
        }
        TextView textView = (TextView) findViewById(R.id.posts_txtview);
        textView.setText(update);

    }

    @Override
    //not really used now
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode ==  REQ_CODE_LIVE && resultCode == RESULT_OK){
            String topic = intent.getStringExtra("topic");
            String post = intent.getStringExtra("post");

            //updates recent post
            TextView textView = (TextView) findViewById(R.id.posts_txtview);
            textView.setText(topic + ":" +  post);
        }

    }


}
