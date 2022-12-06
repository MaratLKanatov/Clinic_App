package kanatovm.bestclinic.utils.jwtauth;

import lombok.Getter;
import lombok.Setter;

/**
 * JwtRefreshRequest.
 *
 * @author Kanatov Marat
 * Date: 02.12.2022
 */
@Getter
@Setter
public class JwtRefreshRequest {
    public String refreshToken;
}
