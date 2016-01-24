package unittesting.domein;

public class SchaalWaarde {
    private double amount;
    private double werknemer;
    private double bedrijfsleider;
    private double pensioen;

    public SchaalWaarde(double amount, double werknemer, double bedrijfsleider, double pensioen) {
        this.amount = amount;
        this.werknemer = werknemer;
        this.bedrijfsleider = bedrijfsleider;
        this.pensioen = pensioen;
    }

    public double getAmount() {
        return amount;
    }

    public double getWerknemer() {
        return werknemer;
    }

    public double getBedrijfsleider() {
        return bedrijfsleider;
    }

    public double getPensioen() {
        return pensioen;
    }
}
