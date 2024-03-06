package com.cissst.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cissst.dao.IHouseDao;
import com.cissst.entity.House;
import com.cissst.util.DBUtil;

public class HouseDaoImpl implements IHouseDao{

	public List<House> getAllHouse() {
		
		String sql = "select * from house order by dep";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<House> list = new ArrayList<House>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				House h = new House();
				h.setId(rs.getInt("id"));
				h.setNum(rs.getString("num"));
				h.setDep(rs.getString("dep"));
				h.setType(rs.getString("type"));
				h.setArea(rs.getString("area"));
				h.setSell(rs.getString("sell"));
				h.setUnit(rs.getString("unit"));
				h.setFloor(rs.getString("floor"));
				h.setDirection(rs.getString("direction"));
				h.setMemo(rs.getString("memo"));
				h.setOwnerid(rs.getString("ownerid"));
				list.add(h);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	public List<House> getHouseByOwnerid(String oid) {
		String sql = "select * from house where ownerid = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<House> list = new ArrayList<House>();
		House h;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oid);
			rs = stmt.executeQuery();
			while(rs.next()){
				h = new House();
				h.setId(rs.getInt("id"));
				h.setNum(rs.getString("num"));
				h.setDep(rs.getString("dep"));
				h.setType(rs.getString("type"));
				h.setArea(rs.getString("area"));
				h.setSell(rs.getString("sell"));
				h.setUnit(rs.getString("unit"));
				h.setFloor(rs.getString("floor"));
				h.setDirection(rs.getString("direction"));
				h.setMemo(rs.getString("memo"));
				h.setOwnerid(rs.getString("ownerid"));
				list.add(h);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	public void add(House h) {
		
		String sql = "insert into house(num,dep,type,area,sell,unit,floor,direction,memo,ownerid) " +
							"values(?,?,?,?,?,?,?,?,?,?) ";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, h.getNum());
			stmt.setString(2, h.getDep());
			stmt.setString(3, h.getType());
			stmt.setString(4, h.getArea());
			stmt.setString(5, h.getSell());
			stmt.setString(6, h.getUnit());
			stmt.setString(7, h.getFloor());
			stmt.setString(8, h.getDirection());
			stmt.setString(9, h.getMemo());
			stmt.setString(10, h.getOwnerid());
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void update(House h) {
		String sql = " update house set num=?,dep=?,type=?,area=?,sell=?,unit=?,floor=?,direction=?,memo=?,ownerid=?" +
				" where id = ? ";
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, h.getNum());
			stmt.setString(2, h.getDep());
			stmt.setString(3, h.getType());
			stmt.setString(4, h.getArea());
			stmt.setString(5, h.getSell());
			stmt.setString(6, h.getUnit());
			stmt.setString(7, h.getFloor());
			stmt.setString(8, h.getDirection());
			stmt.setString(9, h.getMemo());
			stmt.setString(10, h.getOwnerid());
			stmt.setInt(11, h.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void delete(String id) {
		String sql ="delete  from house where id= ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	

	public House findById(String id) {
		String sql = "select * from house where id = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		House h = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while (rs.next()){
				h=new House();
				h.setId(rs.getInt("id"));
				h.setNum(rs.getString("num"));
				h.setDep(rs.getString("dep"));
				h.setType(rs.getString("type"));
				h.setArea(rs.getString("area"));
				h.setSell(rs.getString("sell"));
				h.setUnit(rs.getString("unit"));
				h.setFloor(rs.getString("floor"));
				h.setDirection(rs.getString("direction"));
				h.setMemo(rs.getString("memo"));
				h.setOwnerid(rs.getString("ownerid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return h;
	}
	
}
