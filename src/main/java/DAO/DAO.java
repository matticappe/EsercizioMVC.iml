package DAO;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;

public class DAO {

    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static int inserimentoCorso(String corso){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("INSERT INTO CORSO(TITOLO) VALUES('"+corso+"')");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return ris;
    }

    public static int cancellazionePrenotazione(String codice, String utente, String docente, String corso, String data, String slot_ora){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("UPDATE FROM PRENOTAZIONE SET STATO='0' WHERE UTENTE='"+utente+"' AND CODICE='"+codice+"'AND DOCENTE='"+docente+"' AND CORSO='"+corso+"'AND DATA='"+data+"'AND SLOT_ORA='"+slot_ora+"'");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return ris;
    }
    public static int effettuaPrenotazione(String codice, String utente, String docente, String corso, String data, String slot_ora){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                st.execute("INSERT INTO PRENOTAZIONE(CODICE,UTENTE,DOCENTE,CORSO,DATA,SLOT_ORA) VALUES ('"+codice+"','"+utente+"','"+docente+"','"+corso+"','"+data+"','"+slot_ora+"')");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return ris;
    }


    public static int inserimentoDocente(String account, String password, String ruolo, String nomeCognome, String attivo){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("INSERT INTO DOCENTE(ACCOUNT, PASSWORD, RUOLO, NOMECOGNOME, ATTIVO) VALUES('"+account+"', '"+ password+"', '"+ruolo+"', '"+nomeCognome+"','"+attivo+"')");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return ris;
    }

    public static int inserimentoInsegna(String codDocente, String corso){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("INSERT INTO INSEGNA(CODDOCENTE, CORSO) VALUES('" + codDocente + "', '" + corso + "')");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return ris;
    }


    public static ArrayList<Utente> ViewAllUsers() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            ResultSet rs=null;
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                rs = st.executeQuery("SELECT * FROM UTENTE");
                while (rs.next()) {
                    Utente p = new Utente(rs.getString("ACCOUNT"),rs.getString("PASSWORD"), rs.getInt("RUOLO"));
                    out.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }


