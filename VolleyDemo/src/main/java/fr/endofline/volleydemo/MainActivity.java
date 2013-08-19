package fr.endofline.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import fr.endofline.volleydemo.datas.beans.SampleBean;
import fr.endofline.volleydemo.datas.parser.SampleBeanParser;
import fr.endofline.volleydemo.datas.volley.XMLGenericRequest;

public class MainActivity extends Activity {

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = ((VolleyApplication)getApplication()).getVolleyRequestQueue();
    }

    @Override
    protected void onResume() {
        super.onResume();

        XMLGenericRequest<SampleBean> rq = new XMLGenericRequest<SampleBean>("CALLED_URL", new SampleBeanParser(),
                new XMLGenericRequest.CustomListener<SampleBean>() {
                    @Override
                    public void onResponse(SampleBean bean) {
                        // Do your stuff on response -> cached datas or new datas if TTL expires
                    }

                    @Override
                    public void onResponseUpdate(SampleBean bean) {
                        // Do your stuff on response background update -> softTTL expired
                        // Here we simply update like if we received the first response
                        onResponse(bean);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Handle error
                    }
                });
        rq.setTag(this);
        mRequestQueue.add(rq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
