package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Prenotazione;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.BadRequestException;
import fragnito.U5W3D5.exceptions.NotFoundException;
import fragnito.U5W3D5.exceptions.UnauthorizedException;
import fragnito.U5W3D5.payloads.PrenotazioneDTO;
import fragnito.U5W3D5.repositories.EventoRepository;
import fragnito.U5W3D5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    public Prenotazione savePrenotazione(PrenotazioneDTO body, Utente currentUtente) {
        Evento found = this.eventoService.getEventoById(body.id());
        if (this.prenotazioneRepository.filterByDataAndUtente(currentUtente.getId(), found.getData()).isPresent()) throw new BadRequestException("L'utente ha " +
                "già" +
                " un" +
                " evento in programma per la data richiesta");
        if (found.getPostiDisponibili() == 0) throw new UnauthorizedException("L'evento è al completo");
        found.setPostiDisponibili(found.getPostiDisponibili() - 1);
        this.eventoRepository.save(found);
        return this.prenotazioneRepository.save(new Prenotazione(currentUtente, found));
    }

    public Prenotazione getPrenotazioneById(int id) {
        return this.prenotazioneRepository.filterByEventoId(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }

    public void deletePrenotazione(Utente currentUtente, int id) {
        Prenotazione found = this.getPrenotazioneById(id);
        if (currentUtente.getId() != found.getUtente().getId()) throw new UnauthorizedException("Non hai i permessi per cancellare questa prenotazione");
        Evento evento = this.eventoService.getEventoById(id);
        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        this.eventoRepository.save(evento);
        this.prenotazioneRepository.delete(found);
    }
}
