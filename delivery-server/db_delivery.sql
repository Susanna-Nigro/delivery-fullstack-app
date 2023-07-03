use delivery;

CREATE TABLE depots (
    depot_id int NOT NULL AUTO_INCREMENT,
    address varchar(255) DEFAULT NULL,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (depot_id)
);

INSERT INTO depots(address, name) VALUES ("Via Roma 123", "Deposito A"), ("Via Milano 456", "Deposito B"), ("Via Napoli 789", "Deposito C");

CREATE TABLE suppliers (
    supplier_id int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (supplier_id)
);

INSERT INTO suppliers (name) VALUES ("Supplier X");
INSERT INTO suppliers (name) VALUES ("Supplier Y");
INSERT INTO suppliers (name) VALUES ("Supplier Z");

CREATE TABLE orders (
    order_id int NOT NULL AUTO_INCREMENT,
    created_at datetime(6) DEFAULT NULL,
    shipping_address varchar(255) DEFAULT NULL,
    status enum("DELIVERED","IN_TRANSIT","TO_DELIVER") DEFAULT NULL,
    depot_id int DEFAULT NULL,
    supplier_id int DEFAULT NULL,
    customer varchar(255) DEFAULT NULL,
    PRIMARY KEY (order_id),
    KEY FKa9mxo3yl7060gsn3fm4tcvnrj (depot_id),
    KEY FKg2540vs5sg5b0uov81t6p0229 (supplier_id),
    CONSTRAINT FKa9mxo3yl7060gsn3fm4tcvnrj FOREIGN KEY (depot_id) REFERENCES depots (depot_id),
    CONSTRAINT FKg2540vs5sg5b0uov81t6p0229 FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id)
);

INSERT INTO orders (shipping_address, status, depot_id, supplier_id) VALUES ("Piazza Novecento 2", "TO_DELIVER", "1", "1");
INSERT INTO orders (shipping_address, status, depot_id, supplier_id) VALUES ("Piazza Garibaldi 5", "IN_TRANSIT", "1", "3");
INSERT INTO orders (shipping_address, status, depot_id, supplier_id) VALUES ("Via Verdi 12", "DELIVERED", "1", "2");
INSERT INTO orders (shipping_address, status, depot_id, supplier_id) VALUES ("Piazza del Popolo 33", "TO_DELIVER", "2", "1");
INSERT INTO orders (shipping_address, status, depot_id, supplier_id) VALUES ("Corso Vittorio Emanuele 18", "TO_DELIVER", "1", "1");


CREATE TABLE packages (
    package_id int NOT NULL AUTO_INCREMENT,
    weight double DEFAULT NULL,
    order_id int DEFAULT NULL,
    PRIMARY KEY (package_id),
    KEY FKgdud05n5i92kx4619ff729x44 (order_id),
    CONSTRAINT FKgdud05n5i92kx4619ff729x44 FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

INSERT INTO packages (weight, order_id) VALUES ("2.5", "4");
INSERT INTO packages (weight, order_id) VALUES ("1.8", "1");
INSERT INTO packages (weight, order_id) VALUES ("3.2", "5");
INSERT INTO packages (weight, order_id) VALUES ("0.9", "2");
INSERT INTO packages (weight, order_id) VALUES ("4.7", "3");
