package fragnito.U5W3D5.repositories;

import fragnito.U5W3D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Utente findByEmail(String email);
}
