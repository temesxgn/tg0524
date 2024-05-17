CREATE SCHEMA IF NOT EXISTS toolrental;

USE toolrental;

CREATE TABLE IF NOT EXISTS tool_type
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    deleted    BOOLEAN               DEFAULT FALSE,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS brand
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    deleted    BOOLEAN               DEFAULT FALSE,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS tool
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    code         VARCHAR(10) NOT NULL,
    tool_type_id BIGINT      NOT NULL,
    brand_id     BIGINT      NOT NULL,
    deleted      BOOLEAN              DEFAULT FALSE,
    created_at   TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP   NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    UNIQUE (code),
    FOREIGN KEY (tool_type_id) REFERENCES tool_type (id),
    FOREIGN KEY (brand_id) REFERENCES brand (id)
);

CREATE INDEX idx_tools_by_type ON tool (tool_type_id);
CREATE INDEX idx_tools_by_brand ON tool (brand_id);

CREATE TABLE IF NOT EXISTS reference_number
(
    date        DATE PRIMARY KEY,
    last_number INT NOT NULL
);

CREATE TABLE IF NOT EXISTS rental_agreement
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    reference_number    VARCHAR(255)      NOT NULL,
    tool_code           VARCHAR(10)       NOT NULL,
    tool_type_id        BIGINT            NOT NULL,
    brand_id            BIGINT            NOT NULL,
    rental_length       SMALLINT UNSIGNED NOT NULL,
    checkout_date       TIMESTAMP         NOT NULL,
    due_date            TIMESTAMP         NOT NULL,
    daily_rental_charge DECIMAL(7, 2)     NOT NULL,
    num_of_charged_days SMALLINT UNSIGNED NOT NULL,
    pre_discount_charge DECIMAL(7, 2)     NOT NULL,
    discount_percent    SMALLINT          NOT NULL,
    discount_amount     DECIMAL(7, 2)     NOT NULL,
    final_charge        DECIMAL(7, 2)     NOT NULL,
    created_at          TIMESTAMP         NOT NULL DEFAULT NOW(),

    UNIQUE (reference_number),
    FOREIGN KEY (tool_code) REFERENCES tool (code),
    FOREIGN KEY (tool_type_id) REFERENCES tool_type (id),
    FOREIGN KEY (brand_id) REFERENCES brand (id)
);

CREATE TABLE IF NOT EXISTS daily_rental_charge
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    tool_type_id      BIGINT        NOT NULL,
    charge_amount     DECIMAL(7, 2) NOT NULL,
    charge_on_weekday BOOLEAN       NOT NULL,
    charge_on_weekend BOOLEAN       NOT NULL,
    charge_on_holiday BOOLEAN       NOT NULL,
    created_at        TIMESTAMP     NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP     NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    UNIQUE (tool_type_id),
    FOREIGN KEY (tool_type_id) REFERENCES tool_type (id)
);

CREATE INDEX idx_charges_by_type ON daily_rental_charge (tool_type_id);

CREATE TABLE IF NOT EXISTS holiday
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    date          DATE         NOT NULL,
    observed_date DATE         NOT NULL,
    year          YEAR         NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP    NOT NULL DEFAULT NOW() ON UPDATE NOW()
);

CREATE INDEX idx_holidays_by_observed_date ON holiday (observed_date);
CREATE INDEX idx_holidays_by_year ON holiday (year);
CREATE INDEX idx_holidays_by_year_observed_date ON holiday (year, observed_date);
