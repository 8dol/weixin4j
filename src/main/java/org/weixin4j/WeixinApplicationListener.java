package org.weixin4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * Created by zhangmin on 2016/10/21.
 */
public class WeixinApplicationListener implements GenericApplicationListener {
    private static Class<?>[] EVENT_TYPES = {
            ApplicationEnvironmentPreparedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class,
            ApplicationContext.class};

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 1800;

    private int order = DEFAULT_ORDER;

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent(
                    (ApplicationEnvironmentPreparedEvent) event);
        }
    }

    private void onApplicationEnvironmentPreparedEvent(
            ApplicationEnvironmentPreparedEvent event) {
        setWeixinConfigs(event.getEnvironment());
    }

    protected void setWeixinConfigs(Environment environment) {
        Map<String, Object> levels = new RelaxedPropertyResolver(environment)
                .getSubProperties("weixin4j.");
        for (Map.Entry<String, Object> entry : levels.entrySet()) {
            Configuration.setProperty("weixin4j." + entry.getKey(), entry.getValue().toString());
        }
    }


    @Override
    public int getOrder() {
        return this.order;
    }
}
