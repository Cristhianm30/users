package com.pragma.powerup.domain.usecase.validations;

import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.domain.model.User;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidations {


    public void validateOwner(User user) {

        validateFields(user);
        validateAge(user.getBirthDate());
        validateEmail(user.getEmail());
        validateDocumentNumber(user.getDocumentNumber());
        validateCellPhone(user.getCellPhone(), 13);
    }

    public void validateEmployee(User user) {
        validateFields(user);
        validateEmail(user.getEmail());
        validateDocumentNumber(user.getDocumentNumber());
        validateCellPhone(user.getCellPhone(), 13);
    }

    public void validateClient(User user){
        validateFields(user);
        validateEmail(user.getEmail());
        validateDocumentNumber(user.getDocumentNumber());
        validateCellPhone(user.getCellPhone(),13);

    }


    private void validateAge(LocalDate birthDate) {
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidOwnerAgeException();
        }
    }

    private void validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException();
        }
    }

    private void validateDocumentNumber(String documentNumber) {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(documentNumber);
        if (!matcher.matches()) {
            throw new InvalidDocumentNumberException();
        }
    }

    private void validateCellPhone(String cellPhone, int maxLength) {
        String regex = "^\\+?\\d{1," + (maxLength - 1) + "}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellPhone);
        if (!matcher.matches()) {
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
