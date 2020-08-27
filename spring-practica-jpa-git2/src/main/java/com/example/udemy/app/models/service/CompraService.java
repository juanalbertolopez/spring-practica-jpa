package com.example.udemy.app.models.service;

import java.util.List;  


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.udemy.app.models.dao.ICompraDao;
import com.example.udemy.app.models.dao.IProductoDao;
import com.example.udemy.app.models.dao.IProductoRepository;
import com.example.udemy.app.models.entity.Compra;
import com.example.udemy.app.models.entity.Producto;

@Service
public class CompraService implements ICompraService {
	
	@Autowired
	private ICompraDao compraDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	IProductoRepository compraRepository;


	@Override
	@Transactional
	public void delete(Long id) {
		compraDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void save(Compra compra) {
		compraDao.save(compra);
		
	}

	@Override
	@Transactional
	public List<Compra> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Compra findOne(Long id) {

		return compraDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Compra findCompraById(Long id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Compra> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return compraDao.findAll(pageable);
	}

	
}
