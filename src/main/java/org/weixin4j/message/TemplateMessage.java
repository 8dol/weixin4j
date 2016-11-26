package org.weixin4j.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangmin on 2016/11/26.
 */
public class TemplateMessage {
    /**
     * 模板ID
     */
    private String TemplateId;

    private String Url;

    private Map<String, Data> key2DataMap = new HashMap<>();

    class Data {
        private String value;

        private String color;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public String getTemplateId() {
        return TemplateId;
    }

    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void addData(String key, String value, String color) {
        Data data = new Data();
        data.value = value;
        data.color = color;

        key2DataMap.put(key, data);
    }

    public Map<String, Data> getData() {
        return key2DataMap;
    }
}
