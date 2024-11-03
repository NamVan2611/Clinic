package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;

import model.BuildingDTO;

public interface BuildingRepository {
	List<BuildingEntity> findAll(Map<String, Object> param, List<String> typeCode);
}
