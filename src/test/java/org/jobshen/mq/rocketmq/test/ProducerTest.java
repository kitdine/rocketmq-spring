package org.jobshen.mq.rocketmq.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import org.jobshen.mq.rocketmq.messages.TestMessage;
import org.jobshen.mq.rocketmq.producer.DefaultProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2016/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rocketmq-producer.xml"})
public class ProducerTest {
	@Autowired
	private DefaultProducer defaultProducer;

	@Autowired
	private TestMessage testMessage;

	@Test
	public void testProducer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer producer = defaultProducer.getProducer();
		for(int i = 0 ; i < 1000000 ; i ++) {
			Message msg = testMessage.getInstance("message--" + i + "--tests");
			SendResult result = producer.send(msg);
            System.out.println("message--"+i+" : result : "+ JSONObject.toJSONString(result));
		}
	}
}
