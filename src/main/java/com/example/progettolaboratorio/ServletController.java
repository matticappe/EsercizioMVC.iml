package com.example.progettolaboratorio;

import DAO.*;
import com.google.gson.Gson;
import sun.java2d.marlin.DPathConsumer2D;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

import java.util.ArrayList;
import static DAO.DAO.loginUtente;
import static java.lang.System.out;

@WebServlet(name = "ServletController", value = "/ServletController") //questa è la value che verrà usata nell'ancora del HTML
public class ServletController extends HttpServlet {
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
                    String s1 = gson.toJson(answ);
                    out.println(s1);
                    break;

                case "inserisciDocente":
                    System.out.println("Sono in inserimento docente");
                    String r2 = inserimentoDocente(request, response);
                    String s2 = gson.toJson(r2);
                    out.println(s2);
                    break;

                case "cancellaPrenotazione":
                    System.out.println("Sono in cancella prenotazione");
                    String r3 = cancellaPrenotazione(request, response);
                    String s3 = gson.toJson(r3);
                    out.println(s3);
                    break;

                case "inserimentoCorso":
                    System.out.println("Sono in inserimento corso");
                    String r4 = inserimentoCorso(request, response);
                    String s4 = gson.toJson(r4);
                    out.println(s4);
                    break;

                case "effettuaPrenotazione":
                    System.out.println("Sono in effettua prenotazione");
                    String r5 = effettuaPrenotazione(request, response);
                    String s5 = gson.toJson(r5);
                    out.println(s5);
                    break;

                case "inserimentoInsegna":
                    System.out.println("Sono in inserimento insegna");
                    String r6 = inserimentoInsegna(request, response);
                    String s6 = gson.toJson(r6);
                    out.println(s6);
                    break;

                case "updateInsegnaDocente":
                    System.out.println("Sono in updateInsegnaDocente");
                    String r7 = updateInsegnaDocente(request, response);
                    String s7 = gson.toJson(r7);
                    out.println(s7);
                    break;

                case "updateInsegnaMateria":
                    System.out.println("Sono in updateInsegnaMateria");
                    String r8 = updateInsegnaMateria(request, response);
                    String s8 = gson.toJson(r8);
                    out.println(s8);
                    break;

                case "stampaUtenti":
                    System.out.println("Sono in stampaUtenti");
                    ArrayList<Utente> array1 = stampaUtenti(request, response);
                    if(array1 != null) {
                        for (Utente u : array1) {
                            String r9 = u.toString();
                            String s9 = gson.toJson(r9);
                            out.println(s9);
                        }
                    }
                    else{
                        String r9 = "Nessun utente trovato";
                        String s9 = gson.toJson(r9);
                        out.println(s9);
                    }
                    break;

                case "viewOwnPrenotations":
                    System.out.println("Sono in viewOwnPrenotations");
                    ArrayList<Prenotazione> array2 = viewOwnPrenotations(request, response);
                    if(array2 != null) {
                        for (Prenotazione p2 : array2) {
                            String r10 = p2.toString();
                            String s10 = gson.toJson(r10);
                            out.println(s10);
                        }
                    }
                    else{
                        String r10 = "Nessuna prenotazione trovata";
                        String s10 = gson.toJson(r10);
                        out.println(s10);
                    }
                    break;

                case "viewAllPrenotations":
                    System.out.println("Sono in viewAllPrenotations");
                    ArrayList<Prenotazione> array3 = viewAllPrenotations(request, response);
                    if(array3 != null){
                        for (Prenotazione p3 : array3) {
                            String r11 = p3.toString();
                            String s11 = gson.toJson(r11);
                            out.println(s11);
                        }
                    }
                    else{
                        String r11 = "Nessuna prenotazione trovata";
                        String s11 = gson.toJson(r11);
                        out.println(s11);
                    }
                    break;

                case "viewOwnActPrenotations":
                    System.out.println("Sono in viewOwnActPrenotations");
                    ArrayList<Prenotazione> array4 = viewOwnActPrenotations(request, response);
                    if (array4 != null){
                        for (Prenotazione p4: array4){
                            String r12 = p4.toString();
                            String s12 = gson.toJson(r12);
                            out.println(s12);
                        }
                    }
                    else {
                        String r12 = "Nessuna prenotazione disponibile per questo username";
                        String s12 = gson.toJson(r12);
                        out.println(s12);
                    }
                    break;

                case "filterProf":
                    System.out.println("Sono in filterProf");
                    ArrayList<Docente> array5 = filterProf(request, response);
                    if (array5 != null){
                        for(Docente d1: array5){
                            String s13 = d1.toString();
                            String r13 = gson.toJson(s13);
                            out.println(r13);
                        }
                    }
                    else{
                        String s13 = "Nessun professore trovato";
                        String r13 = gson.toJson(s13);
                        out.println(r13);
                    }
                    break;

                case "filterPrenotations":
                    System.out.println("Sono in filterPrenotations");
                    ArrayList<Prenotazione> array6 =  filterPrenotations(request, response);
                    if(array6 != null){
                        for(Prenotazione p5: array6){
                            String s14 = p5.toString();
                            String r14 = gson.toJson(s14);
                            out.println(r14);
                        }
                    }
                    else {
                        String s14 = "Nessuna prenotazione trovata";
                        String r14 = gson.toJson(s14);
                        out.println(r14);
                    }
                    break;

