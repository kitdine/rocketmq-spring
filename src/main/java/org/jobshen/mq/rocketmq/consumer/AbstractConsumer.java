/*
 * Copyright (c) 2017 the original author or authors.
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jobshen.mq.rocketmq.consumer;

import java.util.Map;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.jobshen.mq.rocketmq.bean.Subscription;
import org.jobshen.mq.rocketmq.enums.Action;
import org.jobshen.mq.rocketmq.messagelisteners.MessageListener;

import lombok.extern.log4j.Log4j2;

/**
 * AbstractConsumer Description: 消费者bean
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 15:50
 * @since JDK 1.7
 */
@Log4j2
public abstract class AbstractConsumer {

   /**
     * 消费者
     */
    DefaultMQPushConsumer consumer;
    /**
     * namesrv 地址
     */
    final String namesrv;
    /**
     * group name
     */
    final String consumerGroupName;
    /**
     * 实例名
     */
   final String instanceName;
    /**
     * 订阅 关系表
     */
   final Map<Subscription, MessageListener> subscriptionTable;
    /**
     * 每次消费时拉取得消息条数
     */
    final int consumeMessageBatchMaxSize;
    /**
     * 最小线程数
     */
    final int consumeThreadMin;
    /**
     * 最大线程数
     */
    final int consumeThreadMax;

    AbstractConsumer(String namesrv, String consumerGroupName, String instanceName,
        Map<Subscription, MessageListener> subscriptionTable,
        int consumeMessageBatchMaxSize, int consumeThreadMin, int consumeThreadMax) {
        this.namesrv = namesrv;
        this.consumerGroupName = consumerGroupName;
        this.instanceName = instanceName;
        this.subscriptionTable = subscriptionTable;
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
        this.consumeThreadMin = consumeThreadMin;
        this.consumeThreadMax = consumeThreadMax;
    }

    public abstract void init() throws MQClientException;

    public abstract void destroy();

    ConsumeConcurrentlyStatus getReturnAction(Action action) {
        if (action != null) {
            switch (action) {
                case CommitMessage:
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                case ReconsumeLater:
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                default:
                    break;
            }
        }
        return null;
    }

}
