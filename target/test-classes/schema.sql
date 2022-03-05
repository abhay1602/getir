DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer`
(
    `id` VARCHAR(20) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(25) NOT NULL,
    `last_name` VARCHAR(25) NOT NULL,
    `email` VARCHAR(55) NOT NULL UNIQUE,
--    `user_name` VARCHAR(255) NOT NULL UNIQUE,
--    `password` VARCHAR(255) NOT NULL,
    `created_date` DATETIME NOT NULL,
     PRIMARY KEY(`id`)
);


CREATE TABLE `address`
(
    `user_id` BIGINT NOT NULL,
    `address_line1` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `pin_code` NUMERIC(10) NOT NULL,
    `country` VARCHAR(255) NOT NULL,
     FOREIGN KEY (`user_id`) REFERENCES `customer`(`id`)
);

CREATE TABLE `orders`
(
    `user_id` VARCHAR(20) NOT NULL,
    `order_id` BIGINT NOT NULL,
    `amount` BIGINT NOT NULL,
    `created_date` DATETIME NOT NULL,
    PRIMARY KEY(`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES `customer`(`id`)
);

CREATE TABLE `book`
(
    `isbn` VARCHAR(20) NOT NULL,
    `book_name` VARCHAR(20) NOT NULL,
    `order_id` BIGINT NOT NULL,
    `price` BIGINT NOT NULL,
    `added_date` DATETIME NOT NULL,
    PRIMARY KEY(`isbn`),
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`)
);