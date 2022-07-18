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
 * Servlet implementation class ModifyEmployeeServlet
 */
@WebServlet("/ModifyEmployeeServlet")
public class ModifyEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEmployeeServlet() {
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
		PrintWriter out=response.getWriter();
		String source="updateEmployee";
		String message="<h1>Error while"+source+"<h1>";
		Employee emp=new Employee();
		//System.out.println("Enter the Employee id:");
		String emp_id=null;
		 emp_id=request.getParameter("id");
		try {
			//emp_id=sc.nextLine();
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
		System.out.println("Enter Employee first name:");
		String emp_Firstname=request.getParameter("fname");
		try {
			Validator.checkStringOnly(emp_Firstname);
		}catch(InValidInputDataException e) {
			message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
		}
		emp.setFirst_name(emp_Firstname);
		System.out.println("Enter Employee Last Name:");
		String emp_LastName=request.getParameter("lname");
		try {
			Validator.checkStringOnly(emp_LastName);
		}catch(InValidInputDataException e) {
			message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
		}
		emp.setLast_name(emp_LastName);
	    System.out.println("Enter Employee Email:");
	    String emp_email=request.getParameter("email");
	    try {
			Validator.checkEmail(emp_email);
		}catch(InValidInputDataException e) {
			message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
		}
	    emp.setEmail(emp_email);
	    System.out.println("Enter Employee hire_date like \"dd/mm/yyyy\":");
	    SimpleDateFormat hire_dateFormate=new SimpleDateFormat("dd/MM/yyyy");
	    String emp_HireDate=request.getParameter("hdate");
	    //Date hire_date=hire_dateFormate.parse(emp_HireDate);
	    
	    try {
	    	Validator.checkDate(emp_HireDate);
	    	Validator.checkHireDate(emp_HireDate);
	    }catch(InValidInputDataException e)
	    {
	    	message +="error in hire date input </p>";
	    	String errorPage=ExceptionMannager.handleException(e, source, message);
	    	out.print(errorPage);
	    	return;
	    }
	    
	    try {
			emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
		} catch (ParseException e) {
			message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
		}
	    System.out.println("Enter the job_id:");
	    String emp_Job_id=request.getParameter("jobid");
	    try {
	    	Validator.checkJobId(emp_Job_id);
	    }catch(InValidInputDataException e) {
	    	message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
	    }
	    emp.setJob_id(emp_Job_id);
	    System.out.println("Enter Salary of Employee:");
	    String emp_salary=request.getParameter("salary");
	    try {
	    	Validator.checkStringForParseInt(emp_salary);
	    }catch(InValidInputDataException e) {
	    	message +="Error in employee id input <p/>";	
			String errorPage=ExceptionMannager.handleException(e, source, message);
			out.print(errorPage);
			return;	
	    }
	    float salary=Float.parseFloat(emp_salary);
	    emp.setSalary(salary);
	    int result=EmployeeDao.updateEmployee(emp);
	   // System.out.println(result+"row updated");
	    //
	    request.setAttribute("update", result);
		RequestDispatcher rd = request.getRequestDispatcher("/updateemployee.jsp");
		rd.forward(request, response);
		//
	    
	
		

	}

}
