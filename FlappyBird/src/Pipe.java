import java.awt.*; // Mengimpor pustaka AWT untuk tipe data gambar Image

public class Pipe { // Deklarasi kelas publik Pipe untuk objek pipa game
    private int posX; // Atribut privat untuk menyimpan posisi horizontal pipa pada sumbu X
    private int posY; // Atribut privat untuk menyimpan posisi vertikal pipa pada sumbu Y
    private int width; // Atribut privat untuk menyimpan lebar dari pipa
    private int height; // Atribut privat untuk menyimpan tinggi dari pipa
    private Image image; // Atribut privat untuk menyimpan aset gambar visual pipa
    private int velocityX; // Atribut privat untuk menyimpan kecepatan gerak horizontal pipa
    boolean passed; // Atribut boolean untuk menandai apakah pipa sudah dilewati burung

    public Pipe(int posX, int posY, int width, int height, Image image) { // Konstruktor kelas Pipe untuk inisialisasi awal
        this.posX = posX; // Mengisi atribut posX dengan parameter yang dikirim
        this.posY = posY; // Mengisi atribut posY dengan parameter yang dikirim
        this.width = width; // Mengisi atribut width dengan parameter yang dikirim
        this.height = height; // Mengisi atribut height dengan parameter yang dikirim
        this.image = image; // Mengisi atribut image dengan parameter yang dikirim
        this.velocityX = 0; // Mengatur nilai awal kecepatan horizontal menjadi 0
        this.passed = false; // Mengatur status awal passed menjadi false (belum dilewati)
    }

    public int getPosX() { // Method getter untuk mengambil nilai posisi X pipa
        return posX; // Mengembalikan nilai variabel posX
    }

    public void setPosX(int posX) { // Method setter untuk mengubah nilai posisi X pipa
        this.posX = posX; // Memperbarui nilai posX dengan parameter baru
    }

    public int getPosY() { // Method getter untuk mengambil nilai posisi Y pipa
        return posY; // Mengembalikan nilai variabel posY
    }

    public void setPosY(int posY) { // Method setter untuk mengubah nilai posisi Y pip
        this.posY = posY; // Memperbarui nilai posY dengan parameter baru
    }

    public int getWidth() { // Method getter untuk mengambil nilai lebar pipa
        return width; // Mengembalikan nilai variabel width
    }

    public void setWidth(int width) { // Method setter untuk mengubah nilai lebar pipa
        this.width = width; // Memperbarui nilai width dengan parameter baru
    }

    public int getHeight() { // Method getter untuk mengambil nilai tinggi pipa
        return height; // Mengembalikan nilai variabel height
    }

    public void setHeight(int height) { // Method setter untuk mengubah nilai tinggi pipa
        this.height = height; // Memperbarui nilai height dengan parameter baru
    }

    public Image getImage() { // Method getter untuk mengambil objek gambar pipa
        return image; // Mengembalikan objek Image pipa
    }

    public void setImage(Image image) { // Method setter untuk mengubah objek gambar pipa
        this.image = image; // Memperbarui objek Image dengan parameter baru
    }

    public int getVelocityX() { // Method getter untuk mengambil nilai kecepatan horizontal
        return velocityX; // Mengembalikan nilai velocityX
    }

    public void setVelocityX(int velocityX) { // Method setter untuk mengubah nilai kecepatan horizontal
        this.velocityX = velocityX; // Memperbarui nilai velocityX dengan parameter baru
    }

    public boolean isPassed() { // Method getter untuk mengecek status passed pipa
        return passed; // Mengembalikan status boolean passed
    }

    public void setPassed(boolean passed) { // Method setter untuk mengubah status passed pipa
        this.passed = passed; // Memperbarui status passed dengan parameter baru
    }
}