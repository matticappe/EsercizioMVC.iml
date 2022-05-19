package DAO;

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

    public static String inserimentoCorso(String corso){
        Connection conn1 = null;
        String out = "L'inserimento è fallito";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                int ris = st.executeUpdate("INSERT INTO CORSO(TITOLO) VALUES('"+corso+"')");
                if(ris != 0){
                    out = "";
                    out = out + "Corso creato con successo";
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

    public static String cancellazionePrenotazione(String codice){
        Connection conn1 = null;
        String out = "Eliminazione prenotazione fallita";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                int rs = st.executeUpdate("DELETE FROM PRENOTAZIONE WHERE CODICE = '" + codice +"'");
                if(rs != 0){
                    out = "";
                    out = "Eliminazione della prenotazione con il codice" + codice + " effettuata con successo";
                }
                System.out.println(out);
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

    //TODO:DA FIXARE
    public static String inserisciPrenotazione(String codice, String docente, String corso, String data, String slot_ora){
        Connection conn1 = null;
        String out = "L'inserimento della prenotazione è fallita";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                int ris = st.executeUpdate("INSERT INTO PRENOTAZIONE(CODICE,UTENTE,DOCENTE,CORSO,DATA,SLOT_ORA) VALUES ('"+codice+"',NULL,'"+docente+"','"+corso+"','"+data+"','"+slot_ora+"')");
                if(ris != 0){
                    out = "";
                    out = out + "La prenotazione con codice " + codice + " creata con successo";
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

    public static String inserimentoDocente(String account,String nomeCognome,String attivo){
        Connection conn1 = null;
        String out = "L'inserimento del docente è fallito";
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                int ris = st.executeUpdate("INSERT INTO DOCENTE(ACCOUNT, NOMECOGNOME, ATTIVO) VALUES('"+account+"', '"+nomeCognome+"','"+attivo+"')");
                if(ris != 0){
                    out = "Docente aggiunto con successo";
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
        System.out.println(out);
        return out;
    }

    public static ArrayList<Utente> ViewAllUsers() {
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                System.out.println("connessione riuscita");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE RUOLO = 0");

                while (rs.next()) {
                    Utente p = new Utente(rs.getString("account"),rs.getString("password"), rs.getInt("ruolo"));
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
                ResultSet rs = st.executeQuery("SELECT * FROM UTENTE WHERE ACCOUNT = '"  +account+"' AND RUOLO = 1");
                if(rs.next()){
                    if(rs.getString("ruolo") == "1" && rs.getString("account") == account);
                    res=true;
                }
            }
            System.out.println("admin = "+res);
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


    public static String rimuoviInsegna(String codDocente, String corso){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                int rs = st.executeUpdate("DELETE FROM INSEGNA WHERE CORSO = '" + corso +"' AND codDocente = '" + codDocente + "'");
                if(rs != 0){
                    out = "";
                    out = out + "Eliminazione di insegna avvenuta con successo";
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

    public static String addInsegna(String codDocente, String corso){
        Connection conn1 = null;
        String out = "";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                //ResultSet rs = st.executeQuery("UPDATE FROM INSEGNA SET TITOLO='Titolo' WHERE CODDOCENTE = "  + codDocente);
                ResultSet rs = st.executeQuery("UPDATE INSEGNA SET CORSO = '" + corso + "'" + "WHERE CODDOCENTE = '" + codDocente + "'");
                if(rs.next()){
                    out = out + "Aggiornamento del docente" + codDocente + " con la materia " + corso + " fatta con successo";
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
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE UTENTE IS NULL");
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

    //PRENOTAZIONI PRENOTABILI E PRENOTATE MA NON ANCORA FATTE (QUINDI CANCELLABILI), CONTROLLO SU PROF ATTIVI
    public static ArrayList<Prenotazione> viewOwnPrenotations(String utente){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        System.out.println("debugvireOwnPrenot");
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE join DOCENTE on(PRENOTAZIONE.docente = DOCENTE.account) WHERE ATTIVO = '1' AND (UTENTE IS NULL OR "+ utente+")");
                int i = 0;
                while (rs.next()) {
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
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

    //prenotazioni attive (NO CONTROLLI SU PROF ATTIVI)
    public static ArrayList<Prenotazione> viewOwnActPrenotations(String account){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE WHERE " + account);
                int i = 0;
                while (rs.next()) {
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
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
    public static ArrayList<Prenotazione> filterPrenotations(String slot_ora, String data, String corso, String docente, String utente){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE join DOCENTE on(PRENOTAZIONE.docente = DOCENTE.account) WHERE (UTENTE IS NULL OR UTENTE = '"+utente+"') AND ATTIVO = '1' "+data+" "+corso+" "+slot_ora+" "+docente+" " );   //CONTROLLA LE VIRGOLETTE DOVE CI SONO TUTTI I +...+...+...+
                int i = 0;
                while (rs.next()) {
                    //forse va rivista con una document.out per scrivere nel div corretto
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
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

    //filtra per nome, cognome, codiceProf e se attivo
    //da gestire nella servlet il controllo dell'ingresso
    //tutto cio' che in ingresso è null va gestito con *

    public static ArrayList<Prenotazione> filterAdminPrenotations(String slot_ora, String data, String corso, String docente, String utente){
        Connection conn1 = null;
        ArrayList<Prenotazione> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM PRENOTAZIONE join DOCENTE on(PRENOTAZIONE.docente = DOCENTE.account) WHERE (UTENTE IS NULL OR UTENTE IS NOT NULL)"+data+""+corso+""+slot_ora+""+docente+""+utente+"" );   //CONTROLLA LE VIRGOLETTE DOVE CI SONO TUTTI I +...+...+...+
                int i = 0;
                while (rs.next()) {
                    Prenotazione p = new Prenotazione(rs.getString("codice"),rs.getString("utente"), rs.getString("docente"), rs.getString("corso"), rs.getString("data"), rs.getString("slot_ora"));
                    System.out.println(i + " " + p.getCodice() + p.getUtente() + p.getDocente() + p.getCorso() + p.getData() + p.getSlot_ora());
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
                    Docente d = new Docente(rs.getString("account"), rs.getString("nomeCognome"),rs.getString("attivo"));
                    System.out.println(i + " " + d.getAccount() + d.getNomeCognome() + d.getAttivo());
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
        ArrayList<Corso> out = new ArrayList<>();
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM CORSO");
                int i = 0;
                while (rs.next()) {
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

    //viewAllProf
    public static ArrayList<Docente> viewAllProf() {
        System.out.println("Sono in viewAllProf");
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                System.out.println("Connessione riuscita");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM DOCENTE WHERE attivo = '1'");
                int i = 0;
                Docente d;
                while (rs.next()){
                    d = new Docente(rs.getString("account"),rs.getString("nomeCognome"),rs.getString("attivo"));
                    System.out.println(i + " " + d.getAccount()  + d.getNomeCognome() + d.getAttivo());
                    out.add(d);
                }
            }
        } catch (SQLException e) {
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return out;
    }

    //eliminaDocente
    public static String eliminaDocente(String account){
        Connection conn1 = null;
        String out = "Eliminazione docente fallita";
        try {
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                int rs = st.executeUpdate("UPDATE DOCENTE SET attivo = '0' WHERE ACCOUNT = '" + account +"'");
                if(rs != 0){
                    out = "";
                    out = out + "Eliminazione del docente " + account + " avvenuta con successo";
                }
                System.out.println(out);
            }
        }catch (SQLException e){
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return out;
    }

    //eliminaCorso
    public static String eliminaCorso(String titolo){
        Connection conn1 = null;
        String out = "Eliminazione corso fallita";
        try {
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                int rs = st.executeUpdate("DELETE FROM CORSO WHERE TITOLO = '" + titolo +"'");
                if(rs != 0){
                    out = "";
                    out = out + "Eliminazione corso " + titolo + " avvenuta con successo";
                }
                System.out.println(out);
            }
        }catch (SQLException e){
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return out;
    }

    //disdici
    public static String disdiciPrenotazione(String codice){
        Connection conn1 = null;
        String out = "La prenotazione non è stata disdetta";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();

                int rs = st.executeUpdate("UPDATE PRENOTAZIONE SET UTENTE = NULL WHERE CODICE =" + codice);
                if(rs != 0){
                    out = "";
                    out = out + "La prenotazione e stata disdetta con successo";
                }
                System.out.println(out);
            }
        }catch (SQLException e){
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return out;
    }

    public static String effettuaPrenotazione(String codice, String utente){
        Connection conn1 = null;
        String out = "La prenotazione non e stata inserita";
        try{
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                Statement st = conn1.createStatement();
                int rs = st.executeUpdate("UPDATE PRENOTAZIONE SET UTENTE = '" + utente +"'" + "WHERE CODICE ='" + codice + "'");
                if(rs != 0){
                    out = "";
                    out = out + "La prenotazione e stata fatta con successo";
                }
                System.out.println(out);
            }
        }catch (SQLException e){
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return out;
    }

    public static String inserisciInsegna(String codDocente, String corso){
        Connection conn1 = null;
        String out = "L'inserimento è fallitoooo"+codDocente + corso;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
                Statement st = conn1.createStatement();
                int ris = st.executeUpdate("INSERT INTO INSEGNA(CODDOCENTE, CORSO) VALUES('"+codDocente+"', '" + corso + "')");
                if(ris != 0){
                    out = "L inserimento di insegna e avvenuto con successo"+codDocente + corso;
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


    /*
     * Da questa funzione vedo tutti gli insegnamenti
     * */
    public static ArrayList<Insegna> viewInsegna() {
        Connection conn1 = null;
        ArrayList<Insegna> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1,user,password);
            if(conn1 != null){
                System.out.println("Connessione riuscita");
                Statement st = conn1.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM INSEGNA");
                int i = 0;
                Insegna d;
                while (rs.next()){
                    d = new Insegna(rs.getString("codDocente"),rs.getString("corso"));
                    out.add(d);
                }
            }
        } catch (SQLException e) {
            System.out.println("Sono nella catch sql " + e.getMessage());
        }
        finally {
            try {
                if(conn1 != null)
                    conn1.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return out;
    }
}