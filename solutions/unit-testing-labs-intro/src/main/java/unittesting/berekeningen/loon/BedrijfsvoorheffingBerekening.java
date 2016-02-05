package unittesting.berekeningen.loon;

import unittesting.domein.LoonContext;
import unittesting.domein.LoonType;
import unittesting.domein.SchaalWaarde;

import java.util.List;

public class BedrijfsvoorheffingBerekening {
    private BedrijfsvoorheffingSchaalService schaalService = new BedrijfsvoorheffingSchaalService();

    public Double berekenBedrijfsvoorheffing(Double belastbaarLoon, LoonType loonType, LoonContext context) {
        int schaal = bepaalBedrijfsvoorheffingSchaal(context);

        List<SchaalWaarde> schaalWaarden = schaalService.haalSchaalwaardenOp(schaal);

        SchaalWaarde vorigeSchaal = schaalWaarden.get(0);

        for (SchaalWaarde schaalWaarde : schaalWaarden) {
            if (schaalWaarde.getAmount() > belastbaarLoon) {
                return bepaalWaardeInSchaal(vorigeSchaal, loonType);
            } else {
                vorigeSchaal = schaalWaarde;
            }
        }

        throw new IllegalArgumentException("Bedragen boven de aangegeven schalen worden nog niet ondersteund.");
    }

    private Double bepaalWaardeInSchaal(SchaalWaarde waarde, LoonType loonType) {
        switch (loonType) {
            case WERKNEMER:
                return waarde.getWerknemer();
            case BEDRIJFSLEIDER:
                return waarde.getBedrijfsleider();
            case GEPENSIONEERDE:
                return waarde.getPensioen();
            default:
                throw new IllegalArgumentException("Loontype niet ondersteund.");
        }
    }

    private int bepaalBedrijfsvoorheffingSchaal(LoonContext loonContext) {
        switch (loonContext) {
            case ALLEENSTAANDE:
                return 1;
            case EEN_VERDIENER_GEZIN:
                return 2;
            case TWEE_VERDIENER_GEZIN:
                return 1;
            case NIET_INWONERS:
                return 3;
            default:
                throw new IllegalArgumentException("De gegeven LoonContext kon niet worden afgehandeld.");
        }
    }
}
