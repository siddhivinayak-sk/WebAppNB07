package com.app.services;

import java.util.List;

import com.app.models.VinInterfaceTab;

public interface VinInterfaceService {
	public VinInterfaceTab save(VinInterfaceTab vit);
	public List<VinInterfaceTab> getAllVinInterfaceTab();
}
