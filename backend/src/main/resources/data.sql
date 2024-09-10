-- Töröld a meglévő adatokat
TRUNCATE TABLE location RESTART IDENTITY CASCADE;
TRUNCATE TABLE accommodation RESTART IDENTITY CASCADE;
TRUNCATE TABLE role RESTART IDENTITY CASCADE;
TRUNCATE TABLE member RESTART IDENTITY CASCADE;
TRUNCATE TABLE image RESTART IDENTITY CASCADE;
TRUNCATE TABLE reservation RESTART IDENTITY CASCADE;
TRUNCATE TABLE member_roles RESTART IDENTITY CASCADE;

INSERT INTO member (id, first_name, last_name, email, password, phone_number) VALUES
(1, 'Test', 'User', 'test@user.com', 'TestUser123!', '+36 70 123 4567');

INSERT INTO role (id, role_type) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO location (id, street, city, state, country, zip_code) VALUES
(1, 'Széchenyi István tér 5-6', 'Budapest', 'null', 'HU', '1051'),
(2, 'Sikfőkút út 5-7', 'Noszvaj', 'null', 'HU', '3325');

INSERT INTO accommodation (id, name, description, room_number, price_per_night, max_guests, accommodation_type, location_id, owner_id) VALUES
(1, 'Four Seasons Hotel Gresham Palace', 'A five star hotel next to the Danube.', 4, 500.0, 2, 'HOTEL', 1, 1),
(2, 'Nomad Hotel & Glamping', 'A nomad village for the people who like nature.', 3, 80.0, 2, 'TINY_HOME', 2, 1);

INSERT INTO member_roles (member_id, role_id) VALUES
(1, 1);
