package nr.data_model.validator;

import nr.data_model.form_fields.position.Position;

public class PlayerPositionValidator {

    public static boolean isUniquePosition(Position targetPosition, Position... otherPositions) {
        if (targetPosition == Position.NO_POSITION) {
            return true;
        }
        boolean isUnique = true;
        for (Position otherPosition : otherPositions) {
            if (otherPosition != Position.NO_POSITION) {
                isUnique &= (targetPosition != otherPosition);
            }
        }
        return isUnique;
    }

    public static boolean previousPositionDefined(Position... positions) {
        boolean isValid = true;
        for (int i = 0; i < positions.length; i++) {
            int currentIndex = positions.length - (i+1);
            if (currentIndex != 0 && positions[currentIndex] != Position.NO_POSITION) {
                isValid &= positions[currentIndex-1] != Position.NO_POSITION;
            }
        }
        return true;
    }
}
