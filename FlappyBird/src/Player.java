import java.awt.*; // Mengimpor pustaka AWT untuk tipe data gambar (Image)

public class Player {
    // Deklarasi atribut privat
    private int posX; // Posisi horizontal burung pada sumbu X
    private int posY; // Posisi vertikal burung pada sumbu Y
    private int width; // Lebar dari gambar burung
    private int height; // Tinggi dari gambar burung
    private Image image; // Aset gambar visual burung
    private int velocityY = -0; // Kecepatan gerak vertikal (positif = turun ke bawah, negatif = naik ke atas)

    // Konstruktor untuk menginisialisasi nilai awal saat objek Player pertama kali dibuat
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = -0;
    }


    // Getter untuk mengambil nilai posisi X
    public int getPosX() {
        return posX;
    }

    //Setter untuk mengubah nilai posisi X
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter untuk mengambil nilai posisi Y
    public int getPosY() {
        return posY;
    }

    //Setter untuk mengubah nilai posisi Y
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter untuk mengambil nilai lebar player
    public int getWidth() {
        return width;
    }

    //Setter untuk mengubah nilai lebar player
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter untuk mengambil nilai tinggi player
    public int getHeight() {
        return height;
    }
    //Setter untuk mengubah nilai tinggi player
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter untuk mengambil objek gambar (Image) player
    public Image getImage() {
        return image;
    }
    //Setter untuk mengubah objek gambar (Image) player
    public void setImage(Image image) {
        this.image = image;
    }

    // Getter untuk mengambil nilai kecepatan vertikal (velocityY)
    public int getVelocityY() {
        return velocityY;
    }
    //Setter untuk mengubah nilai kecepatan vertikal (velocityY)
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}