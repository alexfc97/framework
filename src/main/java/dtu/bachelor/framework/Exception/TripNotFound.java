package dtu.bachelor.framework.Exception;

public class TripNotFound extends RuntimeException {

    public TripNotFound(int id) {
        super("Could not find trip with id: " + id);
    }
}
