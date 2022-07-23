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
    public void init() {
        //registrazione del driver
        DAO.registerDriver();
    }
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
                    String answ2 =logout(request,response);
                    request.setAttribute("risultato",answ2);
                    break;
            }

    }
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession s=null;
        ServletContext ctx= request.getServletContext();
        RequestDispatcher rd =null;
        String res="";
        try {
            String st="";
            System.out.println("login");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username.equals(password) && username==""){      //non è null, è una stringa vuota
                st="Guest";
                return st;
            }
            else {
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                Utente result = null;
                result = loginUtente(username, password);
                //
                if (result != null) {
                    res = result.toString();
                    s = request.getSession();
                    if (s.isNew()) {            //isNew è un metodo di librerie gestione utente
                        String account = result.getAccount();
                        s.setAttribute("User", account);
                        int role = result.getRuolo();
                        s.setAttribute("Ruolo", role);
                        System.out.println("LoginEffettuato");
                        if(isAdmin(request, response) == "yes")
                            st = "admin";
                        else
                            st = "utenteRegistrato";
                        return st;
                    } else{
                        //DEVO METTERE UN ELSE SE L UTENTE NON é ANCORA REGOISTRATOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
                    }
                } else {
                    String messaggio = "Login errato, Username o password errati";
                    res=messaggio;
                    //a sto punto sta roba sotto è useless
                    request.setAttribute("message", messaggio);
                    rd = getServletContext().getNamedDispatcher("ServletError");

                    // String messaggio="Login errato, Username o password errati";
                    //request.setAttribute("message",messaggio);
                    rd.include(request, response);
                    st="Login Errato";
                    return st;
                }
            }
            String end ="risposta: sono arrivato alla fine";
            return end;
        }finally {
            risTest=res;
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        HttpSession s=request.getSession();
        s.invalidate();
        String st="Logout effettuato con successo";
        return st;
    }
    public String isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String result = "";
        String username = request.getParameter("username");
        boolean flag;
        flag = DAO.isAdmin(username);
        if(flag){
            result = "yes";         //è un admin
        }
        else{
            result = "no";          //non è un admin
        }
        return result;
    }
}
