package com.example.progettolaboratorio;
import DAO.*;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static DAO.DAO.loginUtente;
import static java.lang.System.out;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet{
    public String risTest;
    HttpSession s = null;
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
            String action = (String) request.getAttribute("actionRD");
            switch (action){
                case "login":
                    String answ =(String) login(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "logout":
                    String answ1 =logout(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "isAdmin":
                    String answ2 =isAdmin(request,response);
                    request.setAttribute("risultato",answ2);
                    break;
            }

    }


    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
         IOException {
        String st = "";
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username.equals(password) && username.equals("")) {
                    st = "Guest";
                } else {
                    Utente result = null;
                    result = loginUtente(username, password);
                    if (result != null) {
                        s = request.getSession();
                        String account = result.getAccount();
                        s.setAttribute("userName", account);
                        int role = result.getRuolo();
                        s.setAttribute("Role", role);
                        if (isAdmin(request, response).equals("yes"))
                            st = "admin";
                        else
                            st = "utenteRegistrato";
                    } else {
                        st = "Login fallito, Username o password errati";
                    }
                }
                return st;
            }
            catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            s.invalidate();
            return st;
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        String st="Logout effettuato con successo";
        s.invalidate();
        return st;
    }
    public String isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String result = "";
        String username;
        int ruolo=0;
        if(s==null)
            username = request.getParameter("username");
        else{
            username = s.getAttribute("userName").toString();
            ruolo= (int) s.getAttribute("Role");
        }

        DAO.isAdmin(username);
        if(DAO.isAdmin(username)||ruolo==1){
            result = "yes";         //è un admin
        }
        else{
            result = "no";          //non è un admin
        }
        return result;
    }
}
