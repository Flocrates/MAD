package floris_van_lent_500717249.mad_assignment_2;

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

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.SerieHolder> {

    private List<Task> seriesList;


    public class SerieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year;
        private Task task;

        public SerieHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
        }

        public void bindSerie(Task task) {
            this.task = task;
            title.setText(task.getTitle());
            year.setText(task.getPerson());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), TaskUpdateActivity.class);
            intent.putExtra("id", task.getId());
            view.getContext().startActivity(intent);
        }
    }

    public TasksRecyclerAdapter(List<Task> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public SerieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks_list_row, parent, false);

        return new SerieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SerieHolder holder, int position) {
        Task task = seriesList.get(position);
        holder.bindSerie(task);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}