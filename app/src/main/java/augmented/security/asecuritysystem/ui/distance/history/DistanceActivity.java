package augmented.security.asecuritysystem.ui.distance.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.distance;
import augmented.security.asecuritysystem.ui.distance.history.adapter.distanceAdapter;

public class DistanceActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    distanceAdapter adapter;
    private DatabaseReference mReference;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        setTitle("Proximity History");
        user = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference("distance");
        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<distance> options
                = new FirebaseRecyclerOptions.Builder<distance>()
                .setQuery(mReference, distance.class)
                .build();
        adapter = new distanceAdapter(options);
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
