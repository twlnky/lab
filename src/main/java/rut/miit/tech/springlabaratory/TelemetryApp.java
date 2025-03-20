package rut.miit.tech.springlabaratory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TelemetryApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TelemetryApp.class, args);

        TelemetrySensor sensor = context.getBean(TelemetrySensor.class);

        for(int i = 0; i < 5; i++) {
            sensor.generateData();
            try { Thread.sleep(500); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
