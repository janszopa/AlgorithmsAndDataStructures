package pl.edu.pw.ee.aisd2023zlab6.lcs;

public class LongestCommonSubsequence {

    public String findLcs(String topText, String leftText) {
        String NWP;
        CellInfo[][] nwpMatrix = new CellInfo[leftText.length() + 1][topText.length() + 1];

        int currentValue = 0;
        Direction currentDirection = null;

        for (int i = 0; i < leftText.length() + 1; i++) {
            nwpMatrix[i][0] = new CellInfo(0, null);
        }

        for(int i = 0; i < topText.length() + 1; i++) {
            nwpMatrix[0][i] = new CellInfo(0, null);
        }

        for (int i = 1; i < leftText.length() + 1; i++) {
            for (int j = 1; j < topText.length() + 1; j++) {
                if(leftText.charAt(i - 1) == topText.charAt(j - 1)) {
                    currentValue = nwpMatrix[i - 1][j - 1].getValue() + 1;
                    currentDirection = Direction.DIAGONALLY;
                } else {
                    if(nwpMatrix[i][j - 1].getValue() == nwpMatrix[i - 1][j].getValue()) {
                        currentValue = nwpMatrix[i - 1][j].getValue();
                        currentDirection = Direction.UP;
                    } else if (nwpMatrix[i][j - 1].getValue() >= nwpMatrix[i - 1][j].getValue()) {
                        currentValue = nwpMatrix[i][j - 1].getValue();
                        currentDirection = Direction.LEFT;
                    } else {
                        currentValue = nwpMatrix[i - 1][j].getValue();
                        currentDirection = Direction.UP;
                    }
                }
                nwpMatrix[i][j] = new CellInfo(currentValue, currentDirection);
            }
        }

        for (int i = 0; i < leftText.length() + 1; i++) {
            for (int j = 0; j < topText.length() + 1; j++) {
                System.out.print(nwpMatrix[i][j].getValue() + " : " + nwpMatrix[i][j].getDirection() + "  ");
            }
            System.out.println("");
        }

        NWP = getNWP(nwpMatrix, topText, leftText);
        return NWP;
    }
    private String getNWP(CellInfo[][] nwpMatrix, String topText, String leftText) {
        int i = leftText.length();
        int j = topText.length();

        StringBuilder NWP = new StringBuilder();

        while (i != 0 && j != 0) {
            System.out.println(i + " " + j);
            if(leftText.charAt(i - 1) == topText.charAt(j - 1)) {
                NWP.append(leftText.charAt(i - 1));
            }

            if (nwpMatrix[i][j].getDirection() == Direction.UP) {
                i--;
            } else if (nwpMatrix[i][j].getDirection() == Direction.LEFT) {
                j--;
            } else {
                i--;
                j--;
            }
        }

        NWP.reverse();
        return NWP.toString();
    }

}
