package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionMannager;
import com.chainsys.jspproject.commonutil.InValidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class AddNewEmployeeServlet
 */
@WebServlet("/AddNewEmployeeServlet")
public class AddNewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String submitvalue=request.getParameter("submit");
		
		if(submitvalue.equals("Add employee")) {
			PrintWriter out=response.getWriter();
			Employee emp=new Employee();
			//
			
			//
			String emp_id=request.getParameter("id");
		String source="AddNewEmployee";
		String message="<h1>Error while"+source+"</h1>";
		try {
			Validator.checkStringForParseInt(emp_id);
		}catch(InValidInputDataException e) {
		  message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
		}
		int id=Integer.parseInt(emp_id);
		try {
			Validator.CheckNumberForGreaterThanZero(id);
		}catch(InValidInputDataException e) {
			 message +="Error in employee id input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;	
		}
		emp.setEmployee_id(id);
		
		
		String emp_Firstname=request.getParameter("fname");
		try {
			Validator.checkStringOnly(emp_Firstname);
		}catch(InValidInputDataException e) {
			 message +="Error in employee fname input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;		
		}
		emp.setFirst_name(emp_Firstname);
		
		
		String emp_LastName=request.getParameter("lname");
		try {
			Validator.checkStringOnly(emp_LastName);
		}catch(InValidInputDataException e) {
			 message +="Error in employee lname input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;	
		}
		emp.setLast_name(emp_LastName);
		
		
		
		
	    String emp_email=request.getParameter("email");
	    try {
			Validator.checkEmail(emp_email); 
		}catch(InValidInputDataException e) {
			 message +="Error in employee email input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;	
		}
	    emp.setEmail(emp_email);
	    
	    
	    
	    SimpleDateFormat hire_dateFormate=new SimpleDateFormat("dd/MM/yyyy");
	    String emp_HireDate=request.getParameter("hdate");
	    //Date hire_date=hire_dateFormate.parse(emp_HireDate);
	    
	    try {
	    	Validator.checkDate(emp_HireDate);
	    	Validator.checkHireDate(emp_HireDate);
	    }catch(InValidInputDataException e)
	    {
	    	message+="Error in hire Date input</p>";
	    	String errorPage=ExceptionMannager.handleException(e, source, message);
	    	out.print(errorPage);
	    	return;
	    }
	    
	    
	    try {
			emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
		} catch (ParseException e) {
			 message +="Error in employee hdate input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;	
		}
	    
	    
	    
	    String emp_Job_id=request.getParameter("jobid");
	    try {
	    	Validator.checkJobId(emp_Job_id);
	    }catch(InValidInputDataException e) {
	    	 message +="Error in employee jobid input <p/>";	
	 		String errorPage=ExceptionMannager.handleException(e, source, message);
	 		out.print(errorPage);
	 		return;	
	    }
	    emp.setJob_id(emp_Job_id);
	    
	    
	    
	    String emp_salary=request.getParameter("salary");
	    try {
			Validator.checkStringForParseInt(emp_salary);
		}catch(InValidInputDataException e) {
			 message +="Error in employee salary input <p/>";	
				String errorPage=ExceptionMannager.handleException(e, source, message);
				out.print(errorPage);
				return;	
		}
	    float salary=Float.parseFloat(emp_salary);
	    emp.setSalary(salary);
	    int result=EmployeeDao.insertEmployee(emp);
	   // System.out.println(result+"row inserted");
	    //
	    request.setAttribute("add", result);
		RequestDispatcher rd = request.getRequestDispatcher("/addemployee.jsp");
		rd.forward(request, response);
		//
		
	}
	
	}

}
