package com.example.manish.flyinffish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameOverActivity extends AppCompatActivity {

    private Button StartGameAgain;
    private TextView Displayscore;
    private TextView AboutUs;
    String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        StartGameAgain=(Button)findViewById(R.id.play_againButton);
        Displayscore=(TextView)findViewById(R.id.gamescore);
        AboutUs=(TextView)findViewById(R.id.aboutUs);

        score=getIntent().getExtras().get("score").toString();



        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
        Displayscore.setText("Your Score = "+score);
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutUs=new Intent(GameOverActivity.this,com.example.manish.flyinffish.AboutUs.class);
                startActivity(aboutUs);
            }
        });
    }

}
