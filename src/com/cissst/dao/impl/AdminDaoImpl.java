package com.cissst.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cissst.dao.IAdminDao;
import com.cissst.entity.Admin;
import com.cissst.util.DBUtil;

public class AdminDaoImpl implements IAdminDao{
	public List<Admin> getAllAdmin() {
		
		String sql = "select * from admin order by name";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Admin> list = new ArrayList<Admin>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Admin a = new Admin();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				a.setAge(rs.getInt("age"));
				a.setSex(rs.getString("sex"));
				a.setTel(rs.getString("tel"));
				a.setPhone(rs.getString("phone"));
				a.setAddr(rs.getString("addr"));
				a.setMemo(rs.getString("memo"));
				list.add(a);
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
	
	
	
	
	
	public void save(Admin a) {
		
		String sql = "insert into admin(ID,NAME,PASSWORD,SEX,AGE,TEL,PHONE,ADDR,MEMO) " +
							"values(sys_guid(),?,?,?,?,?,?,?,?) ";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getName());
			stmt.setString(2, a.getPassword());
			stmt.setString(3, a.getSex());
			stmt.setInt(4, (Integer) a.getAge());
			stmt.setString(5, a.getTel());
			stmt.setString(6, a.getPhone());
			stmt.setString(7, a.getAddr());
			stmt.setString(8, a.getMemo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
	
	
	
	
	public Admin getAdminById(String id) {
		
		String sql = "select * from admin where id = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Admin a = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				a = new Admin();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				a.setAge(rs.getInt("age"));
				a.setSex(rs.getString("sex"));
				a.setTel(rs.getString("tel"));
				a.setPhone(rs.getString("phone"));
				a.setAddr(rs.getString("addr"));
				a.setMemo(rs.getString("memo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return a;
	}
	
	
	
	
	public void update(Admin a) {
		String sql = " update admin  set  NAME = ?,PASSWORD = ?,SEX = ?,AGE = ?, TEL = ?," +
				"PHONE = ?,ADDR = ?,MEMO = ? where id = ? ";
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, a.getName());
			stmt.setString(2, a.getPassword());
			stmt.setString(3, a.getSex());
			stmt.setInt(4, (Integer) a.getAge());
			stmt.setString(5, a.getTel());
			stmt.setString(6, a.getPhone());
			stmt.setString(7, a.getAddr());
			stmt.setString(8, a.getMemo());
			stmt.setInt(9, a.getId());
			
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
	
	
	
	public void delete(String id) {
		String sql ="delete  from admin where id= ?";
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





	public Admin getAdminBynp(String name, String password) {
		String sql = "select * from admin where name = ? and password = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Admin a = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while(rs.next()){
				a = new Admin();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPassword(rs.getString("password"));
				a.setAge(rs.getInt("age"));
				a.setSex(rs.getString("sex"));
				a.setTel(rs.getString("tel"));
				a.setPhone(rs.getString("phone"));
				a.setAddr(rs.getString("addr"));
				a.setMemo(rs.getString("memo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return a;
	}
}
