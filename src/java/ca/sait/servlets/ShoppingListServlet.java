
package ca.sait.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sri
 */
public class ShoppingListServlet extends HttpServlet 
{


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String name = (String) request.getSession().getAttribute("name");
        
        String action = request.getParameter("action");
        
        if(action != null && action.equals("logout"))
        {
            request.getSession().invalidate();
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        
        if(name != null)
        {
             this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        else
        {
             this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
     
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       String action = request.getParameter("action");
       
       if(action.equals( "register"))
       {
           String name = request.getParameter("name");
            if(name != null)
            {
                //create Session
                request.getSession().setAttribute("name", name);
                
                ArrayList<String> items = new ArrayList<> ();
                request.getSession().setAttribute("items", items);
            }
       }
       else if(action.equals( "add"))
       {
           String item = request.getParameter("item");
            
           //Add Items
           ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
           
           items.add(item);
           
           request.getSession().setAttribute("items", items);
       }
       else if(action.equals("delete"))
       {
           String itemValue = request.getParameter("item");
           
           ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
           
           items.remove(itemValue);
           
           request.getSession().setAttribute("items", items);
       }
       
      this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response); 
    }

   
}
