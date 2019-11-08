package com.example.carros.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

@Entity
@Data                      //Implementando a classe UserDetails na nossa classe de usuários
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String login;
    private String senha;
    private String email;    
    
    //Define o tipo de associação
    //FetchType.EAGER: O oposto do Lazy, carrega os dados mesmo que não os utilize
    @ManyToMany(fetch = FetchType.EAGER)
    //Tabela de associação de usuário x roles
    @JoinTable(name = "user_roles",
                                    //Define a associação da tabela em questão
    		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
     		                              //Define a associação da tabela de role
    		inverseJoinColumns = @JoinColumn(name = "roler_id", referencedColumnName = "id"))
    //Usuário tem uma lista de roles
    private List<Role> roles;

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123");
        System.out.print(pwd);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {		
		return senha;
	}

	@Override
	public String getUsername() {		
		return login;
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
