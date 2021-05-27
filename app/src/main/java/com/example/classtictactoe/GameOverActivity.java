package com.example.classtictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {

    private RadioButton rbmale, rbfemale;
    private RadioGroup radioGroup;
    private EditText etName;
    private String gender;
    //private String gender;
    private PlayerDataBase playerDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        radioGroup = findViewById(R.id.rb);
        etName = findViewById(R.id.etName);
        rbmale = findViewById(R.id.maleBt);




//        if (rbmale.isChecked()){gender = "male";}
//        else gender = "female";
    }

    public void startover(View view) {
        String name = etName.getText().toString();
        if (name.isEmpty()){name = "noName";}

        //String gender;

//

        int genderId = radioGroup.getCheckedRadioButtonId();
        if (genderId==1){gender="male";}
        else {gender = "female";}

        int score = 10;

        Intent intent1= new Intent();
        intent1.putExtra("name", name);
        intent1.putExtra("gender", gender);
        intent1.putExtra("score", score);
        setResult(RESULT_OK, intent1);
        finish();
    }

    public void save(View view) {
        //view.setEnabled(false);
        // creation of Player instance!
        String name = etName.getText().toString();
        if (name.isEmpty()){name = "noName";}

        if(rbmale.isChecked()){gender = "male";}
        else {gender = "female";}

        Player player = new Player(name, 1 , gender);

        // create DB (Table)
        PlayerDataBase playerDataBase = new PlayerDataBase(this);

        // upload the data---> save!
        playerDataBase.setRecord(player);

        // move to ListActivity
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        //finish();

    }
}