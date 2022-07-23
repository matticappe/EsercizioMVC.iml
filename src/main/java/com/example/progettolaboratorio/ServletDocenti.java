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

@WebServlet(name = "ServletDocenti", value = "/ServletDocenti")
public class ServletDocenti extends HttpServlet{
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
        String answ;
        ArrayList<Docente> answ1;
        try(PrintWriter out = response.getWriter()) {
            String action = request.getParameter("actionRD");
            switch (action){
                case "inserimentoDocente":
                    answ =(String) inserimentoDocente(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "filterProf":
                    answ1 =filterProf(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "viewAllProf":
                    answ1 =viewAllDocente(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "eliminaDocente":
                    answ =eliminaDocente(request,response);
                    request.setAttribute("risultato",answ);
                    break;
            }
        }

    }

    public String inserimentoDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        System.out.println("Sono nella InsertProf");
        String account = request.getParameter("account");
        System.out.println(account);
        String nomeCognome = request.getParameter("nomeCognome");
        System.out.println(nomeCognome);
        String attivo = request.getParameter("attivo");
        result = DAO.inserimentoDocente(account, nomeCognome,attivo);

        return result;
    }
    public ArrayList<Docente> filterProf(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Docente> list;
        list = DAO.filterProf();
        return list;
    }
    public ArrayList<Docente> viewAllDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<Docente> list;
        list = DAO.viewAllProf();
        return list;
    }

    public String eliminaDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String account = request.getParameter("account");
        String result = DAO.eliminaDocente(account);
        return result;
    }
}
