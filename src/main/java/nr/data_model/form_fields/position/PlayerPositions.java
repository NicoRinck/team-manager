package nr.data_model.form_fields.position;

import nr.data_model.validator.PlayerPositionValidator;

public class PlayerPositions {

    private Position primaryPosition;
    private Position secondaryPosition = Position.NO_POSITION;
    private Position tertiaryPosition = Position.NO_POSITION;

    public PlayerPositions(Position primaryPosition) throws IllegalArgumentException {
        setPrimaryPosition(primaryPosition);
    }

    public void setPrimaryPosition(Position primaryPosition) throws IllegalArgumentException {
        if (primaryPosition != Position.NO_POSITION &&
                PlayerPositionValidator.isUniquePosition(primaryPosition,secondaryPosition,tertiaryPosition)) {
            this.primaryPosition = primaryPosition;
        } else throw new IllegalArgumentException();
    }

    public void setSecondaryPosition(Position secondaryPosition) throws IllegalArgumentException {
        if (primaryPosition != Position.NO_POSITION &&
                PlayerPositionValidator.isUniquePosition(secondaryPosition,primaryPosition,tertiaryPosition)) {
            this.secondaryPosition = secondaryPosition;
        } else throw new IllegalArgumentException();
    }

    public void setTertiaryPosition(Position tertiaryPosition) throws IllegalArgumentException {
        if (primaryPosition != Position.NO_POSITION && secondaryPosition != Position.NO_POSITION &&
                PlayerPositionValidator.isUniquePosition(tertiaryPosition,primaryPosition,secondaryPosition)
                ) {
            this.tertiaryPosition = tertiaryPosition;
        } else throw new IllegalArgumentException();
    }

    public Position getPrimaryPosition() {
        return primaryPosition;
    }

    public Position getSecondaryPosition() {
        return secondaryPosition;
    }

    public Position getTertiaryPosition() {
        return tertiaryPosition;
    }

}
