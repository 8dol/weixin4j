package org.weixin4j.test;

import org.junit.Test;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.message.InputMessage;
import org.weixin4j.message.PicList;
import org.weixin4j.message.TemplateMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Yakson
 */
public class WeixinJUnitTest {

    public WeixinJUnitTest() {
    }

    @Test
    public void sendTemplateMessage() throws WeixinException {
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTemplateId("6g0No_XshO7JclisNSpsWusODNof7FRLkSRarfXQyV0");
        templateMessage.setUrl("http://wx.8dol.com/wechat/test/bind_weixin.php?state=1,");
        templateMessage.addData("first", "8天在线送钱来啦", "#173177");
        templateMessage.addData("keyword1", "8天组合红包", "#FF4500");

        Weixin weixin = new Weixin();
        weixin.sendTemplateMessage("aaa", templateMessage);
    }
}
