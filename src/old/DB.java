package old;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.sql.*;

public class DB {

	private static String URL = "jdbc:mysql://127.0.0.1:3306/cafecrawler?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8mb4&useSSL=false";
	static final String USER = "root";
	static final String PASS = "qbee";

	public static void saveApp(App app) {

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where url = '" + app.url + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.first()) {
				sql = "Insert into app(category_id , app_name,url,img,price) values("
						+ app.category_id
						+ " , '"
						+ app.app_name
						+ "' , '"
						+ app.url
						+ "' , '"
						+ app.img
						+ "' , '"
						+ app.price
						+ "')";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}

		// if app contains nothings
	}

	public static void saveCategory(Category category) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM category where title = '" + category.getTitle()
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.first()) {
				sql = "Insert into category(title) values('" + category.getTitle()
						+ "')";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}
	}

	public static void updateApp(String url, App app) {

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where url = '" + url + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				app.description = app.description.replaceAll("[^\\u0000-\\uFFFF]", "");
				System.out.println(app.app_name);
				sql = "UPDATE  app set description = '" + app.description+"',"+"app_category ='"+app.app_category 
						+ "' where url = '" + url + "'";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}

	}

	public static void updateApp(String url, int isnew) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where url = '" + url + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				sql = "UPDATE  app set isnew = " + isnew + " where url = '"
						+ url + "'";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}

	}

	public static Category getCategory(int categori_id) {
		Connection conn = null;
		Statement stmt = null;
		Category category = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM category where id = '" + categori_id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				category = new Category();
				category.setId( categori_id);
				category.setTitle(rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}
		return category;
	}

	public static boolean isInDB(App app) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where url = '" + app.url + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.first()) {
				return false;

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	public static int getCategoryId(String title) {
		Connection conn = null;
		Statement stmt = null;
		int id = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM category where title = '" + title + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}
		return id;
	}

	public static void saveData(HashMap<Category, ArrayList<App>> data) {
		// isInDB{ saveapp());
		
		
		
		for (Entry<Category, ArrayList<App>> ent : data.entrySet()) {
			System.out.println(ent.getKey().getTitle());
			saveCategory(ent.getKey());
			int category_id = getCategoryId(ent.getKey().getTitle());
			System.out.println("categoryiss = =" + category_id);
			for (App app : ent.getValue()) {
				if (!isInDB(app)) {
					app.category_id = category_id;
					saveApp(app);
				}
				try {
					if (getAppDescription(app).equalsIgnoreCase("test"))
						updateApp(app.url,  new CafeCrowler().crawlApp(app));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	private static String getAppDescription(App app1) {
		Connection conn = null;
		Statement stmt = null;
		String description = "";
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where url = '" + app1.url + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				description = rs.getString("description");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return description;
	}

	public static App getNewApp() {
		Connection conn = null;
		Statement stmt = null;
		App app = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM app where isnew = 1";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				app = new App();
				app.id = rs.getInt("id");
				app.category_id = rs.getInt("category_id");
				app.description = rs.getString("description");
				app.img = rs.getString("img");
				app.url = rs.getString("url");
				app.price = rs.getString("price");
				app.app_name = rs.getString("app_name");
				app.app_category=rs.getString("app_category");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return app;
	}
}
