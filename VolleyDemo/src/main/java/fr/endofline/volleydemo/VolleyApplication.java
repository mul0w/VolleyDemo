package fr.endofline.volleydemo;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import fr.endofline.volleydemo.datas.volley.BitmapLruCache;

/**
 * Created by stef on 19/08/13.
 */
public class VolleyApplication extends Application {

    private RequestQueue volleyRequestQueue;
    private ImageLoader volleyImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        volleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        volleyImageLoader = new ImageLoader(volleyRequestQueue, new BitmapLruCache());
        volleyRequestQueue.start();
    }

    public RequestQueue getVolleyRequestQueue() {
        return volleyRequestQueue;
    }

    public ImageLoader getVolleyImageLoader() {
        return volleyImageLoader;
    }

}
