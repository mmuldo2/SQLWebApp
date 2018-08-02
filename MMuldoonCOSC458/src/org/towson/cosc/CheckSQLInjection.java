package org.towson.cosc;

public class CheckSQLInjection {

	
	public static String validate( String value, int maxLength ) {

		//
		// check for a SQL injection attack...
		//
		
		if ( value.contains( ";" ) ){
			return "Error: SQL injection attack detected, value contains \";\": " + value;
		}
		
		if ( value.contains( "'" ) ){
			return "Error: SQL injection attack detected, value contains \"'\": " + value;
		}
		
		if ( value.length() > maxLength ){
			return "Error: SQL injection attack detected, value is greater than maximum length: " + value;
		}
		
		//TODO add more tests here...
		
		
		
		// passed all the tests, value is not an attack
		return "";
	}

}
