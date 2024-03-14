package mikhail.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String errorMessage;
    private long timestamp;
    private HttpStatus httpStatus;
}