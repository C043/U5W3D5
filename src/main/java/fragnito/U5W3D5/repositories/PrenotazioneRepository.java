package fragnito.U5W3D5.repositories;

import fragnito.U5W3D5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    @Query("SELECT p FROM Prenotazione p WHERE p.utente.id = :id AND p.evento.data = :data")
    Optional<Prenotazione> filterByDataAndUtente(int id, LocalDate data);

    @Query("SELECT p FROM Prenotazione p WHERE p.evento.id = :id")
    Optional<Prenotazione> filterByEventoId(int id);
}
