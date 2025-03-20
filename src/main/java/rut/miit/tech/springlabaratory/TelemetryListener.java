package rut.miit.tech.springlabaratory;

import org.springframework.stereotype.Component;

@Component
public class TelemetryListener implements Observer<TelemetryData>{

    @Override
    public void notify(TelemetryData data) {
        System.out.println(data);
    }
}
