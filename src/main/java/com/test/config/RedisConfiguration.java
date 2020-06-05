package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration {


    @Bean
    public StringRedisTemplate redisTemplate()
    {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("47.106.184.200");
        configuration.setDatabase(0);
        configuration.setPort(6379);
        configuration.setPassword("Love.903960669");
        return new StringRedisTemplate(new JedisConnectionFactory(configuration));

    }
}
