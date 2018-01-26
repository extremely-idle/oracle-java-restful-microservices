/* Copyright © 2017 Oracle and/or its affiliates. All rights reserved. */

package com.example.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);
    private static final EmployeeDAO eDao = new EmployeeListDAO();

    // Get all employees
    @RequestMapping(method = GET)
    public List<Employee> getAll() {
        return eDao.getAllEmployees();
    }

    // Get an employee
    @RequestMapping(method = GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {
        final Employee match = eDao.getEmployee(id);
        
        if (match != null) {
            return new ResponseEntity<>(match, OK);
        } else {
            return new ResponseEntity<>(null, NOT_FOUND);
        }
    }

    // Get employees by lastName (Week 2)
    @RequestMapping(method = GET, value = "/lastname/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {
        final List<Employee> employeeCollection = eDao.getByLastName(name);
        final HttpStatus statusCode = deriveStatusCodeFromCollection(employeeCollection);

        return new ResponseEntity(employeeCollection, statusCode);
    }

    // Get employee by title (Week 2)
    @RequestMapping(method = GET, value = "/title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title) {
        final List<Employee> employeeCollection = eDao.getByTitle(title);
        final HttpStatus statusCode = deriveStatusCodeFromCollection(employeeCollection);

        return new ResponseEntity(employeeCollection, statusCode);
    }

    // Get employee by dept (Week 2)
    @RequestMapping(method = GET, value = "/department/{department}")
    public ResponseEntity getByDepartment(@PathVariable String department) {
        final List<Employee> employeeCollection = eDao.getByDept(department);
        final HttpStatus statusCode = deriveStatusCodeFromCollection(employeeCollection);

        return new ResponseEntity(employeeCollection, statusCode);
    }

    private HttpStatus deriveStatusCodeFromCollection(List<Employee> employeeCollection) {
        return !employeeCollection.isEmpty() ? OK : NOT_FOUND;
    }

    // Add an employee
    @RequestMapping(method = POST)
    public ResponseEntity add(@RequestBody Employee employee) {
        final boolean wasOperationSuccessful = eDao.add(employee);

        if (wasOperationSuccessful) {
            return new ResponseEntity<>(null, CREATED);
        } else {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }

    // Update an employee
    @RequestMapping(method = PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Employee employee) {
        final boolean wasOperationSuccessful = eDao.update(id, employee);

        if (wasOperationSuccessful) {
            return new ResponseEntity<>(null, OK);
        } else {
            return new ResponseEntity<>(null, NOT_FOUND);
        }
    }

    // Delete a employee (Week 3)
    @RequestMapping(method = DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {
        final boolean wasOperationSuccessful = eDao.delete(id);

        if (wasOperationSuccessful) {
            return new ResponseEntity(null, OK);
        } else {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }
}