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

@WebServlet(name = "ServletCorsi", value = "/ServletCorsi")
public class ServletCorsi extends HttpServlet{

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
        ArrayList <Corso> answ1;

        String action =(String) request.getAttribute("actionRD");
            switch (action){
                case "inserimentoCorsi":
                    answ =(String) inserimentoCorso(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "availableSubjects":
                    answ1 =availableSubjects(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "eliminaCorso":
                    answ =eliminaCorso(request,response);
                    request.setAttribute("risultato",answ);
                    break;
            }


    }
    public String inserimentoCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        System.out.println("Sono nella inserimentoDocente");
        String corso = request.getParameter("corso");
        result = DAO.inserimentoCorso(corso);
        System.out.println(corso);

        return result;
    }
    public ArrayList<Corso> availableSubjects(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Corso> list;
        list = DAO.availableSubjects();
        return list;
    }
    public String eliminaCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String titolo = request.getParameter("titolo");
        String result = DAO.eliminaCorso(titolo);
        return result;
    }
}
