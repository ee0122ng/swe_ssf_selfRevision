package iss.ssf.selfRevisionws06.service;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ssf.selfRevisionws06.Constants;
import iss.ssf.selfRevisionws06.model.Boardgame;
import iss.ssf.selfRevisionws06.repo.BoardgameRepo;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class BoardgameService {

    @Autowired
    BoardgameRepo gameRepo;

    public Boardgame insertGame(String payload) {

        JsonReader jReader = Json.createReader(new StringReader(payload));
        JsonObject jObj = jReader.readObject();

        // instantiate an id
        String id = UUID.randomUUID().toString().substring(0, 8);
        Boardgame game = new Boardgame();
        game.setId(id);

        // set boardgame properties
        game.setName(jObj.getString("name", "null"));
        game.setYear(jObj.getInt("year", 9999));
        game.setUser_rated(jObj.getInt("user_rated", 0));
        game.setRanking(jObj.getInt("ranking", 0));
        game.setUrl(jObj.getString("url", "null"));
        game.setImage(jObj.getString("image", "null"));

        // insert to redis
        gameRepo.insertGame(game);

        return game;
    }

    public Optional<JsonObject> getGameById(String id) {

        Long index = gameRepo.getIndexById(id);

        if (index != Constants.NOT_FOUND) {

            String gameString = gameRepo.getGameById(id);
            JsonReader jReader = Json.createReader(new StringReader(gameString));
            JsonObject gameJson = jReader.readObject();

            return Optional.of(gameJson);

        } else {
    
            return Optional.empty();
        }
    }

    public Optional<JsonObject> updateGameById(String id) {

        Integer updateCount = gameRepo.updateGameById(id);

        if (updateCount > 0) {

            JsonObject json = createUpdateResponse(updateCount, id);

            return Optional.of(json);
        }

        return Optional.empty();

    }

    public JsonObject createUpdateResponse(Integer updateCount, String id) {

        JsonObject json = Json.createObjectBuilder()
            .add("update_count", updateCount)
            .add("id", id)
            .build();

        return json;
    }

    
}
