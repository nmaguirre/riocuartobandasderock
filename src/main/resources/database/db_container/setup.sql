CREATE USER rock_db_owner WITH PASSWORD 'rockenrio4';
CREATE DATABASE rcrockbands;
\connect rcrockbands
GRANT ALL PRIVILEGES ON DATABASE rcrockbands TO rock_db_owner;
