/*
 * Copyright 2017-2020 the original author or authors.
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

package org.jobshen.mq.rocketmq.consumer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.jobshen.mq.rocketmq.bean.ConsumeContext;
import org.jobshen.mq.rocketmq.bean.Subscription;
import org.jobshen.mq.rocketmq.enums.Action;
import org.jobshen.mq.rocketmq.execptions.RocketMqClientException;
import org.jobshen.mq.rocketmq.messagelisteners.MessageListener;
import org.jobshen.mq.rocketmq.messagelisteners.SingletonMessageActionListener;

import lombok.extern.log4j.Log4j2;

/**
 * SingletonMessageConsumer Description: 每次只接收一条message
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 16:25
 * @since JDK 1.7
 */
@Log4j2
public class SingletonMessageConsumer extends AbstractConsumer {

    /**
     * Map<Topic, SingletonMessageActionListener>
     */
    private final ConcurrentHashMap<String, SingletonMessageActionListener> subscribeTable = new ConcurrentHashMap<>();

    /**
     * message 数量 默认1条
     * 只允许1条，需要返回多条消息请使用 @code BatchMessageConsumer
     */
    private final static int DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE = 1;
    /**
     * 线程池最小线程数
     */
    private final static int DEFAULT_CONSUME_THREAD_MIN = 20;
    /**
     * 线程池最大线程数
     */
    private final static int DEFAULT_CONSUME_THREAD_MAX = 64;

    public SingletonMessageConsumer(String namesrv, String consumerGroupName, String instanceName,
        Map<Subscription, MessageListener> subscriptionTable) {
        super(namesrv, consumerGroupName, instanceName, subscriptionTable, DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE,
            DEFAULT_CONSUME_THREAD_MIN, DEFAULT_CONSUME_THREAD_MAX);
    }

    public SingletonMessageConsumer(String namesrv, String consumerGroupName, String instanceName,
        Map<Subscription, MessageListener> subscriptionTable, int consumeThreadMin,
        int consumeThreadMax) {
        super(namesrv, consumerGroupName, instanceName, subscriptionTable, DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE,
            consumeThreadMin, consumeThreadMax);
    }

    @Override
    public void init() throws MQClientException {
        log.info("start DefaultMQProducer initialize! consumerGroupName:{}, namesrv:{}", consumerGroupName, namesrv);

        if (null == namesrv || null == consumerGroupName || null == instanceName || null == subscriptionTable) {
            throw new RuntimeException("properties not set");
        }

        Iterator<Entry<Subscription, MessageListener>> it = this.subscriptionTable.entrySet().iterator();

        consumer = new DefaultMQPushConsumer(consumerGroupName);
        consumer.setNamesrvAddr(namesrv);
        consumer.setInstanceName(instanceName);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);

        while (it.hasNext()) {
            Map.Entry<Subscription, MessageListener> next = it.next();
            consumer.subscribe(next.getKey().getTopic(), next.getKey().getExpression());
            this.subscribeTable.put(next.getKey().getTopic(), (SingletonMessageActionListener) next.getValue());
        }
        consumer.registerMessageListener(new SingletonMessageListener());
        consumer.start();
        log.info("the DefaultMQProducer start success!");
    }

    @Override
    public void destroy() {
        log.info("start DefaultMQProducer destroy!");
        consumer.shutdown();
        log.info("DefaultMQProducer destroy success!");
    }

    class SingletonMessageListener implements MessageListenerConcurrently {

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext contextExt) {
            MessageExt msg = msgs.get(0);
            SingletonMessageActionListener listener = SingletonMessageConsumer.this.subscribeTable.get(msg.getTopic());
            if (null == listener) {
                throw new RocketMqClientException("SingletonMessageActionListener is null");
            }

            final ConsumeContext context = new ConsumeContext(contextExt);
            Action action = listener.consume(msg, context);
            return getReturnAction(action);
        }
    }

}
