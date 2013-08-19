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

        return bean;
    }

}
