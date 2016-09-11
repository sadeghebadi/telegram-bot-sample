package old;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.sql.*;

public class DB {

	private static String URL = "jdbc:mysql://127.0.0.1:3306/cafecrawler?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
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
			sql = "SELECT * FROM category where title = '" + category.title
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.first()) {
				sql = "Insert into category(title) values('" + category.title
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

	public static void updateApp(String url, String description) {

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
				sql = "UPDATE  app set description = '" + description
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
				category.id = categori_id;
				category.title = rs.getString("title");
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
			System.out.println(ent.getKey().title);
			saveCategory(ent.getKey());
			int category_id = getCategoryId(ent.getKey().title);
			System.out.println("categoryiss = =" + category_id);
			for (App app : ent.getValue()) {
				if (!isInDB(app)) {
					app.category_id = category_id;
					saveApp(app);
				}
				try {
					if (getAppDescription(app).equalsIgnoreCase("test1"))
						updateApp(app.url, /*
											 * new CafeCrowler().crawlApp(app)
											 */
								"test2");
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
		System.out.println("des === " + description);
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
