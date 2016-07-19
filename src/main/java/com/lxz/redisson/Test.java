package com.lxz.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.codec.MsgPackJacksonCodec;
import org.redisson.core.RAtomicLong;

import java.util.concurrent.TimeUnit;


/**
 * Created by xiaolezheng on 16/7/19.
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        //config.setUseLinuxNativeEpoll(true);
        config.useSingleServer().setAddress("127.0.0.1:6379");
        config.setCodec(new MsgPackJacksonCodec());

        RedissonClient redisson = Redisson.create(config);
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        for(int i=0; i<10000;i++) {
            atomicLong.incrementAndGet();
            //log.info("{}", atomicLong.get());

            //TimeUnit.SECONDS.sleep(1);
        }
        log.info("{}", atomicLong.get());
    }
}
