package com.cissst.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cissst.entity.Admin;
import com.cissst.service.IAdminService;
import com.cissst.service.impl.AdminServiceImpl;
import com.cissst.util.MD5Util;

public class AdminServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8;");
		String action = request.getParameter("action");
		IAdminService adminService = new AdminServiceImpl();
		if("adminList".equals(action)){
			List<Admin> list =  adminService.findAlladmins();
			request.setAttribute("admins", list);
			RequestDispatcher rd = request.getRequestDispatcher("admin/admin-list.jsp");
			rd.forward(request, response);
		
		}else if("adminAdd".equals(action)){
			
			String name = request.getParameter("name");
			String password = MD5Util.encode(request.getParameter("password"));
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String tel = request.getParameter("tel");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String memo = request.getParameter("memo");
			
			System.out.println(name+password+tel+phone+addr+memo);
			
			Number ag = Integer.parseInt(age);

			Admin a = new Admin();
			a.setName(name);
			a.setPassword(password);
			a.setSex(sex);
			a.setAge(ag);
			a.setTel(tel);
			a.setPhone(phone);
			a.setAddr(addr);
			a.setMemo(memo);
			
			adminService.save(a);
			
			response.sendRedirect("admin?action=adminList");
		}else if("findById".equals(action)){
			String id = request.getParameter("id");
			
			Admin a = adminService.findById(id);
			
			request.setAttribute("admin", a);
			
			RequestDispatcher rd = request.getRequestDispatcher("admin/admin-edit.jsp");
			rd.forward(request, response);
			
			
		}else if("adminEdit".equals(action)){
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String password = MD5Util.encode(request.getParameter("password"));
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String tel = request.getParameter("tel");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String memo = request.getParameter("memo");

			int ag = Integer.parseInt(age);
			
			Admin a = new Admin();
			a.setId(id);
			a.setName(name);
			a.setPassword(password);
			a.setSex(sex);
			a.setAge(ag);
			a.setTel(tel);
			a.setPhone(phone);
			a.setAddr(addr);
			a.setMemo(memo);
			
			adminService.update(a);
			
			response.sendRedirect("admin?action=adminList");
		}else if("adminDelete".equals(action)){
			String id = request.getParameter("id");
			adminService.delete(id);
			response.sendRedirect("admin?action=adminList");
		}
		else if("change".equals(action)){
			String id = request.getParameter("id");
			Admin a = adminService.findById(id);
			request.setAttribute("admin", a);
			RequestDispatcher rd = request.getRequestDispatcher("admin/adminChange.jsp");
			rd.forward(request, response);
		}
	}
}
