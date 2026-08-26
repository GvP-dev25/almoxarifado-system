package br.com.almoxarifado.model;

import java.util.UUID;
import java.time.LocalDateTime;

public class Movement {
    private UUID uuid;
    private MovementType movementType;
    private LocalDateTime date;
    private int quantity;
    private String originNumber;
    private OriginType originType;


    public Movement(int quantity, MovementType type, OriginType originType, String originNumber) {
        uuid = UUID.randomUUID();
        this.quantity = quantity;
        this.movementType = type;
        this.originType = originType;
        this.originNumber = originNumber;
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

    public String getOriginNumber() {
        return originNumber;
    }

    public OriginType getOriginType() {
        return originType;
    }
}
