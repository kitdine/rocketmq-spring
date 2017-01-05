package org.jobshen.mq.rocketmq.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.zzjr.mq.rocketmq.producer.DefaultProducer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jobshen.mq.rocketmq.messages.TestMessage;
import org.jobshen.util.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2016/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rocketmq-producer.xml","classpath:redis.xml"})
public class ProducerTest {
    
    protected final Logger log = LogManager.getLogger(ProducerTest.class);
    
	@Autowired
	private DefaultProducer defaultProducer;

	@Autowired
	private TestMessage testMessage;
	
//	@Autowired
//	private RedisService redisService;
	
	public static final String KEY = "MSG_SEND";
	
	@Test
	public void testProducer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		DefaultMQProducer producer = defaultProducer.getProducer();
		for(int i = 0 ; i < 10 ; i ++) {
//		    redisService.incrementByLongOneStep(KEY);
			Message msg = testMessage.getInstance("message--" + i + "--tests");
			SendResult result = producer.send(msg);
			log.info("message--{} : result : {}", i, JSONObject.toJSONString(result));
//            System.out.println("message--"+i+" : result : "+ JSONObject.toJSONString(result));
		}
	}
}
