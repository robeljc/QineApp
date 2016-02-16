package qapec.qineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Scanner;

public class about_project extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_about_project);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        setupSubject(subject);

    }

    private void setupSubject(String subject) {

        //set the subject name
        TextView name = (TextView)findViewById(R.id.subjectName);
        name.setText(subject.toUpperCase());

        //set the subject image
        ImageView picView = (ImageView) findViewById(R.id.subjectPic);
        int subid = getResources().getIdentifier(subject.toLowerCase(), "drawable", getPackageName());
        picView.setImageResource(subid);

        //set the details text
        int fileId = getResources().getIdentifier(subject.toLowerCase(), "raw", getPackageName());
        Scanner scan = new Scanner (getResources().openRawResource(fileId));
        String details = "";
        while(scan.hasNextLine()){
                details += scan.nextLine();
        }
        TextView detView = (TextView)findViewById(R.id.details);
        detView.setText(details);



    }
}
