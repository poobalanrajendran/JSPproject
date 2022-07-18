package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class RemoveEmployeeServlet
 */
@WebServlet("/RemoveEmployeeServlet")
public class RemoveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveEmployeeServlet() {
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
		 String source="AddNewEmployee";
	        String message="<h1>Error while "+source+"</h1>";
	        PrintWriter out = response.getWriter();
	        String emp_id = request.getParameter("id");
	        try {
	            Validator.checkStringForParseInt(emp_id);
	        } catch (InValidInputDataException e) {
	            message +=" Error in Employee id input </p>";
	            String errorPage=ExceptionMannager.handleException(e, source, message);
	            out.print(errorPage);
	           return;
	        }
	        int id = Integer.parseInt(emp_id);
	        int result = EmployeeDao.deleteEmployee(id);
	      //  out.println(result + "row deleted");
	        //
	        request.setAttribute("delete", result);
			RequestDispatcher rd = request.getRequestDispatcher("/deleteemployee.jsp");
			rd.forward(request, response);
			//
	        
	        try {
	            out.close();
	        } catch (Exception e) {
	            message +=" Error in deleted employee input </p>";
	            String errorPage=ExceptionMannager.handleException(e, source, message);
	            out.print(errorPage);
	           return;
	        }
	    }

	}


