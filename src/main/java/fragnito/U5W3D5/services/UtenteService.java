package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Ruoli;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.BadRequestException;
import fragnito.U5W3D5.exceptions.NotFoundException;
import fragnito.U5W3D5.payloads.NewUserDTO;
import fragnito.U5W3D5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente findByEmail(String email) {
        Utente found = this.utenteRepository.findByEmail(email);
        if (found == null) throw new NotFoundException("Utente non trovato");
        return found;
    }

    public Utente postUser(NewUserDTO body) {
        Utente found = this.utenteRepository.findByEmail(body.email());
        if (found != null) throw new BadRequestException("Utente già esistente");
        return this.utenteRepository.save(new Utente(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()), Ruoli.valueOf(body.ruolo())));
    }

    public Utente getUtenteById(int id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente con id: " + id + " non trovato"));
    }
}
