USE toolrental;

-- Insert brands
INSERT INTO brand (name, created_at)
VALUES ('Stihl', now()),
       ('Werner', now()),
       ('DeWalt', now()),
       ('Ridgid', now());

-- Insert tool types
INSERT INTO tool_type (name, created_at)
VALUES ('Chainsaw', now()),
       ('Ladder', now()),
       ('Jackhammer', now());

-- Insert tools
INSERT INTO tool (code, tool_type_id, brand_id, created_at, updated_at)
SELECT 'CHNS', tt.id, b.id, NOW(), NOW()
FROM tool_type tt,
     brand b
WHERE tt.name = 'Chainsaw'
  AND b.name = 'Stihl'
  AND NOT EXISTS (SELECT code
                  FROM tool
                  WHERE code = 'CHNS');

-- Insert LADW Tool
INSERT INTO tool (code, tool_type_id, brand_id, created_at, updated_at)
SELECT 'LADW', tt.id, b.id, NOW(), NOW()
FROM tool_type tt,
     brand b
WHERE tt.name = 'Ladder'
  AND b.name = 'Werner'
  AND NOT EXISTS (SELECT code
                  FROM tool
                  WHERE code = 'LADW');

-- Insert JAKD Tool
INSERT INTO tool (code, tool_type_id, brand_id, created_at, updated_at)
SELECT 'JAKD', tt.id, b.id, NOW(), NOW()
FROM tool_type tt,
     brand b
WHERE tt.name = 'Jackhammer'
  AND b.name = 'DeWalt'
  AND NOT EXISTS (SELECT code
                  FROM tool
                  WHERE code = 'JAKD');

-- Insert JAKR Tool
INSERT INTO tool (code, tool_type_id, brand_id, created_at, updated_at)
SELECT 'JAKR', tt.id, b.id, NOW(), NOW()
FROM tool_type tt,
     brand b
WHERE tt.name = 'Jackhammer'
  AND b.name = 'Ridgid'
  AND NOT EXISTS (SELECT code
                  FROM tool
                  WHERE code = 'JAKR');

-- Insert daily rental charges
INSERT INTO daily_rental_charge (tool_type_id, charge_amount, charge_on_weekday, charge_on_weekend, charge_on_holiday, created_at, updated_at)
SELECT tt.id, 1.99, TRUE, TRUE, FALSE, NOW(), NOW()
FROM tool_type tt
WHERE tt.name = 'Ladder';

INSERT INTO daily_rental_charge (tool_type_id, charge_amount, charge_on_weekday, charge_on_weekend, charge_on_holiday, created_at, updated_at)
SELECT tt.id, 1.49, TRUE, FALSE, TRUE, NOW(), NOW()
FROM tool_type tt
WHERE tt.name = 'Chainsaw';

INSERT INTO daily_rental_charge (tool_type_id, charge_amount, charge_on_weekday, charge_on_weekend, charge_on_holiday, created_at, updated_at)
SELECT tt.id, 2.99, TRUE, FALSE, FALSE, NOW(), NOW()
FROM tool_type tt
WHERE tt.name = 'Jackhammer';

-- Insert holidays
-- Year 2015
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2015-07-04', '2015-07-03', 2015),
       ('Labor Day', '2015-09-07', '2015-09-07', 2015);

-- Year 2020
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2020-07-04', '2020-07-03', 2020),
       ('Labor Day', '2020-09-07', '2020-09-07', 2020);

-- Year 2024
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2024-07-04', '2024-07-04', 2024),
       ('Labor Day', '2024-09-02', '2024-09-02', 2024);

-- Year 2025
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2025-07-04', '2025-07-04', 2025),
       ('Labor Day', '2025-09-01', '2025-09-01', 2025);

-- Year 2026
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2026-07-04', '2026-07-03', 2026),
       ('Labor Day', '2026-09-07', '2026-09-07', 2026);

-- Year 2027
INSERT INTO holiday (name, date, observed_date, year)
VALUES ('Independence Day', '2027-07-04', '2027-07-05', 2027),
       ('Labor Day', '2027-09-06', '2027-09-06', 2027);

