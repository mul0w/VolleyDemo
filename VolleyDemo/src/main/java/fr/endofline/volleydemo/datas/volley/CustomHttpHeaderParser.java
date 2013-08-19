package fr.endofline.volleydemo.datas.volley;

import com.android.volley.Cache;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created by stef on 19/08/13.
 */
public class CustomHttpHeaderParser extends HttpHeaderParser {

    public static com.android.volley.Cache.Entry parseIgnoringCacheHeaders(com.android.volley.NetworkResponse response) {

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverETag = headers.get("ETag");
        String headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = parseDateAsEpoch(headerValue);
        }

        long now = System.currentTimeMillis();
        final long cacheHitButRefreshed = 15 * 60 * 1000; // in 15 minutes cache will be hit, but also refreshed on background
        final long cacheExpired = 12 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverETag;
        entry.softTtl = now + cacheHitButRefreshed;
        entry.ttl = now + cacheExpired;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }

}
