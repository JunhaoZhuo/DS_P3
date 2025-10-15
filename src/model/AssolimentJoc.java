package model;

import java.time.LocalDateTime;

public class AssolimentJoc {
    private final EspecAssolimentJoc especAssolimentJoc;
    private final LocalDateTime dataHora;

    public AssolimentJoc(EspecAssolimentJoc especAssolimentJoc, LocalDateTime dataHora) {
        this.especAssolimentJoc = especAssolimentJoc;
        this.dataHora = dataHora;
    }

    public EspecAssolimentJoc getEspecAssolimentJoc() {
        return especAssolimentJoc;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
