package fr.endofline.volleydemo;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by stef on 19/08/13.
 */
public class VolleyApplication extends Application {

    private RequestQueue volleyRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        volleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public RequestQueue getVolleyRequestQueue() {
        return volleyRequestQueue;
    }

    @Override
    public void onTerminate() {
        volleyRequestQueue.stop();
        super.onTerminate();
    }

}
