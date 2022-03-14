package DAO;


import java.util.PrimitiveIterator;

public class Prenotazione {
    private String codice;
    private String utente;
    private String docente;
    private String corso;
    private String data;
    private String slot_ora;
    private String stato;

    public Prenotazione(String codice, String utente, String docente, String corso, String data, String slot_ora, String stato) {
        this.codice = codice;
        this.utente = utente;
        this.docente = docente;
        this.corso = corso;
        this.data = data;
        this.slot_ora = slot_ora;
        this.stato = stato;
    }

    public String getCodice() {
        return codice;
    }

    public String getUtente() {
        return utente;
    }

    public String getDocente() {
        return docente;
    }

    public String getCorso() {
        return corso;
    }

    public String getData() {
        return data;
    }

    public String getSlot_ora() {
        return slot_ora;
    }

    public String getStato() {
        return stato;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public void setSlot_ora(String slot_ora) {
        this.slot_ora = slot_ora;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }


    public String toString(){
        return "";
    }
}
