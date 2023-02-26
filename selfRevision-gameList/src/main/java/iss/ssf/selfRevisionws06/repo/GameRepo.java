package iss.ssf.selfRevisionws06.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import iss.ssf.selfRevisionws06.model.Game;

@Repository
public class GameRepo {
    
    @Autowired
    @Qualifier("my-redis")
    RedisTemplate<String, Object> redisTemplate;

    public void upload(String fileName, Game game) {

        redisTemplate.opsForHash().put(fileName,String.valueOf(game.getGid()), game);
        
    }

}
