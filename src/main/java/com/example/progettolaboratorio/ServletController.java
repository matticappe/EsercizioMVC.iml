package com.example.progettolaboratorio;

import DAO.*;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "ServletController", value = "/ServletController") //questa è la value che verrà usata nell'ancora del HTML
public class ServletController extends HttpServlet {
    public void init() {
        //registrazione del driver
        DAO.registerDriver();
    }
    public String risTest;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson gson = new Gson();
            String action = request.getParameter("invia");
            ServletContext ctx=getServletContext();
            RequestDispatcher rd;
            String actionRD;
            System.out.println("Action = " + action); //inserire un controllo per il != da null

            String userName = request.getParameter("username");
            HttpSession s = request.getSession();
            if(userName != null)
                s.setAttribute("userName", userName);
            System.out.println("HTTP SESSIONE " + s.getAttribute("userName"));

            switch (action) {
                case "login":
                    actionRD="login";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletLogin");
                    rd.include(request,response);

                    String answ = (String) request.getAttribute("risultato");
                    String s1 = gson.toJson(answ);
                    out.println(s1);
                    break;

                case "inserimentoDocente":
                    actionRD="inserimentoDocente";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletDocenti");
                    rd.include(request,response);

                    String r2 = (String) request.getAttribute("risultato");
                    String s2 = gson.toJson(r2);
                    out.println(s2);
                    break;

                case "cancellaPrenotazione":
                    actionRD="cancellaPrenotazione";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    String r3 = (String) request.getAttribute("risultato");
                    String s3 = gson.toJson(r3);
                    out.println(s3);
                    break;

                case "inserimentoCorso":
                    actionRD="inserimentoCorso";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletCorsi");
                    rd.include(request,response);

                    String r4 = (String) request.getAttribute("risultato");
                    String s4 = gson.toJson(r4);
                    out.println(s4);
                    break;

                case "inserisciPrenotazione":
                    actionRD="inserisciPrenotazione";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    String r5 = (String) request.getAttribute("risultato");
                    String s5 = gson.toJson(r5);
                    out.println(s5);
                    break;

                case "inserisciInsegna":
                    actionRD="inserisciInsegna";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletInsegna");
                    rd.include(request,response);

                    String r6 = (String) request.getAttribute("risultato");
                    String s6 = gson.toJson(r6);
                    out.println(s6);
                    break;

                case "rimuoviInsegna":
                    actionRD="rimuoviInsegna";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletInsegna");
                    rd.include(request,response);

                    String r7 = (String) request.getAttribute("risultato");
                    String s7 = gson.toJson(r7);
                    out.println(s7);
                    break;

                case "addInsegna":
                    actionRD="addInsegna";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletInsegna");
                    rd.include(request,response);

                    String r8 = (String) request.getAttribute("risultato");
                    String s8 = gson.toJson(r8);
                    out.println(s8);
                    break;

                case "stampaUtenti":
                    System.out.println("Sono in stampaUtenti");
                    ArrayList<Utente> array11 = stampaUtenti(request, response);
                    if(array11!=null) {
                        String s111 = gson.toJson(array11);
                        out.println(s111);
                    }
                    break;

                case "viewOwnPrenotations":
                    System.out.println("1");
                    actionRD="viewOwnPrenotations";
                    request.setAttribute("actionRD",actionRD);
                    System.out.println("2");
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    System.out.println("3");
                    rd.include(request,response);
                    System.out.println("4");

                    ArrayList<Prenotazione> s9 = (ArrayList<Prenotazione>) request.getAttribute("risultato");
                    if(s9 != null){
                        String r9 = gson.toJson(s9);
                        out.println(r9);
                        System.out.println(s9);
                    }
                    else{
                        String s9_1 = "Nessuna prenotazione trovata";
                        String r9 = gson.toJson(s9_1);
                        out.println(r9);
                    }
                    break;

                case "viewAllPrenotations":
                    actionRD="viewAllPrenotations";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    ArrayList<Prenotazione> string3 = (ArrayList<Prenotazione>) request.getAttribute("risultato");
                    if(string3!=null){
                        System.out.println("test Stringa");
                        String s11 = gson.toJson(string3);
                        out.println(s11);
                    }
                    else{
                        String r11 = "Nessuna prenotazione trovata";
                        String s11 = gson.toJson(r11);
                        out.println(s11);
                    }
                    break;

                case "viewOwnActPrenotations":
                    actionRD="viewOwnActPrenotations";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    ArrayList<Prenotazione> out4 = (ArrayList<Prenotazione>) request.getAttribute("risultato");
                    if (out4 != null){
                        String s12 = gson.toJson(out4);
                        out.println(s12);
                    }
                    else {
                        String r12 = "Nessuna prenotazione disponibile per questo username";
                        String s12 = gson.toJson(r12);
                        out.println(s12);
                    }
                    break;

                case "filterProf":
                    actionRD="filterProf";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletDocenti");
                    rd.include(request,response);

                    ArrayList<Docente> out5 = (ArrayList<Docente>) request.getAttribute("risultato");
                    if (out5 != null){
                        String r13 = gson.toJson(out5);
                        out.println(r13);
                        System.out.println(r13);
                    }
                    else{
                        String s13 = "Nessun professore trovato";
                        String r13 = gson.toJson(s13);
                        out.println(r13);
                    }
                    break;

                case "filterPrenotations":
                    actionRD="filterPrenotations";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    ArrayList<Prenotazione> out6 =  (ArrayList<Prenotazione>) request.getAttribute("risultato");
                    System.out.println("FILTRATE"+out6);
                    if(out6 != null){
                        String r14 = gson.toJson(out6);
                        out.println(r14);
                    }
                    else {
                        String s14 = "Nessuna prenotazione trovata";
                        String r14 = gson.toJson(s14);
                        out.println(r14);
                    }
                    break;

                case "filterAdminPrenotations":
                    actionRD="filterAdminPrenotations";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    ArrayList<Prenotazione> out61 =  (ArrayList<Prenotazione>) request.getAttribute("risultato");
                    System.out.println("FILTRATE"+out61);
                    if(out61 != null){
                        String r14 = gson.toJson(out61);
                        out.println(r14);
                    }
                    else {
                        String s14 = "Nessuna prenotazione trovata";
                        String r14 = gson.toJson(s14);
                        out.println(r14);
                    }
                    break;

                case "availableSubjects":
                    actionRD="availableSubjects";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletCorsi");
                    rd.include(request,response);
                    ArrayList<Corso> out7 = (ArrayList<Corso>) request.getAttribute("risultato");
                    if(out7 != null){
                        String r15 = gson.toJson(out7);
                        out.println(r15);
                    }
                    else {
                        String s14 = "Nessun corso trovato";
                        String r14 = gson.toJson(s14);
                        out.println(r14);
                    }
                    break;

                case "isAdmin":
                    actionRD="isAdmin";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletLogin");
                    rd.include(request,response);

                    String s15 = (String) request.getAttribute("risultato");
                    String r15 = gson.toJson(s15);
                    out.println(r15);
                    break;

                case "logout":
                    actionRD="logout";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletLogin");
                    rd.include(request,response);
                    s.invalidate();

                    String lout=(String) request.getAttribute("risultato");
                    String sLout = gson.toJson(lout);
                    out.println(sLout);
                    break;

                case "viewAllProf":
                    actionRD="viewAllProf";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletDocenti");
                    rd.include(request,response);

                    ArrayList<Docente> list16 = (ArrayList<Docente>) request.getAttribute("risultato");
                    if(list16 != null){
                        System.out.println("Sono nell if viewAllProf");
                        System.out.println(list16);
                        for (Docente d : list16) {
                            System.out.println(d.getAccount() + d.getNomeCognome() + d.getAttivo());
                        }
                        String s16 = gson.toJson(list16);
                        out.println(s16);
                    }
                    else{
                        String r16 = "Nessun docente trovato";
                        String s16 = gson.toJson(r16);
                        out.println(s16);
                    }
                    break;

                case "eliminaDocente":
                    actionRD="eliminaDocente";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletDocenti");
                    rd.include(request,response);

                    String  r17 = (String) request.getAttribute("risultato");
                    String s17 = gson.toJson(r17);
                    out.println(s17);
                    break;

                case "eliminaCorso":
                    actionRD="eliminaCorso";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletCorsi");
                    rd.include(request,response);

                    String r18 = (String) request.getAttribute("risultato");
                    String s18 = gson.toJson(r18);
                    out.println(s18);
                    break;

                case "disdiciPrenotazione":
                    actionRD="disdiciPrenotazione";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    String r19 = (String) request.getAttribute("risultato");
                    String s19 = gson.toJson(r19);
                    out.println(s19);
                    break;

                case "effettuaPrenotazione":
                    actionRD="effettuaPrenotazione";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletPrenotazione");
                    rd.include(request,response);

                    System.out.println("Sono in effettuaPrenotazione");
                    String r20 = (String) request.getAttribute("risultato");
                    String s20 = gson.toJson(r20);
                    out.println(s20);
                    break;

                case "getInsegna":
                    actionRD="getInsegna";
                    request.setAttribute("actionRD",actionRD);
                    rd=ctx.getNamedDispatcher("ServletInsegna");
                    rd.include(request,response);

                    ArrayList<Insegna> list17 = (ArrayList<Insegna>) request.getAttribute("risultato");
                    if(list17 != null){
                        System.out.println("Sono nell if viewInsegna");
                        System.out.println(list17);
                        for (Insegna d : list17) {
                            System.out.println(d.getCodDocente() + d.getCorso());
                        }
                        String s16 = gson.toJson(list17);
                        out.println(s16);
                    }
                    else{
                        String r21 = "Nessun insegnamento trovato";
                        String s21 = gson.toJson(r21);
                        out.println(s21);
                    }
                    break;
            }
            out.flush();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    //STAMPA UTENTI NON LO SPOSTO
    public ArrayList<Utente> stampaUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Utente> list = null;
        if(isAdmin(request,response).equals("yes")){
            list = DAO.ViewAllUsers();
        }
        return list;
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