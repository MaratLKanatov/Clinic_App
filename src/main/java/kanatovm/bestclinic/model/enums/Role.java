package kanatovm.bestclinic.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    DOCTOR(List.of(Permission.DOCTOR_INDEX.getPermission())),
    PATIENT(List.of(Permission.PATIENT_INDEX.getPermission())),
    ADMIN(List.of(Permission.ADMIN_INDEX.getPermission()));

    private final List<String> authoritiesList;

    Role(List<String> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    public List<SimpleGrantedAuthority> getAuthoritiesList() {
        return authoritiesList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getStringPermissions() {
        String permissions = authoritiesList.get(0);
        for (int i=1; i<authoritiesList.size(); i++) {
            permissions += ", " + authoritiesList.get(i);
        }
        return permissions;
    }
}
