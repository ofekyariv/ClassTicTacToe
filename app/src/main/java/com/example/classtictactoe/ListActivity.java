package com.example.classtictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ListView listView;
    private ArrayList<Player> players;
    private PlayerAdapter adapter;
    private PlayerDataBase playerDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listView);

        //create DB instance;
        playerDataBase  = new PlayerDataBase(this);

        //pull data to Array
        players = PlayerDataBase.getPlayers();
        //players = playerDataBase.getAllRecords();
        adapter = new PlayerAdapter(this, players);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(this);



        //Toast!!! of list length.
        //Toast.makeText(this, " "+ players.size(), Toast.LENGTH_SHORT).show();


  }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("delete player");
        alert.setMessage("are you sure you want to delete player?");
        alert.setIcon(R.drawable.x);
        alert.setCancelable(false);

        alert.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
              //do something
                playerDataBase.deletePlayerByRow(players.get(position).getId());
                PlayerDataBase.getPlayers().remove(position);
                adapter.notifyDataSetChanged();
                dialogInterface.dismiss();

            }
        });



        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        alert.create();
        alert.show();

        //we want to... delete player from db

        // deleted from list

        //players.remove(position);
        //notify listview adapter
        adapter.notifyDataSetChanged();
        return true;
    }
}
