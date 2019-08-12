package com.numbergame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Game")
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Game() {
        boolean gameOver;
        int randomNumber = 0;
        boolean hasGuessed = false;
        boolean over = false;
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Integer randomNumber = (int)(Math.random() * 99) + 1;
		if(session.getAttribute("gameOver") == null) {
			session.setAttribute("gameOver", false);
			session.setAttribute("randomNumber", randomNumber);
			session.setAttribute("hasGuessed", false);
			session.setAttribute("over", false);
		}
		
		
		
		request.getRequestDispatcher("/game.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("guess")!=null) {
			Integer guess = Integer.parseInt(request.getParameter("guess"));
			Integer randomNumber = (Integer) request.getSession().getAttribute("randomNumber");
			if(randomNumber == guess) {
				request.getSession().setAttribute("gameOver", true);
			}else if(randomNumber > guess) {
				request.getSession().setAttribute("over", false);
			}else {
				request.getSession().setAttribute("over", true);
			}
			request.getSession().setAttribute("hasGuessed", true);
		}
		if(request.getParameter("reset") != null) {
			request.getSession().invalidate();
		}
		doGet(request, response);
	}

}