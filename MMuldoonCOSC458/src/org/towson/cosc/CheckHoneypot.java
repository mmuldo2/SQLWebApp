package org.towson.cosc;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class CheckHoneypot {

	
	public static String validate( HttpServletRequest request ) {
		
		String errorMsg = "Honeypot service detected an attack from remote host " + request.getRemoteHost() + "\n";
		
		// show the request headers
		Enumeration<String> headers = request.getHeaderNames();
		while ( headers.hasMoreElements() ){
			String headerName = headers.nextElement();
			errorMsg += "  (" + headerName + "=" + request.getHeader( headerName ) + ")\n";
		}
		
		return errorMsg;
	}

}
