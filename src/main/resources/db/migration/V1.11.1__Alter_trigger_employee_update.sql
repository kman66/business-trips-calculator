DROP TRIGGER employee_update ON EMPLOYEES;

CREATE TRIGGER employee_update
  AFTER UPDATE
  ON EMPLOYEES
  FOR EACH ROW
EXECUTE PROCEDURE log_employee_after_update();