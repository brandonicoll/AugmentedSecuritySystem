package augmented.security.asecuritysystem.ui.distance.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.distance;


public class distanceAdapter extends FirebaseRecyclerAdapter<
        distance, distanceAdapter.distanceViewholder> {

    public distanceAdapter(
            @NonNull FirebaseRecyclerOptions<distance> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull distanceViewholder holder,
                     int position, @NonNull distance model)
    {

        holder.range.setText(String.valueOf(model.getRange()));
        holder.time.setText(String.valueOf(model.getDate(model.getTimestamp())));


    }

    @NonNull
    @Override
    public distanceViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.distance_item, parent, false);
        return new distanceAdapter.distanceViewholder(view);
    }

    public static class distanceViewholder
            extends RecyclerView.ViewHolder {
        TextView range,time;
        public distanceViewholder(@NonNull View itemView)
        {
            super(itemView);
            range = itemView.findViewById(R.id.range_history);
            time = itemView.findViewById(R.id.time_history);

        }
    }
}