package fragnito.U5W3D5.controllers;

import fragnito.U5W3D5.entities.Prenotazione;
import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.Validation;
import fragnito.U5W3D5.payloads.PrenotazioneDTO;
import fragnito.U5W3D5.payloads.RespDTO;
import fragnito.U5W3D5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private Validation validation;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('UTENTE', 'ORGANIZZATORE')")
    public RespDTO postPrenotazione(@AuthenticationPrincipal Utente currentUtente, @RequestBody PrenotazioneDTO body) {
        Prenotazione newPrenotazione = this.prenotazioneService.savePrenotazione(body, currentUtente);
        return new RespDTO(newPrenotazione.getId());
    }

}
