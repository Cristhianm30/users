package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IPasswordEncoder;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.validations.UserValidations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private UserValidations userValidations;

    @Mock
    private IRoleServicePort roleServicePort;

    @Mock
    private IPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserUseCase userUseCase;

    private User dummyUser;
    private Role ownerRole;
    private Role employeeRole;
    private Role clientRole;

    @BeforeEach
    void setUp() {
        ownerRole = new Role(1L, "PROPIETARIO");
        employeeRole = new Role(2L, "EMPLEADO");
        clientRole = new Role(3L, "CLIENTE");
        // Se crea un usuario de ejemplo; el rol se asignará en el use case.
        dummyUser = new User(null, null, "plainPassword", LocalDate.of(1990, 1, 1),
                "+123456789", "1234567890", "Doe", "John", "john.doe@example.com");
    }

    @Test
    void testCreateOwnerSuccessfully() {
        // Simula que la validación se ejecuta sin errores
        doNothing().when(userValidations).validateOwner(dummyUser);
        // Stub: obtener rol propietario
        when(roleServicePort.getOwnerRole()).thenReturn(ownerRole);
        // Stub: encriptar contraseña, se espera que se pase "plainPassword"
        when(passwordEncoder.encode(dummyUser.getPassword())).thenReturn("encodedPassword");
        // Stub: guardar usuario y retornar usuario guardado con id asignado
        User savedUser = new User(1L, ownerRole, "encodedPassword", dummyUser.getBirthDate(),
                dummyUser.getCellPhone(), dummyUser.getDocumentNumber(),
                dummyUser.getLastName(), dummyUser.getName(), dummyUser.getEmail());
        when(userPersistencePort.save(dummyUser)).thenReturn(savedUser);

        // Ejecutar el caso de uso
        User result = userUseCase.createOwner(dummyUser);

        // Verificar interacciones y resultado
        verify(userValidations, times(1)).validateOwner(dummyUser);
        verify(roleServicePort, times(1)).getOwnerRole();
        // Verificamos que se invoque encode con el valor original "plainPassword"
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userPersistencePort, times(1)).save(dummyUser);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(ownerRole, result.getRole());
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    void testCreateEmployeeSuccessfully() {
        doNothing().when(userValidations).validateEmployee(dummyUser);
        when(roleServicePort.getEmployeeRole()).thenReturn(employeeRole);
        when(passwordEncoder.encode(dummyUser.getPassword())).thenReturn("encodedPassword");
        User savedUser = new User(2L, employeeRole, "encodedPassword", dummyUser.getBirthDate(),
                dummyUser.getCellPhone(), dummyUser.getDocumentNumber(),
                dummyUser.getLastName(), dummyUser.getName(), dummyUser.getEmail());
        when(userPersistencePort.save(dummyUser)).thenReturn(savedUser);

        User result = userUseCase.createEmployee(dummyUser);

        verify(userValidations, times(1)).validateEmployee(dummyUser);
        verify(roleServicePort, times(1)).getEmployeeRole();
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userPersistencePort, times(1)).save(dummyUser);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals(employeeRole, result.getRole());
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    void testCreateClientSuccessfully() {
        doNothing().when(userValidations).validateClient(dummyUser);
        when(roleServicePort.getClientRole()).thenReturn(clientRole);
        when(passwordEncoder.encode(dummyUser.getPassword())).thenReturn("encodedPassword");
        User savedUser = new User(3L, clientRole, "encodedPassword", dummyUser.getBirthDate(),
                dummyUser.getCellPhone(), dummyUser.getDocumentNumber(),
                dummyUser.getLastName(), dummyUser.getName(), dummyUser.getEmail());
        when(userPersistencePort.save(dummyUser)).thenReturn(savedUser);

        User result = userUseCase.createClient(dummyUser);

        verify(userValidations, times(1)).validateClient(dummyUser);
        verify(roleServicePort, times(1)).getClientRole();
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userPersistencePort, times(1)).save(dummyUser);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals(clientRole, result.getRole());
        assertEquals("encodedPassword", result.getPassword());
    }


    @Test
    void testGetUserById() {
        // Preparamos un usuario simulado
        User user = new User(1L, ownerRole, "encodedPassword", dummyUser.getBirthDate(),
                dummyUser.getCellPhone(), dummyUser.getDocumentNumber(),
                dummyUser.getLastName(), dummyUser.getName(), dummyUser.getEmail());
        when(userPersistencePort.findById(1L)).thenReturn(user);

        // Ejecutamos el método y verificamos
        User result = userUseCase.getUserById(1L);
        verify(userPersistencePort, times(1)).findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetUserByEmail() {
        // Preparamos un usuario simulado
        User user = new User(1L, ownerRole, "encodedPassword", dummyUser.getBirthDate(),
                dummyUser.getCellPhone(), dummyUser.getDocumentNumber(),
                dummyUser.getLastName(), dummyUser.getName(), dummyUser.getEmail());
        when(userPersistencePort.getUserByEmail("john.doe@example.com")).thenReturn(user);

        // Ejecutamos el método y verificamos
        User result = userUseCase.getUserByEmail("john.doe@example.com");
        verify(userPersistencePort, times(1)).getUserByEmail("john.doe@example.com");
        assertNotNull(result);
        assertEquals("john.doe@example.com", result.getEmail());
    }
}
