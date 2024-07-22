package com.smartera.ordersapp.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<P> {
    public void save(P p);
    public P findById(int id);
    public List<P> findAll();
    public List<P> findByKeyword(String keyword);
    public void update(P p);
    public void deleteById(int id);
    public void deleteAll();
}
