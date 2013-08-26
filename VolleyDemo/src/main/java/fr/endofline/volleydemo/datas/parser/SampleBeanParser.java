package fr.endofline.volleydemo.datas.parser;

import fr.endofline.volleydemo.datas.beans.SampleBean;
import fr.endofline.volleydemo.datas.parser.common.XMLParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by stef on 19/08/13.
 */
public class SampleBeanParser extends XMLParser<SampleBean> {

    @Override
    protected SampleBean readDatas(XmlPullParser parser) throws XmlPullParserException, IOException {
        SampleBean bean = new SampleBean();

        // Parse your datas
        // -> Adding fake datas from http://www.w3schools.com/xml/note.xml
        parser.require(XmlPullParser.START_TAG, ns, "note");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("to")) {
                bean.to = readValue(parser, "to");
            } else if (name.equals("from")) {
                bean.from = readValue(parser, "from");
            } else if (name.equals("heading")) {
                bean.heading = readValue(parser, "heading");
            } else if (name.equals("body")) {
                bean.body = readValue(parser, "body");
            }
        }

        return bean;
    }

}
