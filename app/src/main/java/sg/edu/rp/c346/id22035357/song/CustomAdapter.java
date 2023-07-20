package sg.edu.rp.c346.id22035357.song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {

    Context parent_context;
    int layout_id;
    ArrayList<Song> List;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super (context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.List = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);

        // Obtain the Android Version information based on the position
        Song currentVersion = List.get(position);

        // Set values to the TextView to display the corresponding information
        int stars = currentVersion.getStars();
        int years = currentVersion.getYears();
        String star = "";
        for(int i = 0 ; i < stars; i++){
            star += "* ";
        }


        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(years));
        tvStars.setText(star);
        tvSinger.setText(currentVersion.getSingers());

        return rowView;
    }


}

