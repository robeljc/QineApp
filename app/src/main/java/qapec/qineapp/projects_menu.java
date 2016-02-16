package qapec.qineapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class projects_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_menu);
    }

    public void subject_clicked(View view) {
        ImageButton button = (ImageButton) view;
        String tag = button.getTag().toString();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent(this, about_project.class);
            intent.putExtra("subject", tag);
            startActivity(intent);
        }else{
            //lanscape mode
            aboutProjectFragment frag =   (aboutProjectFragment)
                    getFragmentManager().findFragmentById(R.id.pro_menu);
                frag.setSubject(tag);
        }


    }
}
