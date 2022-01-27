package au.com.rainmore.game.domains;

public class Score {

    private long score = 0;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void add(int step) {
        this.score += step;
    }

}
