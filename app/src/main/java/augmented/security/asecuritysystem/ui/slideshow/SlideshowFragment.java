package augmented.security.asecuritysystem.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.data;
import augmented.security.asecuritysystem.firebase.distance;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FirebaseUser user;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("data");


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);

                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;

                textView.setText(String.format("%s%s", "Fire: ", fire));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);

                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;

                textView.setText(String.format("%s%s", "Fire: ", fire));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);

                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;

                textView.setText(String.format("%s%s", "Fire: ", fire));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });





        return root;
    }
}