package augmented.security.asecuritysystem.ui.DoorLock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.distance;
import augmented.security.asecuritysystem.firebase.rfid;


public class LockFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference breference;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = root.findViewById(R.id.text_home);



        user = FirebaseAuth.getInstance().getCurrentUser();
        breference = FirebaseDatabase.getInstance().getReference("rfid");


        breference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rfid rfidprofile = snapshot.getValue(rfid.class);

                long id = rfidprofile.id;
                String info = rfidprofile.info;
                Integer time = rfidprofile.timestamp;

                textView.setText(String.format("%s%s", "Id: ", id) + "\n" + String.format("%s%s", "Info: ", info));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rfid rfidprofile = snapshot.getValue(rfid.class);

                long id = rfidprofile.id;
                String info = rfidprofile.info;
                Integer time = rfidprofile.timestamp;

                textView.setText(String.format("%s%s", "Id: ", id) + "\n" + String.format("%s%s", "Info: ", info));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rfid rfidprofile = snapshot.getValue(rfid.class);

                long id = rfidprofile.id;
                String info = rfidprofile.info;
                Integer time = rfidprofile.timestamp;

                textView.setText(String.format("%s%s", "Id: ", id) + "\n" + String.format("%s%s", "Info: ", info));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
    }
