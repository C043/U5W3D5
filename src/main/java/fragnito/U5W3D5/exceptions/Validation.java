package fragnito.U5W3D5.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@Component
public class Validation {
    public void validate(BindingResult validation) {
        String messages = validation.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(". "));
        if (validation.hasErrors()) throw new BadRequestException("Ci sono stati degli errori di validazione: " + messages);
    }
}
