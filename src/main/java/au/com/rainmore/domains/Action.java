package au.com.rainmore.domains;

public class Action {

    private final Player   player;
    private final Position position;

    public Action(Player player, Position dot) {
        this.player = player;
        this.position = dot;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getPosition() {
        return position;
    }

}
