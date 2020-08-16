package demo.com.testemployees.screens.employees;

import java.util.List;

import demo.com.testemployees.pojo.Employee;

public interface EmployeesListView {

    void showData(List<Employee> employees);
    void showError();

}
