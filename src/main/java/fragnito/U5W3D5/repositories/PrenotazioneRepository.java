package fragnito.U5W3D5.repositories;

import fragnito.U5W3D5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
}
