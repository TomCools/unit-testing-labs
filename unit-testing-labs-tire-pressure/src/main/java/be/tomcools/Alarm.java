package be.tomcools;

public class Alarm {
    private final double lowPressureThreshold = 17;
    private final double highPressureThreshold = 21;

    private boolean alarmOn = false;

    public void check() {
        double psiPressureValue = new Sensor().popNextPressurePsiValue();

        if (psiPressureValue < lowPressureThreshold || highPressureThreshold < psiPressureValue) {
            alarmOn = true;
        } else {
            alarmOn = false;
        }
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
