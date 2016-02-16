package qapec.qineapp;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class aboutProjectFragment extends Fragment {


    public aboutProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_project, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //just copy and paste from about project

        super.onActivityCreated(savedInstanceState);
        Activity myact = getActivity();

        Intent intent = myact.getIntent();
        String subject = intent.getStringExtra("subject");
        if (subject == null) {
            subject = "biology";
        }
        setSubject(subject);

    }

    public void setSubject(String subject) {
        Activity myact = getActivity();
        //set the subject name
        TextView name = (TextView)myact.findViewById(R.id.subjectName);
        name.setText(subject.toUpperCase());

        //set the subject image
        ImageView picView = (ImageView) myact.findViewById(R.id.subjectPic);
        int subid = getResources().getIdentifier(subject.toLowerCase(), "drawable", myact.getPackageName());
        picView.setImageResource(subid);

        //set the details text
        int fileId = getResources().getIdentifier(subject.toLowerCase(), "raw", myact.getPackageName());
        Scanner scan = new Scanner (getResources().openRawResource(fileId));
        String details = "";
        while(scan.hasNextLine()){
            details += scan.nextLine();
        }
        TextView detView = (TextView)myact.findViewById(R.id.details);
        detView.setText(details);
    }




}
