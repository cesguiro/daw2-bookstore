CREATE TABLE publishers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    base_price DECIMAL(10, 2) NOT NULL,
    discount_percentage DECIMAL(4, 2) DEFAULT 0,
    cover VARCHAR(255),
    publication_date DATE,
    publisher_id BIGINT NOT NULL,
    CONSTRAINT fk_book_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES publishers(id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE book_translations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    locale VARCHAR(5) NOT NULL,
    title VARCHAR(255) NOT NULL,
    synopsis TEXT,
    book_id BIGINT NOT NULL,
    UNIQUE KEY uk_book_locale (book_id, locale),
    CONSTRAINT fk_book_translation_book
        FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE authors (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL,
     slug VARCHAR(255) UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE author_translations (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     locale VARCHAR(5) NOT NULL,
     biography TEXT NOT NULL,
     author_id BIGINT NOT NULL,
     UNIQUE KEY uk_author_locale (author_id, locale),
     CONSTRAINT fk_author_translation_author
         FOREIGN KEY (author_id)
             REFERENCES authors(id)
             ON DELETE CASCADE
             ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE book_authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT uk_book_author UNIQUE (book_id, author_id),
    CONSTRAINT fk_book_authors_book
        FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_book_authors_author
        FOREIGN KEY (author_id)
            REFERENCES authors(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    address VARCHAR(100),
    locale VARCHAR(5) NOT NULL DEFAULT 'es'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE user_roles(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    rol_id INT NOT NULL,
    UNIQUE KEY uk_user_rol (user_id, rol_id),
    CONSTRAINT fk_user_rol_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_user_roles_rol
        FOREIGN KEY (rol_id)
            REFERENCES roles(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_date DATETIME NOT NULL,
    delivery_date DATETIME,
    total DECIMAL(10, 2) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE cascade
            ON UPDATE cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE status_translations (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     locale VARCHAR(5) NOT NULL,
     name VARCHAR(50) NOT NULL, -- Nombre legible para el usuario (traducido)
     status_id INT NOT NULL,
     UNIQUE KEY uk_status_locale (status_id, locale),
     CONSTRAINT fk_status_translation_status
         FOREIGN KEY (status_id)
             REFERENCES status(id)
             ON DELETE CASCADE
             ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;


CREATE TABLE order_status (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    status_id INT NOT NULL,
    status_change_date DATETIME NOT NULL,
    CONSTRAINT fk_order_status_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_order_status_status
        FOREIGN KEY (status_id)
            REFERENCES status(id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE order_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    unit_selling_price DECIMAL(10, 2) NOT NULL,
    order_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    CONSTRAINT uk_order_book UNIQUE (order_id, book_id),
    CONSTRAINT fk_order_details_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_order_details_book
        FOREIGN KEY (book_id)
            REFERENCES books(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;