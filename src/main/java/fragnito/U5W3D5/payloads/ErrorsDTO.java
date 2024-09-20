package fragnito.U5W3D5.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsDTO {
    private String message;
    private LocalDateTime timestamp;
}
