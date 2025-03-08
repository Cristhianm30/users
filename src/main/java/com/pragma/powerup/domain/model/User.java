package com.pragma.powerup.domain.model;

import java.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String cellPhone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;

    public User (Long id, Role role, String password, LocalDate birthDate, String cellPhone, String documentNumber, String lastName, String name, String email) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.birthDate = birthDate;
        this.cellPhone = cellPhone;
        this.documentNumber = documentNumber;
        this.lastName = lastName;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
