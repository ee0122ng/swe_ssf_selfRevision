package iss.ssf.selfRevisionopenWeatherMapAPI.model;

public class Weather {

    private Long id;
    private String main;
    private String desc;
    private Float temp;
    private Float humidity;
    private Float temp_min;
    private Float temp_max;
    private String location;

    public Weather() {
    }

    public Weather(Long id, String main, String desc, Float temp, Float humidity, Float temp_min, Float temp_max, String location) {
        this.id = id;
        this.main = main;
        this.desc = desc;
        this.temp = temp;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.location = location;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return this.main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getTemp() {
        return this.temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getHumidity() {
        return this.humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(Float temp_min) {
        this.temp_min = temp_min;
    }

    public Float getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(Float temp_max) {
        this.temp_max = temp_max;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " location='" + getLocation() + "'" +
            ", main='" + getMain() + "'" +
            ", desc='" + getDesc() + "'" +
            ", temp='" + getTemp() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", temp_min='" + getTemp_min() + "'" +
            ", temp_max='" + getTemp_max() + "'" +
            "}";
    }

    
}
