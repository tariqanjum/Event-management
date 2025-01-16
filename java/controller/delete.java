package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import model.Registration;

@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class delete extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       HttpSession session = request.getSession();
       try {
           int id = Integer.parseInt(request.getParameter("userid"));
          
           Registration reg = new Registration(session);
           String status = reg.delete(id);
           if (status.equals("success")) {
               request.setAttribute("status","Successfully Deleted");
               request.getRequestDispatcher("DeleteUser.jsp").forward(request, response);
           }
           if (status.equals("failure")) {
                request.setAttribute("status","Deletion failure");
               //request.getRequestDispatcher("DeleteUser.jsp").forward(request, response);
               response.sendRedirect("DeleteUser.jsp");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       processRequest(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       processRequest(request, response);
   }

   public String getServletInfo() {
       return "Short description";
   }// </editor-fold>

}