package nr.data_model.form_fields.position;

public class PlayerPositions {

    private Position primaryPosition;
    private Position secondaryPosition = Position.NO_POSITION;
    private Position tertiaryPosition = Position.NO_POSITION;

    public PlayerPositions(Position primaryPosition) throws IllegalArgumentException {
        setPrimaryPosition(primaryPosition);
    }

    public PlayerPositions(Position primaryPosition, Position secondaryPosition) throws IllegalArgumentException{
        setPrimaryPosition(primaryPosition);
        setSecondaryPosition(secondaryPosition);
    }

    public PlayerPositions(Position primaryPosition, Position secondaryPosition, Position tertiaryPosition)throws IllegalArgumentException {
        setPrimaryPosition(primaryPosition);
        setSecondaryPosition(secondaryPosition);
        setTertiaryPosition(tertiaryPosition);
    }

    public void setPrimaryPosition(Position primaryPosition) throws IllegalArgumentException {
        if (primaryPosition != Position.NO_POSITION) {
            this.primaryPosition = primaryPosition;
        } else throw new IllegalArgumentException();
    }

    public void setSecondaryPosition(Position secondaryPosition) {
        if (primaryPosition != Position.NO_POSITION && secondaryPosition != primaryPosition) {
            this.secondaryPosition = secondaryPosition;
        }
    }

    public void setTertiaryPosition(Position tertiaryPosition) {
        if (secondaryPosition != Position.NO_POSITION && secondaryPosition != primaryPosition && secondaryPosition != tertiaryPosition) {
            this.tertiaryPosition = tertiaryPosition;
        }
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
