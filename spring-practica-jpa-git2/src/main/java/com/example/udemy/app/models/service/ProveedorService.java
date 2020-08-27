package com.example.udemy.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.udemy.app.models.dao.IProductoRepository;
import com.example.udemy.app.models.dao.IProveedorDao;
import com.example.udemy.app.models.dao.IProveedorRepository;
import com.example.udemy.app.models.entity.Proveedor;

@Service
public class ProveedorService implements IProveedorService{
	
	@Autowired
	private IProveedorDao proveedorDao;
	
	@Autowired
	IProveedorRepository proveedorRepository;
	

	@Override
	public void delete(Long id) {
		proveedorDao.deleteById(id);
		
	}

	@Override
	public void save(Proveedor proveedor) {
		proveedorDao.save(proveedor);
	}

	@Override
	public List<Proveedor> listProveedor() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Proveedor> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return proveedorDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly= true)
	public List<Proveedor> findByNombre(String term) {
		
		return proveedorDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor findOne(Long id) {
		
		return proveedorDao.findById(id).orElse(null);
	}

}
