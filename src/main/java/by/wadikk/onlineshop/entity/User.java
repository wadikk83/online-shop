package by.wadikk.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Min(value = 3, message = "")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;

}