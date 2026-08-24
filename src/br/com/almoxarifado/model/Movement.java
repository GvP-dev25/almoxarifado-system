package br.com.almoxarifado.model;

import java.util.UUID;
import java.time.LocalDateTime;

public class Movement {
    private UUID uuid;
    private MovementType movementType;
    private LocalDateTime date;
    private int quantity;
    private String description;


    public Movement(int quantity, MovementType type, String description) {
        uuid = UUID.randomUUID();
        this.quantity = quantity;
        this.movementType = type;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

}
