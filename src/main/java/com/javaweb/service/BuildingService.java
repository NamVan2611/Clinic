package com.javaweb.service;

import java.util.List;

import model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(String name, Long districtId);
}
