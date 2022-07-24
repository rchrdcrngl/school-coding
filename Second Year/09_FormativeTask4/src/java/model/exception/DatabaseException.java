package model.exception;

/**
 *
 * @author Richard Maru
 */
public class DatabaseException extends Exception{
    public DatabaseException(){
        super("Database connection is null.");
    }
    public DatabaseException(String error){
        super(error);
    }
}
