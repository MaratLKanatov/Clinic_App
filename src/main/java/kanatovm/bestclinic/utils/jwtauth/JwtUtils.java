package kanatovm.bestclinic.utils.jwtauth;

import io.jsonwebtoken.Claims;
import kanatovm.bestclinic.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JwtUtils.
 *
 * @author Kanatov Marat
 * Date: 02.12.2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final Role roles = claims.get("roles", Role.class);
        return Collections.singleton(roles);
    }
}
