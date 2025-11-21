package vista;

import model.Joc;
import java.util.List;
import java.util.stream.Collectors;
import model.Adquisicio;
import model.Valoracio;
import model.Comentari;

import java.time.format.DateTimeFormatter;

public class PreparadorVista {

    /**
     * Formata la llista de jocs del catàleg (US4)
     */
    public static String prepararLlistaJocsCataleg(List<Joc> jocs) {
        List<String> titols = jocs.stream()
                .map(Joc::getTitol)
                .collect(Collectors.toList());
        return "Llista de jocs del catàleg:\n" + String.join("\n", titols);
    }
    /**
     * Prepara el text per a la llista de jocs adquirits (US5)
     */
    public static String prepararLlistaAdquisicions(List<Adquisicio> adquisicions) {
        List<String> titols = adquisicions.stream()
                .map(Adquisicio::getTitolJoc)
                .collect(Collectors.toList());
        return "Llista de jocs adquirits per l'usuari:\n" + String.join("\n", titols);
    }


    /**
     * Prepara el text per als detalls d'un joc (US6)
     */
    public static String prepararDetallsJoc(Joc joc) {
        // TOTA aquesta lògica estava abans al Controlador
        String titolFormatat = String.format("\"%s\"", joc.getTitol());
        String generesFormatat = String.join(", ", joc.getGeneres());
        String desenvolupadoresFormatat = String.join(", ", joc.getDesenvolupadores());
        String distribuidoresFormatat = String.join(", ", joc.getDistribuidores());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataAnunci = joc.getDataAnunci() != null ? joc.getDataAnunci().format(pattern) : "N/A";
        String dataLlancament = joc.getDataLlancament() != null ? joc.getDataLlancament().format(pattern) : "N/A";
        String dataRetirada = joc.getDataRetirada() != null ? joc.getDataRetirada().format(pattern) : "N/A";

        StringBuilder details = new StringBuilder();
        details.append("Títol: ").append(titolFormatat).append("\n")
                .append("Gènere(s): ").append(generesFormatat).append("\n")
                .append("Desenvolupadora(es): ").append(desenvolupadoresFormatat).append("\n")
                .append("Distribuïdora(es): ").append(distribuidoresFormatat).append("\n")
                .append("Data d'anunci: ").append(dataAnunci).append("\n")
                .append("Data de llançament: ").append(dataLlancament).append("\n")
                .append("Data de retirada: ").append(dataRetirada).append("\n")
                .append("Estat: ").append(joc.getEstat());

        // Actualitzem la visualització dels detalls del joc (US6) perquè inclogui els comentaris (US12).
        List<Comentari> comentaris = joc.getComentaris();
        if (!comentaris.isEmpty()) {
            details.append("\n\nComentaris:");
            for (Comentari c : comentaris) {
                details.append("\n- ").append(c.getResum());
            }
        }
        return details.toString();
    }

    /**
     * Prepara el text per a una llista de recomanacions (US10)
     */
    public static String prepararRecomanacions(List<Joc> jocs) {
        List<String> titols = jocs.stream()
                .map(Joc::getTitol)
                .collect(Collectors.toList());
        return "Jocs recomanats per a tu:\n" + String.join("\n", titols);
    }

    /**
     * mètode per llistar valoracions. Gràcies al polimorfisme, serà molt net.
     */
    public static String prepararLlistaValoracions(List<Adquisicio> adquisicions) {
        StringBuilder sb = new StringBuilder("Valoracions de l'usuari:\n");

        for (Adquisicio adq : adquisicions) {
            if (adq.teValoracio()) {
                Valoracio val = adq.getValoracio();
                // Polimorfisme en acció: getTipus() i getResum() funcionen diferent segons la classe
                sb.append("- ")
                        .append(adq.getJoc().getTitol())
                        .append(": [")
                        .append(val.getTipus())
                        .append("] ")
                        .append(val.getResum())
                        .append("\n");
            }
        }
        return sb.toString().trim(); // trim per treure l'últim salt de línia
    }

}