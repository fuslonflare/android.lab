package com.android.labo;

import android.app.Application;

import com.android.labo.enums.Constants;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class MainApplication extends Application {
    private static Socket mSocket;

    {
        try {
            String url = Constants.URL;
            mSocket = IO.socket(url);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Socket getSocket() {
        return mSocket;
    }
}
