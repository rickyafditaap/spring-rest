package com.sister.siasat.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(Integer id) {
        super("Program Studi dengan id '" + id + "' tidak ditemukan!");
    }

}