                case "availableSubjects":
                    System.out.println("Sono in availableSubjects");
                    ArrayList<Corso> array7 = availableSubjects(request, response);
                    if(array7 != null){
                        for (Corso c1 : array7){
                            String s15 = c1.toString();
                            String r15 = gson.toJson(s15);
                            out.println(r15);
                        }
                    }
                    else {
                        String s14 = "Nessun corso trovato";
                        String r14 = gson.toJson(s14);
                        out.println(r14);

                    }
                    break;

                case "isAdmin":
                    System.out.println("Sono in isAdmin");
                    String s15 = isAdmin(request, response);
                    String r15 = gson.toJson(s15);
                    out.println(r15);
                    break;

                case "logout":
                    logout(request, response);
                    break;

            }
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
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        HttpSession s=request.getSession();
        s.invalidate();
    }

    public String inserimentoDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        int ris;
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
                if(nome == null){
                    nome = "*";
                }else if (code == null){
                    code = "*";
                }else if(a == null){
                    a = "*";
                }
                if(cognome == null){
                    cognome = "*";
                }
                ris = DAO.inserimentoDocente(code, nome, cognome, a);
                response.setContentType("text/plain");
                if(ris != 0)
                    result = "Inserimento avvenuto correttamente";
                else
                    result = "Inserimento fallito";
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

    public ArrayList<Utente> stampaUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession s=request.getSession();
        ArrayList<Utente> array = null;
        if((int)s.getAttribute("role")==1) {
            response.setContentType("text/html");
            array = DAO.ViewAllUsers();
        }
        return array;
    }

    public String cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        String utente= request.getParameter("utente");
        String docente= request.getParameter("docente");
        String corso= request.getParameter("corso");
        if(utente == null){
            utente = "*";
        }else if(docente == null){
            docente = "*";
        }else if(corso == null)
            corso = "*";
        int ris = DAO.cancellazionePrenotazione(utente, docente, corso);
        response.setContentType("text/plain");
        if(ris != 0){
            result = "Cancellazione avvenuta con successo";
        }
        else
            result = "Cancellazione fallita";
        out.close();
        return result;
    }

    public ArrayList<Prenotazione> viewOwnPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> array = null;
        String username = request.getParameter("username");
        if(username == null){
            username = "*";
        }
        array = DAO.viewOwnPrenotations(username);
        return array;
    }

    public ArrayList<Prenotazione> viewOwnActPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> array = null;
        String username = request.getParameter("username");
        if(username == null){
            username = "*";
        }
        array = DAO.viewOwnActPrenotations(username);
        return array;
    }

    public ArrayList<Prenotazione> viewAllPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> array;
        array = DAO.viewAllPrenotations();
        return array;
    }

    public ArrayList<Prenotazione> filterPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> array = null;
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
        array = DAO.filterPrenotations(o,data,materia,docente,s);
        return array;
    }

    public ArrayList<Docente> filterProf(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Docente> array = null;
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String codProf = request.getParameter("codProf");
        String attivo = request.getParameter("attivo");
        if(nome == null){
            nome = "*";
        }else if(cognome == null){
            cognome = "*";
        }else if(codProf == null){
            codProf = "*";
        }else if(attivo == null){
            attivo = "*";
        }
        array = DAO.filterProf(nome,cognome,codProf,attivo);
        return array;
    }

    public ArrayList<Corso> availableSubjects(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Corso> array = null;
        String titolo = request.getParameter("titlo");
        if(titolo == null){
            titolo = "*";
        }
        array = DAO.availableSubjects(titolo);
        return array;
    }

    public String inserimentoCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "INSERIMENTO FALLITO";
        try {
            PrintWriter out=response.getWriter();
            HttpSession s=request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                System.out.println("Sono nella inserimentoDocente");
                String corso = request.getParameter("corso");
                if(corso == null){
                    corso = "*";
                }
                System.out.println(corso);
                int ris = DAO.inserimentoCorso(corso);
                response.setContentType("text/plain");
                if (ris == 0) {
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
            return result;
        }
    }

    public String effettuaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        try {
            PrintWriter out= response.getWriter();
            System.out.println("Sono nella effettuaPrenotazione");
            String docente = request.getParameter("docente");
            String corso = request.getParameter("corso");
            String utente = request.getParameter("utente");
            if(docente == null){
                docente = "*";
            }else if(corso == null){
                corso = "*";
            }else if(utente == null){
                utente = "*";
            }
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
            return result;
        }
    }

    public String inserimentoInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        try {
            PrintWriter out=response.getWriter();
            HttpSession s= request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                System.out.println("Sono nella effettuaPrenotazione");
                String docente = request.getParameter("docente");
                String corso = request.getParameter("corso");
                if(docente == null){
                    docente = "*";
                }else if(corso == null){
                    corso = "*";
                }
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
            return result;
        }
    }

    public String isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String result = "";
        try(PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            if(username == null){
                username = "*";
            }
            boolean flag;
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
            return result;
        }
    }

    public String updateInsegnaDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        try (PrintWriter out = response.getWriter()) {
            HttpSession s= request.getSession();
            if((int)s.getAttribute("ruolo")==1) {
                String titolo = request.getParameter("titolo");
                if(titolo == null){
                    titolo = "*";
                }
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
            return result;
        }
    }

    public String updateInsegnaMateria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        try (PrintWriter out = response.getWriter()) {
            HttpSession s= request.getSession();
            if((int) s.getAttribute("ruolo")==1) {
                String codDocente = request.getParameter("codDocente");
                if(codDocente == null){
                    codDocente = "*";
                }
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
            return result;
        }
    }
}
