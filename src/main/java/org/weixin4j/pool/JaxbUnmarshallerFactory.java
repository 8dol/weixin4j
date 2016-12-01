package org.weixin4j.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.weixin4j.message.InputMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Created by zhangmin on 2016/12/1.
 */
@Slf4j
public class JaxbUnmarshallerFactory implements KeyedPoolableObjectFactory {
    private static JAXBContext context;

    static {
        try {
            log.info("创建JAXBContext开始");
            context = JAXBContext.newInstance(InputMessage.class);
        } catch (JAXBException e) {
            log.error("创建JAXBContext失败", e);
        }
    }

    @Override
    public Object makeObject(Object o) throws Exception {
        return context.createUnmarshaller();
    }

    @Override
    public void destroyObject(Object o, Object o1) throws Exception {

    }

    @Override
    public boolean validateObject(Object o, Object o1) {
        return true;
    }

    @Override
    public void activateObject(Object o, Object o1) throws Exception {

    }

    @Override
    public void passivateObject(Object o, Object o1) throws Exception {

    }
}
