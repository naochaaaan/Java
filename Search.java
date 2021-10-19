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
 * Servlet implementation class Search
 */
@WebServlet("/mix/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
			out.println("<title>検索結果</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>おぼえてね！</h3>");

			//データベースreportへの接続
			String driverUrl = "jdbc:derby:C:\\JavaDB\\report;create=false";


			con = DriverManager.getConnection(driverUrl, "db", "db");


			stmt = con.createStatement();


			//入力されたMix_idを取得
			String Mix_id = request.getParameter("Mix_id");

			String sql = "select * from IDOL_MIX where Mix_id=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Mix_id));

			ResultSet rs = ps.executeQuery();

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
			out.println("<a href=\"top.html\">トップページへ</a>");


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
