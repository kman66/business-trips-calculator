CREATE TRIGGER employee_insert
  AFTER INSERT
  ON EMPLOYEES
  FOR EACH ROW
  EXECUTE PROCEDURE log_employee_after_insert();