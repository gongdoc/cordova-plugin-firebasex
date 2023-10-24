package org.apache.cordova.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;
import android.app.Notification;
import android.text.TextUtils;
import android.text.Html;
import android.text.Spanned;
import android.content.ContentResolver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.widget.RemoteViews;
import org.apache.cordova.CallbackContext;
import com.google.firebase.iid.FirebaseInstanceId;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

public class FirebasePluginMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebasePlugin";

    private static String lastId;

    static final String defaultSmallIconName = "notification_icon";
    static final String defaultLargeIconName = "notification_icon_large";

    static final String imageTypeCircle = "circle";
    static final String imageTypeBigPicture = "big_picture";

    /**
     * Get a string from resources without importing the .R package
     *
     * @param name Resource Name
     * @return Resource
     */
    private String getStringResource(String name) {
        return this.getString(
                this.getResources().getIdentifier(
                        name, "string", this.getPackageName()
                )
        );
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String refreshedToken) {
        try{
            super.onNewToken(refreshedToken);
            Log.d(TAG, "Refreshed token: " + refreshedToken);
            FirebasePlugin.sendToken(refreshedToken);
        }catch (Exception e){
            FirebasePlugin.handleExceptionWithoutContext(e);
        }
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Called when message is received.
     * Called IF message is a data message (i.e. NOT sent from Firebase console)
     * OR if message is a notification message (e.g. sent from Firebase console) AND app is in foreground.
     * Notification messages received while app is in background will not be processed by this method;
     * they are handled internally by the OS.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try{
            // [START_EXCLUDE]
            // There are two types of messages data messages and notification messages. Data messages are handled
            // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
            // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
            // is in the foreground. When the app is in the background an automatically generated notification is displayed.
            // When the user taps on the notification they are returned to the app. Messages containing both notification
            // and data payloads are treated as notification messages. The Firebase console always sends notification
            // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
            // [END_EXCLUDE]

            // Pass the message to the receiver manager so any registered receivers can decide to handle it
            boolean wasHandled = FirebasePluginMessageReceiverManager.onMessageReceived(remoteMessage);
            if (wasHandled) {
                Log.d(TAG, "Message was handled by a registered receiver");

                // Don't process the message in this method.
                return;
            }

            if(FirebasePlugin.applicationContext == null){
                FirebasePlugin.applicationContext = this.getApplicationContext();
            }

            // TODO(developer): Handle FCM messages here.
            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            String messageType;
            String title = null;
            String titleLocKey = null;
            String[] titleLocArgs = null;
            String body = null;
            String bodyLocKey = null;
            String[] bodyLocArgs = null;
            String bodyHtml = null;
            String id = null;
            String sound = null;
            String vibrate = null;
            String light = null;
            String color = null;
            String icon = null;
            String channelId = null;
            String visibility = null;
            String priority = null;
            String image = null;
            String imageType = null;
            boolean foregroundNotification = false;
            String flagWakeUp = "";
            String flagPush = "";
            String text = "";
            String wakeUp = "";
            String lights = "";
            Map<String, String> data = remoteMessage.getData();

            if (remoteMessage.getNotification() != null) {
                title = remoteMessage.getNotification().getTitle();
                text = remoteMessage.getNotification().getBody();
                id = remoteMessage.getMessageId();
                // Notification message payload
                // Log.i(TAG, "Received message: notification");
                // messageType = "notification";
                // id = remoteMessage.getMessageId();
                // RemoteMessage.Notification notification = remoteMessage.getNotification();
                // title = notification.getTitle();
                // titleLocKey = notification.getTitleLocalizationKey();
                // titleLocArgs = notification.getTitleLocalizationArgs();
                // body = notification.getBody();
                // bodyLocKey = notification.getBodyLocalizationKey();
                // bodyLocArgs = notification.getBodyLocalizationArgs();
                // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //     channelId = notification.getChannelId();
                // }
                // sound = notification.getSound();
                // color = notification.getColor();
                // icon = notification.getIcon();
                // if (notification.getImageUrl() != null) {
                //     image = notification.getImageUrl().toString();
                // }
                // if (!TextUtils.isEmpty(titleLocKey)) {
                //     int titleId = getResources().getIdentifier(titleLocKey, "string", getPackageName());
                //     title = String.format(getResources().getString(titleId), (Object[])titleLocArgs);
                // }
                // if (!TextUtils.isEmpty(bodyLocKey)) {
                //     int bodyId = getResources().getIdentifier(bodyLocKey, "string", getPackageName());
                //     body = String.format(getResources().getString(bodyId), (Object[])bodyLocArgs);
                // }
            }else{
                Log.i(TAG, "Received message: data");
                messageType = "data";
            }

            if (data != null) {
                // Data message payload
                flagWakeUp = data.get("flagWakeUp");
                flagPush = data.get("flagPush");
                title = data.get("title");
                text = data.get("text");
                id = data.get("id");
                wakeUp = data.get("wakeUp");
                sound = data.get("sound");
                lights = data.get("lights"); //String containing hex ARGB color, miliseconds on, miliseconds off, example: '#FFFF00FF,1000,3000'

                if(data.containsKey("notification_foreground")){
                    foregroundNotification = true;
                }
                if(data.containsKey("notification_title")) title = data.get("notification_title");
                if(data.containsKey("notification_body")) body = data.get("notification_body");
                if(data.containsKey("notification_android_body_html")) bodyHtml = data.get("notification_android_body_html");
                if(data.containsKey("notification_android_channel_id")) channelId = data.get("notification_android_channel_id");
                if(data.containsKey("notification_android_id")) id = data.get("notification_android_id");
                if(data.containsKey("notification_android_sound")) sound = data.get("notification_android_sound");
                if(data.containsKey("notification_android_vibrate")) vibrate = data.get("notification_android_vibrate");
                if(data.containsKey("notification_android_light")) light = data.get("notification_android_light"); //String containing hex ARGB color, miliseconds on, miliseconds off, example: '#FFFF00FF,1000,3000'
                if(data.containsKey("notification_android_color")) color = data.get("notification_android_color");
                if(data.containsKey("notification_android_icon")) icon = data.get("notification_android_icon");
                if(data.containsKey("notification_android_visibility")) visibility = data.get("notification_android_visibility");
                if(data.containsKey("notification_android_priority")) priority = data.get("notification_android_priority");
                if(data.containsKey("notification_android_image")) image = data.get("notification_android_image");
                if(data.containsKey("notification_android_image_type")) imageType = data.get("notification_android_image_type");
            }

            if (TextUtils.isEmpty(id)) {
                Random rand = new Random();
                int n = rand.nextInt(50) + 1;
                id = Integer.toString(n);
            }

            Log.d(TAG, "From: " + remoteMessage.getFrom());
            Log.d(TAG, "Notification Message flagWakeUp: " + flagWakeUp);
            Log.d(TAG, "Notification Message flagPush: " + flagPush);
            Log.d(TAG, "Notification Message id: " + id);
            Log.d(TAG, "Notification Message Title: " + title);
            Log.d(TAG, "Notification Message Body/Text: " + text);
            Log.d(TAG, "Notification Message WakeUp: " + wakeUp);
            Log.d(TAG, "Notification Message Sound: " + sound);
            Log.d(TAG, "Notification Message Lights: " + lights);

            // TODO: Add option to developer to configure if show notification when app on foreground
            Context context = this.getApplicationContext();

            if (wakeUp != null && wakeUp.equals("Y")) {
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                if (!notificationManagerCompat.areNotificationsEnabled()) return;

                boolean showNotification = (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(title));
                if (!showNotification) return;

                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(context, OverlayActivity.class);

                Bundle bundle = new Bundle();
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    bundle.putString(entry.getKey(), entry.getValue());
                }

                PowerManager powerManager = (PowerManager)getApplicationContext().getSystemService(Context.POWER_SERVICE);
                if (powerManager != null && powerManager.isInteractive()) {
                    bundle.putString("screen", "on");
                } else {
                    bundle.putString("screen", "off");
                }

                intent.putExtras(bundle);

                if (flagPush.equals("N")) {
                    try {
                        final AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                        if (audioManager != null) {
                            int ringerMode = audioManager.getRingerMode();
                            if (ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                                //Uri soundPath = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION);
                                Uri soundPath = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/gongdoc.mp3");

                                final int maxVolumeMusic = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                final int volumeMusic = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                                int maxVolumeNotification = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
                                int volumeNotification = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

                                int volume = volumeNotification * maxVolumeMusic / maxVolumeNotification;
                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);

                                final MediaPlayer mediaPlayer = new MediaPlayer();
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.setDataSource(getApplicationContext(), soundPath);
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mediaPlayer.release();
                                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeMusic, 0);
                                    }
                                });
                            }

                            if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                                long[] defaultVibration = new long[] { 0, 280, 250, 280, 250 };
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (vibrator != null && vibrator.hasVibrator()) {
                                    if (android.os.Build.VERSION.SDK_INT >= 26) {
                                        vibrator.vibrate(VibrationEffect.createWaveform(defaultVibration, -1));
                                    } else {
                                        vibrator.vibrate(defaultVibration, -1);
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Log.d(TAG, "Sound file load failed");
                    }
                }

                startActivity(intent);


                // save id
                FirebasePluginMessagingService.lastId = id;
            }

            if (flagPush.equals("Y") && (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(title) || !data.isEmpty())) {
                PushWakeLock.acquireWakeLock(getApplicationContext());

                boolean showNotification = (FirebasePlugin.inBackground() || !FirebasePlugin.hasNotificationsCallback()) && (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(title));
                sendMessage(id, title, text, data, showNotification, sound, lights);

                PushWakeLock.releaseWakeLock();
            }

            if (flagWakeUp.equals("X")) {
                if (id.equals(FirebasePluginMessagingService.lastId)) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setClass(context, OverlayActivity.class);

                    Bundle bundle = new Bundle();
                    for (Map.Entry<String, String> entry : data.entrySet()) {
                        bundle.putString(entry.getKey(), entry.getValue());
                    }
                    intent.putExtras(bundle);

                    startActivity(intent);

                    FirebasePluginMessagingService.lastId = "";
                }

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    notificationManager.cancel(id.hashCode());
                }

                return;
            }
        }catch (Exception e){
            FirebasePlugin.handleExceptionWithoutContext(e);
        }
    }
                            
    private void sendMessage(String id, String title, String messageBody, Map<String, String> data, boolean showNotification, String sound, String lights) {
        Bundle bundle = new Bundle();
        for (String key : data.keySet()) {
            bundle.putString(key, data.get(key));
        }

        if (showNotification) {
            Intent intent;
            PendingIntent pendingIntent;
            final int flag = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;  // Only add on platform levels that support FLAG_MUTABLE

            if(getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.S && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                intent = new Intent(this, OnNotificationReceiverActivity.class);
                intent.putExtras(bundle);
                pendingIntent = PendingIntent.getActivity(this, id.hashCode(), intent, flag);
            }else{
                intent = new Intent(this, OnNotificationOpenReceiver.class);
                intent.putExtras(bundle);
                pendingIntent = PendingIntent.getBroadcast(this, id.hashCode(), intent, flag);
            }

            String groupId = getPackageName() + ".NOTIFICATIONS";

            String channelId = this.getStringResource("default_notification_channel_id");
            String channelName = this.getStringResource("default_notification_channel_name");
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
            /*
            notificationBuilder
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            int resID = getResources().getIdentifier("ic_notification", "drawable", getPackageName());
            if (resID != 0) {
                notificationBuilder.setSmallIcon(resID);
            } else {
                notificationBuilder.setSmallIcon(getApplicationInfo().icon);
            }
            */

            int contentViewId = getResources().getIdentifier("notification", "layout", getPackageName());
            RemoteViews contentView = new RemoteViews(getPackageName(), contentViewId);

            int bigContentViewId = getResources().getIdentifier("notification_expanded", "layout", getPackageName());
            RemoteViews bigContentView = new RemoteViews(getPackageName(), bigContentViewId);

            int titleId = getResources().getIdentifier("notificationTitle", "id", getPackageName());
            int contentId = getResources().getIdentifier("notificationContent", "id", getPackageName());

            if (bundle.getString("type").equals("register")) {
                String titleMessage = bundle.getString("workAddress");
                String contentMessage = bundle.getString("workType") + "(" + bundle.getString("workEquipments") + ") - " + bundle.getString("workDate");
                contentView.setTextViewText(titleId, titleMessage);
                contentView.setTextViewText(contentId, contentMessage);
            } else {
                contentView.setTextViewText(titleId, title);
                contentView.setTextViewText(contentId, messageBody);
            }

            bigContentView.setTextViewText(titleId, title);
            bigContentView.setTextViewText(contentId, messageBody);

            

            notificationBuilder
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setGroup(groupId)
                    .setGroupSummary(true)
                    .setCustomContentView(contentView)
                    .setCustomBigContentView(bigContentView)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            int resID = getResources().getIdentifier("ic_notification", "drawable", getPackageName());
            if (resID != 0) {
                notificationBuilder.setSmallIcon(resID);
            } else {
                notificationBuilder.setSmallIcon(getApplicationInfo().icon);
            }

            if (lights != null) {
                try {
                    String[] lightsComponents = lights.replaceAll("\\s", "").split(",");
                    if (lightsComponents.length == 3) {
                        int lightArgb = Color.parseColor(lightsComponents[0]);
                        int lightOnMs = Integer.parseInt(lightsComponents[1]);
                        int lightOffMs = Integer.parseInt(lightsComponents[2]);

                        notificationBuilder.setLights(lightArgb, lightOnMs, lightOffMs);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "Lights set failed");
                }
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                int accentID = getResources().getIdentifier("primary", "color", getPackageName());
                notificationBuilder.setColor(getResources().getColor(accentID, null));
            }

            Uri soundPath = defaultSoundUri;
            if (sound != null) {
                soundPath = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/res/raw/gongdoc.mp3");
                notificationBuilder.setSound(soundPath);
            } else {
                Log.d(TAG, "Sound was null ");
                notificationBuilder.setDefaults(Notification.DEFAULT_SOUND);
            }

            long[] defaultVibration = new long[] { 0, 280, 250, 280, 250 };
            AudioManager audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                int ringerMode = audioManager.getRingerMode();
                if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                    notificationBuilder.setVibrate(defaultVibration);
                }
            }

            Notification notification = notificationBuilder.build();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int iconID = android.R.id.icon;
                int notiID = getResources().getIdentifier("ic_notification", "drawable", getPackageName());
                if (notification.contentView != null) {
                    notification.contentView.setImageViewResource(iconID, notiID);
                }
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                // Since android Oreo notification channel is needed.
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);

                    AudioAttributes attributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build();
                    if (sound != null) {
                        channel.setSound(soundPath, attributes);
                    } else {
                        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        channel.setSound(uri, attributes);
                    }

                    notificationManager.createNotificationChannel(channel);
                }

                if (android.os.Build.VERSION.SDK_INT >= 26) {
                    if (audioManager != null) {
                        int ringerMode = audioManager.getRingerMode();
                        if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                            if (channel.shouldVibrate()) {
                                channel.setVibrationPattern(defaultVibration);
                            }
                        }
                    }
                }

                notificationManager.notify(id.hashCode(), notification);
            }
        } else {
            bundle.putBoolean("tap", false);
            bundle.putString("title", title);
            bundle.putString("body", messageBody);
            FirebasePlugin.sendMessage(bundle, this.getApplicationContext());
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {

        if (bitmap == null) {
            return null;
        }

        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        float cx = bitmap.getWidth() / 2;
        float cy = bitmap.getHeight() / 2;
        float radius = cx < cy ? cx : cy;
        canvas.drawCircle(cx, cy, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    private Spanned fromHtml(String source) {
        if (source != null)
            return Html.fromHtml(source);
        else
            return null;
    }

    private void putKVInBundle(String k, String v, Bundle b){
        if(v != null && !b.containsKey(k)){
            b.putString(k, v);
        }
    }
}
