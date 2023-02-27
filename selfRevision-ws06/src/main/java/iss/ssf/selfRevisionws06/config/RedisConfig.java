package iss.ssf.selfRevisionws06.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.redis.database}")
    private Optional<Integer> redisDatabase;

    @Bean("my-redis")
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate() {

        // configure redis
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        // setup redis environment
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        config.setDatabase(redisDatabase.get());

        // configure jedis client and connection factory
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisFac);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        RedisSerializer<Object> serializerObj = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        redisTemplate.setValueSerializer(serializerObj);
        redisTemplate.setHashValueSerializer(serializerObj);
        // redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    
    
}
