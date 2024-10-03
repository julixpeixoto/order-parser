CREATE TABLE IF NOT EXISTS tb_product_sold
(
    id BIGINT(20) AUTO_INCREMENT NOT NULL,
    product_id BIGINT(20) NOT NULL,
    sold_value DECIMAL(15, 2) NOT NULL,
    order_id BIGINT(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES tb_order(id)
);