package com.apms.launch;

import java.sql.Connection;

import com.apms.dal.DBConnection;

public class Launch {

	public static void main(String[] args) {
		
		DBConnection dbConnection=DBConnection.getInstance();
		Connection connection=dbConnection.getConnection();
		
	}

}
