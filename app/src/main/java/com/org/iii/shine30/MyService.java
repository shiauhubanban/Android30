
package com.org.iii.shine30;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    private NotificationManager nmgr;
    private int i;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        nmgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        timer = new Timer();
        timer.schedule(new MyTask(), 3*1000, 3*1000);
        timer.schedule(new CancelTask(), 20*1000);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            sendNotice();
        }
    }

    private class CancelTask extends TimerTask {
        @Override
        public void run() {
            if (timer != null){
                timer.purge();
                timer.cancel();
                timer = null;
            }
        }
    }

    private void sendNotice(){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ball0)
                        .setAutoCancel(true)
                        .setTicker("你看不到...")
                        .setContentTitle("重要訊息:" + i)
                        .setContentText("期末考成績公布");

        Intent resultIntent = new Intent(this, NoticeActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NoticeActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        Notification notification = mBuilder.build();
        nmgr.notify(7, notification);
        i++;
    }

}