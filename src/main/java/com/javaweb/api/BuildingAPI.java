package com.javaweb.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.service.BuildingService;

import CustomException.FieldRequiredException;
import model.BuildingDTO;
import model.ErrorResponseDTO;

@RestController
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getbuilding(@RequestParam(name="name", required = false) String name,
										@RequestParam(name="districtid", required = false) Long districtId,
										@RequestParam(name="typecode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(name, districtId);	
		return result;
	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)//@GetMapping
//	public BuildingDTO getbuilding(@RequestParam(value="name", required = false)String name,
//							@RequestParam(value="numberOfBasement", required = false)Integer numberOfBasement,
//							@RequestParam(value="ward", required = false)String ward,
//							@RequestParam(value="street", required = false)String street){
//		BuildingDTO result=new BuildingDTO();
//		result.setName(name);
//		result.setNumberOfBasement(numberOfBasement);
//		result.setWard(ward);
//		result.setStreet(street);
//		return result;
//		}
//	@GetMapping(value="api/building/")
//	public Object getbuilding(@RequestParam(value="name", required= false)String name,
//										 @RequestParam(value="numberOfBasement", required = false)Integer numberOfBasement,
//										 @RequestParam(value="ward", required = false)String ward,
//										 @RequestParam(value="street", required = false)String street){
//		try {
//			System.out.println(1/1);
//		} catch (Exception e) {
//			ErrorResponseDTO error = new ErrorResponseDTO();
//			error.setErr(e.getMessage());
//			List<String> detail = new ArrayList<String>();
//			detail.add("can not divine by zero");
//			error.setDetail(detail);
//			return error;
//		}
//		List<BuildingDTO> list=new ArrayList<BuildingDTO>();
//		BuildingDTO building1 =new BuildingDTO();
//		building1.setName("ACM buiding");
//		building1.setNumberOfBasement(1);
//		building1.setWard("Hiep Hoa");
//		building1.setStreet("Nhon");
//		BuildingDTO building2 = new BuildingDTO();
//		building2.setName("AWS building");
//		building2.setNumberOfBasement(2);
//		building2.setWard("Tan Mai");
//		building2.setStreet("Mai Phong");
//		list.add(building1);
//		list.add(building2);
//		return list;
//	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void postbuilding(@RequestBody Map<String,String> param){
//		System.out.println("ok"); 
//	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)//@PostMapping
//	public Object postbuilding2(@RequestBody BuildingDTO building){
//		validate(building);
//		return null;
//	}
	public void validate(BuildingDTO building) throws FieldRequiredException {
		if(building.getName().equals("") || building.getName() == null || building.getNumberOfBasement() == null)
			throw new FieldRequiredException("name or numberOfBasement is null");
	}
	@DeleteMapping(value="/api/building/{id}/{name}")
	public void deletebuilding(@PathVariable Integer id,
							@PathVariable String name) {
		System.out.println("deleted "+id +" "+ name);
	}
}
