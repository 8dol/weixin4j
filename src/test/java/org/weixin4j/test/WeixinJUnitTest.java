package org.weixin4j.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.message.InputMessage;
import org.weixin4j.message.MediaType;
import org.weixin4j.message.PicList;
import org.weixin4j.message.TemplateMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Yakson
 */
public class WeixinJUnitTest extends WeixinSupport {

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

    @Test
    public void uploadMedia() throws WeixinException {
//        MediaType mediaType = MediaType.Image;
//        File file = new File("/Users/zhangmin/Downloads/8天宅计划通用图片.jpg");
//        //创建请求对象
//        HttpsClient http = new HttpsClient();
//        //上传素材，返回JSON数据包
//        String jsonStr = http.uploadHttps("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + "87fFgvwECZ7KjiUmyyRtQDVa32RUXqR0H_ceQfCgBsjvjx-iF1mzYxrdAfv37E3rORS8DtKds4noVRcMKaLeqtztiRqhD_3GScBl2P1NetnB-1wrZH0G27ORQul2-_jcBKIjAAATCA" + "&type=" + mediaType.toString(), file);
//        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
//        System.out.println(jsonObj);
    }
}
