package io.github.sunshine001.consumer.factory;

import io.github.sunshine001.consumer.annotation.Consumer;
import io.github.sunshine001.consumer.annotation.Topic;
import io.github.sunshine001.consumer.model.ConsumerTopicMap;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class ConsumerFactory {
    @Resource
    private ApplicationContext applicationContext;
    private ConsumerTopicMap consumerTopicMap = new ConsumerTopicMap();

    public ConsumerFactory() {
        Map<String, Object> consumerMap = applicationContext.getBeansWithAnnotation(Consumer.class);
        for (Map.Entry<String, Object> consumer : consumerMap.entrySet()) {
            Object obj = consumer.getValue();
            Method[] methods = obj.getClass().getMethods();
            Arrays.stream(methods).forEach(method->{
                Topic annotation = method.getAnnotation(Topic.class);
                if (annotation != null && !StringUtils.isEmpty(annotation.value())) {
                    consumerTopicMap.put(annotation.value(), new ConsumerTopicMap.Consumer(obj, method));
                }
            });
        }
    }

    /**
     * dispatch method
     * @param topic topicId
     * @param message message
     */
    public void dispatch(String topic, String message) {
        consumerTopicMap.handle(topic, message);
    }
}
