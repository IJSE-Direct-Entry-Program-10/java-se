CREATE TABLE IF NOT EXISTS Item(
    code INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    buying_price DECIMAL(7, 2) NOT NULL,
    selling_price DECIMAL(7, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Item_Picture(
    item_code INT PRIMARY KEY,
    preview MEDIUMBLOB NOT NULL,
    CONSTRAINT fk_item_pic FOREIGN KEY (item_code) REFERENCES Item(code)
);

INSERT INTO Item (description, buying_price, selling_price, stock) VALUES
       ('Mouse', 700, 1200, 5),
       ('Keyboard', 1200, 1400, 8);