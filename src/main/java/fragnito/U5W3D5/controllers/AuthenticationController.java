package fragnito.U5W3D5.controllers;

import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.Validation;
import fragnito.U5W3D5.payloads.NewUserDTO;
import fragnito.U5W3D5.payloads.RespDTO;
import fragnito.U5W3D5.services.AuthenticationService;
import fragnito.U5W3D5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private Validation validation;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO registerUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Utente saved = this.utenteService.postUser(body);
        return new RespDTO(saved.getId());
    }
}
