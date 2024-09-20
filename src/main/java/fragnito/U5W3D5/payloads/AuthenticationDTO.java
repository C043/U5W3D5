package fragnito.U5W3D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO(
        @NotNull(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String email,
        @NotNull(message = "La password è obbligatoria")
        @Size(min = 8, max = 20, message = "La password deve essere di minimo 8 e massimo 20 caratteri")
        String password
) {
}
