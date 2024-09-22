package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.BadRequestException;
import fragnito.U5W3D5.exceptions.NotFoundException;
import fragnito.U5W3D5.exceptions.UnauthorizedException;
import fragnito.U5W3D5.payloads.NewEventoDTO;
import fragnito.U5W3D5.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento saveEvento(NewEventoDTO body, Utente currentUtente) {
        if (!LocalDate.parse(body.data()).isAfter(LocalDate.now())) throw new BadRequestException("La data impostata è già passata");
        return this.eventoRepository.save(new Evento(body.titolo(), body.descrizione(), LocalDate.parse(body.data()), body.location(), body.postiDisponibili(),
                currentUtente));
    }

    public List<Evento> getAllEventi() {
        return this.eventoRepository.findAll().stream().filter(evento -> evento.getData().isAfter(LocalDate.now())).toList();
    }

    public Evento updateEvento(int id, NewEventoDTO body, Utente currentUtente) {
        Evento found = this.getEventoById(id);
        if (found.getUtente().getId() != currentUtente.getId()) throw new UnauthorizedException("Non hai i permessi per modificare questo evento");
        found.setData(LocalDate.parse(body.data()));
        found.setLocation(body.location());
        found.setDescrizione(body.descrizione());
        found.setTitolo(body.titolo());
        found.setPostiDisponibili(body.postiDisponibili());
        return this.eventoRepository.save(found);
    }

    public Evento getEventoById(int id) {
        return this.eventoRepository.findById(id).orElseThrow(() -> new NotFoundException("Evento con id: " + id + " non trovato"));
    }

    public void deleteEvento(int id, Utente currentUtente) {
        Evento found = this.getEventoById(id);
        if (currentUtente.getId() != found.getUtente().getId()) throw new UnauthorizedException("Non hai i permessi per eliminare questo evento");
    }

    public List<Evento> getAllUserEvent(Utente currentUtente) {
        return this.eventoRepository.getAllUserEvents(currentUtente.getId());
    }
}
