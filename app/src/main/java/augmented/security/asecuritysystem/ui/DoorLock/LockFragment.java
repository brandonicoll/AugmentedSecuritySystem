package augmented.security.asecuritysystem.ui.DoorLock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.userdata;

public class LockFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        TextView textView = root.findViewById(R.id.text_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("distance");


        reference.child("-MUjfQvJr9V4tXsTmwQd").addListenerForSingleValueEvent(new ValueEventListener() {
            //Sets the textview for the greeting message
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                userdata userprofile = snapshot.getValue(userdata.class);

                long range = userprofile.range;
                int time = userprofile.timestamp;

                textView.setText(String.format("%s%s", "Range: ", range));

            }
            //Displays an error message if you cancel the onCreate
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
    }
