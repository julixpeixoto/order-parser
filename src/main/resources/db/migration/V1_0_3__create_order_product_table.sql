CREATE TABLE IF NOT EXISTS order_product
(
    id INT(11) AUTO_INCREMENT NOT NULL,
    order_id INT(11) NOT NULL,
    product_id INT(11) NOT NULL,
    product_sold_value FLOAT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);