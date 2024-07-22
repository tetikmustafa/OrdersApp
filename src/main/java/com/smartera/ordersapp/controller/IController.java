package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.service.IService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface IController<P> {
    public P save(P p);
    public P findById(int id);
    public List<P> findAll();
    public List<P> findByKeyword(String keyword);
    public P update(P p, int id);
    public String deleteById(int id);
    public String deleteAll();
}
