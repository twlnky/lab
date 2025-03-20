package rut.miit.tech.springlabaratory;

import lombok.Data;

@Data
public class TelemetryData {
    private String name;
    private String location;
    private int temperature;

    public TelemetryData(String name, String location, int temp) {
        this.name = name;
        this.location = location;
        this.temperature = temp;
    }
}
