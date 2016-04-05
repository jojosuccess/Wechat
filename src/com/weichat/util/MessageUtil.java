package com.weichat.util;

import java.io.IOException;
import java.io.InputStream;  
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;  
import org.dom4j.io.SAXReader;  
  
  
import com.weichat.entity.Message.resp.*; 

@SuppressWarnings("unchecked") 
public class MessageUtil {
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 将解析结果存储在HashMap中  
		//java.io.File file=new java.io.File("/Users/chongguo/Downloads/text.xml");  
        Map<String, String> map = new HashMap<String, String>();  
  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
          
        @SuppressWarnings("unchecked")  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点  
        for (Element e : elementList)  {
            map.put(e.getName(), e.getText());  
        }
  
        // 释放资源  
        //inputStream.close();  
        //inputStream = null;  
  
        return map;  
    }  
	
	private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
	
	public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
	
	public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }
	
}
