package iss.ssf.selfRevisionws06.repo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import iss.ssf.selfRevisionws06.Constants;
import iss.ssf.selfRevisionws06.model.Boardgame;

@Repository
public class BoardgameRepo {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void insertGame(Boardgame game) {
        redisTemplate.opsForList().leftPush("boardgameIdList", game.getId());
        redisTemplate.opsForHash().put("boardgameMap", game.getId(), game);
    }

    public Boardgame getGameById(String id) {

        return (Boardgame) redisTemplate.opsForHash().get("boardgameMap", id);

    }

    public Long getIndexById(String id) {

        Long index = redisTemplate.opsForList().indexOf("boardgameIdList", id);

        return index == null ? Constants.NOT_FOUND : index;
    }

    public Integer updateGameById(String id) {

        Integer count = getUpdateCount(id);

        return count;

    }

    public Integer getUpdateCount(String id) {

        Integer count = Collections.frequency(getAllId(), id);

        return count;

    }

    public List<String> getAllId() {

        List<String> idList = new LinkedList<>();
        Long endList = redisTemplate.opsForList().size("boardgameIdList");

        if (null != endList)
            idList = redisTemplate.opsForList().range("boardgameIdList", 0, endList).stream().map(String.class::cast).collect(Collectors.toList());
        
        return idList;
    }
    
}
