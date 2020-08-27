package com.example.udemy.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.udemy.app.models.dao.IProductoDao;
import com.example.udemy.app.models.dao.IProductoRepository;
import com.example.udemy.app.models.entity.Cliente;
import com.example.udemy.app.models.entity.Producto;

@Service
public class ProductoService implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	IProductoRepository productoRepository;

	@Override
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}

	@Override
	public void save(Producto producto) {
		productoDao.save(producto);		
	}

	@Override
	@Transactional(readOnly= true)
	public List<Producto> findByNombre(String term) {
		// TODO Auto-generated method stub
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return productoDao.findAll(pageable);
	}


	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public void saveAll(Iterable<Producto> productos) {
		productoDao.saveAll(productos);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findProductoById(Long id) {
		// TODO Auto-generated method stub
		return  productoDao.findById(id).orElse(null);
	}

	

}
