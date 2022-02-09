package DAO;

public class Docente {
        private String nome;
        private String cognome;
        private String codDocente;
        private int attivo;


        public Docente(String nome, String cognome, String codDocente, int attivo) {
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

        public int getAttivo() {return  attivo;}

    @Override
        public String toString() {
            return nome + " " + cognome + " " + codDocente + " " + attivo;
        }

}
