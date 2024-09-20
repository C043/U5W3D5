package fragnito.U5W3D5.controllers;

import fragnito.U5W3D5.repositories.UtenteRepository;
import fragnito.U5W3D5.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UtenteRepository utenteRepository;
}
