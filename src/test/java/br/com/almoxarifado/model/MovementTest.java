package br.com.almoxarifado.model;

import br.com.almoxarifado.exception.InvalidQuantityException;
import br.com.almoxarifado.model.Movement;
import br.com.almoxarifado.model.MovementType;
import br.com.almoxarifado.model.OriginType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


    @Test
    void createMovementQuantityInvalid() {
        assertThrows(InvalidQuantityException.class, () ->
                new Movement(-50, MovementType.ENTRY, OriginType.INVOICE, "123"));
    }


    @Test
    void cannotCreateMovementWithInvalidValues(){
        assertThrows(NullPointerException.class,()->
                new Movement(100,null,OriginType.INVOICE,"123"));

        assertThrows(NullPointerException.class,()->
                new Movement(100,MovementType.ENTRY,null,"123"));

        assertThrows(IllegalArgumentException.class,()->
                new Movement(100,MovementType.ENTRY,OriginType.INVOICE,""));


        assertThrows(NullPointerException.class,()->
                new Movement(100,MovementType.ENTRY,OriginType.INVOICE,null));


        assertThrows(IllegalArgumentException.class,()->
                new Movement(100,MovementType.ENTRY,OriginType.INVOICE,"  "));


    }

}
