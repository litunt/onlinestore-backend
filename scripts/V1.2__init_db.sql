CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE SEQUENCE IF NOT EXISTS product_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS cart_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS order_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS user_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS user_credentials_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS cart_item_id_seq START 1;

CREATE SEQUENCE IF NOT EXISTS order_item_id_seq START 1;

CREATE TYPE IF NOT EXISTS ORDER_STATUS AS ENUM ('CREATED', 'PROCESSING', 'SHIPPED', 'COMPLETED');

CREATE TABLE store_user
(
    id        INTEGER   DEFAULT nextval('user_id_seq') NOT NULL,
    name      VARCHAR(50)                              NOT NULL,
    reg_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP      NOT NULL,
    is_active INTEGER   DEFAULT 1                      NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_credentials
(
    id       INTEGER DEFAULT nextval('user_credentials_id_seq') NOT NULL,
    user_id  INTEGER                                            NOT NULL,
    username VARCHAR(50)                                        NOT NULL,
    password VARCHAR(128)                                       NOT NULL,
    CONSTRAINT pk_credentials PRIMARY KEY (id, user_id, username),
    CONSTRAINT fk_user_id_credentials FOREIGN KEY (user_id) REFERENCES store_user (id)
);

CREATE TABLE store_order
(
    id      BIGINT       DEFAULT nextval('order_id_seq') NOT NULL,
    user_id INTEGER                                      NOT NULL,
    status  ORDER_STATUS DEFAULT 'CREATED'               NOT NULL,
    created TIMESTAMP    DEFAULT CURRENT_TIMESTAMP       NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id),
    CONSTRAINT fk_user_id_order FOREIGN KEY (user_id) REFERENCES store_user (id)
);


CREATE TABLE product
(
    id               BIGINT DEFAULT nextval('product_id_seq') NOT NULL,
    name             VARCHAR(50)                              NOT NULL,
    price            NUMERIC(5, 2)                            NOT NULL,
    amount_available INTEGER                                  NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE cart
(
    id      INTEGER   DEFAULT nextval('cart_id_seq') NOT NULL,
    user_id INTEGER                                  NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP      NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id),
    CONSTRAINT fk_user_id_cart FOREIGN KEY (user_id) REFERENCES store_user (id)
);

CREATE TABLE order_item
(
    id         INTEGER DEFAULT nextval('order_item_id_seq') NOT NULL,
    order_id   BIGINT                                       NOT NULL,
    product_id BIGINT                                       NOT NULL,
    quantity   INTEGER                                      NOT NULL,
    CONSTRAINT pk_order_item PRIMARY KEY (id),
    CONSTRAINT fk_order_id_order_item FOREIGN KEY (order_id) REFERENCES store_order (id),
    CONSTRAINT fk_product_id_order_item FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE cart_item
(
    id         INTEGER DEFAULT nextval('cart_item_id_seq') NOT NULL,
    cart_id    INTEGER                                     NOT NULL,
    product_id INTEGER                                     NOT NULL,
    quantity   INTEGER                                     NOT NULL,
    CONSTRAINT pk_cart_item PRIMARY KEY (id),
    CONSTRAINT fk_cart_id_cart_item FOREIGN KEY (cart_id) REFERENCES cart (id),
    CONSTRAINT fk_product_id_cart_item FOREIGN KEY (product_id) REFERENCES product (id)
);
