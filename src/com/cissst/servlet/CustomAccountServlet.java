package com.cissst.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cissst.entity.Admin;
import com.cissst.entity.CustomAccount;
import com.cissst.service.ICustomAccountService;
import com.cissst.service.impl.CustomAccountServiceImpl;
import com.cissst.util.MD5Util;

public class CustomAccountServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		ICustomAccountService ca = new CustomAccountServiceImpl();
		if("customAccountList".equals(action)){
			List<CustomAccount> list =  ca.findAllcustomAccount();
			request.setAttribute("customAccounts", list);
			RequestDispatcher rd = request.getRequestDispatcher("customAccount/custom-list.jsp");
			rd.forward(request,response);
		
		}else if("customAccountAdd".equals(action)){
			String username = request.getParameter("username");
			String password = MD5Util.encode(request.getParameter("password"));
			String ownerid = request.getParameter("ownerid");
			String carid = request.getParameter("carid");

			CustomAccount c = new CustomAccount();
			c.setUsername(username);
			c.setPassword(password);
			c.setOwnerid(ownerid);
			c.setCarid(carid);
			
			ca.save(c);
			
			response.sendRedirect("custom?action=customAccountList");
		}else if("findById".equals(action)){
			String accountid = request.getParameter("accountid");
			CustomAccount c = ca.findById(accountid);
			request.setAttribute("customAccount", c);
			
			RequestDispatcher rd = request.getRequestDispatcher("customAccount/custom-edit.jsp");
			rd.forward(request, response);
			
		}else if("findById2".equals(action)){
			String accountid = request.getParameter("accountid");
			CustomAccount c = ca.findById(accountid);
			request.setAttribute("customAccount", c);
			RequestDispatcher rd = request.getRequestDispatcher("customAccount/user-custom-list.jsp");
			rd.forward(request, response);
			
		}else if("customAccountEdit".equals(action)){
			int accountid = Integer.parseInt(request.getParameter("accountid"));
			String username = request.getParameter("username");
			String password = MD5Util.encode(request.getParameter("password"));
			String ownerid = request.getParameter("ownerid");
			String carid = request.getParameter("carid");

            CustomAccount c = new CustomAccount();
    		
            c.setAccountid(accountid);
			c.setUsername(username);
			c.setPassword(password);
			c.setOwnerid(ownerid);
			c.setCarid(carid);
	
			ca.update(c);
			response.sendRedirect("custom?action=customAccountList");
		}else if("customAccountDelete".equals(action)){
			String accountid = request.getParameter("accountid");
			ca.delete(accountid);
			response.sendRedirect("custom?action=customAccountList");
		}
		else if("change".equals(action)){
			String accountid = request.getParameter("accountid");
			CustomAccount c = ca.findById(accountid);
			request.setAttribute("customAccount", c);
			RequestDispatcher rd = request.getRequestDispatcher("customAccount/user-change-passwd.jsp");
			rd.forward(request, response);
		}else if("customAccountUserEdit".equals(action)){
			int accountid = Integer.parseInt(request.getParameter("accountid"));
			String username = request.getParameter("username");
			String password = MD5Util.encode(request.getParameter("password"));
			String ownerid = request.getParameter("ownerid");
			String carid = request.getParameter("carid");

            CustomAccount c = new CustomAccount();
    		
            c.setAccountid(accountid);
			c.setUsername(username);
			c.setPassword(password);
			c.setOwnerid(ownerid);
			c.setCarid(carid);
	
			ca.update(c);
			response.sendRedirect("custom?action=findById2&accountid="+accountid);
		}
	}
}
