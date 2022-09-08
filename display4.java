import java.io.*;
import java.sql.*;
import jakarta.servlet.*;            // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;            // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/display4")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class display4 extends HttpServlet {

    

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query


      try (

              // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/clicker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
          
              ){
              int countA =0;
              int countB =0;
              int countC =0;
              int countD =0;

              out.println("<html>");
              out.println("<head>");
              out.println("<link rel='stylesheet' href='style.css'>");
              out.println("<link href='https://fonts.googleapis.com/css?family=Karla' rel='stylesheet'>");
              out.println("<title>Display Question 4 Answer</title></head><body>");
              out.println("<center><h2 style=\"font-size:30px;font-family:'Karla';margin-top: 25px;\">Responses</h2></center>");
              out.println("<center><h3 style=\"font-size:30px;font-family:'Karla';\"> Question Number 4 </h3></center>");
              out.println("<div class='bg'></div>");
              out.println("<div class='bg bg2'></div>");
              out.println("<div class='bg bg3'></div>");

              ResultSet rs = stmt.executeQuery("SELECT * FROM responses WHERE questionNo = 4 and choice = 'A'" );
              while (rs.next()) {
                  countA++;
              }
              
              rs = stmt.executeQuery("SELECT * FROM responses WHERE questionNo = 4 and choice = 'B' ");
              while (rs.next()) {
                  countB++;
              }
             
              rs = stmt.executeQuery("SELECT * FROM responses WHERE questionNo = 4 and choice = 'C'" );
              while (rs.next()) {
                  countC++;
              }
              
              rs = stmt.executeQuery("SELECT * FROM responses WHERE questionNo = 4 and choice = 'D'");
              while (rs.next()) {
                  countD++;
              }
             

              out.println("<center><script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js\"></script>"
                +"<body>"+""
                +"<canvas id=\"myChart\" style=\"width:90%;max-width:1000px; background-color:rgba(255,255,255,.5);border-radius: 20px;\"></canvas>"+""
                + "<script>"+ "var xValues = [\"A\", \"B\", \"C\", \"D\"];"+
                "var yValues = ["+countA+"," +countB+", "+countC+", "+countD+"];"+
                "var barColors = [\"red\", \"gray\",\"blue\",\"orange\",\"brown\"];"+ ""+
                "new Chart(\"myChart\", {"+"  type: \"bar\","+"  data: {"+"    labels: xValues,"+ "    datasets: [{"+ "      backgroundColor: barColors,"+"      data: yValues"+"    }]"+"  },"
                +"  options: {"+ "    legend: {display: false},"+ "    title: {"+ "      display: true,"+  "    }"+ "  }"+ "});"+ "</script></center>");
              
              out.println("<form method='get' action='end.html'>");
              out.println("<br>");
              out.println("<center><input type='submit' value='Next Question' style=\"font-size:30px;font-family:'Karla';background-color:rgba(255,255,255,.5);border-radius: 20px;\"/></center>");
              

         // Step 1: Allocate a database 'Connection' object
         

         
      out.println("</body></html>");
          
      }catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</body></html>");
      out.close();
   }
}