package au.com.rainmore.domains;

public class Action {

    private final Player player;
    private final Dot dot1;
    private final Dot dot2;

    public Action(Player player, Dot dot1, Dot dot2) {
        this.player = player;
        this.dot1 = dot1;
        this.dot2 = dot2;
    }

    public Player getPlayer() {
        return player;
    }

    public Dot getDot1() {
        return dot1;
    }

    public Dot getDot2() {
        return dot2;
    }

}
