package floris_van_lent_500717249.mad_assignment_1;

/**
 * Created by Floris on 30-03-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.MyViewHolder> {

    private List<Serie> seriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public SeriesRecyclerAdapter(List<Serie> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Serie serie = seriesList.get(position);
        holder.title.setText(serie.getTitle());
        holder.year.setText(serie.getYear());
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}