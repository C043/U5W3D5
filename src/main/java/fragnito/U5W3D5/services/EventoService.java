package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.BadRequestException;
import fragnito.U5W3D5.payloads.NewEventoDTO;
import fragnito.U5W3D5.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento saveEvento(NewEventoDTO body, Utente currentUtente) {
        if (!LocalDate.parse(body.data()).isAfter(LocalDate.now())) throw new BadRequestException("La data impostata è già passata");
        return this.eventoRepository.save(new Evento(body.titolo(), body.descrizione(), LocalDate.parse(body.data()), body.location(), body.postiDisponibili(),
                currentUtente));
    }
}
