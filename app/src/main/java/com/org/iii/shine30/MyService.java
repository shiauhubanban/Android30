
package com.org.iii.shine30;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;

public class MyService extends Service {
    private Timer timer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       throw new UnsupportedOperationException("Not yet implemented");

    }
}
