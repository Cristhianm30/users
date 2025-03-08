package com.pragma.powerup.infrastructure.out.jpa.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "cell_phone", nullable = false)
    private String cellPhone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
