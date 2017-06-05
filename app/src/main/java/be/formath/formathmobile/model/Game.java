package be.formath.formathmobile.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Game {
    private User user;
    private GregorianCalendar gameStartDateTime;
    private ArrayList<Operation> listOperation;
    private int remainingTimeInMillisecond;
    private GameType type;
    private int result;

    public int getResult() {
        return result;
    }

    public void generateResult() {
        if (listOperation.size() == 10) {
            int sum = 0;
            for (Operation oper : listOperation) {
                if (oper.getGivenResponse().equals(oper.getResponse())) {
                    sum++;
                }
            }
            result = sum;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GregorianCalendar getGameStartDateTime() {
        return gameStartDateTime;
    }

    public void setGameStartDateTime(GregorianCalendar gameStartDateTime) {
        this.gameStartDateTime = gameStartDateTime;
    }

    public ArrayList<Operation> getListOperation() {
        return listOperation;
    }

    public void setListOperation(ArrayList<Operation> listOperation) {
        this.listOperation = listOperation;
    }

    public int getRemainingTimeInMillisecond() {
        return remainingTimeInMillisecond;
    }

    public void setRemainingTimeInMillisecond(int remainingTimeInMillisecond) {
        this.remainingTimeInMillisecond = remainingTimeInMillisecond;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Game()  {
        listOperation = new ArrayList<Operation>();
    }
}
