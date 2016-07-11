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
// Created on 2016年7月8日
// $Id$

package org.jobshen.mq.rocketmq.listeners;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jobshen.mq.rocketmq.messageLisnters.DefaultConcurrentMessageListener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;


/**
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月8日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
public class TestListener implements DefaultConcurrentMessageListener{
    
    private static AtomicInteger count = new AtomicInteger(0);
    private int n;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            n = count.addAndGet(1);
            System.out.println("msg : " + new String(msg.getBody()));
        }
        System.out.println("------------------------------count : " + n);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
