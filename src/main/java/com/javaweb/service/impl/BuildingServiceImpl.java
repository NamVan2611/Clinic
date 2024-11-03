package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;
import model.BuildingDTO;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> param, List<String> typeCode) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(param, typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictId());
			building.setAddress(item.getStreet() + ", " + item.getWard() + "," + districtEntity.getName());
			result.add(building);
		}
		return result;
	}

}
