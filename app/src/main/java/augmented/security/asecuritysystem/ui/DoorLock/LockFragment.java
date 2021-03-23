package augmented.security.asecuritysystem.ui.DoorLock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.rfid;
import augmented.security.asecuritysystem.ui.DoorLock.History.LockHist;
import augmented.security.asecuritysystem.ui.distance.history.DistanceActivity;


public class LockFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference breference;
    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lock, container, false);
        TextView tvID = root.findViewById(R.id.ID);
        TextView tvInfo = root.findViewById(R.id.Info);
        ConstraintLayout CL = (ConstraintLayout) root.findViewById(R.id.BGConstraint);


        user = FirebaseAuth.getInstance().getCurrentUser();
        breference = FirebaseDatabase.getInstance().getReference("rfid");

        FloatingActionButton Histbtn = root.findViewById(R.id.Histbtn);


        breference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rfid rfidprofile = snapshot.getValue(rfid.class);
                long rc = 322320012688L;
                long id = rfidprofile.id;
                String info = rfidprofile.info;
                Integer time = rfidprofile.timestamp;

                tvID.setText(String.format("%s%s", " Id: ", id));
                tvInfo.setText(String.format("%s%s", "Info: ", info));
                if (id == rc){
                        CL.setBackgroundColor(getResources().getColor(R.color.greenc));
                }
                else{
                    CL.setBackgroundColor(getResources().getColor(R.color.redc));

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rfid rfidprofile = snapshot.getValue(rfid.class);

                long id = rfidprofile.id;
                String info = rfidprofile.info;
                Integer time = rfidprofile.timestamp;

                tvID.setText(String.format("%s%s", " Id: ", id));
                tvInfo.setText(String.format("%s%s", "Info: ", info));
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

                tvID.setText(String.format("%s%s", " Id: ", id));
                tvInfo.setText(String.format("%s%s", "Info: ", info));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Histbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LockHist.class));
            }
        });
        return root;
    }
    }
