package augmented.security.asecuritysystem.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;

import augmented.security.asecuritysystem.MainActivity;
import augmented.security.asecuritysystem.R;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {

        public static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onNewToken(String refreshToken) {
        super.onNewToken(refreshToken);
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
     //   sendRegistrationToServer(refreshToken);

    }

    private void sendRegistrationToServer(String refreshedToken) {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        try {
            // TODO(developer): Handle FCM messages here.
            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.d(TAG, "From: " + remoteMessage.getFrom());

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
                //notification channel and manager code
                int notifyID = 1;
                String CHANNEL_ID = "my_channel_02";
                CharSequence name = "distance";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                if (remoteMessage.getData() != null) {
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= 26) {

                        if (getApplicationContext() != null) {
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            PendingIntent pendingIntent = PendingIntent.getActivity(this, notifyID, intent,
                                    PendingIntent.FLAG_ONE_SHOT);
                            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                            manager.createNotificationChannel(channel);
                            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                                    .setSmallIcon(R.drawable.fire_png_transparent)
                                    .setContentTitle(remoteMessage.getData().get("title"))
                                    .setContentText(remoteMessage.getData().get("text"))
                                    .setAutoCancel(true)
                                    .setShowWhen(true)
                                  .setContentIntent(pendingIntent)
                                    .build();
                            manager.notify(notifyID, notification);
                        }
                    }
                }

                if (/* Check if data needs to be processed by long running job */ true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    handleNow();
                }

            }

            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            }
        }catch (NullPointerException exception)
        {
            exception.printStackTrace();
        }
    }

    private void scheduleJob() {
        // [START dispatch_job]
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();
        WorkManager.getInstance().beginWith(work).enqueue();
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }


}
