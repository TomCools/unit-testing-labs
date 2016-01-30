package custommatcher.domain;

import lombok.Value;

@Value
public class Adres {
    private String street;
    private int number;
    private String bus;
    private String city;
    private String postalCode;

    @java.beans.ConstructorProperties({"street", "number", "bus", "city", "postalCode"})
    Adres(String street, int number, String bus, String city, String postalCode) {
        this.street = street;
        this.number = number;
        this.bus = bus;
        this.city = city;
        this.postalCode = postalCode;
    }

    public static AdresBuilder builder() {
        return new AdresBuilder();
    }

    public static class AdresBuilder {
        private String street;
        private int number;
        private String bus;
        private String city;
        private String postalCode;

        AdresBuilder() {
        }

        public Adres.AdresBuilder street(String street) {
            this.street = street;
            return this;
        }

        public Adres.AdresBuilder number(int number) {
            this.number = number;
            return this;
        }

        public Adres.AdresBuilder bus(String bus) {
            this.bus = bus;
            return this;
        }

        public Adres.AdresBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Adres.AdresBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Adres build() {
            return new Adres(street, number, bus, city, postalCode);
        }

        public String toString() {
            return "custommatcher.domain.Adres.AdresBuilder(street=" + this.street + ", number=" + this.number + ", bus=" + this.bus + ", city=" + this.city + ", postalCode=" + this.postalCode + ")";
        }
    }
}
