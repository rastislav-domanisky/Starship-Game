public class Player {

    private Obdlznik hitbox = new Obdlznik();

    private Obdlznik left = new Obdlznik();
    private Obdlznik middle = new Obdlznik();
    private Obdlznik right = new Obdlznik();

    private final int width = 60;
    private final int height = 80;

    private final int movementSpeed = 20;

    private int posX;
    private final int posY;

    private Game game;

    public Player(Game game) {
        this.hitbox.zmenFarbu("black");
        this.hitbox.zmenStrany(this.width, this.height);
        this.posX = game.getWidth() / 2 - this.width / 2;
        this.posY = (game.getHeight() - this.height) - 100;
        this.hitbox.posunVodorovne(this.posX);
        this.hitbox.posunZvisle(this.posY);
        this.hitbox.zobraz();

        this.game = game;

        this.initRects();
    }

    private void initRects() {
        int width = this.width / 3;
        int height = 40;
        this.left.zmenFarbu("red");
        this.middle.zmenFarbu("red");
        this.right.zmenFarbu("red");
        this.left.zmenStrany(width,height);
        this.middle.zmenStrany(width,height + 10);
        this.right.zmenStrany(width,height);
        this.left.posunVodorovne(posX);
        this.left.posunZvisle(posY + height);
        this.middle.posunVodorovne(posX + width);
        this.middle.posunZvisle(posY);
        this.right.posunVodorovne(posX + width*2);
        this.right.posunZvisle(posY + height);
        this.middle.zobraz();
        this.left.zobraz();
        this.right.zobraz();
    }

    public void kill() {
        this.hitbox.skry();
        this.left.skry();
        this.middle.skry();
        this.right.skry();
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT:
                if(this.posX <= 0) {
                    return;
                }
                this.posX -= this.movementSpeed;
                this.render(true);
                break;
            case RIGHT:
                if(this.posX >= game.getWidth() - this.width) {
                    return;
                }
                this.posX += this.movementSpeed;
                this.render(false);
                break;
        }
    }

    private void render(Boolean isLeft) {
        this.hitbox.skry();
        this.left.skry();
        this.middle.skry();
        this.right.skry();

        if(isLeft) {
            this.hitbox.posunVodorovne(-this.movementSpeed);
            this.left.posunVodorovne(-this.movementSpeed);
            this.middle.posunVodorovne(-this.movementSpeed);
            this.right.posunVodorovne(-this.movementSpeed);
        } else {
            this.hitbox.posunVodorovne(this.movementSpeed);
            this.left.posunVodorovne(this.movementSpeed);
            this.middle.posunVodorovne(this.movementSpeed);
            this.right.posunVodorovne(this.movementSpeed);
        }

        this.hitbox.zobraz();
        this.left.zobraz();
        this.middle.zobraz();
        this.right.zobraz();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return  this.posY;
    }
}
