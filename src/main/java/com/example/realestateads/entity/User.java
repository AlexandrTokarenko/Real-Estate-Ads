package com.example.realestateads.entity;

import com.example.realestateads.dto.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "patronymic", length = 50)
    private String patronymic;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "activation_code", length = 6)
    private String activationCode;
    @Column(name = "effective_time")
    private Timestamp effectiveTime;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "verified_email")
    private Boolean isVerify;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isVerify;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

   /* @OneToMany(mappedBy = "userEmail")
    private Set<Advertisement> advertisements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userEmail")
    private Set<LockUser> lockUsers = new LinkedHashSet<>();*/

    public static User fromUserDTO(UserRegisterDTO userRegisterDTO) {

        return User.builder()
                .email(userRegisterDTO.getEmail())
                .password(userRegisterDTO.getPassword())
                .lastname(userRegisterDTO.getLastname())
                .firstname(userRegisterDTO.getFirstname())
                .phoneNumber(userRegisterDTO.getPhoneNumber())
                .patronymic(userRegisterDTO.getPatronymic())
                .role(Role.USER)
                .isVerify(false)
                .build();
    }
}