package com.pragma.powerup.domain.usecase.validations;

import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.domain.model.User;

import java.time.LocalDate;

public class UserValidations {


    public void validateOwner(User user) {

        validateFields(user);
        validateAge(user);
        validateEmail(user);
        validateDocumentNumber(user);
        validateCellPhone(user);
    }

    public void validateEmployee(User user) {
        validateFields(user);
        validateEmail(user);
        validateDocumentNumber(user);
        validateCellPhone(user);
    }

    public void validateClient(User user){
        validateFields(user);
        validateEmail(user);
        validateDocumentNumber(user);
        validateCellPhone(user);

    }


    private void validateAge(User user) {

        if (user.getBirthDate() == null) {
            throw new InvalidUserFieldsException();
        }

        if (user.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidOwnerAgeException();
        }
    }

    private void validateEmail(User user) {

        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new InvalidEmailException();
        }
    }

    private void validateDocumentNumber(User user) {

        if (user.getDocumentNumber() == null || !user.getDocumentNumber().matches("^\\d{10}$")){
            throw new InvalidDocumentNumberException();
        }
    }

    private void validateCellPhone(User user) {

        if (user.getCellPhone() == null || !user.getCellPhone().matches("^\\+?\\d{1," + (13 - 1) + "}$")){
            throw new InvalidCellPhoneException();
        }
    }


    private void validateFields(User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            throw new InvalidUserFieldsException();
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new InvalidUserFieldsException();
        }
        if (user.getDocumentNumber() == null || user.getDocumentNumber().isBlank()) {
            throw new InvalidUserFieldsException();
        }
        if (user.getCellPhone() == null || user.getCellPhone().isBlank()) {
            throw new InvalidUserFieldsException();
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidUserFieldsException();
        }
    }

}
