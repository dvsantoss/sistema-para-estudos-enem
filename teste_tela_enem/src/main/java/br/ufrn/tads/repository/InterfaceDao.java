package br.ufrn.tads.repository;

import java.util.List;

public interface InterfaceDao<T>{
    
    Object findByName(String name);
    Object findById(Long id);
    List<T> findAll();
    boolean save(T t);
    boolean update(T t, String[] params);
    boolean delete(Long id);
}
