package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Joc {
    private final String titol;
    private final List<String> generes;
    private final List<String> desenvolupadores;
    private final List<String> distribuidores;
    private final LocalDate dataLlancament;
    private final LocalDate dataAnunci;
    private final LocalDate dataRetirada;
    private final List<EspecAssolimentJoc> especsAssoliment;
    private final EstatJoc estatJoc;

    // Nou atribut per US12
    private final List<Comentari> comentaris;


    public Joc(
        String titol,
        List<String> generes,
        List<String> desenvolupadores,
        List<String> distribuidores,
        LocalDate dataLlancament,
        LocalDate dataAnunci,
        LocalDate dataRetirada,
        EstatJoc estatJoc
    ) {
        this.titol = titol;
        this.generes = generes;
        this.desenvolupadores = desenvolupadores;
        this.distribuidores = distribuidores;
        this.dataLlancament = dataLlancament;
        this.dataAnunci = dataAnunci;
        this.dataRetirada = dataRetirada;
        this.estatJoc = estatJoc;

        this.especsAssoliment = new ArrayList<>();
        // inicialitzem el nou atribut US12
        this.comentaris = new ArrayList<>();
    }

    // TODO: getters & setters

    public String getTitol() {
        return titol;
    }

    public List<String> getGeneres() {
        return generes;
    }

    public List<String> getDesenvolupadores() {
        return desenvolupadores;
    }

    public List<String> getDistribuidores() {
        return distribuidores;
    }

    public LocalDate getDataLlancament() {
        return dataLlancament;
    }

    public LocalDate getDataAnunci() {
        return dataAnunci;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public EstatJoc getEstat() {
        return estatJoc;
    }

    public List<EspecAssolimentJoc> getEspecAssoliments() {
        return especsAssoliment;
    }

    public void afegirEspecAssolimentJoc(EspecAssolimentJoc especAssoliment) {
        especsAssoliment.add(especAssoliment);
    }

    // el Joc és l'expert en buscar dins la seva llista
    public EspecAssolimentJoc findEspecAssolimentByTitol(String titolAssoliment) {
        for (EspecAssolimentJoc ea : especsAssoliment) {
            if (ea.getTitol().equals(titolAssoliment)) {
                return ea;
            }
        }
        return null;
    }

    public static Joc crearJocAnunciat(
            String titol,
            List<String> genere,
            List<String> desenvolupadora,
            List<String> distribuidora,
            LocalDate dataAnunci
    ) {

        return new Joc(
                titol,
                genere,
                desenvolupadora,
                distribuidora,
                null,
                dataAnunci,
                null,
                EstatJoc.ANUNCIAT
        );
    }

    public static Joc crearJocDisponible(
            String titol,
            List<String> genere,
            List<String> desenvolupadora,
            List<String> distribuidora,
            LocalDate dataLlancament,
            LocalDate dataAnunci
    ) {
        return new Joc(
                titol,
                genere,
                desenvolupadora,
                distribuidora,
                dataLlancament,
                dataAnunci,
                null,
                EstatJoc.DISPONIBLE
        );
    }

    public static Joc crearJocRetirat(
            String titol,
            List<String> genere,
            List<String> desenvolupadora,
            List<String> distribuidora,
            LocalDate dataLlancament,
            LocalDate dataAnunci,
            LocalDate dataRetirada
    ) {
        return new Joc(
                titol,
                genere,
                desenvolupadora,
                distribuidora,
                dataLlancament,
                dataAnunci,
                dataRetirada,
                EstatJoc.RETIRAT
        );
    }

}
