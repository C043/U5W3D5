package fragnito.U5W3D5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String location;
    @Column(name = "posti_disponibili")
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Evento(String titolo, String descrizione, LocalDate data, String location, int postiDisponibili, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.location = location;
        this.postiDisponibili = postiDisponibili;
        this.utente = utente;
    }
}
