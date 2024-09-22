package com.poloit.grupo12.inscripciones.service.interfaces;

public interface IEncryptService {
    String encryptPassword(String password);
    boolean verifyPassword(String password, String passwordEncriptada);

}
