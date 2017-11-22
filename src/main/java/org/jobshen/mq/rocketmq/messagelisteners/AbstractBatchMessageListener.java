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

package org.jobshen.mq.rocketmq.messagelisteners;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.rocketmq.common.message.MessageExt;
import org.jobshen.mq.rocketmq.bean.ConsumeContext;
import org.jobshen.mq.rocketmq.enums.Action;
import org.jobshen.mq.rocketmq.execptions.SerializationException;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;

import com.google.common.collect.Lists;

import lombok.extern.log4j.Log4j2;

/**
 * AbstractBatchMessageListener Description:
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 17:45
 * @since JDK 1.7
 */
@Log4j2
public abstract class AbstractBatchMessageListener<T> implements BatchMessageActionListener {

    private final Class<T> clazz ;

    private final RocketMqSerializer serializer;

    public AbstractBatchMessageListener(RocketMqSerializer serializer) {
        this.clazz  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.serializer = serializer;
    }

    @Override
    public Action consume(List<MessageExt> messages, ConsumeContext context) {
        try {
            log.debug("Received new messages, size: {} ",messages.size());
            List<T> list = Lists.newArrayListWithCapacity(messages.size());
            for (MessageExt message : messages) {
                T t = this.serializer.deserialize(message.getBody(), clazz);
                list.add(t);
            }

            boolean isSuccess = handleMessage(list, messages, context);
            if (!isSuccess) {
                log.error("Handle the message failure!");
                return Action.ReconsumeLater;
            }
            return Action.CommitMessage;
        } catch (SerializationException serializationException) {
            log.error("deserialize the message failure!deserialize type :{}", serializer.getContentType());
            return Action.ReconsumeLater;
        } catch (Exception e) {
            log.error("Received the message but process failed!", e);
            return Action.ReconsumeLater;
        }
    }

    /**
     * 处理消息
     * @param list          对象集合
     * @param messages     message 额外信息 集合
     * @param context       上下文
     * @return              是否成功
     * @throws Exception    异常
     */
    public abstract boolean handleMessage(List<T> list, List<MessageExt> messages, ConsumeContext context) throws Exception;
}
