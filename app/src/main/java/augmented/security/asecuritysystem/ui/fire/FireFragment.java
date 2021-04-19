package augmented.security.asecuritysystem.ui.fire;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import augmented.security.asecuritysystem.R;
import augmented.security.asecuritysystem.firebase.data;

public class FireFragment extends Fragment {

    private FireViewModel slideshowViewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private static final String TAG = "MainActivity";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(FireViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        final TextView textView = root.findViewById(R.id.text_slideshow);
        ImageView fireimg = root.findViewById(R.id.fire);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("data");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);
                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;
                textView.setText(String.format("%s", fire));
                if (fire.equals("We're safe! No fire.")) {
                    fireimg.setVisibility(View.INVISIBLE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                }
                else  {
                    fireimg.setVisibility(View.VISIBLE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 36);

                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);
                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;
                textView.setText(String.format("%s", fire));
                if (fire.equals("We're safe! No fire.")) {
                    fireimg.setVisibility(View.INVISIBLE);
                }
                else  {
                    fireimg.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data dataprofile = snapshot.getValue(data.class);
                String fire = dataprofile.fire;
                Integer time = dataprofile.timestamp;
                textView.setText(String.format("%s", fire));
                if (fire.equals("We're safe! No fire.")) {
                    fireimg.setVisibility(View.INVISIBLE);
                }
                else  {
                    fireimg.setVisibility(View.VISIBLE);
                 
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
        createTopic();
        return root;
    }
    public void createTopic() {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("fire")
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