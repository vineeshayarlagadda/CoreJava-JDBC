package com.capg.app.ui;

import java.util.List;
import java.util.Scanner;

import com.capg.app.beans.Instructor;
import com.capg.app.service.InstructorService;
import com.capg.app.service.InstructorServiceImpl;

public class MainClass {
	
	
	InstructorService service;
	
	public MainClass() {
		service = new InstructorServiceImpl();
	}
	
	public static void main(String[] args) {
		
		MainClass obj = new MainClass();
		
		while(true)
		{
			
			System.out.println("1. Insert");
			System.out.println("2. Show All");
			System.out.println("3. Get details by instructor code");
			System.out.println("4. Get details by job location");
			System.out.println("5. Update the instructor");
			System.out.println("6. Delete instructor by instructor code");
			System.out.println("7. Get instructors by salary in ascending order");
			System.out.println("8. Get instructors count based on location");
			System.out.println("0. EXIT");
			
		
			
			int opt =new Scanner(System.in).nextInt(); 
			
			
			if(opt == 1)
			{
				obj.insertInstructorDetailsFromUser();
			}
			if(opt == 2)
			{
				
				obj.readInstructorDetails();
			}
			if(opt == 3)
			{
				System.out.println("Enter the instructor code : ");
				Scanner sc=new Scanner(System.in);
				int code=sc.nextInt();
				obj.getInstructorDetailsByCode(code);
			}
			if(opt == 4)
			{
				System.out.println("Enter the job location : ");
				Scanner sc=new Scanner(System.in);
				String loc=sc.next();
				obj.getDetailsByLocation(loc);
			}
			if(opt == 5)
			{
				System.out.println("The updated data : ");
				obj.updateInstructorDetails();
			}
			if(opt == 6)
			{
				System.out.println("Enter the instructor code for deletion : ");
				Scanner sc=new Scanner(System.in);
				int code=sc.nextInt();
				obj.deleteInstructor(code);
			}
			if(opt == 7)
			{
				System.out.println("Instructors based on salary in ascending order : ");
				obj.getInstructorBasedOnSalary();;
			}
			if(opt == 8)
			{
				System.out.println("Instructors count based on location : ");
				obj.getInstructorsCountByLocation();
			}
			if(opt == 0)
			{
				System.exit(0);
			}
			
			
		}
		
		
	}//end main
	
	public void insertInstructorDetailsFromUser()
	{
		
		// ... write code to read data hope 
		Instructor instructor = new Instructor(130, "X", 7000, 2000, "x@x.com", "B");
		try {
			boolean result = service.insertInstructor(instructor);
			
			if(result) System.out.println(" Instructor Added");
			else System.out.println("Contact Admin ...");
		} catch (Exception e) {
			showErr(e);
		}
		
		
	}
	public void readInstructorDetails()
	{
		
		try
		{			
			List<Instructor> list = service.getInstructors() ;
			list.stream().forEach(instructor->displayInstructor(instructor));
		}
		catch(Exception e)
		{
			showErr(e);
		}
	
	}
	
	public void displayInstructor(Instructor instructor)
	{
		
		System.out.println(instructor);
		System.out.println("-------------------------------------------------------");
	}
	public void showErr(Exception e)
	{
		System.out.println("====>> "+e);
	}
	

	public void getInstructorDetailsByCode(int code) {
		try
		{			
			Instructor i = service.getInstructorByCode(code) ;
			displayInstructor(i);
		}
		catch(Exception e)
		{
			showErr(e);
		}
	}
	public void getDetailsByLocation(String loc) {
		try
		{		
			List<Instructor> list = service.getInstructorsByLocation(loc) ;	
			list.stream().forEach(instructor->displayInstructor(instructor));
		}
		catch(Exception e)
		{
			showErr(e);
		}
	}
	
	public void updateInstructorDetails(){
		try {
			Instructor instructor = new Instructor(130, "X", 7000, 2000, "x@x.com", "B");
			Instructor i =service.updateInstructor(instructor);
			System.out.println(i);
		}
		catch(Exception e)
		{
			showErr(e);
		}
		
	}
	
	public void deleteInstructor(int code) {
		try {
			boolean isDeleted=service.delteInstructorByCode(code);
			System.out.println(isDeleted);
		}
		catch(Exception e)
		{
			showErr(e);
		}
	}
	
	public void getInstructorBasedOnSalary() {
		try {
			List<Instructor> list=service.getInstructorsBySalary();
			for(Instructor i:list) {
				System.out.println(i);
			}
		}
		catch(Exception e)
		{
			showErr(e);
		}
	}
	
	public void getInstructorsCountByLocation() {
		try {
			List list=service.getInstructorsCountByLocation();
			for(Object i:list) {
				System.out.println(i);
			}
		}
		catch(Exception e)
		{
			showErr(e);
		}
	}
	
	
}//end class
