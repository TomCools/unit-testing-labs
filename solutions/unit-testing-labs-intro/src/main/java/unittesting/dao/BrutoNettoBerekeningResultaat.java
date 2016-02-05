package unittesting.dao;

import unittesting.domein.ContractType;
import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;

public class BrutoNettoBerekeningResultaat {
    private Double brutoLoon;
    private ContractType contractType;
    private LoonContext loonContext;
    private LoonType loonType;

    private Double rszBijdrage;
    private Double belastbaarLoon;
    private Double bedrijfsvoorheffing;
    private Double nettoLoon;

    @java.beans.ConstructorProperties({"brutoLoon", "contractType", "loonContext", "loonType", "rszBijdrage", "belastbaarLoon", "bedrijfsvoorheffing", "nettoLoon"})
    BrutoNettoBerekeningResultaat(Double brutoLoon, ContractType contractType, LoonContext loonContext, LoonType loonType, Double rszBijdrage, Double belastbaarLoon, Double bedrijfsvoorheffing, Double nettoLoon) {
        this.brutoLoon = brutoLoon;
        this.contractType = contractType;
        this.loonContext = loonContext;
        this.loonType = loonType;
        this.rszBijdrage = rszBijdrage;
        this.belastbaarLoon = belastbaarLoon;
        this.bedrijfsvoorheffing = bedrijfsvoorheffing;
        this.nettoLoon = nettoLoon;
    }

    public Double getBrutoLoon() {
        return brutoLoon;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public LoonContext getLoonContext() {
        return loonContext;
    }

    public LoonType getLoonType() {
        return loonType;
    }

    public Double getRszBijdrage() {
        return rszBijdrage;
    }

    public Double getBelastbaarLoon() {
        return belastbaarLoon;
    }

    public Double getBedrijfsvoorheffing() {
        return bedrijfsvoorheffing;
    }

    public Double getNettoLoon() {
        return nettoLoon;
    }

    public static BrutoNettoBerekeningResultaatBuilder builder() {
        return new BrutoNettoBerekeningResultaatBuilder();
    }

    public static class BrutoNettoBerekeningResultaatBuilder {
        private Double brutoLoon;
        private ContractType contractType;
        private LoonContext loonContext;
        private LoonType loonType;
        private Double rszBijdrage;
        private Double belastbaarLoon;
        private Double bedrijfsvoorheffing;
        private Double nettoLoon;



        BrutoNettoBerekeningResultaatBuilder() {
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder brutoLoon(Double brutoLoon) {
            this.brutoLoon = brutoLoon;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder contractType(ContractType contractType) {
            this.contractType = contractType;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder loonContext(LoonContext loonContext) {
            this.loonContext = loonContext;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder loonType(LoonType loonType) {
            this.loonType = loonType;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder rszBijdrage(Double rszBijdrage) {
            this.rszBijdrage = rszBijdrage;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder belastbaarLoon(Double belastbaarLoon) {
            this.belastbaarLoon = belastbaarLoon;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder bedrijfsvoorheffing(Double bedrijfsvoorheffing) {
            this.bedrijfsvoorheffing = bedrijfsvoorheffing;
            return this;
        }

        public BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder nettoLoon(Double nettoLoon) {
            this.nettoLoon = nettoLoon;
            return this;
        }

        public BrutoNettoBerekeningResultaat build() {
            return new BrutoNettoBerekeningResultaat(brutoLoon, contractType, loonContext, loonType, rszBijdrage, belastbaarLoon, bedrijfsvoorheffing, nettoLoon);
        }

        public String toString() {
            return "unittesting.dao.BrutoNettoBerekeningResultaat.BrutoNettoBerekeningResultaatBuilder(brutoLoon=" + this.brutoLoon + ", contractType=" + this.contractType + ", loonContext=" + this.loonContext + ", loonType=" + this.loonType + ", rszBijdrage=" + this.rszBijdrage + ", belastbaarLoon=" + this.belastbaarLoon + ", bedrijfsvoorheffing=" + this.bedrijfsvoorheffing + ", nettoLoon=" + this.nettoLoon + ")";
        }
    }
}
