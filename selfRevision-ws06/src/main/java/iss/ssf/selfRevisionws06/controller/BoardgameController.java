package iss.ssf.selfRevisionws06.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.ssf.selfRevisionws06.model.Boardgame;
import iss.ssf.selfRevisionws06.service.BoardgameService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path={"/api/boardgame"}, produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardgameController {

    @Autowired
    BoardgameService gameSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertGame(@RequestBody String payload) {

        System.out.println(">>> payload: " + payload);

        // process the request
        Boardgame game = gameSvc.insertGame(payload);

        JsonObject json = game.createResponseJson();
            
        return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());

    }

    @GetMapping(path="{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGame(@PathVariable String id) {

        Optional<JsonObject> opt = gameSvc.getGameById(id);

        // catch NoSuchElementException if Optional return empty
        try {
            JsonObject gameJson = opt.get();

            System.out.println(gameJson.toString());

            return ResponseEntity.status(HttpStatus.OK).body(gameJson.toString());

        } catch(Exception ex) {

            JsonObject error = Json.createObjectBuilder()
                .add("message", "Id %s not found".formatted(id))
                .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }

    }

    @PutMapping(path="{id}")
    public ResponseEntity<String> updateGame(@PathVariable String id, @RequestParam(required=true, defaultValue="false") String upsert) {

        if (Boolean.parseBoolean(upsert) == true) {
            Boardgame game = gameSvc.insertGame(id);
        }

        Optional<JsonObject> opt = gameSvc.updateGameById(id);
        try {
            JsonObject json = opt.get();

            return ResponseEntity.status(HttpStatus.OK).body(json.toString());

        } catch (Exception ex) {

            JsonObject error = Json.createObjectBuilder()
                .add("message", "id %s not found".formatted(id))
                .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.toString());
        }

    }
    
}
