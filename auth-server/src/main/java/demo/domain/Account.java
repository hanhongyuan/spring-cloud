package demo.domain;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by João on 24/06/2016.
 */

@Entity
public class Account implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 300)
    @NotNull
    @Email
    @NaturalId
    private String username;

    @Column(nullable = false, length = 300)
    @NotNull
    private String password;

    @Column(nullable = false)
    @NotNull
    private Boolean active = true;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    @ElementCollection(targetClass = Authority.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "account_authority")
    @Column(name = "authority")
    private Set<Authority> authorities = new HashSet<>();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account() {

    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void encryptPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(authorities.stream().map(a -> a.toString()).collect(Collectors.joining(",")));
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
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
