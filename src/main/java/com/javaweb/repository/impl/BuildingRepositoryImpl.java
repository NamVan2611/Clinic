package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.google.protobuf.MapEntry;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.util.NumberUtil;
import com.javaweb.util.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sql.append(" inner join assignmentbuilding a on b.id = a.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			sql.append("inner join buildingrenttype br on b.id = br.buildingid ");
			sql.append("inner join renttype r on br.renttypeid = r.id ");
		}
		Long areaFrom = buildingSearchBuilder.getAreaFrom();
		Long areaTo = buildingSearchBuilder.getAreTo();
		if (areaFrom != null || areaTo != null) {
			sql.append("inner join rentarea ra on b.id = ra.buildingid ");
		}
	}

	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : field) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
							where.append(" and b. " + fieldName + " = " + value );
						}
						else {
							where.append(" and b. " + fieldName + " like '%" + value + "%' ");
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" and a.staffid = " + staffId);
		}
		Long areaFrom = buildingSearchBuilder.getAreaFrom();
		Long areaTo = buildingSearchBuilder.getAreTo();
		if (areaFrom != null || areaTo != null) {
			if (areaFrom != null) {
				where.append(" and ra.value >= " + areaFrom);
			}
			if (areaTo != null) {
				where.append(" and ra.value <= " + areaTo);
			}
		}
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getAreTo();
		if (rentPriceFrom != null || rentPriceTo != null) {
			if (rentPriceFrom != null) {
				where.append(" and b.rentprice >= " + rentPriceFrom);
			}
			if (rentPriceTo != null) {
				where.append(" and b.rentprice <= " + rentPriceTo);
			}
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" and( ");
			String sql = typeCode.stream().map(it -> "r.code like" + "'%" + it + "%'")
					.collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" )");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
				" SELECT b.id, b.name, b.districtid, b.street, b.ward, b.rentprice, b.floorarea, b.servicefee, b.brokeragefee, b.managerphonenumber FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY id ");
		sql.append(where);
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setDistrictId(rs.getLong("districtid"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setServiceFee(rs.getString("servicefee"));
				building.setBrokerageFee(rs.getString("brokeragefee"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
