package br.com.almoxarifado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

public class MovementTest {


    @Test
    void createMovement() {
        Movement movement = new Movement(100, MovementType.ENTRY, OriginType.REQUEST, "123");
        assertEquals(100, movement.getQuantity());
        assertEquals(MovementType.ENTRY, movement.getMovementType());
        assertEquals(OriginType.REQUEST, movement.getOriginType());
        assertEquals("123", movement.getOriginNumber());
        assertNotNull(movement.getUuid());
        assertNotNull(movement.getDate());
    }
}
