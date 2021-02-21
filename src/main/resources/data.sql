/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
------                      -                          -------
--          INITIALIZE H2 DATABASE WITH SAMPLE DATA         --
------                      -                          -------

-- DROP AUTO GENERATED TABLES IF EXISTS
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;

-- CREATE USERS TABLE --
CREATE TABLE users (
    identifier VARCHAR PRIMARY KEY,
    email VARCHAR NOT NULL UNIQUE,
    first_name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    creation_date TIMESTAMP with time zone DEFAULT NOW(),
    last_update_date TIMESTAMP with time zone
);
INSERT INTO users(identifier, email, first_name, surname) VALUES
  ('cf8d3518-d503-449a-9093-3a4819b01589', 'ibrasherr@cab.com', 'Ibrahim', 'SHERIFF'),
  ('c24b8345-bed1-4f3a-b4db-577498ef06ce', 'alikdangote@cement.com', 'Aliko', 'DANGOTE'),
  ('cf23ff12-e26b-4e5a-b71a-ebc80d8da0b5', 'idriscompil0@cab.com', 'Idris', 'COMPILATOR');


-- CREATE ACCOUNTS TABLE --
CREATE TABLE accounts (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    owner_identifier VARCHAR NOT NULL,
    account_number VARCHAR NOT NULL UNIQUE,
    balance DOUBLE PRECISION DEFAULT 0,
    is_primary_account BOOLEAN DEFAULT false,
    creation_date TIMESTAMP with time zone DEFAULT NOW(),
    last_update_date TIMESTAMP with time zone,
    CONSTRAINT user_account_fk FOREIGN KEY (owner_identifier) REFERENCES users(identifier)
);
INSERT INTO accounts(owner_identifier, account_number, balance, is_primary_account) VALUES
    ('cf8d3518-d503-449a-9093-3a4819b01589', '8b45d3cd-4f8a-4b1a-bac6-9b57693203f2', 21532.32, true),
    ('c24b8345-bed1-4f3a-b4db-577498ef06ce', '0ae7422c-8b18-4ffd-a31f-9f91d439bcbe', 0, true),
    ('cf23ff12-e26b-4e5a-b71a-ebc80d8da0b5', 'aa0dddbe-25c0-4ce3-b64e-571457e4347d', -123, false),
    ('cf23ff12-e26b-4e5a-b71a-ebc80d8da0b5', 'b830ca86-a827-41b1-92d6-32896a3bac39', 323, true);


-- CREATE TRANSACTIONS TABLE --
CREATE TABLE transactions (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER NOT NULL,
    transaction_number VARCHAR NOT NULL UNIQUE,
    amount DOUBLE PRECISION NOT NULL,
    issuer_ref VARCHAR NOT NULL,
    issuer_full_name VARCHAR NOT NULL,
    reason VARCHAR NOT NULL,
    initiation_date TIMESTAMP with time zone DEFAULT NOW(),
    cancellation_date TIMESTAMP with time zone,
    last_update_date TIMESTAMP with time zone,
    CONSTRAINT account_transaction_fk FOREIGN KEY (account_id) REFERENCES accounts(id)
);
INSERT INTO transactions(account_id, transaction_number, amount, issuer_ref, issuer_full_name, reason) VALUES
    (1, 'd66bd650-abaf-46d9-aec9-cd65c9fdeb50', 21532.32, 'CAB-BRUXELLES-XTG512OG', 'CAPGEMINI AMAZING BANK BXL', 'Secret project'),
    (3, '741db341-c7dd-466b-b5fe-550e108b6dde', -123, 'PAYPAL-COMPANY-DG0XXT', 'PAYPAL COMPANY', 'PAYPAL DEBIT $123'),
    (4, '27357da6-d989-421a-ab3e-90dc6d94950b', 80, 'CAB-LYON-R56TREO', 'CAPGEMINI AMAZING BANK LYON', 'ONBOARDING OFFER'),
    (4, '744aba94-b5dc-4aff-95ab-b1927beedcc8', 243, 'TRANSACTION-GABI-PK-CXDFD545666', 'GABY PARKER', 'My Debt regularization');