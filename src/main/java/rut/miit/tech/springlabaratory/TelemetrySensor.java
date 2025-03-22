package rut.miit.tech.springlabaratory;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TelemetrySensor implements Observable<TelemetryData> {
    private final List<Observer<TelemetryData>> observers = new ArrayList<>();
    private final Faker faker = new Faker();
    private final Random random = new Random();
    private String name;
    private String location;

    @Autowired
    public TelemetrySensor(List<Observer<TelemetryData>> listeners) {
        this.name = faker.funnyName().name();
        this.location = faker.address().cityName();
        listeners.forEach(this::subscribe);
    }

    @PostConstruct
    public void startDataGeneration() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::generateData, 0, 3, TimeUnit.SECONDS);
    }

    public void generateData() {
        int temp = random.nextInt(-50, 50);
        TelemetryData data = new TelemetryData(name, location, temp);
        notifySubscribers(data);
    }


    @Override
    public void subscribe(Observer<TelemetryData> observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<TelemetryData> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers(TelemetryData data) {
        observers.forEach(observer -> observer.notify(data));
    }
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("CmdLineRunner");
            for (Object o : args) {
                System.out.println(args);
            }
        };
    }
}