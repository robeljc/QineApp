package qapec.qineapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.ArrayList;


public class tasks extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private ArrayList<String> todoarray;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ListView todoview = (ListView) findViewById(R.id.todoview);
        // todoview.setOnItemClickListener((AdapterView.OnItemClickListener) this);    //not sure
        todoarray = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                todoarray
        );
        todoview.setAdapter(adapter);
        todoview.setOnItemLongClickListener(this);

    }


    public void add_clicked(View view) {
        EditText addtodo = (EditText) findViewById(R.id.editText);
        String newtodo = addtodo.getText().toString();
        //handles the case where user inputs an empty string
        if (newtodo.length() > 0) {
            todoarray.add(newtodo);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "New Task Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please enter a task first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    //removes an item from the to do list when the item is long cliked on
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ListView todoview = (ListView) findViewById(R.id.todoview);
        String text = todoview.getItemAtPosition(position).toString();
        todoarray.remove(text);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "'" + text + "' removed", Toast.LENGTH_SHORT).show();
        return true;
    }





}
