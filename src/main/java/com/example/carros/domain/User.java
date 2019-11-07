package com.example.carros.domain;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String login;
    private String senha;
    private String email;    

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");
        System.out.print(pwd);
    }
}
