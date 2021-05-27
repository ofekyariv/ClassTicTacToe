package com.example.classtictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final int GAME_OVER = 1;
    private Controller controller;
    private LinearLayout llmain;
    private ImageView imageView;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller();
        llmain = findViewById(R.id.mainBoard);
        imageView = findViewById(R.id.imgPlayer);
        imageView.setImageResource(R.drawable.goodluck);
        imageView.setScaleX(3f);
        imageView.setScaleY(1.5f);
        controller.startGame();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR) {tts.setLanguage(Locale.US);}
            }
        });
    }



    public void select(View v) {
        // להעביר את זהות הכפתור הנלחץ לבקר
        //0-8
        int loc = Integer.parseInt(v.getTag().toString());
        //geting last player and send location
        char player = controller.userSelect(loc);

        // משנה את התמונה על הכפתור
        Button b = (Button) v;
        if (player == 'x') {b.setBackgroundResource(R.drawable.x);}
        else b.setBackgroundResource(R.drawable.o);

        //לנטרל את הכפתור
        b.setEnabled(false);

        //presenting the player
        char corent = controller.getPlayer();
        if (corent=='x'){imageView.setImageResource(R.drawable.x);}
        else imageView.setImageResource(R.drawable.o);

        if (controller.checkforwinner()){

            tts.speak("game over",TextToSpeech.QUEUE_FLUSH,null);
            //maybe later: intent
            Intent intent= new Intent(this,GameOverActivity.class);
            startActivityForResult(intent,GAME_OVER);
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_OVER && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String gender = data.getStringExtra("gender");
            int score = data.getIntExtra("score", 0);

            String answer = "player: " + name + ", gender: " + gender + ", score: " + score ;
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
            newGame();
        }


    }

    public void startGame(View view) {
        newGame();
    }

    public void newGame(){
        imageView.setImageResource(R.drawable.goodluck);
        imageView.setScaleX(3f);
        imageView.setScaleY(1.5f);
        for (int i=0;i<9;i++){
            Button b = llmain.findViewWithTag(String.valueOf(i));
            b.setBackgroundResource(android.R.drawable.btn_default);
            b.setEnabled(true);
        }
        controller.startGame();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.share_item){
            Toast.makeText(this, "share item chosen", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.list_item){
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        }
        return true;
    }
}