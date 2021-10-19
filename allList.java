package mix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class allList
 */
@WebServlet("/mix/allList")
public class allList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public allList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;

		try {
			PrintWriter out = response.getWriter();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>MIX</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>MIXのリスト</h3>");


			String driverUrl = "jdbc:derby:C:\\JavaDB\\report;create=false";

			con = DriverManager.getConnection(driverUrl, "db", "db");

			stmt = con.createStatement();



			//現在のレコード（MIX）を表示
			String sql = "select * from IDOL_MIX";


			ResultSet rs = stmt.executeQuery(sql);

			out.println("<ul>");
			while (rs.next()) {
			    String result = "";
			    result += rs.getInt("Mix_id") + ":";
                result += rs.getString("Mix_name") + ":";
                result += rs.getString("Mix") + "  使用頻度レベル:";
                result += rs.getInt("RANK");
			    out.println("<li>" + result + "</li>");
			}
			out.println("</ul>");
			out.println("<br><a href=\"top.html\">トップページへ</a>");

			rs.close();

			out.println("</body>");
			out.println("</html>");
	} catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            	throw new ServletException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            	throw new ServletException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
            	throw new ServletException(e);
            }
        }
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
