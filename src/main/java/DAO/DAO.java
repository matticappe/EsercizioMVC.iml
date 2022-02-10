package DAO;

import java.io.PrintWriter;
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

    public static int cancellazionePrenotazione(String utente, String docente, String corso){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("UPDATE FROM PRENOTAZIONE SET STATO='0' WHERE UTENTE='"+utente+"' AND DOCENTE='"+docente+"' AND CORSO='"+corso+"'");
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
    public static int effettuaPrenotazione(String utente, String docente, String corso){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("UPDATE FROM PRENOTAZIONE SET STATO='1' AND  UTENTE='"+utente+"'  WHERE DOCENTE='"+docente+"' AND CORSO='"+corso+"'");
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


    public static int inserimentoDocente(String codiceFiscale, String nome, String cognome,int attivo){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("INSERT INTO DOCENTE(CODICEFISCALE, NOME, COGNOME, ATTIVO) VALUES('"+codiceFiscale+"', '"+ nome+"', '"+cognome+"', '"+attivo+"')");
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

    public static int inserimentoInsegna(String docente, String corso){
        Connection conn1 = null;
        int ris = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                ris = st.executeUpdate("INSERT INTO INSEGNA(CODDOCENTE, CORSO) VALUES('" + docente + "', '" + corso + "')");
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
                    System.out.println(out);
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
    public static String updateInsegnaDocente(String Titolo){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("UPDATE FROM INSEGNA SET CODDOCENTE='codDocente' WHERE Titolo = "  +Titolo);
                if(rs.next()){
                    out = out + "Aggiornamento fatto con successo";
                    System.out.println(out);
                } else {
                    out = out + "Aggiornamento fallito";
                    System.out.println(out);
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

    public static String updateInsegnaMateria(String codDocente){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("UPDATE FROM INSEGNA SET TITOLO='Titolo' WHERE CODDOCENTE = "  +codDocente);
                if(rs.next()){
                    out = out + "Aggiornamento fatto con successo";
                    System.out.println(out);
                } else {
                    out = out + "Aggiornamento fallito";
                    System.out.println(out);
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

    public static ArrayList<Prenotazione> viewAllPrenotations(){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE ");
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("docente"),rs.getString("utente"), rs.getString("corso"), rs.getString("stato"), rs.getString("data"), rs.getString("slot_ora"));
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
    public static ArrayList<Prenotazione> viewOwnPrenotations(String account){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE UTENTE ="  +account);
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("docente"),rs.getString("utente"), rs.getString("corso"), rs.getString("stato"), rs.getString("data"), rs.getString("slot_ora"));
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

    //prenotazioni attive
    public static ArrayList<Prenotazione> viewOwnActPrenotations(String account){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE UTENTE = '" + account + "'" + "AND STATO = '" + 1 + "'");
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("docente"),rs.getString("utente"), rs.getString("corso"), rs.getString("stato"), rs.getString("data"), rs.getString("slot_ora"));
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

    //filtra per fascia oraria, giorno, materia e professore
    //da gestire nella servlet il controllo dell'input
    //tutto cio' che in input è null va gestito con *
    public static ArrayList<Prenotazione> filterPrenotations(String slot_ora, String data, String materia, String professore, String stato){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE SLOT_ORA= '" +slot_ora+"'" +"AND DATA= '"+data+"'"+" AND CORSO= '" +materia+"'"+"AND PROFESSORE= '"+professore+"'"+"AND STATO= '"+stato+"'");
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("docente"),rs.getString("utente"), rs.getString("corso"), rs.getString("stato"), rs.getString("data"), rs.getString("slot_ora"));
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

    //filtra per nome,cognome,codiceProf e se attivo
    //da gestire nella servlet il controllo dell'input
    //tutto cio' che in input è null va gestito con *
    public static ArrayList<Docente> filterProf(String nome, String cognome, String codProf, int attivo){
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE WHERE NOME= '" +nome+"'" +"AND COGNOME= '"+cognome+"'"+" AND CODDOCENTE= '" +codProf+"'"+"AND ATTIVO= '"+attivo+"'");
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Docente p = new Docente(rs.getString("nome"),rs.getString("cognome"), rs.getString("codDocente"), rs.getInt("attivo"));
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

    public static ArrayList<Corso> availableSubjects(String titolo){
        Connection conn1 = null;
        ArrayList<Corso> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM CORSO WHERE TITOLO= '" +titolo+"'");
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Corso p = new Corso(rs.getString("Titolo"));
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
}

