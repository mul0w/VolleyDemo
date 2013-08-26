package fr.endofline.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import fr.endofline.volleydemo.datas.beans.SampleBean;
import fr.endofline.volleydemo.datas.parser.SampleBeanParser;
import fr.endofline.volleydemo.datas.volley.XMLGenericRequest;

/*
 * Created by stef on 19/08/13.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDatas();

        NetworkImageView img = (NetworkImageView) findViewById(R.id.volleyImage);
        img.setImageUrl("http://developer.android.com/images/home/android-jellybean.png", ((VolleyApplication)getApplication()).getVolleyImageLoader());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void fetchDatas() {
        XMLGenericRequest<SampleBean> rq = new XMLGenericRequest<SampleBean>("http://www.w3schools.com/xml/note.xml", new SampleBeanParser(),
                new XMLGenericRequest.CustomListener<SampleBean>() {
                    @Override
                    public void onResponse(SampleBean bean) {
                        // Do your stuff on response -> cached datas or new datas if TTL expires
                        ((TextView)findViewById(R.id.volleyXml)).setText(bean.toString());
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
        ((VolleyApplication)getApplication()).getVolleyRequestQueue().add(rq);
    }
    
}