    public static Utente loginUtente(String account, String password2) {
        Connection conn1 = null;
        System.out.println("Account nella query loginUtente: " + account);
        System.out.println("Password nella password loginUtente: " + password2);
        Utente out = null;
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE ACCOUNT = '" + account + "'" + "AND PASSWORD = '" + password2 + "'");
                if(rs.next()){
                    out = new Utente(rs.getString("account"),rs.getString("password"),rs.getInt("ruolo"));
                    System.out.println("loginUtente = "+out);
                }
            }
        }catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }
    public static boolean isAdmin(String account){
        Connection conn1 = null;
        System.out.println("Account nella query isAdmin: " + account);
        boolean res= false;
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE ACCOUNT = "  +account);
                if(rs.next()){
                    if(rs.getInt("ruolo") == 1)
                        res=true;
                }
            }
        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        } finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return res;
    }

    //gli input vanno gestiti da servlet
    public static String updateInsegnaDocente(String corso){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("UPDATE FROM INSEGNA SET CODDOCENTE='codDocente' WHERE corso = "  + corso);
                if(rs.next()){
                    out = out + "Aggiornamento fatto con successo";
                } else {
                    out = out + "Aggiornamento fallito";
                }
                System.out.println(out);
            }
        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    public static String updateInsegnaMateria(String codDocente){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("UPDATE FROM INSEGNA SET TITOLO='Titolo' WHERE CODDOCENTE = "  + codDocente);
                if(rs.next()){
                    out = out + "Aggiornamento del docente" + codDocente +" fatto con successo";
                } else {
                    out = out + "Aggiornamento fallito";
                }
                System.out.println(out);
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    public static ArrayList<Prenotazione> viewAllPrenotations(){
        System.out.println("sono in viewAllPrenotations");
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                System.out.println("connessione riuscita");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE");
                int i = 0;
                Prenotazione p;
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i+" "+p.getCodice()+p.getUtente()+p.getDocente()+p.getCorso()+p.getData()+p.getSlot_ora());
                    out.add(p);
                }
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    //storico utente
    public static ArrayList<Prenotazione> viewOwnPrenotations(String utente){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE "  + utente);
                int i = 0;
                while (rs.next()) {
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
                    out.add(p);

                    /*
                    if(i != 0)
                        out = out + ",\n";
                    out = out + "{\n Codice prenotazione: '"+p.getCodice()+"',\n Utente: '"+p.getUtente()+"',\n Docente: '"+p.getDocente()+"',\n Corso: '"+p.getCorso()+"',\n Data: '"+p.getData()+"',\n Slot Ora: '"+p.getSlot_ora()+"',\n}";
                    i++;
                     */
                }
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    //prenotazioni attive
    public static ArrayList<Prenotazione> viewOwnActPrenotations(String account){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        //String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE UTENE IS NULL OR UTENTE = '" + account + "'");
                int i = 0;
                while (rs.next()) {
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
                    out.add(p);

                    /*
                    if(i != 0)
                        out = out + ",\n";
                    out = out + "{\n Codice prenotazione: '"+p.getCodice()+"',\n Utente: '"+p.getUtente()+"',\n Docente: '" +p.getDocente() + "',\n Corso: '" +p.getCorso()+ "',\n Data: '"+ p.getData()+ "',\n Slot Ora: '" + p.getSlot_ora()+"',\n}";
                    i++;
                     */
                }
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    //filtra per fascia oraria, giorno, materia e professore
    //da gestire nella servlet il controllo dell'input
    //tutto cio' che in input è null va gestito con *
    public static ArrayList<Prenotazione> filterPrenotations(String slot_ora, String data, String corso, String docente, String utente){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        //String out = " ";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                //ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE DATA = '"+data+"'" );   //CONTROLLA LE VIRGOLETTE DOVE CI SONO TUTTI I +...+...+...+

                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE (UTENTE IS NULL OR UTENTE = '"+utente+"') "+data+" "+corso+" "+slot_ora+" "+docente+" " );   //CONTROLLA LE VIRGOLETTE DOVE CI SONO TUTTI I +...+...+...+
                //                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE (UTENTE = null or UTENTE = '"+utente+"' ) "+data+" "+corso+" "+slot_ora+" "+docente+" " );   //CONTROLLA LE VIRGOLETTE DOVE CI SONO TUTTI I +...+...+...+
                int i = 0;
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
                    out.add(p);

                }
                //Prenotazione p = new Prenotazione("impestata", "dio", "cane", "e", "la", "madonna");
                //out.add(p);
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }

    //filtra per nome,cognome,codiceProf e se attivo
    //da gestire nella servlet il controllo dell'input
    //tutto cio' che in input è null va gestito con *

    //modifica cappe, filtra tutti i docenti attivi per l user, che allo stronzo normale servono solo loro
    public static ArrayList<Docente> filterProf(){
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE WHERE ATTIVO= '1'");
                int i = 0;
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Docente d = new Docente(rs.getString("account"),rs.getString("password"), rs.getString("ruolo"), rs.getString("nomeCognome"),rs.getString("attivo"));
                    System.out.println(i + " " + d.getAccount() + d.getRuolo() + d.getNomeCognome() + d.getAttivo());
                    out.add(d);
                }
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }



    public static ArrayList<Corso> availableSubjects(){
        Connection conn1 = null;
        //String out = "";
        ArrayList<Corso> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM CORSO");
                int i = 0;
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Corso c = new Corso(rs.getString("Titolo"));
                    out.add(c);
                }
            }

        } catch (SQLException e){
            System.out.println("SONO NELLA CATCH SQL " + e.getMessage());
        }
        finally {
            try {
                if (conn1 != null)
                    conn1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return out;
    }
}