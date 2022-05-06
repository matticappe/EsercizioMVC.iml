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

                case "inserisciPrenotazione":
                    System.out.println("Sono in inserisci prenotazione");
                    String r5 = inserisciPrenotazione(request, response);
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
                    ArrayList<Utente> array11 = stampaUtenti(request, response);
                    if(array11!=null) {
                        System.out.println("sono nell if viewAllPrenotations");
                        String s111 = gson.toJson(array11);
                        out.println(s111);
                    }
                    break;

                case "viewOwnPrenotations":
                    System.out.println("Sono in viewOwnPrenotations");
                    ArrayList<Prenotazione> s9 = viewOwnPrenotations(request, response);
                    if(s9 != null){
                        String r9 = gson.toJson(s9);
                        out.println(r9);
                    }
                    else{
                        String s9_1 = "Nessuna prenotazione trovata";
                        String r9 = gson.toJson(s9_1);
                        out.println(r9);
                    }
                    break;

                case "viewAllPrenotations":
                    System.out.println("Sono in viewAllPrenotations");
                    ArrayList<Prenotazione> string3 = viewAllPrenotations(request, response);
                    if(string3!=null){
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

                case "filterAdminPrenotations":
                    System.out.println("Sono in filterAdminPrenotations");
                    ArrayList<Prenotazione> out61 =  filterAdminPrenotations(request, response);
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

                case "viewAllProf":
                    System.out.println("Sono in viewAllProf");
                    ArrayList<Docente> list16 = viewAllDocente(request,response);
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
                    System.out.println("Sono in eliminaDocente");
                    String  r17 = eliminaDocente(request,response);
                    String s17 = gson.toJson(r17);
                    out.println(s17);
                    break;

                case "eliminaCorso":
                    System.out.println("Sono in eliminaCorso");
                    String r18 = eliminaCorso(request,response);
                    String s18 = gson.toJson(r18);
                    out.println(s18);
                    break;

                case "disdiciPrenotazione":
                    System.out.println("Sono in disdiciPrenotazione");
                    String r19 = disdiciPrenotazione(request,response);
                    String s19 = gson.toJson(r19);
                    out.println(s19);
                    break;

                case "effettuaPrenotazione":
                    System.out.println("Sono in effettuaPrenotazione");
                    String r20 = effettuaPrenotazione(request,response);
                    String s20 = gson.toJson(r20);
                    out.println(s20);
                    break;

                case "getInsegna":
                    System.out.println("Sono in viewInsegna");
                    ArrayList<Insegna> list17 = viewInsegna(request,response);
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
                        if(isAdmin(request, response) == "yes")
                            st = "admin";
                        else
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
       HttpSession s = request.getSession();
       String result = "";
        if((int)s.getAttribute("ruolo")==1) {
            System.out.println("Sono nella InsertProf");
            String account = request.getParameter("account");
            System.out.println(account);
            String nomeCognome = request.getParameter("nomeCognome");
            System.out.println(nomeCognome);
            String attivo = request.getParameter("attivo");
            result = DAO.inserimentoDocente(account, nomeCognome,attivo);
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

    public ArrayList<Utente> stampaUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession s=request.getSession();
        ArrayList<Utente> list = null;
        //TODO un controllino per vedere se è admin non ci starebbe male ma rompe cose per ora
        if(isAdmin(request,response).equals("yes")){
            list = DAO.ViewAllUsers();
        }
        return list;
    }

    public String cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String codice= request.getParameter("codice");
        String ris = DAO.cancellazionePrenotazione(codice);
        return ris;
    }

    //penso prenotazioni prenotabili
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

    //penso prenotazioni mie prenotate effettivamente
    public ArrayList<Prenotazione> viewOwnActPrenotations(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Prenotazione> list;
        String username = request.getParameter("username");

        String username1;
        if(username==null || username==""){
            username1="";
        }else{
            username1 = " AND USERNAME = '"+username+"'";
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

        String data1;
        String utente1;
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
        if(utente==null || utente == ""){
            utente1="";
        }else{
            utente1 = " AND UTENTE = '"+utente+"'";
        }
        list = DAO.filterAdminPrenotations(slot_ora1,data1,materia1,docente1, utente1);
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
        list = DAO.availableSubjects();
        return list;
    }

    public String inserimentoCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        HttpSession s=request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            System.out.println("Sono nella inserimentoDocente");
            String corso = request.getParameter("corso");
            result = DAO.inserimentoCorso(corso);
            if(corso == null){
                corso = "*";
            }
            System.out.println(corso);
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

    public String inserimentoInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String result = "";
        HttpSession s= request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            System.out.println("Sono nella inserimentoInsegna");
            String docente = request.getParameter("docente");
            String corso = request.getParameter("corso");
            result = result + DAO.inserisciInsegna(docente, corso);
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

    public String updateInsegnaDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "Non hai i permessi";
        HttpSession s= request.getSession();
        if((int)s.getAttribute("ruolo")==1) {
            String codDocente = request.getParameter("codDocente");
            String titolo = request.getParameter("titolo");
            if(titolo == null){
                titolo = "*";
            }
            result = DAO.updateInsegnaDocente(codDocente,titolo);

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
            String corso = request.getParameter("corso");
            if(codDocente == null){
                codDocente = "*";
            }
            result = DAO.updateInsegnaMateria(codDocente,corso);
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

    public String eliminaCorso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String titolo = request.getParameter("titolo");
        String result = DAO.eliminaCorso(titolo);
        return result;
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
    public ArrayList<Insegna> viewInsegna(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ArrayList<Insegna> list = null;
        list = DAO.viewInsegna();
        return list;
    }

    //TODO MODO

}