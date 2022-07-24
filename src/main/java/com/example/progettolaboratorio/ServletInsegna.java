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

@WebServlet(name = "ServletInsegna", value = "/ServletInsegna")
public class ServletInsegna extends HttpServlet{

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
        ArrayList<Insegna>answ1;

        String action =(String) request.getAttribute("actionRD");
            switch (action){
                case "inserisciInsegna":
                    answ =(String) inserimentoInsegna(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "rimuoviInsegna":
                    answ =(String) rimuoviInsegna(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "addInsegna":
                    answ =(String) addInsegna(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "getInsegna":
                    answ1 =(ArrayList<Insegna>) viewInsegna(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
            }


    }

    public String inserimentoInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        HttpSession s= request.getSession();
        System.out.println("Sono nella inserimentoInsegna");
        String docente = request.getParameter("codDocente");
        String corso = request.getParameter("corso");
        result = result + DAO.inserisciInsegna(docente, corso);
        System.out.println(result);
        return result;

    }

    public String rimuoviInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "Non hai i permessi";
        HttpSession s= request.getSession();
        String codDocente = request.getParameter("codDocente");
        String titolo = request.getParameter("titolo");
        result = DAO.rimuoviInsegna(codDocente,titolo);
        return result;

    }

    public String addInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        String codDocente = request.getParameter("codDocente");
        String corso = request.getParameter("corso");
        result = DAO.addInsegna(codDocente,corso);
        return result;
    }
    public ArrayList<Insegna> viewInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Insegna> list = null;
        list = DAO.viewInsegna();
        return list;
    }
}
