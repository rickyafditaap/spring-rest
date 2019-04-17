package com.sister.siasat.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Integer id) {
        super("Mahasiswa dengan id '" + id + "' tidak ditemukan!");
    }

}
