package kanatovm.bestclinic.utils.jwtauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JwtResponse.
 *
 * @author Kanatov Marat
 * Date: 02.12.2022
 */
@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
