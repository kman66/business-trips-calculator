CREATE OR REPLACE FUNCTION log_employee_after_insert()
  RETURNS TRIGGER AS
$BODY$
BEGIN
  INSERT INTO EMPLOYEES_AUD (EVENT_DATE, EVENT_TYPE, NEW_ID, NEW_FORENAME, NEW_SURNAME)
    VALUES (CURRENT_TIMESTAMP, "INSERT", NEW.ID, NEW.FORENAME, NEW.SURNAME);
END;
$BODY$
LANGUAGE plpgsql;