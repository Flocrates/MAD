package floris_van_lent_500717249.carecodex;

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

public class EntryRecyclerAdapter extends RecyclerView.Adapter<EntryRecyclerAdapter.SerieHolder> {

    private List<XDS> entriesList;


    public class SerieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView type, value;
        private XDS xds;

        public SerieHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            type = (TextView) view.findViewById(R.id.type);
            value = (TextView) view.findViewById(R.id.value);
        }

        public void bindSerie(XDS xds) {
            this.xds = xds;
            type.setText(xds.getType());
            value.setText(xds.getValue());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), EntryUpdateActivity.class);
            intent.putExtra("id", xds.getId());
            view.getContext().startActivity(intent);
        }
    }

    public EntryRecyclerAdapter(List<XDS> entriesList) {
        this.entriesList = entriesList;
    }

    @Override
    public SerieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_list_row, parent, false);

        return new SerieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SerieHolder holder, int position) {
        XDS XDS = entriesList.get(position);
        holder.bindSerie(XDS);
    }

    @Override
    public int getItemCount() {
        return entriesList.size();
    }
}