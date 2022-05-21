package DAO;

public class Corso {
    private String titolo;
    private String attivo;

    public Corso(String titolo, String attivo) {

        this.titolo = titolo;
        this.attivo = attivo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAttivo() {
        return attivo;
    }

    @Override
    public String toString() {
        return titolo + " ";
    }
}
