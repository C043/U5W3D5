package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.NotFoundException;
import fragnito.U5W3D5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente findByEmail(String email) {
        Utente found = this.utenteRepository.findByEmail(email);
        if (found == null) throw new NotFoundException("Utente non trovato");
        return found;
    }
}
