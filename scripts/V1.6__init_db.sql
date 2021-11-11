CREATE SEQUENCE product_id_seq START 1;

CREATE SEQUENCE cart_id_seq START 1;

CREATE SEQUENCE order_id_seq START 1;

CREATE SEQUENCE user_id_seq START 1;

CREATE SEQUENCE user_credentials_id_seq START 1;

CREATE SEQUENCE cart_item_id_seq START 1;

CREATE SEQUENCE order_item_id_seq START 1;

CREATE TABLE store_user
(
    id        INTEGER   DEFAULT nextval('user_id_seq') NOT NULL,
    name      VARCHAR(50)                              NOT NULL,
    reg_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP      NOT NULL,
    is_active BOOLEAN   DEFAULT TRUE                   NOT NULL,
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
    id      BIGINT      DEFAULT nextval('order_id_seq') NOT NULL,
    user_id INTEGER                                     NOT NULL,
    status  VARCHAR(18) DEFAULT 'CREATED'               NOT NULL,
    created TIMESTAMP   DEFAULT CURRENT_TIMESTAMP       NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id),
    CONSTRAINT fk_user_id_order FOREIGN KEY (user_id) REFERENCES store_user (id)
);

CREATE TABLE product
(
    id               BIGINT DEFAULT nextval('product_id_seq') NOT NULL,
    name             VARCHAR(50)                              NOT NULL,
    pet_type         VARCHAR(10)                              NOT NULL,
    category         VARCHAR(24)                              NOT NULL,
    description      TEXT   DEFAULT 'No description'          NOT NULL,
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

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Mr. Fishy', 'CAT', 'TOYS', 'Cute fish toy for your cat.', 5.39, 140);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Fur Ball', 'CAT', 'TOYS', 'Fur ball that looks like a mouse.', 3.98, 320);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Mouse Teaser Stick', 'CAT', 'TOYS', 'Teaser stick especially for active cats with cute mouse.', 11.29, 100);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Christmas Pattern Bow', 'CAT', 'ACCESSORIES',
        'A cute bow to make your cat look exceptionally representative for upcoming Christmas season.', 4.30, 270);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Bunny Ear Plush Hat', 'CAT', 'ACCESSORIES',
        'An extraordinary hat with bunny ears to make your cat even cuter.', 7.80, 55);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Collar With Flower', 'CAT', 'ACCESSORIES', 'Collar with beautiful flower shaped charm.', 6.33, 87);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Cartoon Bear Socks', 'CAT', 'CLOTHING', 'Funny socks to warm you cats paws this winter.', 11.99, 132);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Cloud Print Hoodie', 'CAT', 'CLOTHING', 'Warm and comfy hoodie with a hood and cloud print.', 23.57, 36);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Neon Pink Rain Coat', 'CAT', 'CLOTHING',
        'Extra bright rain coat for comfortable outdoor walks in any weather.', 34.19, 58);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Mr. Avo', 'DOG', 'TOYS', 'Cute avocado shaped toy for your playful dog.', 4.39, 200);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Cartoon Chicken Chew Toy', 'DOG', 'TOYS', 'Chicken shaped gum toy for dogs.', 11.28, 123);

INSERT INTO product (name, pet_type, category, description, price, amount_available)
VALUES ('Yellow Bite Rope', 'DOG', 'TOYS', 'Braided bite rope for dogs with knot.', 14.35, 58);