CREATE TRIGGER employee_delete
  AFTER DELETE
  ON EMPLOYEES
  FOR EACH ROW
EXECUTE PROCEDURE log_employee_after_delete();