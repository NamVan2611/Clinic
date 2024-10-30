package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;

import model.BuildingDTO;

public interface BuildingRepository {
	List<BuildingEntity> findAll(String name, Long districtId);
}
