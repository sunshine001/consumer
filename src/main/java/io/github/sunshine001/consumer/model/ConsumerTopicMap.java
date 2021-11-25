package io.github.sunshine001.consumer.model;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Data
public class ConsumerTopicMap {
    private Map<String, Consumer> map = new HashMap<>();

    public void put(String topic, Consumer consumer) {
        map.put(topic, consumer);
    }

    public void handle(String topic, Object message) {
        Consumer consumer = map.get(topic);
        if (consumer != null) {
            try {
                consumer.method.invoke(consumer.object, message);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Data
    public static class Consumer {
        private Object object;
        private Method method;

        public Consumer(Object object, Method method) {
            this.object = object;
            this.method = method;
        }
    }
}
