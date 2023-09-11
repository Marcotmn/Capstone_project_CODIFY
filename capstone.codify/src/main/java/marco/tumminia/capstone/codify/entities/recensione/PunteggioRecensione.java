package marco.tumminia.capstone.codify.entities.recensione;

public enum PunteggioRecensione {
    UNA_STELLA(1),
    DUE_STELLE(2),
    TRE_STELLE(3),
    QUATTRO_STELLE(4),
    CINQUE_STELLE(5);

    private final int valore;

    PunteggioRecensione(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }
}
