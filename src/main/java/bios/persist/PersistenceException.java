package bios.persist;

/**
 *
 */
public class PersistenceException extends Exception {
    public PersistenceException() {
    }

    public PersistenceException(String string) {
        super(string);
    }

    public PersistenceException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public PersistenceException(Throwable throwable) {
        super(throwable);
    }
}
