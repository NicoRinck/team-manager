package nr.data_model.form_fields;

import nr.data_model.validator.DurationValidator;

public class AppointmentDuration {

    private final Integer minimalTime;
    private final Integer maximalTime;
    private final Integer exactTime;

    public AppointmentDuration(Integer minimalTime, Integer maximalTime) throws IllegalArgumentException {
        if (DurationValidator.validDurationBounds(minimalTime,maximalTime)) {
            this.minimalTime = minimalTime;
            this.maximalTime = maximalTime;
            exactTime = -1;
        } else throw new IllegalArgumentException();
    }

    public AppointmentDuration(Integer exactTime) throws IllegalArgumentException {
        if (DurationValidator.isNaturalNumber(exactTime)) {
            this.exactTime = exactTime;
            maximalTime = -1;
            minimalTime = -1;
        } else throw new IllegalArgumentException();
    }

    public Integer getMinimalTime() {
        return minimalTime;
    }

    public Integer getMaximalTime() {
        return maximalTime;
    }

    public Integer getExactTime() {
        return exactTime;
    }

    public String getDurationString() {
        if (exactTime != -1) {
            return exactTime + "min";
        }
        return minimalTime + "min - " + maximalTime + "min";
    }
}
