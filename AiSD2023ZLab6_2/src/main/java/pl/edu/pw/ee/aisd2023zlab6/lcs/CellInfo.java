package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class CellInfo {
    private int value;
    private Direction direction;

    public CellInfo(int value, Direction direction){
        this.value = value;
        this.direction = direction;
    }

    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }
}
