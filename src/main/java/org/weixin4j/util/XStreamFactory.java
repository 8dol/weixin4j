/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.weixin4j.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 将微信POST的流转换为XStream，然后转换为InputMessage对象
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public class XStreamFactory {

    protected static String PREFIX_CDATA = "<![CDATA[";
    protected static String SUFFIX_CDATA = "]]>";

    /**
     * 初始化XStream 可支持某一字段可以加入CDATA标签 如果需要某一字段使用原文
     * 就需要在String类型的text的头加上"<![CDATA["和结尾处加上"]]>"标签， 以供XStream输出时进行识别
     *
     * @param isAddCDATA 是否支持CDATA标签
     * @return XStream
     */
    public static XStream init(boolean isAddCDATA) {
        XStream xstream;
        if (isAddCDATA) {
            xstream = new XStream(new XppDriver() {
                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out) {
                        @Override
                        protected void writeText(QuickWriter writer, String text) {
                            if (!text.startsWith(PREFIX_CDATA)) {
                                text = PREFIX_CDATA + text + SUFFIX_CDATA;
                            }
                            writer.write(text);
                        }
                    };
                }
            });
        } else {
            xstream = new XStream(new DomDriver());
        }
        return xstream;
    }

    /**
     * 将输入流转读取成字符串
     *
     * @param in 输入流
     * @return 字符串
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static String inputStream2String(InputStream in)
            throws UnsupportedEncodingException, IOException {
        if (in == null) {
            return "";
        }

        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }
}
