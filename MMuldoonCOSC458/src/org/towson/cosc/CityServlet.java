package org.towson.cosc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String errorMsg = "";
        List<City> cities = null;
        
        String countryCode = request.getParameter( "country" );
        String type = request.getParameter( "type" );
        
        switch( type ){
        case "Protected":
        	errorMsg = CheckSQLInjection.validate( countryCode, 3 );
        	break;
        	
        case "Unprotected":
        	break;
        	
        case "Honeypot":
        	errorMsg = CheckHoneypot.validate( request );
        	break;
        }
        
        // any errors?
        if ( errorMsg.length() == 0 ){
        	// no
	        try {
	            Connection con = CityUtils.getMySQLConnection();
	
	        	cities = CityUtils.queryCities(con, countryCode );
	        	
	        	con.close();
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            errorMsg = e.getMessage();
	        }
        }

        
        // Store info in request attribute, before forward to views
        request.setAttribute("cityList", cities );
        request.setAttribute("countryCode", countryCode );
        request.setAttribute("errorString", errorMsg);
         
        // Forward to /WEB-INF/views/cityView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/CityView.jsp");
        dispatcher.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
