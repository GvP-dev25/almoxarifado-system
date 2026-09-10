package br.com.almoxarifado.jdbc;

import br.com.almoxarifado.exception.BranchProductNotFoundException;
import br.com.almoxarifado.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BranchProductRepository {


    public BranchProduct findBranchProduct(int id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sql = """
                SELECT
                	p.id_product,
                    p.code as product_code,
                    p.name as product_name,
                    b.id_branch,
                    b.code as branch_code,
                    b.name as branch_name,
                    m.id_movement,
                    m.date,
                    m.movementType,
                	m.originType,
                    m.originNumber,
                    m.quantity as move_quantity,
                    bp.id_branchProduct,
                	bp.quantity as bp_quantity,
                	bp.location
                FROM branch_product bp
                LEFT JOIN product p
                    ON bp.product_id = p.id_product
                LEFT JOIN branch b
                    ON bp.branch_id = b.id_branch
                LEFT JOIN movement m
                    ON bp.id_branchProduct = m.fk_branchProduct
                WHERE bp.id_branchProduct = ?
                ORDER BY m.date ASC;
                """;
        try (Connection c = databaseConnection.connect();
             PreparedStatement preparedStatement = c.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                String productCode, productDescription, branchCode, branchName, branchProductLocation,
                        movementType, movementOriginType, movementOriginNumber, movementUuid;
                int productId, branchId, branchProductId, branchProductQuantity, movementQuantity;


                if (resultSet.next() == false) {
                    throw new BranchProductNotFoundException();
                }

                productId = resultSet.getInt("id_product");
                productCode = resultSet.getString("product_code");
                productDescription = resultSet.getString("product_name");
                Product product = new Product(productCode, productDescription);
                product.assignId(productId);

                branchId = resultSet.getInt("id_branch");
                branchCode = resultSet.getString("branch_code");
                branchName = resultSet.getString("branch_name");
                Branch branch = new Branch(branchCode, branchName);
                branch.assignId(branchId);

                branchProductId = resultSet.getInt("id_branchProduct");
                branchProductQuantity = resultSet.getInt("bp_quantity");
                branchProductLocation = resultSet.getString("location");

                movementType = resultSet.getString("movementType");
                LocalDateTime m_date = resultSet.getObject("date", LocalDateTime.class);
                movementOriginType = resultSet.getString("originType");
                movementOriginNumber = resultSet.getString("originNumber");
                movementQuantity = resultSet.getInt("move_quantity");
                movementUuid = resultSet.getString("id_movement");
                List<Movement> movementList = new ArrayList<Movement>();

                if (movementUuid != null) {
                    UUID uuid = UUID.fromString(movementUuid);
                    Movement movement = Movement.reconstructMovement(uuid, m_date, movementQuantity, MovementType.valueOf(movementType), OriginType.valueOf(movementOriginType), movementOriginNumber);
                    movementList.add(movement);

                    while (resultSet.next()) {
                        movementUuid = resultSet.getString("id_movement");
                        if (movementUuid == null) {
                            break;
                        }
                        uuid = UUID.fromString(movementUuid);
                        m_date = resultSet.getObject("date", LocalDateTime.class);
                        movementQuantity = resultSet.getInt("move_quantity");
                        movementType = resultSet.getString("movementType");
                        movementOriginType = resultSet.getString("originType");
                        movementOriginNumber = resultSet.getString("originNumber");
                        movement = Movement.reconstructMovement(uuid, m_date, movementQuantity, MovementType.valueOf(movementType), OriginType.valueOf(movementOriginType), movementOriginNumber);
                        movementList.add(movement);
                    }
                }
                //Reconstrução do branchProduct após reconstrução dos objetos e lista relacionado
                BranchProduct branchProduct = BranchProduct.reconstructor(branchProductId, product, branch, branchProductQuantity, branchProductLocation, movementList);
                return branchProduct;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }


}
