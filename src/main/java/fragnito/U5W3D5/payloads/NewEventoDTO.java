package fragnito.U5W3D5.payloads;

import jakarta.validation.constraints.NotNull;

public record NewEventoDTO(
        @NotNull(message = "Il titolo è obbligatorio")
        String titolo,
        @NotNull(message = "La descrizione è obbligatoria")
        String descrizione,
        @NotNull(message = "I posti disponibili sono obbligatori")
        int postiDisponibili,
        @NotNull(message = "La location è obbligatoria")
        String location,
        @NotNull(message = "La data è obbligatoria")
        String data
) {
}
