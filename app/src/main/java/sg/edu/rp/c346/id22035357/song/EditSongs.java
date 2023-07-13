package sg.edu.rp.c346.id22035357.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class EditSongs extends AppCompatActivity {

    EditText etSongID,etTitle,etSinger,etYear ;
    RadioGroup rgStars;
    RadioButton rbStars;
    Button btnUpdate,btnDelete,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etSongID = findViewById(R.id.etSongID);
        etTitle =  findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        Song obj = i.getParcelableExtra("song");
        etSongID.setText(String.valueOf(obj.getId()));
        etTitle.setText(obj.getTitle());
        etSinger.setText(obj.getSingers());
        etYear.setText(String.valueOf(obj.getYears()));
        etSongID.setEnabled(false);



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSongs.this);
                dbh.deleteSong(obj.getId());

                finish();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSongs.this);
                obj.setTitle(etTitle.getText().toString());
                obj.setSingers(etSinger.getText().toString());
                obj.setYears(Integer.parseInt(etYear.getText().toString()));
                int selectedId = rgStars.getCheckedRadioButtonId();
                rbStars = findViewById(selectedId);
                obj.setStars(Integer.parseInt(rbStars.getText().toString()));
                dbh.updateNote(obj);
                dbh.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}