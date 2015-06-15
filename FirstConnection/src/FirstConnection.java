/*Program: First Connection.java
 * Author: Rakshit Kota
 * Date: 06/12/2015
 */

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class FirstConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Check the order");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		orderIdNum(str);
	}
		
	public static void orderIdNum(String orderidnum){
		try {
			//Load the Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2: create the connection object
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "password");
			
			//step3: create statement object
			Statement stmt = con.createStatement();
			String sql_1 = "select demo_orders.order_timestamp" + " from testuser.demo_orders" + " " +
						
					 		"where demo_orders.order_id = " + orderidnum.toString();
			
			String sql_2 = "select demo_orders.order_id, demo_customers.cust_first_name, demo_customers.cust_last_name, demo_customers.cust_street_address1, demo_customers.cust_street_address2, demo_customers.cust_city, demo_customers.cust_state" + " from testuser.demo_customers" + " " + 
							"join testuser.demo_orders" + " " + "on demo_customers.customer_id = " + "demo_orders.customer_id" + " " +
					 		"where demo_orders.order_id = " + orderidnum.toString();
			
			
			String sql_3 = "select demo_order_items.quantity, demo_product_info.product_description, demo_product_info.list_price" + " " +"from testuser.demo_product_info" + " " 
							+ "join testuser.demo_order_items" + " "
							+ "on demo_order_items.product_id =" + " " + "demo_product_info.product_id" + " "
							+ "where demo_order_items.order_id = " + orderidnum.toString();
	
			//step4: execute the query
			ResultSet rs = stmt.executeQuery(sql_1);
		//	System.out.println(sql_3);
			while(rs.next())
			{
				
				System.out.println(rs.getDate(1) + "\t\t" + orderidnum);
			
			}
			rs = stmt.executeQuery(sql_2);
			while(rs.next())
			{
				System.out.println(rs.getString(2) + " " + rs.getString(3) + "\n" + rs.getString(4) + " " + rs.getString(5) + "\n" + rs.getString(6) + " " + rs.getString(7));
			}
			
			
			rs = stmt.executeQuery(sql_3);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Quantity" + "\t" + "Description" + "\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Amount");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next())
			{
				System.out.printf("%-10s %-90s %30s", rs.getString(1), rs.getString("product_description"), rs.getString("list_price"));
				System.out.println();// + "\t" + rs.getString(1));
			}
			
			con.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

}
