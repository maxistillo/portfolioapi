package max.dev.portfolioapi.controller;

import lombok.AllArgsConstructor;
import max.dev.portfolioapi.dto.AuthenticationRequest;
import max.dev.portfolioapi.dto.AuthenticationResponse;
import max.dev.portfolioapi.dto.MailDTO;
import max.dev.portfolioapi.service.AuthService;
import max.dev.portfolioapi.service.EmailContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    EmailContactService emailContactService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/mail")
    public void sendMail(@RequestBody MailDTO request) {
        emailContactService.sendEmail(request.getToEmail(),
                request.getSubject(),
                request.getBody(),
                request.getFromEmail());
    }

}
