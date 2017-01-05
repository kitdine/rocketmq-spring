package com.zzjr.mq.rocketmq.messageLisnters;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.zzjr.mq.rocketmq.bean.Action;
import com.zzjr.mq.rocketmq.bean.ConsumeContext;

/**
 * 消息监听器，Consumer注册消息监听器来订阅消息
 */
public interface MessageListener {
	/**
	 * 消费消息接口，由应用来实现<br>
	 *
	 * @param message
	 *         消息
	 * @param context
	 *         消费上下文
	 *
	 * @return 消费结果，如果应用抛出异常或者返回Null等价于返回Action.ReconsumeLater
	 */
	Action consume(final MessageExt message, final ConsumeContext context);
}
