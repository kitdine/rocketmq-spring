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

package org.jobshen.mq.rocketmq.messagelisteners;


import org.apache.rocketmq.common.message.MessageExt;
import org.jobshen.mq.rocketmq.bean.ConsumeContext;
import org.jobshen.mq.rocketmq.enums.Action;

/**
 * SingletonMessageActionListener Description: 消息监听器，Consumer注册消息监听器来订阅消息
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.1
 * @date 2017/11/22 15:50
 * @since JDK 1.7
 */
public interface SingletonMessageActionListener extends MessageListener{

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
