package kanatovm.bestclinic.utils.jwtauth;

import lombok.Getter;
import lombok.Setter;

/**
 * JwtRequest.
 *
 * @author Kanatov Marat
 * Date: 02.12.2022
 */
@Getter
@Setter
public class JwtRequest {

    private String login;
    private String password;
}
