package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    private Role ownerRole;

    @BeforeEach
    void setUp() {
        ownerRole = new Role(1L, "PROPIETARIO");
    }

    @Test
    void testGetOwnerRoleSuccessfully() {
        // Simulamos que se encuentra el rol "PROPIETARIO"
        when(rolePersistencePort.findByName("PROPIETARIO")).thenReturn(Optional.of(ownerRole));

        // Ejecutamos el método
        Role result = roleUseCase.getOwnerRole();

        // Verificamos la interacción y resultado
        verify(rolePersistencePort, times(1)).findByName("PROPIETARIO");
        assertNotNull(result);
        assertEquals("PROPIETARIO", result.getName());
    }

    @Test
    void testGetOwnerRoleThrowsExceptionWhenNotFound() {
        // Simulamos que no se encuentra el rol "PROPIETARIO"
        when(rolePersistencePort.findByName("PROPIETARIO")).thenReturn(Optional.empty());

        // Verificamos que se lance la excepción correspondiente
        assertThrows(RoleNotFoundException.class, () -> roleUseCase.getOwnerRole());
        verify(rolePersistencePort, times(1)).findByName("PROPIETARIO");
    }
}