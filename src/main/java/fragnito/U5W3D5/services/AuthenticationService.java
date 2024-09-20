package fragnito.U5W3D5.services;

import fragnito.U5W3D5.entities.Utente;
import fragnito.U5W3D5.exceptions.UnauthorizedException;
import fragnito.U5W3D5.payloads.AuthenticationDTO;
import fragnito.U5W3D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    public String generateToken(@RequestBody @Validated AuthenticationDTO body) {
        Utente found = this.utenteService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
