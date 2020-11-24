public class Asteroid {

    private Kruh shape = new Kruh();

    private final int size;

    private int moveSpeed = 13;

    private int posX;
    private int posY;

    public Asteroid (int posX, int size) {
        this.shape.zmenFarbu("white");
        this.size = size;
        this.shape.zmenPriemer(this.size);

        this.posX = posX - this.size;
        this.posY = 0;

        this.shape.posunVodorovne(this.posX);
        this.shape.posunZvisle(this.posY);

        this.shape.zobraz();
    }

    public void move() {
        this.posY += this.moveSpeed;
        this.render();
    }

    public void kill() {
        this.shape.skry();
    }

    public int getPosY() {
        return this.posY;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getSize() {
        return this.size;
    }

    private void render() {
        this.shape.skry();
        this.shape.posunZvisle(this.moveSpeed);
        this.shape.zobraz();
    }
}
