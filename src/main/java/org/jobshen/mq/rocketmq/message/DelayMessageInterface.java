package org.jobshen.mq.rocketmq.message;

import org.apache.rocketmq.common.message.Message;
import org.jobshen.mq.rocketmq.enums.DelayTimeLevel;

/**
 * @author <a href="mailto:kitdnie@gmail.com">JobShen<a/>
 * @since JDK1.7 Created on 2017/1/5.
 */
public interface DelayMessageInterface<T> {

    /**
     * 生成延时消息实体
     * @param t             对象
     * @param timeLevel     延迟类型
     * @return               message 对象
     */
    Message getInstance(T t, DelayTimeLevel timeLevel);

    /**
     * 生成延时消息实体
     * @param t             对象
     * @param keys          message key
     * @param timeLevel     延迟类型
     * @return                message 对象
     */
    Message getInstance(T t, String keys, DelayTimeLevel timeLevel);
}
