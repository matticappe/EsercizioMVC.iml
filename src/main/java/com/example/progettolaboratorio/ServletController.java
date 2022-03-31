package com.example.progettolaboratorio;

import DAO.*;
import com.google.gson.Gson;
//import sun.java2d.marlin.DPathConsumer2D;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;

import static DAO.DAO.loginUtente;
import static java.lang.System.out;

// CIAO CAPPEEEEEEEEEEEEEEEEEE

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
            System.out.println("Action = " + action); //inserire un controllo per il != da null

            switch (action) {
                case "login":
                    System.out.println("Sono nel login");
                    String answ =login(request,response);
                    System.out.println("TRA POCO INVIO CORRETTO");
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
                    Utente[] array1;
                    array1 = stampaUtenti(request, response);
                    if(array1 != null) {
                        String r9 = Arrays.toString(array1);
                        String s9 = gson.toJson(r9);
                        out.println(s9);
                    }
                    else{
                        String r9 = "Nessun utente trovato";
                        String s9 = gson.toJson(r9);
                        out.println(s9);
                    }
                    break;

                case "viewOwnPrenotations":
                    System.out.println("Sono in viewOwnPrenotations");
                    ArrayList<Prenotazione> out2 = viewOwnPrenotations(request, response);
                    if(out2 != null) {
                        String s10 = gson.toJson(out2);
                        out.println(s10);
                    }
                    else{
                        String r10 = "Nessuna prenotazione trovata";
                        String s10 = gson.toJson(r10);
                        out.println(s10);
                    }
                    break;

                case "viewAllPrenotations":
                    System.out.println("Sono in viewAllPrenotations");
                    //Prenotazione[] array3 = viewAllPrenotations(request, response);
                    /*String*/ ArrayList<Prenotazione> string3 = viewAllPrenotations(request, response);
                    /*System.out.println("test array3[]");
                    for(int i = 0; i<array3.length; i++){
                        System.out.println(i+" "+array3[i].getCodice()+array3[i].getUtente()+array3[i].getDocente()+array3[i].getData()+array3[i].getSlot_ora()+array3[i].getCorso());
                    }
                    if(array3 != null){
                        System.out.println("sono nell if viewAllPrenotations");
                        String r11 = Arrays.toString(array3);
                        //String r11 = Arrays.copyOf(array3, array3.length, String[].class);
                        System.out.println("test Stringa");
                        System.out.println(r11);
                        String s11 = gson.toJson(r11);
                        out.println(s11);
                    }
                     */
                    if(string3!=null){
                        System.out.println("sono nell if viewAllPrenotations");
                        //String r11 = Arrays.copyOf(array3, array3.length, String[].class);
                        System.out.println(string3);
                        for(Prenotazione str: string3){
                            System.out.println(str.getCodice()+str.getUtente()+str.getDocente()+str.getCorso()+str.getData()+str.getSlot_ora()+"\n");
                        }
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
                    System.out.println("Sono in viewOwnActPrenotations");
                    ArrayList<Prenotazione> out4 = viewOwnActPrenotations(request, response);
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
                    System.out.println("Sono in filterProf");
                    ArrayList<Docente> out5 = filterProf(request, response);
                    if (out5 != null){
                        String r13 = gson.toJson(out5);
                        out.println(r13);
                    }
                    else{
                        String s13 = "Nessun professore trovato";
                        String r13 = gson.toJson(s13);
                        out.println(r13);
                    }
                    break;

                case "filterPrenotations":
                    System.out.println("Sono in filterPrenotations");
                    ArrayList<Prenotazione> out6 =  filterPrenotations(request, response);
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

                case "availableSubjects":
                    System.out.println("Sono in availableSubjects");
                    ArrayList<Corso> out7 = availableSubjects(request, response);
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
                    System.out.println("Sono in isAdmin");
                    String s15 = isAdmin(request, response);
                    String r15 = gson.toJson(s15);
                    out.println(r15);
                    break;

                case "logout":
                    System.out.println("Sono in logout");
                    String lout=logout(request, response);
                    String sLout = gson.toJson(lout);
                    out.println(sLout);
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
        RequestDispatcher rd =null;
        String res="";
        try {
            String st="";
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username.equals(password) && username==""){      //non è null, è una stringa vuota
                st="Guest";
                out.flush();
                out.close();
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
                        st = "utenteRegistrato";
                        out.flush();
                        out.close();
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
                    out.flush();
                    out.close();
                    return st;
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

    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        HttpSession s=request.getSession();
        s.invalidate();
        String st="Logout effettuato con successo";
        return st;
    }

    public String inserimentoDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {;
        String result = "Inserimento fallito";
        int ris;
        HttpSession s=request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            System.out.println("Sono nella InsertProf");
            String account = request.getParameter("account");
            System.out.println(account);
            String password = request.getParameter("password");
            System.out.println(password);
            String ruolo = request.getParameter("ruolo");
            System.out.println(ruolo);
            String nomeCognome = request.getParameter("nomeCognome");
            System.out.println(nomeCognome);
            String attivo = request.getParameter("attivo");
            if(account == null){
                account = "*";
            }else if (password == null){
                password = "*";
            }else if(ruolo == null){
                ruolo = "*";
            }else if(nomeCognome == null){
                nomeCognome = "*";
            }else if(attivo == null){
                attivo = "*";
            }
            ris = DAO.inserimentoDocente(account, password, ruolo, nomeCognome,attivo);
            if(ris != 0)
                result = "L'inserimento del docente " + nomeCognome + "è avvenuto con successo";
            System.out.println(result);
        }
        else{
            ServletContext ctx=request.getServletContext();
            RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
            String messaggio="Non hai i permessi per utilizzare questa funzione";
            request.setAttribute("message",messaggio);
            rd.include(request,response);
        }
        return result;
    }

    public Utente[] stampaUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession s=request.getSession();
        ArrayList<Utente> list = null;
        if((int)s.getAttribute("role")==1) {
            list = DAO.ViewAllUsers();
        }
        Utente[] array = null;
        if(list != null)
            array = new Utente[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public String cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "Cancellazione fallita";
        String codice= request.getParameter("codice");
        String utente= request.getParameter("utente");
        String docente= request.getParameter("docente");
        String corso= request.getParameter("corso");
        String data= request.getParameter("data");
        String slot_ora= request.getParameter("slot_ora");
        String attivo = request.getParameter("attivo");
        int ris = DAO.cancellazionePrenotazione(codice,utente,docente,corso, data,slot_ora);
        if(ris != 0){
            result = "La cancellazione della prenotazione dell'utente " + utente + " con il docente " + docente + "per il corso" + corso + " è avvenuta con successo";
        }
        return result;
    }

    //penso prenotazioni prenotabili
    public ArrayList<Prenotazione> viewOwnPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String username = request.getParameter("username");
        if(username == null){
            username = "*";
        }
        list = DAO.viewOwnPrenotations(username);
        return list;
    }

    //penso prenotazioni mie prenotate effettivamente
    public ArrayList<Prenotazione> viewOwnActPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String username = request.getParameter("username");
        if(username == null){
            username = "*";
        }
        list = DAO.viewOwnActPrenotations(username);
        return list;
    }

    public /*Prenotazione[]*/ /*String*/ ArrayList<Prenotazione> viewAllPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        //String list;
        list = DAO.viewAllPrenotations();
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
        if(data==null){
            data="*";
        }else{
            data = " AND DATA = '"+data+"'";
        }if(materia==null){
            materia="*";
        }else{
            data = " AND CORSO = '"+materia+"'";
        }if(docente==null){
            docente="*";
        }else{
            data = " AND DOCENTE = '"+docente+"'";
        }if(slot_ora==null){
            slot_ora="*";
        }else{
            data = " AND SLOT_ORA = '"+slot_ora+"'";
        }
        list = DAO.filterPrenotations(slot_ora,data,materia,docente,utente);
        return list;
    }

    public ArrayList<Docente> filterProf(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Docente> list;
        String account = request.getParameter("account");
        String nomeCognome = request.getParameter("nomeCognome");
        String attivo = request.getParameter("attivo");

        if(account == null){
            account = "*";
        }else if(nomeCognome == null){
            nomeCognome = "*";
        }else if(attivo == null){
            attivo = "*";
        }
        list = DAO.filterProf();
        return list;
    }


    public ArrayList<Corso> availableSubjects(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Corso> list;
        //String titolo = request.getParameter("titlo");
        //System.out.println(titolo);
        //if(titolo == null){
        //    titolo = "*";
        //}
        list = DAO.availableSubjects();
        return list;
    }

    public String inserimentoCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "Inserimento fallito";
        HttpSession s=request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            System.out.println("Sono nella inserimentoDocente");
            String corso = request.getParameter("corso");
            if(corso == null){
                corso = "*";
            }
            System.out.println(corso);
            int ris = DAO.inserimentoCorso(corso);
            if (ris != 0) {
                result = "L'inserimento del corso " + corso + " è avvenuta con successo";
            }
            System.out.println(result);
        }
        else{
            ServletContext ctx=request.getServletContext();
            RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
            String messaggio="Non hai i permessi per utilizzare questa funzione";
            request.setAttribute("message",messaggio);
            rd.include(request,response);
        }
        return result;
    }

    // Commento inutile
    public String effettuaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "Prenotazione fallita";
        System.out.println("Sono nella effettuaPrenotazione");
        String codice= request.getParameter("codice");
        String slot_ora= request.getParameter("slot_ora");
        String data= request.getParameter("data");
        String utente= request.getParameter("utente");
        String docente= request.getParameter("docente");
        String corso= request.getParameter("corso");
        int ris = DAO.effettuaPrenotazione(codice,utente,docente,corso,data,slot_ora);
        if(ris != 0){
            result = "La prenotazione dell'utente " + utente + " con il docente " + docente+ " del corso " + corso + " è avvenuta con successo";
        }
        System.out.println(result);
        return result;
    }

    public String inserimentoInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "Inserimento fallito";
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
            if (ris != 0)
                result = "L'inserimento del docente " + docente + " del corso " + corso + " è avvenuta con successo";
            System.out.println(result);
        }
        else{
            ServletContext ctx=request.getServletContext();
            RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
            String messaggio="Non hai i permessi per utilizzare questa funzione";
            request.setAttribute("message",messaggio);
            rd.include(request,response);
        }
        return result;
    }

    public String isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String result = "";
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
        return result;
    }

    public String updateInsegnaDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        HttpSession s= request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            String titolo = request.getParameter("titolo");
            if(titolo == null){
                titolo = "*";
            }
            result = DAO.updateInsegnaDocente(titolo);

        }
        else{
            ServletContext ctx=request.getServletContext();
            RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
            String messaggio="Non hai i permessi per utilizzare questa funzione";
            request.setAttribute("message",messaggio);
            rd.include(request,response);
        }
        return result;
    }

    public String updateInsegnaMateria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        HttpSession s= request.getSession();
        if((int) s.getAttribute("ruolo")==1) {
            String codDocente = request.getParameter("codDocente");
            if(codDocente == null){
                codDocente = "*";
            }
            result = DAO.updateInsegnaMateria(codDocente);
            return result;
        }
        else{
            ServletContext ctx=request.getServletContext();
            RequestDispatcher rd=ctx.getNamedDispatcher("servletError");
            String messaggio="Non hai i permessi per utilizzare questa funzione";
            request.setAttribute("message",messaggio);
            rd.include(request,response);
        }
        return result;
    }
}