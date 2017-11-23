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

package org.jobshen.mq.rocketmq.test.xml;

import com.alibaba.fastjson.JSONObject;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.jobshen.mq.rocketmq.producer.DefaultProducer;
import org.jobshen.mq.rocketmq.test.messages.PersonMessage;
import org.jobshen.mq.rocketmq.test.po.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j2;

/**
 * Created by lenovo on 2016/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rocketmq-producer.xml"})
@Log4j2
public class ProducerTest {

	@Autowired
	@Qualifier("personMessage")
	private PersonMessage personMessage;

	@Autowired
	@Qualifier("personbatchMessage")
	private PersonMessage personbatchMessage;

	@Autowired
	private DefaultProducer defaultProducer;

	@Test
	public void testProducer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer producer = defaultProducer.getProducer();
		for(int i = 0 ; i < 10 ; i ++) {
			Person p = new Person();
			p.setAge(i+10);
			p.setName("xxx"+i);
			p.setSex(i % 2 == 0 ? 'F' : 'M');
			Message msg = personMessage.getInstance(p);
			SendResult result = producer.send(msg);
			log.info("message--{} : result : {}", i, JSONObject.toJSONString(result));
		}

		for(int i = 0 ; i < 5000 ; i ++) {
			Person p = new Person();
			p.setAge(i+10);
			p.setName("xxx"+i);
			p.setSex(i % 2 == 0 ? 'F' : 'M');
			Message msg = personbatchMessage.getInstance(p);
			SendResult result = producer.send(msg);
			log.info("message--{} : result : {}", i, JSONObject.toJSONString(result));
		}
	}
}
