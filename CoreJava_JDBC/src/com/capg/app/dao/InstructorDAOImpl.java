package com.capg.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capg.app.beans.Instructor;
import com.capg.app.dto.InstructorsLocationCountDTO;
import com.capg.app.exception.InstructorNotFoundException;

public class InstructorDAOImpl implements InstructorDAO {

	Connection con;
	PreparedStatement ps;
	
	public InstructorDAOImpl(){
		con = DatabaseUtil.con; // no need to create Util Class for all users/ one shared class
	}
	
	
	
	@Override
	public boolean insertInstructor(Instructor instructor)throws SQLException {
		
		boolean isInserted = false;
		
		int code = instructor.getInstructorCode();
		String name = instructor.getName();
		String email = instructor.getEmail();
		String location = instructor.getTrainerLocation();
		int salary = instructor.getSalary();
		int jobStartYear = instructor.getJobStartYear();
		
		
		String query = "insert into Instructor1 values(?,?,?,?,?,?)";
		
		ps = con.prepareStatement(query);
		ps.setInt(1, code);
		ps.setString(2,name);
		ps.setInt(3, salary);
		ps.setInt(4, jobStartYear);
		ps.setString(5,email);
		ps.setString(6,location);
		
		int i = ps.executeUpdate();
		
		if(i == 1) isInserted = true;
		
		return isInserted;
	}

	@Override
	public Instructor getInstructorByCode(int code) throws InstructorNotFoundException,SQLException {
		String sqlQuery = "select * from instructor1 where instructorCode = ?";
        ps = con.prepareStatement(sqlQuery);
		ps.setInt(1,code);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Instructor i = new Instructor(rs.getInt("instructorCode"),rs.getString("name"),rs.getInt("salary"),rs.getInt("jobStartYear"),rs.getString("emailId"),rs.getString("location"));
		return i;
	}

	@Override
	public List<Instructor> getInstructors() throws SQLException {
		
		
		String sqlQuery = "select * from instructor1";
		ps = con.prepareStatement(sqlQuery);
		
		ResultSet rs = ps.executeQuery();
		List<Instructor> list = new ArrayList<>();
		while(rs.next())
		{
			int code = rs.getInt("instructorCode");
			String name = rs.getString("name");
			String email = rs.getString("emailId");
			String location = rs.getString("location");
			int salary = rs.getInt("salary");
			int jobStartYear = rs.getInt("jobStartYear");
			
		
			list.add(new Instructor(code, name, salary, jobStartYear, email, location));
			
		}
		return list;
	}

	@Override
	public List<Instructor> getInstructorsByLocation(String loc) throws SQLException{
		String sqlQuery = "select * from instructor1 where location = ?";
		ps = con.prepareStatement(sqlQuery);
		ps.setString(1,loc);
		ResultSet rs = ps.executeQuery();
		List<Instructor> list = new ArrayList<>();
		
		while(rs.next())
		{
			int code = rs.getInt("instructorCode");
			String name = rs.getString("name");
			String email = rs.getString("emailId");
			String location = rs.getString("location");
			int salary = rs.getInt("salary");
			int jobStartYear = rs.getInt("jobStartYear");
			
		
			list.add(new Instructor(code, name, salary, jobStartYear, email, location));
		}
		return list;
	}

	@Override
	public List<InstructorsLocationCountDTO> getInstructorsCountByLocation() throws SQLException{
		// TODO Auto-generated method stub
		String query="select location, count(*) as count from instructor1 group by location";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		List<InstructorsLocationCountDTO> list=new ArrayList<>();
		while(rs.next()) {
			String loc=rs.getString("location");
			int count=rs.getInt("count");
			list.add(new InstructorsLocationCountDTO(loc,count));
		}
		return list;
	}

	@Override
	public List<Instructor> getInstructorsBySalary() throws SQLException{
		// TODO Auto-generated method stub
		List<Instructor> list=new ArrayList<>();
		String query="select * from instructor1 order by salary asc";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			int code = rs.getInt("instructorCode");
			String name = rs.getString("name");
			String email = rs.getString("emailId");
			String location = rs.getString("location");
			int salary = rs.getInt("salary");
			int jobStartYear = rs.getInt("jobStartYear");
			
		
			list.add(new Instructor(code, name, salary, jobStartYear, email, location));
		}
		return list;
	}

	@Override
	public boolean delteInstructorByCode(int code) throws InstructorNotFoundException, SQLException {
		// TODO Auto-generated method stub
		boolean isDeleted=false;
		String query="delete from instructor1 where instructorCode = ?";
		ps=con.prepareStatement(query);
		ps.setInt(1, code);
		int i=ps.executeUpdate();
		if(i==1) isDeleted=true;
		return isDeleted;
	}

	@Override
	public Instructor updateInstructor(Instructor oldInfoInstructor) throws SQLException{
		String query="update instructor1 set name = ? where instructorCode = ?";
		ps=con.prepareStatement(query);
		ps.setString(1,"Tyrion");
//		ps.setString(2, "tyrion@t.com");
//		ps.setString(3,"D");
		ps.setInt(2, oldInfoInstructor.getInstructorCode());
		ResultSet rs=ps.executeQuery();
		ps.executeUpdate();
		rs.next();
		Instructor i = new Instructor(rs.getInt("instructorCode"),rs.getString("name"),rs.getInt("salary"),rs.getInt("jobStartYear"),rs.getString("emailId"),rs.getString("location"));
		return i;
	}

}
