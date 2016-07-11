package org.jobshen.mq.rocketmq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 生产者 bean
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月8日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
public class DefaultProducer {

    protected final Logger PRODUCERLOG = LogManager.getLogger("rocketmq-producer-log");

    private DefaultMQProducer producer ;

    private final String namesrv;
    private final String producerGroupName;
    private final String instanceName;

    public DefaultProducer(String namesrv, String producerGroupName, String instanceName) {
        this.namesrv = namesrv;
        this.producerGroupName = producerGroupName;
        this.instanceName = instanceName;
    }

    public void init() throws MQClientException {
        PRODUCERLOG.info("start DefaultMQProducer initialize! producerGroupName:{}, namesrv:{}", producerGroupName, namesrv);

        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(namesrv);
        producer.setInstanceName(instanceName);

        producer.start();

        PRODUCERLOG.info("the DefaultMQProducer start success!");
    }

    public void destroy() {
        PRODUCERLOG.info("start DefaultMQProducer shutdown!");

        producer.shutdown();

        PRODUCERLOG.info("DefaultMQProducer shutdown success!");
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}
