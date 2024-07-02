CREATE TABLE products (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

INSERT INTO products (name, description) VALUES ('Product 1', 'Description for Product 1');
INSERT INTO products (name, description) VALUES ('Product 2', 'Description for Product 2');