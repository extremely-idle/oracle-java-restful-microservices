/* Copyright © 2017 Oracle and/or its affiliates. All rights reserved. */

package com.example.rest;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();

    Employee getEmployee(long id);

    List<Employee> getByLastName(String name);

    List<Employee> getByTitle(String title);

    List<Employee> getByDept(String dept);

    boolean add(Employee employee);  // False equals fail

    boolean update(long id, Employee employee); // False equals fail

    boolean delete(long id); // False equals fail
}