package game.collectibles;

public abstract class Power extends Reward{
    private boolean status;

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
