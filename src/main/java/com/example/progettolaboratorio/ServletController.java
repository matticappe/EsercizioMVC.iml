package com.example.progettolaboratorio;

import DAO.*;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

import java.util.ArrayList;
import java.util.Objects;

import static DAO.DAO.loginUtente;
import static java.lang.System.out;

@WebServlet(name = "ServletController", value = "/ServletController") //questa è la value che verrà usata nell'ancora del HTML
public class ServletController extends HttpServlet {
    private String message;
    public void init() {
        //registrazione del driver
        DAO.registerDriver();
    }
    public String risTest;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson gson = new Gson();
            String action = request.getParameter("invia");
            System.out.println("Action = " + action); //inserire un controllo per il != da null
            //out.println(action);
            switch (action) {
                case "login":
                    System.out.println("Sono nel login");
                    String answ=login(request,response);
                    System.out.println("TRA POCO INVIO CORRETTO"+answ);
                    String s = gson.toJson(answ);
                    out.println(s);
                    break;
                case "inserisci_docente":
                    inserimentoDocente(request, response);
                    break;
                case "cancella_prenotazione":
                    cancellaPrenotazione(request, response);
                    break;
                case "inserimentoCorso":
                    inserimentoCorso(request, response);
                    break;
                case "effettuaPrenotazione":
                    effettuaPrenotazione(request, response);
                    break;
                case "inserimentoInsegna":
                    inserimentoInsegna(request, response);
                    break;
                case "updateInsegnaDocente":
                    updateInsegnaDocente(request, response);
                    break;
                case "updateInsegnaMateria":
                    updateInsegnaMateria(request, response);
                    break;
                case "stampa_utenti":
                    stampaUtenti(request, response);
                    break;
                case "viewOwnPrenotations":
                    viewOwnPrenotations(request, response);
                    break;
                case "viewAllPrenotations":
                    viewAllPrenotations(request, response);
                    break;
                case "viewOwnActPrenotations":
                    viewOwnActPrenotations(request, response);
                    break;
                case "filterProf":
                    filterProf(request, response);
                    break;
                case "filterPrenotations":
                    filterPrenotations(request, response);
                    break;
                case "availableSubjects":
                    availableSubjects(request, response);
                    break;
                case "isAdmin":
                    isAdmin(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;

            }
        }   //da togliere, del try
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //Gson gson = new Gson();
        HttpSession s=null;
        //response.setContentType("application/json");     //questo non ci va
        //PrintWriter out = response.getWriter();          //anche questo non ci va
        ServletContext ctx= request.getServletContext();
        RequestDispatcher rd=null;
        String res="";
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username.equals(password) && username==null){
                res="Guest";
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
                    if (s.isNew()) {
                        String account = result.getAccount();
                        s.setAttribute("User", account);
                        int role = result.getRuolo();
                        s.setAttribute("Ruolo", role);
                        System.out.println("LoginEffettuato");
                        String st = "risposta: Corretto";
                        return st;
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
                }
            }
            out.flush();
            out.close();
            String end ="risposta: sono arrivato alla fine";
            return end;
        }finally {
            out.close();
            risTest=res;
            //
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        HttpSession s=request.getSession();
        s.invalidate();
    }

    public int inserimentoDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int result = 0;
        try {
            HttpSession s=request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                System.out.println("Sono nella InsertProf");
                String nome = request.getParameter("name");
                System.out.println(nome);
                String cognome = request.getParameter("surname");
                System.out.println(cognome);
                String code = request.getParameter("code");
                System.out.println(code);
                String a = request.getParameter("attivo");
                int attivo = Integer.parseInt(a);
                if(nome.equals(cognome) && code.equals(a) && cognome.equals(a) && a==null){
                    result=-1; //codifica interna di errore
                }
                int ris = DAO.inserimentoDocente(code, nome, cognome, attivo);
                response.setContentType("text/plain");
                if (ris == 0) {
                    result = 0;
                } else if(ris>=0){
                    result = ris;
                }
                System.out.println(result);
                out.flush();
                out.close();
            }
            else{
                ServletContext ctx=request.getServletContext();
                RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
                String messaggio="Non hai i permessi per utilizzare questa funzione";
                request.setAttribute("message",messaggio);
                rd.include(request,response);
            }
        }
        finally {
            out.close();
            return result;
        }
    }

    public void stampaUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession s=request.getSession();
        if((int)s.getAttribute("role")==1) {
            response.setContentType("text/html");
            ArrayList<Utente> array = DAO.ViewAllUsers();
            for (Utente utente : array)
                out.println(utente);
        }else{
            // To be handled
            //
        }
    }

    public void cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String utente= request.getParameter("utente");
        String docente= request.getParameter("docente");
        String corso= request.getParameter("corso");
        int ris = DAO.cancellazionePrenotazione(utente, docente, corso);
        response.setContentType("text/plain");
        if(ris == 0){
            out.println("Cancellazione avvenuta con successo");
        }
        else
            out.println("Cancellazione fallita");
        out.close();
    }

    public void viewOwnPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username");
            ArrayList<Prenotazione> array = DAO.viewOwnPrenotations(username);
            String messagge = "Le prenotazioni sono: ";
            out.println(messagge);
            for(Prenotazione prenotazione: array)
                out.println(prenotazione);
        }
    }

    public void viewOwnActPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            String username = request.getParameter("username");
            ArrayList<Prenotazione> array = DAO.viewOwnActPrenotations(username);
            String messagge = "Le prenotazione attive sono: ";
            out.println(messagge);
            for(Prenotazione prenotazione: array)
                out.println(prenotazione);
        }
    }

    public void viewAllPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            ArrayList<Prenotazione> array = DAO.viewAllPrenotations();
            String messagge = "Gli utenti sono: ";
            out.println(messagge);
            for(Prenotazione prenotazione: array)
                out.println(prenotazione);
        }
    }

    public void filterPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            String o = request.getParameter("slot_ora");
            String data = request.getParameter("data");
            String materia = request.getParameter("materia");
            String docente = request.getParameter("docente");
            String s = request.getParameter("stato");
            if(data==null){
                data="*";
            }else if(materia==null){
                materia="*";
            }else if(docente==null){
                docente="*";
            }else if(o==null){
                o="*";
            }else if(s==null){
                s="*";
            }
            ArrayList<Prenotazione> array = DAO.filterPrenotations(o,data,materia,docente,s);
            String messagge = "Gli utenti sono: ";
            out.println(messagge);
            for(Prenotazione prenotazione: array)
                out.println(prenotazione);
        }
    }

    public void filterProf(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String codProf = request.getParameter("codProf");
            String a = request.getParameter("attivo");
            int attivo = Integer.parseInt(a);
            ArrayList<Docente> array = DAO.filterProf(nome,cognome,codProf,attivo);
            String messagge = "I docenti sono: ";
            out.println(messagge);
            for(Docente d: array)
                out.println(d);
        }
    }

    public void availableSubjects(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try(PrintWriter out = response.getWriter()){
            String titolo = request.getParameter("titlo");
            ArrayList<Corso> array = DAO.availableSubjects(titolo);
            String messagge = "I docenti sono: ";
            out.println(messagge);
            for(Corso c: array)
                out.println(c);
        }
    }

    public void inserimentoCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            PrintWriter out=response.getWriter();
            HttpSession s=request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                System.out.println("Sono nella inserimentoDocente");
                String corso = request.getParameter("corso");
                System.out.println(corso);
                String result = "";
                int ris = DAO.inserimentoCorso(corso);
                response.setContentType("text/plain");
                if (ris == 0) {
                    result = "CORSO GIA' INSERITO";
                } else {
                    result = "CORSO INSERITO CORRETTAMENTE";
                }
                System.out.println(result);
                out.println(result);
                out.flush();
            }
            else{
                    ServletContext ctx=request.getServletContext();
                    RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
                    String messaggio="Non hai i permessi per utilizzare questa funzione";
                    request.setAttribute("message",messaggio);
                    rd.include(request,response);
            }
        }
        finally {
            out.close();
        }
    }

    public void effettuaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            PrintWriter out= response.getWriter();
            System.out.println("Sono nella effettuaPrenotazione");
            String docente = request.getParameter("docente");
            String corso = request.getParameter("corso");
            String utente = request.getParameter("utente");
            String result = "";
            int ris = DAO.effettuaPrenotazione(utente,docente,corso);
            response.setContentType("text/plain");
            if(ris == 0){
                result = "PRENOTAZIONE EFFETTUTATA CON SUCCESSO";
            }
            else{
                result = "PRENOTAZIONE FALLITA";
            }
            System.out.println(result);
            //Da rivedere con JS e bootstrap
            out.println(result);
            out.flush();
        }
        finally {
            out.close();
        }
    }

    public void inserimentoInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            PrintWriter out=response.getWriter();
            HttpSession s= request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                System.out.println("Sono nella effettuaPrenotazione");
                String docente = request.getParameter("docente");
                String corso = request.getParameter("corso");
                String result = "";
                int ris = DAO.inserimentoInsegna(docente, corso);
                response.setContentType("text/plain");
                if (ris == 0) {
                    result = "INSERIMENTO EFFETTUTATO CON SUCCESSO";
                } else {
                    result = "INSERIMENTO FALLITO";
                }
                System.out.println(result);
                out.println(result);
                out.flush();
            }
            else{
                ServletContext ctx=request.getServletContext();
                RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
                String messaggio="Non hai i permessi per utilizzare questa funzione";
                request.setAttribute("message",messaggio);
                rd.include(request,response);
            }
        }
        finally {
            out.close();
        }
    }

    public void isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try(PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            boolean flag;
            String result;
            flag = DAO.isAdmin(username);
            response.setContentType("text/plain");
            if(flag){
                result = "E' un amministratore";
            }
            else{
                result = "Non è un amministratore";
            }
            out.println(result);
            out.flush();
        }
        finally {
            out.close();
        }
    }

    public void updateInsegnaDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            HttpSession s= request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                String titolo = request.getParameter("titolo");
                String result;
                result = DAO.updateInsegnaDocente(titolo);
                response.setContentType("text/plain");
                out.println(result);
                out.flush();
            }
            else{
                ServletContext ctx=request.getServletContext();
                RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
                String messaggio="Non hai i permessi per utilizzare questa funzione";
                request.setAttribute("message",messaggio);
                rd.include(request,response);
            }
        }
        finally {
            out.close();
        }
    }

    public void updateInsegnaMateria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            HttpSession s= request.getSession();
            if((int) s.getAttribute("ruolo")==1) {
                String codDocente = request.getParameter("codDocente");
                String result;
                result = DAO.updateInsegnaMateria(codDocente);
                response.setContentType("text/plain");
                out.println(result);
                out.flush();
            }
            else{
                ServletContext ctx=request.getServletContext();
                RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
                String messaggio="Non hai i permessi per utilizzare questa funzione";
                request.setAttribute("message",messaggio);
                rd.include(request,response);
            }
        }
        finally {
            out.close();
        }
    }


}
