/*
 * Copyright 2016 ZZJR All Rights Reserved.
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
// Created on 2016年7月25日
// $Id$

package org.jobshen.mq.rocketmq.listeners;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zzjr.mq.rocketmq.messageLisnters.DefaultConcurrentMessageListener;

import org.jobshen.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 *  收到消息redis key 原子增加1 手动模拟一定概率失败 返回 ConsumeConcurrentlyStatus.RECONSUME_LATER
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月25日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
//@Service
public class RedisListener implements DefaultConcurrentMessageListener {
    
    public static final String KEY = "MSG_SUCCSS";
    
    public static final Double Error_Rate = 0.01;
    
    @Autowired
    private RedisService redisService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        Double random = Math.random();
        if(random.compareTo(Error_Rate) < 0) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        System.out.println("msgs size : " + msgs.size());
        for (MessageExt msg : msgs) {
            redisService.incrementByLongOneStep(KEY);
            System.out.println("msg : " + new String(msg.getBody()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
