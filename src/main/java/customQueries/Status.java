package customQueries;

import java.util.Random;

public enum Status {

    ON_TIME,
    DELAYED,
    CANCELLED;

    Status(){
    }

    private static final Random PRNG = new Random();

    public static Status randomStatus()  {
        Status[] statuses = values();
        return statuses[PRNG.nextInt(statuses.length)];
    }

}
