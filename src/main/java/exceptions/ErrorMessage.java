package exceptions;

import java.util.Date;

/**
 * @author Michal on 24.03.2019
 */

public class ErrorMessage {

    private String message;
    private Date date;

    public ErrorMessage(String message) {
        this.date = new Date();
        this.message = message;
    }
}
