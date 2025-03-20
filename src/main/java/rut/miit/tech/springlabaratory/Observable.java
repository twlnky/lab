package rut.miit.tech.springlabaratory;

public interface Observable<D> {
    void subscribe(Observer<D> observer);
    void unsubscribe(Observer<D> observer);
    void notifySubscribers(TelemetryData data);
}
