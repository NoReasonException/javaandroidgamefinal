package main.game.core.etc;

/**
 * It is used to pass the difficulty level from Activity to activity (using the ID)
 */
public enum DifficultyLevel {
    EASY(0),NORMAL(1),HARD(2);
    private int id;

    DifficultyLevel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public static DifficultyLevel getDifficultyLevelFromID(int id){
        switch (id){
            case 0:
                return EASY;
            case 1:
                return NORMAL;
            case 2:
                return HARD;
        }
        return NORMAL;//this is stupid, but the compiler cant recognise that this condition is never met
    }
}
