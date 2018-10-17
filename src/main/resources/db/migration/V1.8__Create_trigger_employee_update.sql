CREATE TRIGGER employee_update
  AFTER INSERT
  ON EMPLOYEES
  FOR EACH ROW
EXECUTE PROCEDURE log_employee_after_update();