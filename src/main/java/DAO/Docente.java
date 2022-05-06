package DAO;

public class Docente {
        private String account;
        private String nomeCognome;
        private String attivo;

    public Docente(String account, String nomeCognome, String attivo) {
        this.account = account;
        this.nomeCognome = nomeCognome;
        this.attivo = attivo;
    }

    public String getAccount() {
        return account;
    }


    public String getNomeCognome() {
        return nomeCognome;
    }

    public String getAttivo() {
        return attivo;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "account='" + account + '\'' +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", attivo='" + attivo + '\'' +
                '}';
    }
}
