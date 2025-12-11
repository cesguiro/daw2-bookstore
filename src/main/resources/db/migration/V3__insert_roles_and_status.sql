-- Insert roles
INSERT INTO roles (name) VALUES
     ('USER'),
     ('ADMIN');

-- Insert status (Estados del pedido, el 0 es el Carrito de Compra)
INSERT INTO status (code) VALUES
    ('SHOPPING_CART'),
    ('ORDERED'),
    ('IN_PROCESS'),
    ('SENT'),
    ('RECEIVED');

INSERT INTO status_translations (status_id, locale, name) VALUES
    -- Español
    (1, 'es', 'Carrito de Compra'),
    (2, 'es', 'Pedido Realizado'),
    (3, 'es', 'En Proceso'),
    (4, 'es', 'Enviado'),
    (5, 'es', 'Recibido'),
    -- Inglés
    (1, 'en', 'Shopping Cart'),
    (2, 'en', 'Ordered'),
    (3, 'en', 'In Process'),
    (4, 'en', 'Sent'),
    (5, 'en', 'Received');