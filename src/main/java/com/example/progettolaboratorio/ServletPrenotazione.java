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

@WebServlet(name = "ServletPrenotazione", value = "/ServletPrenotazione")
public class ServletPrenotazione extends HttpServlet{

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
        ArrayList <Prenotazione> answ=new ArrayList<Prenotazione>();
        String answ1;
            String action =(String) request.getAttribute("actionRD");
            switch (action){
                case "viewOwnPrenotations":
                    System.out.println("3");
                    answ =viewOwnPrenotations(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "viewAllPrenotations":
                    answ =viewAllPrenotations(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "viewOwnActPrenotations":
                    answ =viewOwnActPrenotations(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "filterPrenotations":
                    answ =filterPrenotations(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "filterAdminPrenotations":
                    answ =filterAdminPrenotations(request,response);
                    request.setAttribute("risultato",answ);
                    break;
                case "cancellaPrenotazione" :
                    answ1 =cancellaPrenotazione(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "inserisciPrenotazione" :
                    answ1 =inserisciPrenotazione(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "disdiciPrenotazione" :
                    answ1 =disdiciPrenotazione(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
                case "effettuaPrenotazione" :
                    answ1 =effettuaPrenotazione(request,response);
                    request.setAttribute("risultato",answ1);
                    break;
            }
    }
    public String inserisciPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Sono nella inserisciPrenotazione");
        String codice= request.getParameter("codice");
        String slot_ora= request.getParameter("slot_ora");
        String data= request.getParameter("data");
        String docente= request.getParameter("docente");
        String corso= request.getParameter("corso");
        String result = DAO.inserisciPrenotazione(codice,docente,corso,data,slot_ora);
        System.out.println(result);
        return result;
    }

    public String cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String codice= request.getParameter("codice");
        String ris = DAO.cancellazionePrenotazione(codice);
        return ris;
    }

    //prenotazioni dell utente ancora disdicibili e prenotabili e docente dev essere attivo
    public ArrayList<Prenotazione> viewOwnPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("ViewOwnPrenot");
        ArrayList<Prenotazione> list;
        String username = request.getParameter("username");
        String username1;
        if(username==null || username==""){
            username1="";
        }else{
            username1 = "  UTENTE = '"+username+"'";
        }
        list = DAO.viewOwnPrenotations(username1);
        return list;
    }

    //penso prenotazioni mie prenotate effettivamente (docente non per forza attivo)
    public ArrayList<Prenotazione> viewOwnActPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String username = request.getParameter("username");

        String username1;
        if(username==null || username==""){
            username1="";
        }else{
            username1 = "UTENTE = '"+username+"' AND (STATO = '1' OR STATO = '2') ";
        }
        list = DAO.viewOwnActPrenotations(username1);
        return list;
    }

    public ArrayList<Prenotazione> viewAllPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        list = DAO.viewAllPrenotations();
        return list;
    }

    public ArrayList<Prenotazione> filterAdminPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String slot_ora = request.getParameter("slot_ora");
        String data = request.getParameter("data");
        String materia = request.getParameter("materia");
        String docente = request.getParameter("docente");
        String utente = request.getParameter("utente");

        String data_filter;
        String utente_filter;
        String materia_filter;
        String docente_filter;
        String slot_ora_filter;
        if(data==null || data==""){
            data_filter="";
        }else{
            data_filter = " AND DATA = '"+data+"'";
        }
        if(materia==null || materia == ""){
            materia_filter="";
        }else{
            materia_filter = " AND CORSO = '"+materia+"'";
        }
        if(docente==null || docente == ""){
            docente_filter="";
        }else{
            docente_filter = " AND PRENOTAZIONE.DOCENTE = '"+docente+"'";
        }
        if(slot_ora==null || slot_ora == ""){
            slot_ora_filter="";
        }else{
            slot_ora_filter = " AND SLOT_ORA = '"+slot_ora+"'";
        }
        if(utente==null || utente == ""){
            utente_filter="";
        }else{
            utente_filter = " AND UTENTE = '"+utente+"'";
        }
        list = DAO.filterAdminPrenotations(slot_ora_filter,data_filter,materia_filter,docente_filter, utente_filter);
        return list;
    }

    public ArrayList<Prenotazione> filterPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String slot_ora = request.getParameter("slot_ora");
        String data = request.getParameter("data");
        String materia = request.getParameter("materia");
        String docente = request.getParameter("docente");
        String utente = request.getParameter("username");

        String data1;
        String materia1;
        String docente1;
        String slot_ora1;
        if(data==null || data==""){
            data1="";
        }else{
            data1 = " AND DATA = '"+data+"'";
        }
        if(materia==null || materia == ""){
            materia1="";
        }else{
            materia1 = " AND CORSO = '"+materia+"'";
        }
        if(docente==null || docente == ""){
            docente1="";
        }else{
            docente1 = " AND DOCENTE = '"+docente+"'";
        }
        if(slot_ora==null || slot_ora == ""){
            slot_ora1="";
        }else{
            slot_ora1 = " AND SLOT_ORA = '"+slot_ora+"'";
        }
        list = DAO.filterPrenotations(slot_ora1,data1,materia1,docente1,utente);
        return list;
    }
    public String disdiciPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String codice = request.getParameter("codice");
        String result = DAO.disdiciPrenotazione(codice);
        return result;
    }

    public String effettuaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String codice = request.getParameter("codice");
        String utente = request.getParameter("utente");
        String result = DAO.effettuaPrenotazione(codice,utente);
        return result;
    }
}
