package org.jobshen.mq.rocketmq.bean;

import lombok.Getter;

/**
 * Subscription 订阅实体类
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月8日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
@Getter
public class Subscription {
    private final String topic;
    private final String expression;
    
    public Subscription(String topic, String expression) {
        this.topic = topic;
        this.expression = expression;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subscription other = (Subscription) obj;
        if (topic == null) {
            if (other.topic != null)
                return false;
        } else if (!topic.equals(other.topic))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Subscription [topic=" + topic + ", expression=" + expression + "]";
    }

}
