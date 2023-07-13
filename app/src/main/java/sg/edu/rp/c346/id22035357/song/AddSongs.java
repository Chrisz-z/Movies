package sg.edu.rp.c346.id22035357.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class AddSongs extends AppCompatActivity {

    EditText etTitle,etSinger,etYear ;
    RadioGroup rgStars;
    RadioButton rbStars;
    Button btnInsert,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle =  findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnCancel = findViewById(R.id.btnCancel);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(AddSongs.this);

                int selectedId = rgStars.getCheckedRadioButtonId();
                rbStars = findViewById(selectedId);
                // Insert a song
                db.insertSongs(etTitle.getText().toString(), etSinger.getText().toString(), Integer.parseInt(etYear.getText().toString())
                        , Integer.parseInt(rbStars.getText().toString()));
                Intent intent = new Intent(AddSongs.this, SongList.class);
                intent.putExtra("refresh", true);
                startActivity(intent);
                finish();
            }


        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}