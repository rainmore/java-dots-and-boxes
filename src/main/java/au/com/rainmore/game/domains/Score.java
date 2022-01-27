package au.com.rainmore.game.domains;

public class Score {

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void add(int step) {
        this.score += step;
    }

}
