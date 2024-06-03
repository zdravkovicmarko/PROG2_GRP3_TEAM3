package at.ac.fhcampuswien.fhmdb;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    default void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    default void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}