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

package org.jobshen.mq.rocketmq.serialize.kryo;

import java.io.ByteArrayOutputStream;

import org.jobshen.mq.rocketmq.enums.SerializeTypeEnum;
import org.jobshen.mq.rocketmq.execptions.SerializationException;
import org.jobshen.mq.rocketmq.serialize.RocketMqSerializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.esotericsoftware.kryo.pool.KryoPool;

/**
 * KryoSerializer Description:
 *
 * @author <a href="mailto:kitdnie@gmail.com">Job Shen</a>
 * @version 1.0
 * @date 2017/11/22 15:45
 * @since JDK 1.7
 */
public class KryoSerializer implements RocketMqSerializer {

    private KryoPool pool ;

    public KryoSerializer(DefaultKryoFactory factory) {
        this.pool = new KryoPool.Builder(factory).softReferences().build();
    }

    @Override
    public byte getContentTypeId() {
        return SerializeTypeEnum.Kryo.getId();
    }

    @Override
    public String getContentType() {
        return SerializeTypeEnum.Kryo.getType();
    }

    @Override
    public <T> byte[] serialize(final Object t, Class<T> clazz) throws SerializationException {
        return  pool.run(new KryoCallback<byte[]>() {
            @Override
            public byte[] execute(Kryo kryo) {
                try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                    Output output = new Output(out)) {
                    kryo.writeClassAndObject(output, t);
                    return output.toBytes();
                } catch (Exception e) {
                    throw new SerializationException("serialize failed", e);
                }
            }
        });
    }

    @Override
    public <T> T deserialize(final byte[] bytes, final Class<T> clazz) throws SerializationException {
        return pool.run(new KryoCallback<T>() {
            @Override
            public T execute(Kryo kryo) {
                try (Input input = new Input(bytes)){
                    return kryo.readObjectOrNull(input, clazz);
                } catch (Exception e) {
                    throw new SerializationException("deserialize failed", e);
                }
            }
        });
    }
}
