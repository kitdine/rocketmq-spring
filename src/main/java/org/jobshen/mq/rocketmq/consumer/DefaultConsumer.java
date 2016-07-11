package org.jobshen.mq.rocketmq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.MQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jobshen.mq.rocketmq.bean.Subscription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 消费者bean
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月8日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
public class DefaultConsumer {

    protected final Logger consumerLOG = LogManager.getLogger("rocketmq-consumer-log");

    private List<MQPushConsumer> consumerList = new ArrayList<MQPushConsumer>();

    private final String namesrv;
    private final String producerGroupName;
    private final String instanceName;
    private final Map<Subscription, MessageListenerConcurrently> subscriptionTable;

    public DefaultConsumer(String namesrv, String producerGroupName, String instanceName, Map<Subscription, MessageListenerConcurrently> subscriptionTable) {
        this.namesrv = namesrv;
        this.producerGroupName = producerGroupName;
        this.instanceName = instanceName;
        this.subscriptionTable = subscriptionTable;
    }

    public void init() throws MQClientException {
        consumerLOG.info("start DefaultMQProducer initialize! producerGroupName:{}, namesrv:{}", producerGroupName, namesrv);

        if (null == namesrv || null == producerGroupName || null == instanceName || null == subscriptionTable) {
            throw new RuntimeException("properties not set");
        }

        Iterator<Entry<Subscription, MessageListenerConcurrently>> it = this.subscriptionTable.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Subscription, MessageListenerConcurrently> next = it.next();
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(producerGroupName);
            consumer.setNamesrvAddr(namesrv);
            consumer.setInstanceName(instanceName);

            consumer.subscribe(next.getKey().getTopic(), next.getKey().getExpression());

            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.setMessageModel(MessageModel.CLUSTERING);

            consumer.registerMessageListener(next.getValue());

            consumer.start();

            consumerList.add(consumer);
        }

        consumerLOG.info("the DefaultMQProducer start success!");
    }

    public void destroy() {
        consumerLOG.info("start DefaultMQProducer shutdown!");

        for (MQPushConsumer consumer : consumerList) {
            consumer.shutdown();
        }

        consumerLOG.info("DefaultMQProducer shutdown success!");
    }

    public List<MQPushConsumer> getConsumerList() {
        return consumerList;
    }

}
