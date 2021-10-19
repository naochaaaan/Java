package mix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rec
 */
@WebServlet("/mix/rec")
public class rec extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public rec() {
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
			out.println("<title>確認画面</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>確認画面</h3>");


			String driverUrl = "jdbc:derby:C:\\JavaDB\\report;create=false";


			con = DriverManager.getConnection(driverUrl, "db", "db");


			stmt = con.createStatement();

			//新しいMIXの追加

			String Mix_name = request.getParameter("Mix_name");
			String mix = request.getParameter("mix");
			String rank = request.getParameter("rank");

			String sql2 = "insert into IDOL_MIX (Mix_name,Mix,Rank) values (?,?,?)";
			System.out.println(rank);
			ps = con.prepareStatement(sql2);
			ps.setString(1, Mix_name);
            ps.setString(2, mix);
            ps.setInt(3, Integer.parseInt(rank));

			ps.executeUpdate();
			ps.close();
			out.println("<p>登録しました</p>");
			out.println("<a href=\"allList\">全件表示</a><br>");
			out.println("<a href=\"top.html\">トップページへ</a>");


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

