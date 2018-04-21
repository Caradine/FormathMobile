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
    private int currentOperationIndex;
    private Boolean answeredArray[] = {false, false, false, false, false, false, false, false, false, false};

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

    private void setAnswered(int index) {
        this.answeredArray[index] = true;
    }

    public void setAnswerToCurrentOperation(String answer) {
        this.listOperation.get(this.currentOperationIndex).setGivenResponse(answer);
        setAnswered(this.currentOperationIndex);
    }

    public boolean isAnswered(int index) {
        return this.answeredArray[index];
    }

    public void setCurrentOperationIndex(int index) {
        this.currentOperationIndex = index;
    }

    public void setCurrentOperation(Operation operation) {
        this.currentOperationIndex = this.listOperation.indexOf(operation);
    }

    public int getCurrentOperationIndex() {
        return this.currentOperationIndex;
    }

    public Operation getCurrentOperation() {
        return this.listOperation.get(this.currentOperationIndex);
    }

    public Operation getNextUnansweredOperation() {
        int saveCurrent = this.getCurrentOperationIndex();
        int cpt = 1;
        while (true) {
            if (this.currentOperationIndex + cpt == saveCurrent) {
                return this.getCurrentOperation();
            }
            if (this.currentOperationIndex + cpt == 10) {
                cpt = -this.currentOperationIndex;
            }
            if (!this.answeredArray[this.currentOperationIndex + cpt]) {
                return this.listOperation.get(this.currentOperationIndex + cpt);
            }
            cpt++;
        }
    }

    public Operation goToNextUnansweredOperation() {
        int operationIndex = this.currentOperationIndex;
        while (true) {
            operationIndex++;
            if (operationIndex == 10)
                operationIndex = 0;
            if (operationIndex == this.currentOperationIndex)
                return null;
            if (!this.answeredArray[operationIndex]) {
                this.currentOperationIndex = operationIndex;
                return this.listOperation.get(operationIndex);
            }
        }
    }

    public Game()  {
        listOperation = new ArrayList<Operation>();
        this.currentOperationIndex = 0;
    }
}
