package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessioJoc {

    private final LocalDateTime dataInici;
    private LocalDateTime dataFi;
    private EstatSessioJoc estatSessioJoc;

    private final List<AssolimentJoc> assolimentsJoc;

    public SessioJoc(LocalDateTime dataInici, LocalDateTime dataFi, EstatSessioJoc estatSessioJoc) {
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.estatSessioJoc = estatSessioJoc;
        this.assolimentsJoc = new ArrayList<>();
    }

    public LocalDateTime getDataInici() {
        return dataInici;
    }

    public void afegirAssolimentJoc(AssolimentJoc assolimentJoc) {
        assolimentsJoc.add(assolimentJoc);
    }

    public Time getDurada() {
        if (dataFi == null) {
            return new Time(0);
        }
        long diffInMillis = java.time.Duration.between(dataInici, dataFi).toMillis();
        return new Time(diffInMillis);
    }

    public static SessioJoc crearSessioJocActiva(String nomUsuari, Joc joc, LocalDateTime dataInici) {
        return new SessioJoc(dataInici, null, EstatSessioJoc.ACTIVA);
    }

    public static SessioJoc crearSessioJocFinalitzada(String nomUsuari, Joc joc, LocalDateTime dataInici, LocalDateTime dataFi) {
        return new SessioJoc(dataInici, dataFi, EstatSessioJoc.FINALITZADA);
    }
}
