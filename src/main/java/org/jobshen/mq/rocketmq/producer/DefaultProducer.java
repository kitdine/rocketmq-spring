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

package org.jobshen.mq.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import lombok.extern.log4j.Log4j2;

/**
 * DefaultProducer Description:  生产者 bean
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 14:30
 * @since JDK 1.7
 */
@Log4j2
public class DefaultProducer {

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
        log.info("start DefaultMQProducer initialize! producerGroupName:{}, namesrv:{}", producerGroupName, namesrv);

        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(namesrv);
        producer.setInstanceName(instanceName);

        producer.start();

        log.info("the DefaultMQProducer start success!");
    }

    public void destroy() {
        log.info("start DefaultMQProducer shutdown!");
        producer.shutdown();
        log.info("DefaultMQProducer shutdown success!");
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}
