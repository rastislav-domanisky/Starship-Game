import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Obdlznik bg = new Obdlznik();

    private final int width = 400;
    private final int height = 600;

    private Random asteroidPosXGenerator = new Random();
    private Random asteroidSizeGenerator = new Random();

    private boolean playerIsMoving = false;
    private MoveDirection playerMoveDirection = MoveDirection.LEFT;

    private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

    private boolean gameStarted = false;

    private final int asteroidDensity = 18;
    private int nextAsteroid = 0;
    private final int maxAsteroids = 4;

    private Manazer manazer = new Manazer();

    private Player player;

    public Game() {
        this.bg.zmenFarbu("black");
        this.bg.zmenStrany(this.width, this.height);
        this.bg.zobraz();
        this.manazer.spravujObjekt(this);

        this.startGame();
    }

    public void startGame() {
        for (Asteroid current: this.asteroids) {
            current.kill();
        }
        this.asteroids.clear();
        this.player = new Player(this);
    }

    public void tik() {
        if(!this.gameStarted) {
            return;
        }

        if(this.nextAsteroid >= this.asteroidDensity) {
            if(this.asteroids.size() < this.maxAsteroids) {
                this.createAsteroid();
            }
        }
        this.nextAsteroid++;

        for(Asteroid current: this.asteroids) {
            current.move();
            if(current.getPosY() > this.height) {
                this.asteroids.remove(current);
            }
        }
        if(this.playerIsMoving) {
            this.player.move(this.playerMoveDirection);
        }
        this.collisionDetector();
    }

    public void aktivuj() {
        this.gameStarted = true;
    }

    public void zrus() {
        this.gameStarted = false;
    }

    private void collisionDetector() {
        for (Asteroid current: this.asteroids) {
            boolean collisionX = false;
            boolean collisionY = false;
            int radius = current.getSize() / 2;
            int centerX = current.getPosX() + radius;
            int centerY = current.getPosY() + radius;
            int dx = Math.abs(centerX - this.player.getPosX());
            int dy = Math.abs(centerY - this.player.getPosY());

            if (dx <= radius || (dx - this.player.getWidth() < radius && current.getPosX()>=this.player.getPosX())) {
                collisionX = true;
            }
            if (dy <= radius || (dy - this.player.getHeight() < radius && current.getPosY()>=this.player.getPosY())) {
                collisionY = true;
            }
            if(collisionX&&collisionY) {
                this.zrus();
                this.player.kill();
                this.startGame();
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void posunVlavo() {
        this.playerMoveDirection = MoveDirection.LEFT;
        this.playerIsMoving = true;
    }

    public void posunVpravo() {
        this.playerMoveDirection = MoveDirection.RIGHT;
        this.playerIsMoving = true;
    }

    public void posunVlavoKoniec() {
        this.playerIsMoving = false;
    }

    public void posunVpravoKoniec() {
        this.playerIsMoving = false;
    }

    private void createAsteroid() {
        int newSize = this.asteroidSizeGenerator.nextInt(70 - 20 + 1) + 20;
        int newPosX = this.asteroidPosXGenerator.nextInt(this.width + 70) - 70;
        this.asteroids.add(new Asteroid(newPosX, newSize));
        this.nextAsteroid = 0;
    }

    //Game starter
    //public static void main(String[] args) {
    //    new Game();
    //}

}
