package floris_van_lent_500717249.mad_assignment_1;

/**
 * Created by Floris on 30-03-2017.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.SerieHolder> {

    private List<Serie> seriesList;


    public class SerieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year;
        private Serie serie;

        public SerieHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
        }

        public void bindSerie(Serie serie) {
            this.serie = serie;
            title.setText(serie.getTitle());
            year.setText(serie.getYear());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SerieUpdateActivity.class);
            intent.putExtra("id", serie.getId());
            view.getContext().startActivity(intent);
        }
    }

    public SeriesRecyclerAdapter(List<Serie> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public SerieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_row, parent, false);

        return new SerieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SerieHolder holder, int position) {
        Serie serie = seriesList.get(position);
        holder.bindSerie(serie);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}