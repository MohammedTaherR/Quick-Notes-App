package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    EditText editText;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editTextTextPersonName2);
        listView=findViewById(R.id.listview2);
        button=findViewById(R.id.button2);
        ArrayList<String>listz= new ArrayList<>();
        dbhandler db = new dbhandler(MainActivity.this);
        ArrayAdapter ad= new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,listz);
        listView.setAdapter(ad);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String l = editText.getText().toString();
                        boolean checkdata = db.addlist(l);
                        if (checkdata==true) {

                                    listz.add(l);
                            ad.notifyDataSetChanged();



                            Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Already exist", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String text  = String.valueOf(((TextView) view).getText());
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Do you want to Delete")
                                .setCancelable(true)
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        listz.remove(new String(text));
                                        db.delete(text);
                                        Toast.makeText(MainActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                                        ad.notifyDataSetChanged();


                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                    })

                                .show();

                    }
                });

                Cursor res = db.getdata();

                while(res.moveToNext()){
                    listz.add(res.getString(0));
                }



            }




}