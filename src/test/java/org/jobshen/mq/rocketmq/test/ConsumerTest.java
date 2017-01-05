package org.jobshen.mq.rocketmq.test;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
//        List<Map<String, String>> test = Arrays.asList(a, b, c);
//        all.addAll(test);
//        all.addAll(test);
//        System.out.println(JSON.toJSONString(all, SerializerFeature.DisableCircularReferenceDetect));
	}
	
}
