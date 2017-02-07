package org.weixin4j;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.weixin4j.message.InputMessage;
import org.weixin4j.pool.JaxbUnmarshallerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by zhangmin on 2016/12/1.
 */
public class Test {
    private static JAXBContext context;

    private static KeyedObjectPool unmarPool = new GenericKeyedObjectPool(new JaxbUnmarshallerFactory());


    static {
        try {
            context = JAXBContext.newInstance(InputMessage.class);
        } catch (JAXBException e) {
        }
    }

    private static InputMessage convert(String inputXml) {
        Unmarshaller unmarshaller = null;
        InputMessage inputMsg = null;
        try {
            unmarshaller = (Unmarshaller) unmarPool.borrowObject(null);
//            ThreadContext.get().addTimeRecord("结束转换节点1");

            inputMsg = (InputMessage) unmarshaller.unmarshal(new StringReader(inputXml));

//            ThreadContext.get().addTimeRecord("结束转换节点2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                unmarPool.returnObject(null, unmarshaller);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return inputMsg;
        }
    }

    public static void main(String[] args) {
        String xml = "<xml><ToUserName><![CDATA[gh_f6c69448fb77]]></ToUserName>\n" +
                "<FromUserName><![CDATA[olJz7ji_x5Ty5YwEsp2VBWZwssXk]]></FromUserName>\n" +
                "<CreateTime>1480585614</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[VIEW]]></Event>\n" +
                "<EventKey><![CDATA[https://m.8dol.com/api/weixin/bind?state=1]]></EventKey>\n" +
                "<MenuId>411228295</MenuId>\n" +
                "</xml>";

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int j = 0; j < 1000; j++) {
                        long beginTime = System.currentTimeMillis();
//                            Unmarshaller unmarshaller = context.createUnmarshaller();
                        convert(xml);
                        long cost = System.currentTimeMillis() - beginTime;
                        if (cost > 50) {
                            System.out.println("=====" + cost);
                        }
                    }

                    System.out.println("=====+" + (System.currentTimeMillis()-start));
                }
            });
            thread.start();
        }
    }
}
