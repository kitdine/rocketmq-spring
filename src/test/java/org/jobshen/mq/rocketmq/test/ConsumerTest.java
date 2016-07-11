package org.jobshen.mq.rocketmq.test;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

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
@ContextConfiguration(locations={"classpath:rocketmq-consumer.xml"})
public class ConsumerTest {

	@Test
	public void testConmser() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		System.out.println("ConsumerTest.testConmser");
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("rocketmq-consumer.xml");
		System.out.println("Consumer Started");
	}
}
