package fragnito.U5W3D5.repositories;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("SELECT p.evento FROM Prenotazione p WHERE p.utente.id = :id")
    List<Evento> getAllUserEvents(int id);

    List<Evento> findByUtente(Utente utente);
}
