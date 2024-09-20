package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Prenotazione;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.BadRequestException;
import fragnito.U5W3D5.payloads.PrenotazioneDTO;
import fragnito.U5W3D5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoService eventoService;

    public Prenotazione savePrenotazione(PrenotazioneDTO body, Utente currentUtente) {
        Evento found = this.eventoService.getEventoById(body.id());
        this.prenotazioneRepository.filterByDataAndUtente(currentUtente.getId(), found.getData()).orElseThrow(() -> new BadRequestException("L'utente ha " +
                "già" +
                " un" +
                " evento in programma per la data richiesta"));
        return this.prenotazioneRepository.save(new Prenotazione(currentUtente, found));
    }
}
