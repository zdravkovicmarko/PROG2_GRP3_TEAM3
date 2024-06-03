package at.ac.fhcampuswien.fhmdb;

import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    private static WatchlistController watchlistControllerInstance;
    private static HomeController homeControllerInstance;

    @Override
    public Object call(Class<?> aClass) {
        if (aClass == WatchlistController.class) {
            if (watchlistControllerInstance == null) {
                watchlistControllerInstance = new WatchlistController();
            }
            return watchlistControllerInstance;
        }

        if (aClass == HomeController.class) {
            if (homeControllerInstance == null) {
                homeControllerInstance = new HomeController();
            }
            return homeControllerInstance;
        }

        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
