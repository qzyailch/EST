package com.qian.ess.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * Created by Administrator on 2018/8/7 0007.
 * <p>
 * xml与bean转换
 */

public class XStreamXmlUtils {

    /**
     * XML转Bean对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        XStream xstream = new XStream();
        xstream.registerConverter(new DateConverter());
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(clazz);
        xstream.setClassLoader(clazz.getClassLoader());
        return (T) xstream.fromXML(xml.replaceAll("^\ufeff", ""));
    }

    /**
     * Bean对象转XML
     */
    public static String beanToXml(Object bean) {
        //return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + new XStream().toXML(bean);
        XStream xstream = new XStream();
        xstream.registerConverter(new DateConverter());
        xstream.autodetectAnnotations(true);
        return xstream.toXML(bean);
    }

    /**
     * java 转换成xml
     *
     * @param obj 对象实例
     * @return String xml字符串
     * @Title: toXml
     * @Description: TODO
     */
    public static String toXml(Object obj) {
        XStream xstream = new XStream();
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
    }

    /**
     * 将传入xml文本转换成Java对象
     *
     * @param xmlStr
     * @param cls    xml对应的class类
     * @return T   xml对应的class类的实例对象
     * <p>
     * 调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     * @Title: toBean
     * @Description: TODO
     */
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = new XStream() {//忽略xml中的未知节点
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public boolean shouldSerializeMember(Class definedIn,
                                                         String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xstream.processAnnotations(cls);
        @SuppressWarnings("unchecked")
        T obj = (T) xstream.fromXML(xmlStr);
        return obj;
    }
}
