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
// Created on 2016年7月12日
// $Id$

package org.jobshen.util.rsa;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author <a href="mailto:shenchenbo@zuozh.com">Shen.Chenbo</a>
 * @version
 * @since JDK 1.6 Created on 2016年7月12日 Copyright 2016 ZZJR All Rights Reserved.
 */
public class RSAUtil {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM   = "RSA";

    /** 密钥长度，用来初始化 */
    private static final int    KEYSIZE     = 1024;

    /** 获取公钥的key */
    public static final String PUBLIC_KEY  = "RSAPublicKey";

    /** 获取私钥的key */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    public static Map<String, String> getGeneratorRSAkeyMap() throws NoSuchAlgorithmException {
        Map<String, String> keyMap = Maps.newHashMap();
        KeyPair keyPair = getGeneratorRSAkeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
        return keyMap;
    }

    public static KeyPair getGeneratorRSAkeyPair() throws NoSuchAlgorithmException {
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(KEYSIZE);
        return keyPairGenerator.generateKeyPair();
    }
    
    public static void main(String[] args) throws Exception{
        System.out.println(JSON.toJSONString(getGeneratorRSAkeyMap()));
    }
}
