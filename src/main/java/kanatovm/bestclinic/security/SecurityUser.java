package kanatovm.bestclinic.security;

import kanatovm.bestclinic.model.entities.User;
import kanatovm.bestclinic.model.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SecurityUser implements UserDetails {
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;
    private boolean status;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean status) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public static UserDetails fromUser(User user) {
        UserDetails this_user;

        if (user == null) {
            this_user = new org.springframework.security.core.userdetails.User("none", "none", false, false, false, false, new HashSet<GrantedAuthority>());
            System.out.println("MY_LOG: Oops, we cannot find such user!!!");
        } else
        this_user = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthoritiesList());

        return this_user;
    }
}
