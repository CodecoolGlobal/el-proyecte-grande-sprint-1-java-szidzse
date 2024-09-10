DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS accommodation;
DROP TABLE IF EXISTS member_roles;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS location;

CREATE TABLE IF NOT EXISTS location
(
    id
    SERIAL
    PRIMARY
    KEY,
    street
    VARCHAR
(
    255
),
    city VARCHAR
(
    255
),
    state VARCHAR
(
    255
),
    country VARCHAR
(
    255
),
    zip_code VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXISTS role
(
    id
    SERIAL
    PRIMARY
    KEY,
    role_type
    VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXISTS member
(
    id
    SERIAL
    PRIMARY
    KEY,
    first_name
    VARCHAR
(
    255
),
    last_name VARCHAR
(
    255
),
    email VARCHAR
(
    255
) UNIQUE,
    password VARCHAR
(
    255
),
    phone_number VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXISTS member_roles
(
    member_id
    INT,
    role_id
    INT,
    FOREIGN
    KEY
(
    member_id
) REFERENCES member
(
    id
),
    FOREIGN KEY
(
    role_id
) REFERENCES role
(
    id
)
    );

CREATE TABLE IF NOT EXISTS accommodation
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
),
    description TEXT,
    room_number INT,
    price_per_night DOUBLE PRECISION,
    max_guests INT,
    accommodation_type VARCHAR
(
    255
),
    location_id INT,
    owner_id INT,
    FOREIGN KEY
(
    location_id
) REFERENCES location
(
    id
),
    FOREIGN KEY
(
    owner_id
) REFERENCES member
(
    id
)
    );

CREATE TABLE IF NOT EXISTS reservation
(
    id
    SERIAL
    PRIMARY
    KEY,
    start_date
    DATE,
    end_date
    DATE,
    accommodation_id
    INT,
    guest_id
    INT,
    FOREIGN
    KEY
(
    accommodation_id
) REFERENCES accommodation
(
    id
),
    FOREIGN KEY
(
    guest_id
) REFERENCES member
(
    id
)
    );

CREATE TABLE IF NOT EXISTS image
(
    id
    SERIAL
    PRIMARY
    KEY,
    url
    VARCHAR
(
    255
),
    accommodation_id INT,
    FOREIGN KEY
(
    accommodation_id
) REFERENCES accommodation
(
    id
)
    );
