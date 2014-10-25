package com.android.systemui.screenshot;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class TakeScreenshotService extends Service {
    private static final String TAG = "TakeScreenshotService";

    private static GlobalScreenshot mScreenshot;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    final Messenger callback = msg.replyTo;
                    if (mScreenshot == null) {
                        mScreenshot = new GlobalScreenshot(TakeScreenshotService.this);
                    }
                    mScreenshot.takeScreenshot(new Runnable() {
                        @Override public void run() {
                            Message reply = Message.obtain(null, 1);
                            try {
                                callback.send(reply);
                            } catch (RemoteException e) {
                            }
                        }
                    }, msg.arg1 > 0, msg.arg2 > 0);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}