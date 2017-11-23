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


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2016/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rocketmq-consumer.xml"})
public class ConsumerTest {

	@Test
	public void testConmser() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		System.out.println("ConsumerTest.testConmser");
	}

	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("rocketmq-redis-consumer.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("rocketmq-consumer.xml");
		System.out.println("Consumer Started");
//	    Map<String, String> a = Maps.newHashMap();
//        a.put("A", "AA");
//        a.put("B", "BB");
//        a.put("C", "CC");
//        Map<String, String> b = Maps.newHashMap();
//        b.put("A", "AA");
//        b.put("B", "BB");
//        b.put("C", "CC");
//        Map<String, String> c = Maps.newHashMap();
//        c.put("A", "AA");
//        c.put("B", "BB");
//        c.put("C", "CC");
//        List<Map<String, String>> all = new ArrayList<>();
//        List<Map<String, String>> xml = Arrays.asList(a, b, c);
//        all.addAll(xml);
//        all.addAll(xml);
//        System.out.println(JSON.toJSONString(all, SerializerFeature.DisableCircularReferenceDetect));
	}
	
}
