package fragnito.U5W3D5.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "posti_disponibili")
    private int postiDisponibili;
    private String location;

    public Evento(String titolo, int postiDisponibili, String location) {
        this.titolo = titolo;
        this.postiDisponibili = postiDisponibili;
        this.location = location;
    }
}
