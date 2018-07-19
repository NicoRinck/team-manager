package nr.data_model.form_fields.position;

public enum Position {
    NO_POSITION("---"),
    TW("TW"),
    IV("IV"),
    LIB("LIB"),
    RV("RV"),
    LV("LV"),
    ZDM("ZDM"),
    ZM("ZM"),
    RM("RM"),
    LM("LM"),
    ZOM("ZOM"),
    RF("RF"),
    LF("LF"),
    MS("MS"),
    ST("ST");

    String text;
    Position(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
       return this.text;
    }
}
