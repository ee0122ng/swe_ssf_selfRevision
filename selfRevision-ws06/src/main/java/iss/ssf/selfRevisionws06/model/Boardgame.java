package iss.ssf.selfRevisionws06.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Boardgame implements Serializable {

    private String id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer user_rated;
    private String url;
    private String image;

    public Boardgame() {
    }

    public Boardgame(String id, String name, Integer year, Integer ranking, Integer user_rated, String url, String image) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.user_rated = user_rated;
        this.url = url;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return this.ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUser_rated() {
        return this.user_rated;
    }

    public void setUser_rated(Integer user_rated) {
        this.user_rated = user_rated;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JsonObject createResponseJson() {

        JsonObject json = Json.createObjectBuilder()
            .add("insert_count", 1)
            .add("id", this.id)
            .build();

        return json;
    }

    public JsonObject createGameJson() {

        JsonObject json = Json.createObjectBuilder()
            .add("id", this.id)
            .add("name", this.name)
            .add("year", this.year)
            .add("ranking", this.ranking)
            .add("user_rated", this.user_rated)
            .add("url", this.url)
            .add("image", this.image)
            .build();

        return json;
    }

    @Override
    public String toString() {
        return """

            { 
            "name" : {name},
            "year" : {year},
            "raking" : {raking},
            "user_rated" : {rating},
            "url" : {url_text},
            "image" : {image_text}
            }
                
                """;
    }

    
}
