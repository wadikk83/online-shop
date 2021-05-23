package by.wadikk.onlineshop.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//https://javarush.ru/groups/posts/2269-druzhim-obihchnihy-vkhod-cherez-email-i-oauth2-v-spring-security-na-primere-servisa-zametok

@Entity
@Data
@Table(name = "USER_TABLE", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private boolean isActive;

    private String googleName;
    private String googleUsername;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
