package sg.edu.rp.c346.id22035357.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class EditMovies extends AppCompatActivity {

    EditText etMovieID,etTitle, etGenre,etYear ;
    Spinner spnRating;
    Button btnUpdate,btnDelete,btnCancel;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etMovieID = findViewById(R.id.etmovieID);
        etTitle =  findViewById(R.id.etMovie);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRating = findViewById(R.id.spnRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_ratings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(adapter);



        Intent i = getIntent();
        Movie obj = i.getParcelableExtra("song");
        etMovieID.setText(String.valueOf(obj.getId()));
        etTitle.setText(obj.getTitle());
        etGenre.setText(obj.getGenre());
        etYear.setText(String.valueOf(obj.getYears()));
        etMovieID.setEnabled(false);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovies.this);
                dbh.deleteMovie(obj.getId());

                finish();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovies.this);
                obj.setTitle(etTitle.getText().toString());
                obj.setGenre(etGenre.getText().toString());
                obj.setYears(Integer.parseInt(etYear.getText().toString()));
                obj.setRating((spnRating.getSelectedItem().toString()));
                dbh.updateMovie(obj);
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