package com.example.android.inkcue.activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.inkcue.R;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aesher on 7/30/2017.
 */

public class Create_Idea extends AppCompatActivity {




    @BindView(R.id.body)
    EditText BOSS;




    @OnClick(R.id.floatingActionButton)
    public void TEXT_TO_SPEECH()
    {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }


    }


TextView INDEXING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_idea);
        ButterKnife.bind(this);
        INDEXING = (TextView) findViewById(R.id.idea_index);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder= new AlertDialog.Builder(Create_Idea.this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.fragment_create_contributor,null));
                builder.setCancelable(false);
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"THIS",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();

            }
        }; handler.postDelayed(r,500);


        INDEXING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(Create_Idea.this,INDEXING);
                popupMenu.getMenuInflater().inflate(R.menu.create_idea_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        INDEXING.setText(""+item);
                        BOSS.setHint(R.string.HINT);

                        return false;
                    }
                });
                popupMenu.show();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 100: {

                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    BOSS.append(" "+result.get(0));
                }
                break;
            }
        }

    }
}
