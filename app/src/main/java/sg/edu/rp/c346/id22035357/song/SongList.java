package sg.edu.rp.c346.id22035357.song;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    ListView lvSong;
    ToggleButton toggleBtn;

    ArrayList<Song> songList;

    ArrayAdapter<Song> aa;


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvSong = findViewById(R.id.lvSongs);
        toggleBtn = findViewById(R.id.btnToggle);

        DBHelper dbh = new DBHelper(SongList.this);
        songList = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
        lvSong.setAdapter(aa);
        songList.addAll(dbh.getSongs());
        aa.notifyDataSetChanged();


        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song clickedSong = songList.get(position);
                Intent intent = new Intent(SongList.this, EditSongs.class);
                intent.putExtra("song", clickedSong);
                startActivity(intent);
            }
        });

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songList.clear();
                if (toggleBtn.isChecked()) {
                    songList.addAll(dbh.getSongs());
                } else {
                    songList.addAll(dbh.getSongsByStars(5));
                }
                aa.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent;
        if (id == R.id.addSong) {
            intent = new Intent(SongList.this, AddSongs.class);
        } else {
            intent = new Intent(SongList.this, EditSongs.class);
        }
        startActivity(intent);
        return true;

    }
    private void refreshListView() {
        DBHelper dbh = new DBHelper(SongList.this);
        songList.clear();
        songList.addAll(dbh.getSongs());
        aa.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}

