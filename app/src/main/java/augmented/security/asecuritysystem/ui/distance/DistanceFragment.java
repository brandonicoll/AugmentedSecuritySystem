package augmented.security.asecuritysystem.ui.distance;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.distance;
import augmented.security.asecuritysystem.ui.distance.history.DistanceActivity;


public class DistanceFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private static final String TAG = "MainActivity";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        TextView textView = root.findViewById(R.id.text_gallery);
        TextView textView1 = root.findViewById(R.id.textView);
        ExtendedFloatingActionButton extendedFloatingActionButton= root.findViewById(R.id.launch_history);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("distance");
        //userID = distance.getUID;
        createTopic();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                distance distanceprofile = snapshot.getValue(distance.class);

                long range = distanceprofile.range;
                int time = distanceprofile.timestamp;

                textView.setText(String.format("%s%s", "Range: ", range) + "mm");
                if(range ==0) {
                    textView1.setText("No Motion Detected");
                }else{
                    textView1.setText("Motion Detected");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                distance distanceprofile = snapshot.getValue(distance.class);

                long range = distanceprofile.range;
                int time = distanceprofile.timestamp;

                textView.setText(String.format("%s%s", "Range: ", range) + "mm");

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                textView1.setText("No Motion Detected");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                distance distanceprofile = snapshot.getValue(distance.class);

                long range = distanceprofile.range;
                int time = distanceprofile.timestamp;

                textView.setText(String.format("%s%s", "Range: ", range) + "mm");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DistanceActivity.class));
            }
        });

        return root;
    }
    public void createTopic() {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("distance")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = getString(R.string.msg_subscribed);
                            if (!task.isSuccessful()) {
                                msg = getString(R.string.msg_subscribe_failed);
                            }
                            Log.d(TAG, msg);
                        }
                    });
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}