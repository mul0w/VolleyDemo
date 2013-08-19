package fr.endofline.volleydemo.datas.volley;

import android.util.Log;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import fr.endofline.volleydemo.datas.parser.common.XMLParser;

import java.io.ByteArrayInputStream;

/**
 * Created by stef on 19/08/13.
 */
public class XMLGenericRequest<T> extends Request<T> {

    private final CustomListener<T> mListener;
    private final XMLParser<T> mParser;
    private boolean firstResponseDelivered;

    public XMLGenericRequest(int method, String url, XMLParser<T> parser, CustomListener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mParser = parser;
    }

    public XMLGenericRequest(String url, XMLParser<T> parser, CustomListener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, parser, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        T data = null;
        try {
            data = mParser.parse(new ByteArrayInputStream(response.data));
        } catch (Exception e) {
            Log.w("TAG", e.getMessage());
        }
        return Response.success(data, CustomHttpHeaderParser.parseIgnoreCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        if (!firstResponseDelivered) {
            mListener.onResponse(response);
            firstResponseDelivered = true;
        } else {
            mListener.onResponseUpdate(response);
        }
    }

    public interface CustomListener<T> extends Response.Listener<T> {
        public void onResponseUpdate(T t);
    }

}
