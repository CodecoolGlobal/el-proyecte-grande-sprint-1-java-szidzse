-- Insert roles
INSERT INTO role (role_type) VALUES ('USER'), ('ADMIN');

-- Insert locations
INSERT INTO location (street, city, state, country, zip_code)
VALUES
    ('123 Main St', 'New York', 'NY', 'USA', '10001'),
    ('456 Park Ave', 'San Francisco', 'CA', 'USA', '94101'),
    ('789 Broadway', 'Los Angeles', 'CA', 'USA', '90001');

-- Insert a member
INSERT INTO member (first_name, last_name, email, password, phone_number)
VALUES ('Test', 'User', 'test@user.com', 'TestUser123!', '123-456-7890');

-- Assign roles to the member
INSERT INTO member_roles (member_id, role_id)
VALUES
    ((SELECT id FROM member WHERE email = 'test@user.com'), (SELECT id FROM role WHERE role_type = 'USER'));

-- Insert accommodations
INSERT INTO accommodation (name, description, room_number, price_per_night, max_guests, accommodation_type, location_id, owner_id)
VALUES
    ('Cozy Apartment', 'A small and cozy apartment in the city center', 2, 100.0, 4, 'APARTMENT', (SELECT id FROM location WHERE street = '123 Main St'), (SELECT id FROM member WHERE email = 'test@user.com')),
    ('Luxury Villa', 'A luxurious villa with sea view', 5, 500.0, 10, 'VILLA', (SELECT id FROM location WHERE street = '456 Park Ave'), (SELECT id FROM member WHERE email = 'test@user.com')),
    ('Budget Hostel', 'An affordable hostel for backpackers', 1, 30.0, 1, 'HOSTEL', (SELECT id FROM location WHERE street = '789 Broadway'), (SELECT id FROM member WHERE email = 'test@user.com'));
