package model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Adquisicio {
    private final Joc joc;
    private final LocalDate data;
    private final Preu preu;

    private Time tempsJugat; // derivat
    private List<SessioJoc> sessionsJoc;

    public Adquisicio(Joc joc, LocalDate data, Preu preu) {
        this.joc = joc;
        this.data = data;
        this.preu = preu;
        this.tempsJugat = new Time(0);
        this.sessionsJoc = new java.util.ArrayList<>();
    }

    public Joc getJoc() {
        return joc;
    }

    public LocalDate getData() {
        return data;
    }

    public void afegirSessioJoc(SessioJoc sessioJoc) {
        sessionsJoc.add(sessioJoc);
        tempsJugat = new Time(tempsJugat.getTime() + sessioJoc.getDurada().getTime());
    }

    public String getTitolJoc() {
        return joc.getTitol();
    }

    public static Adquisicio crearAdquisicio(
            Joc joc,
            LocalDate data,
            Double quantitatPreu,
            String monedaPreu
    ) throws Exception {
        Preu preu = new Preu(quantitatPreu, monedaPreu);
        return new Adquisicio(joc, data, preu);
    }

    public SessioJoc findSessioJocByDataInici(LocalDateTime dataIniciSessioJoc) {
        for (SessioJoc sessioJoc : sessionsJoc) {
            if (sessioJoc.getDataInici().equals(dataIniciSessioJoc)) {
                return sessioJoc;
            }
        }
        return null;
    }
}
