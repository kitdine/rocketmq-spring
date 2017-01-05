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

package org.jobshen.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 *
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version 
 * @since JDK 1.6
 * Created on 2016年7月25日
 * Copyright 2016 ZZJR All Rights Reserved.
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public Long incrementByLongWithStep(String key, Long step) {
        return stringRedisTemplate.opsForValue().increment(key, step);
    }
    
    public Long incrementByLongOneStep(String key) {
        return incrementByLongWithStep(key, 1L);
    }
}
