package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.VinInterfaceTab;
import com.app.repositories.VinInterfaceTabRepository;

@Service
public class VinInterfaceServiceImpl implements VinInterfaceService {

	@Autowired
	private VinInterfaceTabRepository repository;
	
	@Override
	public VinInterfaceTab save(VinInterfaceTab vit) {
		repository.save(vit);
		return vit;
	}

	@Override
	public List<VinInterfaceTab> getAllVinInterfaceTab() {
		return repository.findAll();
	}

}
