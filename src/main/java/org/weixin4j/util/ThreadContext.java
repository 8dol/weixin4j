package org.weixin4j.util;

import lombok.extern.slf4j.Slf4j;
import org.weixin4j.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmin on 2016/11/30.
 */
@Slf4j
public class ThreadContext {
    private static int TRACE_TIMEOUT = Configuration.getIntProperty("weixin4j.trace.timeout", 2000);

    private List<String> timeRecordList = new ArrayList<>(8);

    private long beginTime;

    private long lastTime;

    private String inXml;

    private String outXml;

    private static ThreadLocal<ThreadContext> threadLocal = new ThreadLocal() {
        @Override
        protected ThreadContext initialValue() {
            return new ThreadContext();
        }

    };


    public static ThreadContext get() {
        return threadLocal.get();
    }

    public void addTimeRecord(String name) {
        long currentTime = System.currentTimeMillis();
        timeRecordList.add(name + ":" + (currentTime - lastTime));
        lastTime = currentTime;
    }

    public void setInXml(String inXml) {
        this.inXml = inXml;
    }

    public void setOutXml(String outXml) {
        this.outXml = outXml;
    }

    public ThreadContext init() {
        beginTime = System.currentTimeMillis();
        lastTime = beginTime;
        timeRecordList.clear();
        return this;
    }

    public void trace() {
        long endTime = System.currentTimeMillis();
        long cost = endTime - beginTime;
        if (cost > TRACE_TIMEOUT) {
            log.info("[微信跟踪]总耗时={}, 时间链={}, 请求报文={}, 响应报文={}", cost, timeRecordList, inXml, outXml);
        }
    }
}
