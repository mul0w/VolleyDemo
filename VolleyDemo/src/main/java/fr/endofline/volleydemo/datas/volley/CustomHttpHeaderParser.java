package fr.endofline.volleydemo.datas.volley;

import com.android.volley.Cache;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: stef
 * Date: 17/07/13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class CustomHttpHeaderParser extends HttpHeaderParser {

    public static com.android.volley.Cache.Entry parseIgnoreCacheHeaders(com.android.volley.NetworkResponse response) {
        long now = System.currentTimeMillis();

        Map<String, String> headers = response.headers;
        long serverDate = 0;
        String serverEtag = null;
        String headerValue;

        headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = parseDateAsEpoch(headerValue);
        }

        serverEtag = headers.get("ETag");

        final long cacheHitButRefreshed = 15 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
        final long cacheExpired = 12 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = ttl;
        entry.serverDate = serverDate;
        entry.responseHeaders = headers;

        return entry;
    }
}
