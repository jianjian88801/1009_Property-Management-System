package com.cissst.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cissst.entity.CustomAccount;
import com.cissst.entity.Maintain;
import com.cissst.service.ICustomAccountService;
import com.cissst.service.IMaintainService;
import com.cissst.service.impl.CustomAccountServiceImpl;
import com.cissst.service.impl.MaintainServiceImpl;

public class MaintainServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8;");
		String action = request.getParameter("action");
		IMaintainService maintainService = new MaintainServiceImpl();
		ICustomAccountService ca = new CustomAccountServiceImpl();
		if("maintainList".equals(action)){
			List<Maintain> list =  maintainService.findAllMaintains();
			request.setAttribute("maintains", list);
			RequestDispatcher rd = request.getRequestDispatcher("maintain/maintain-list.jsp");
			rd.forward(request, response);
		}
		else if("maintainAdd".equals(action)){
			String thing = request.getParameter("thing");
			String status = request.getParameter("status");
			String homesnumber = request.getParameter("homesnumber");
			String sdate = request.getParameter("sdate");
			String rdate = request.getParameter("rdate");
			String tcost = request.getParameter("tcost");
			String scost = request.getParameter("scost");
			String maintainer = request.getParameter("maintainer");
			String smemo = request.getParameter("smemo");
			
			Double tt,st;
			if(tcost==null){tt=(double)0.0;}else{tt = Double.parseDouble(tcost);}
			if(scost==null||scost.equals("")){st=(double)0.0;}else{ st = Double.parseDouble(scost);}
			Maintain m = new Maintain();
			
			m.setThing(thing);
			m.setStatus(status);
			m.setHomesnumber(homesnumber);
			m.setSdate(sdate);
			m.setRdate(rdate);
			m.setTcost(tt);
			m.setScost(st);
			m.setMaintainer(maintainer);
			m.setSmemo(smemo);
			
			maintainService.save(m);
			
			response.sendRedirect("main?action=maintainList");
			return;
		}else if("findById".equals(action)){
			String id = request.getParameter("id");
			
			Maintain a = maintainService.findById(id);
			
			request.setAttribute("maintain", a);
			
			RequestDispatcher rd = request.getRequestDispatcher("maintain/maintain-edit.jsp");
			rd.forward(request, response);
			
			
		}else if("maintainUpdate".equals(action)){
			int id = Integer.parseInt(request.getParameter("id"));
			String thing = request.getParameter("thing");
			String status = request.getParameter("status");
			String homesnumber = request.getParameter("homesnumber");
			String sdate = request.getParameter("sdate");
			String rdate = request.getParameter("rdate");
			String tcost = request.getParameter("tcost");
			String scost = request.getParameter("scost");
			String maintainer = request.getParameter("maintainer");
			String smemo = request.getParameter("smemo");
			
			Double tt,st;
			if(tcost==null){tt=(double)0.0;}else{tt = Double.parseDouble(tcost);}
			if(scost==null||scost.equals("")){st=(double)0.0;}else{st = Double.parseDouble(scost);}
			Maintain m = new Maintain();
			m.setId(id);
			m.setThing(thing);
			m.setStatus(status);
			m.setHomesnumber(homesnumber);
			m.setSdate(sdate);
			m.setRdate(rdate);
			m.setTcost(tt);
			m.setScost(st);
			m.setMaintainer(maintainer);
			m.setSmemo(smemo);
			
			maintainService.update(m);
			
			response.sendRedirect("main?action=maintainList");
		}else if("maintainDelete".equals(action)){
			String id = request.getParameter("id");
			maintainService.delete(id);
			response.sendRedirect("main?action=maintainList");
		}
		else if("maintainUserList".equals(action)){
			String maintainer = request.getParameter("username");
			List<Maintain> list =  maintainService.findByMaintainer(maintainer);
			request.setAttribute("maintains", list);
			RequestDispatcher rd = request.getRequestDispatcher("maintain/user-maintain-list.jsp");
			rd.forward(request, response);
		}
		else if("maintainUserAdd".equals(action)){
			String thing = request.getParameter("thing");
			String status = request.getParameter("status");
			String homesnumber = request.getParameter("homesnumber");
			String sdate = request.getParameter("sdate");
			String rdate = request.getParameter("rdate");
			String tcost = request.getParameter("tcost");
			String scost = request.getParameter("scost");
			String maintainer = request.getParameter("maintainer");
			String smemo = request.getParameter("smemo");
			
			if(sdate.length()==0||thing.length()==0||status.length()==0){
				RequestDispatcher rd = request.getRequestDispatcher("maintain/maintainError.jsp");
			rd.forward(request, response);}
			
			Double tt,st;
			if(tcost==null){tt=(double)0.0;}else{tt = Double.parseDouble(tcost);}
			if(scost==null||scost.equals("")){st=(double)0.0;}else{ st = Double.parseDouble(scost);}
			Maintain m = new Maintain();
			
			m.setThing(thing);
			m.setStatus(status);
			m.setHomesnumber(homesnumber);
			m.setSdate(sdate);
			m.setRdate(rdate);
			m.setTcost(tt);
			m.setScost(st);
			m.setMaintainer(maintainer);
			m.setSmemo(smemo);
			maintainService.save(m);
			response.sendRedirect("main?action=maintainUserList&username="+maintainer);
		}else if("maintainUserDelete".equals(action)){
			String maintainer = request.getParameter("maintainer");
			String id = request.getParameter("id");
			maintainService.delete(id);
			response.sendRedirect("main?action=maintainUserList&username="+maintainer);
		}
	
	}

}
