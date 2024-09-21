package fragnito.U5W3D5.controllers;

import fragnito.U5W3D5.entities.Evento;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.Validation;
import fragnito.U5W3D5.payloads.NewEventoDTO;
import fragnito.U5W3D5.payloads.RespDTO;
import fragnito.U5W3D5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public RespDTO postEvento(@RequestBody @Validated NewEventoDTO body, BindingResult validation, @AuthenticationPrincipal Utente currentUtente) {
        this.validation.validate(validation);
        Evento saved = this.eventoService.saveEvento(body, currentUtente);
        return new RespDTO(saved.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE', 'UTENTE')")
    public List<Evento> getEventi() {
        return this.eventoService.getAllEventi();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public RespDTO putEvento(@PathVariable int id, @RequestBody @Validated NewEventoDTO body, BindingResult validation, @AuthenticationPrincipal Utente currentUtente) {
        this.validation.validate(validation);
        Evento updatedEvent = this.eventoService.updateEvento(id, body, currentUtente);
        return new RespDTO(updatedEvent.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable int id, @AuthenticationPrincipal Utente currentUtente) {
        this.eventoService.deleteEvento(id, currentUtente);
    }
}
