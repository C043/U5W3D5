package fragnito.U5W3D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotNull(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 10, message = "Il nome deve avere minimo 3 e massimo 10 caratteri")
        String nome,
        @NotNull(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 10, message = "Il cognome deve avere minimo 3 e massimo 10 caratteri")
        String cognome,
        @NotNull(message = "L'email è obbligatoria")
        @Email(message = "L'emaill inserita non è un indirizzo valido")
        String email,
        @NotNull(message = "La password è obbligatoria")
        @Size(min = 8, max = 20, message = "La password deve avere minimo 8 e massimo 20 caratteri")
        String password
) {
}
