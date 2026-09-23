CREATE DATABASE gaminghub
USE gaminghub;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('CUSTOMER', 'ADMIN') NOT NULL DEFAULT 'CUSTOMER',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE platforms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image_path VARCHAR(255),
    release_date DATE,
    category_id INT NOT NULL,
    platform_id INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id),

    CONSTRAINT fk_product_platform
        FOREIGN KEY (platform_id)
        REFERENCES platforms(id)
);


CREATE TABLE product_keys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    activation_key VARCHAR(255) NOT NULL UNIQUE,
    sold BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_key_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);


CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,

    billing_name VARCHAR(100) NOT NULL,
    billing_email VARCHAR(100) NOT NULL,
    billing_address VARCHAR(255) NOT NULL,

    payment_method VARCHAR(50) NOT NULL,

    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT,
    product_name VARCHAR(100) NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    activation_key VARCHAR(255),

    CONSTRAINT fk_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id),

    CONSTRAINT fk_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);