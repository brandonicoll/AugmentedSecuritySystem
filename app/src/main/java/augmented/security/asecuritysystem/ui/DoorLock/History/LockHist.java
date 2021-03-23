package augmented.security.asecuritysystem.ui.DoorLock.History;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.rfid;
import augmented.security.asecuritysystem.ui.distance.history.adapter.distanceAdapter;

public class LockHist extends AppCompatActivity {
    private RecyclerView recyclerView;
    LockAdapter adapter;
    private DatabaseReference mReference;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lockhist_activity);
        setTitle("Door Lock card History");
        user = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference("rfid");
        recyclerView = findViewById(R.id.RVLock);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<rfid> options
                = new FirebaseRecyclerOptions.Builder<rfid>()
                .setQuery(mReference, rfid.class)
                .build();
        adapter = new LockAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}