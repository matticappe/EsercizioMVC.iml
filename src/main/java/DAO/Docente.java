package DAO;

public class Docente {
        private String nome;
        private String cognome;
        private String codDocente;
        private String attivo;


        public Docente(String nome, String cognome, String codDocente, String attivo) {
            this.nome = nome;
            this.cognome = cognome;
            this.codDocente = codDocente;
            this.attivo=attivo;
        }


        public String getNome() {
            return nome;
        }

        public String getCognome() {
            return cognome;
        }

        public String getCodDocente() { return codDocente;}

        public String getAttivo() {return  attivo;}

    @Override
        public String toString() {
            return nome + " " + cognome + " " + codDocente + " " + attivo;
        }

}
