package org.jobshen.mq.rocketmq.messageLisnters;

import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * 监听消费类 接口 异步模式
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月8日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
public interface DefaultConcurrentMessageListener extends MessageListenerConcurrently {
}
