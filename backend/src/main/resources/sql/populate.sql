-- Location Table
INSERT INTO location (id, street, city, state, country, zip_code) VALUES
(1, '123 Main St', 'Springfield', 'IL', 'USA', '62701'),
(2, '456 Elm St', 'Shelbyville', 'IL', 'USA', '62565');

-- Member Table
INSERT INTO member (id, first_name, last_name, email, password, phone_number) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', 'password123', '555-1234'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456', '555-5678');

-- Accommodation Table
INSERT INTO accommodation (id, name, description, room_number, price_per_night, max_guests, accommodation_type, location_id, owner_id) VALUES
(1, 'Cozy Cottage', 'A charming cottage with beautiful garden', 3, 100.0, 4, 'HOUSE', 1, 1),
(2, 'Urban Flat', 'Modern flat in the heart of the city', 2, 80.0, 2, 'FLAT_APARTMENT', 2, 2);

-- Reservation Table
INSERT INTO reservation (id, start_date, end_date, accommodation_id, guest_id) VALUES
(1, '2024-08-01', '2024-08-07', 1, 2),
(2, '2024-09-10', '2024-09-15', 2, 1);
