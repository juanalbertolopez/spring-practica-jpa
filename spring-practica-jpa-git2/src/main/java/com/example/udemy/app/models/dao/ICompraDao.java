package com.example.udemy.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.udemy.app.models.entity.Compra;

public interface ICompraDao extends PagingAndSortingRepository<Compra,Long > {

}
