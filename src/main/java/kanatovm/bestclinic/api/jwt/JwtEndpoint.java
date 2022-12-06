package kanatovm.bestclinic.api.jwt;

import io.swagger.v3.oas.annotations.Operation;
import kanatovm.bestclinic.utils.jwtauth.JwtAuthService;
import kanatovm.bestclinic.utils.jwtauth.JwtRefreshRequest;
import kanatovm.bestclinic.utils.jwtauth.JwtRequest;
import kanatovm.bestclinic.utils.jwtauth.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JwtController.
 *
 * @author Kanatov Marat
 * Date: 02.12.2022
 */
@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtEndpoint {

    private final JwtAuthService authService;

    @PostMapping("login")
    @Operation(summary = "Auth and take token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    @Operation(summary = "Get new access token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRefreshRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    @Operation(summary = "Get new refresh token")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRefreshRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
