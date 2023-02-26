package iss.ssf.selfRevisionws06.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ssf.selfRevisionws06.model.Game;
import iss.ssf.selfRevisionws06.repo.GameRepo;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    // read file
    public void readJsonFile(String fileName, File file) throws IOException {

        // read json file
        // convert the file to json array
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        JsonReader jsonReader = Json.createReader(br);
        JsonArray jsonArr = jsonReader.readArray();

        // close the file
        br.close();
        fr.close();

        // manipulate the json array
        // create new game object from each json object found
        for (int i = 0; i < jsonArr.size(); i++) {

            // instantiate a new game object
            Game game = new Game();
            JsonObject json = jsonArr.getJsonObject(i);

            // update game object
            game.setGid(json.getInt("gid"));
            game.setName(json.getString("name"));
            game.setYear(json.getInt("year"));
            game.setRanking(json.getInt("ranking"));
            game.setUserRated(json.getInt("users_rated"));
            game.setUrl(json.getString("url"));
            game.setImage(json.getString("image"));

            // save the game object to database
            upload(fileName, game);
        }
        
    }

    public void upload(String fileName, Game game) {
        gameRepo.upload(fileName, game);
    }
    
}
