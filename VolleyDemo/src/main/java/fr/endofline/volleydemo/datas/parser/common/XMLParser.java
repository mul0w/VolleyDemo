package fr.endofline.volleydemo.datas.parser.common;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public abstract class XMLParser<T> {

    protected static final String ns = null;

    public T parse(InputStream is) throws Exception {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            parser.nextTag();
            return readDatas(parser);
        } finally {
            is.close();
        }
    }

    protected abstract T readDatas(XmlPullParser parser) throws XmlPullParserException, IOException;

    protected String readValue(XmlPullParser parser, String name) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, name);
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, name);
        return value;
    }

    protected int readIntValue(XmlPullParser parser, String name) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, name);
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, name);
        return Integer.parseInt(value);
    }

    protected String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    protected void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
