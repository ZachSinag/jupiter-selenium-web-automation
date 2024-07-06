package jupiter.utilities;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataModel {

    @CsvBindByName(column = "email", required = false)
    private String email;

    @CsvBindByName(column = "forename", required = false)
    private String forename;

    @CsvBindByName(column = "message", required = false)
    private String message;

    @CsvBindByName(column = "notification_modal", required = false)
    private String notification_modal;

    @CsvBindByName(column = "forename_error_message", required = false)
    private String forename_error_message;

    @CsvBindByName(column = "email_error_message", required = false)
    private String email_error_message;

    @CsvBindByName(column = "error_message_box", required = false)
    private String error_message_box;

    @CsvBindByName(column = "environment", required = false)
    private String environment;

    @CsvBindByName(column = "identifier", required = false)
    private String identifier;

    @CsvBindByName(column = "fluffy_bunny_price", required = false)
    private String fluffy_bunny_price;

    @CsvBindByName(column = "valentines_bear_price", required = false)
    private String valentines_bear_price;

    @CsvBindByName(column = "stuff_frog_price", required = false)
    private String stuff_frog_price;


}



