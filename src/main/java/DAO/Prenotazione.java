package DAO;

public class Prenotazione {
    private String docente;
    private String corso;
    private String utente;
    private String stato;
    private String data;
    private String slot_ora;


    public Prenotazione(String docente, String corso, String utente,String stato,String data, String slot_ora) {
        this.docente = docente;
        this.corso = corso;
        this.utente = utente;
        this.stato=stato;
        this.data=data;             //Formato:dd/mm/yy
        this.slot_ora=slot_ora;
    }

    public String getDocente() {
        return docente;
    }

    public String getCorso() {
        return corso;
    }

    public String getUtente() {
        return utente;
    }

    public String getStato(){ return stato; }

    public String getSlot_ora(){ return slot_ora;}

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return docente + " " + corso  + " " + utente  + " " + stato + " " + data + " " + slot_ora ;
    }
}
