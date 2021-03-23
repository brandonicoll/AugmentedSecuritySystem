package augmented.security.asecuritysystem.ui.DoorLock.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.rfid;


public class LockAdapter extends FirebaseRecyclerAdapter<rfid, LockAdapter.LviewH> {

    public LockAdapter(@NonNull FirebaseRecyclerOptions<rfid> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LockAdapter.LviewH holder, int position, @NonNull rfid model) {

        holder.id.setText(String.valueOf(model.getId()));
        holder.info.setText(String.valueOf(model.getInfo()));


    }

    @NonNull
    @Override
    public LockAdapter.LviewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rfid_item, parent, false);
        return new LockAdapter.LviewH(view);
    }

    public static class LviewH extends RecyclerView.ViewHolder {
        TextView id,info;
        public LviewH(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.ID_history);
            info = itemView.findViewById(R.id.Info_history);

        }
    }
}