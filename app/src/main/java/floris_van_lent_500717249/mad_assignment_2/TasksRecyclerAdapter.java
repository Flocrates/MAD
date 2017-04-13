package floris_van_lent_500717249.mad_assignment_2;

/**
 * Created by Floris on 30-03-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.SerieHolder> {

    private List<Task> seriesList;
    private Context context;

    private final String USER_1_NAME = "Floris";
    private final String USER_2_NAME = "Marco";

    public class SerieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, person, deadline;
        public ImageView personIcon;
        public CheckBox done;
        private Task task;
        final Drawable USER_1_ICON = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ironman, null);
        final Drawable USER_2_ICON = ResourcesCompat.getDrawable(context.getResources(), R.drawable.spiderman, null);
        final Drawable USER_3_ICON = ResourcesCompat.getDrawable(context.getResources(), R.drawable.joker, null);

        public SerieHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            personIcon = (ImageView) view.findViewById(R.id.imageView);
            done = (CheckBox) view.findViewById(R.id.doneCheckBox);
            title = (TextView) view.findViewById(R.id.titleTextView);
            person = (TextView) view.findViewById(R.id.nameTextView);
            deadline = (TextView) view.findViewById(R.id.deadlineTextView);
        }

        // Sets the right icon for the right person
        public void bindTask(Task task) {
            this.task = task;

            String name = task.getPerson();

            title.setText(task.getTitle());
            person.setText(name);
            deadline.setText(task.getDeadline());

            if (task.getDone().equals("1")) {
                done.setChecked(true);
            }

            if (name.equals(USER_1_NAME)) {
                personIcon.setImageDrawable(USER_1_ICON);
            } else if (name.equals(USER_2_NAME)) {
                personIcon.setImageDrawable(USER_2_ICON);
            } else {
                personIcon.setImageDrawable(USER_3_ICON);
            }
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), TaskUpdateActivity.class);
            intent.putExtra("id", task.getId());
            view.getContext().startActivity(intent);
        }
    }

    public TasksRecyclerAdapter(List<Task> seriesList, Context context) {
        this.seriesList = seriesList;
        this.context = context;
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
        holder.bindTask(task);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}