package au.com.rainmore.domains;

public class Action {

    private final Player player;
    private final Point  point;

    public Action(Player player, Point dot) {
        this.player = player;
        this.point = dot;
    }

    public Player getPlayer() {
        return player;
    }

    public Point getPoint() {
        return point;
    }

}
