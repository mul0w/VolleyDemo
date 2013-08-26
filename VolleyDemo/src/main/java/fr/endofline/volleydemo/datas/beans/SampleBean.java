package fr.endofline.volleydemo.datas.beans;

/**
 * Created by stef on 19/08/13.
 */
public class SampleBean {

    public String from, to, heading, body;

    @Override
    public String toString() {
        return "SampleBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", heading='" + heading + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
