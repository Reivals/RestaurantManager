package dto;

import java.time.LocalDateTime;

/**
 * @author Michal on 08.05.2019
 */

public class WSError {

    private LocalDateTime timestamp;
    private String message;

    private WSError(String message){
        this.message = message;
        this.timestamp = LocalDateTime.now();

    }
    public static WSError create(String message){
        return new WSError(message);
    }
}
