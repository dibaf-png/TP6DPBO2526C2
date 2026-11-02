import javax.swing.*; // Mengimpor pustaka Swing untuk menggunakan Timer dan komponen GUI
import java.awt.*;    // Mengimpor pustaka AWT untuk mengelola objek gambar (Image) dan grafik
import java.awt.event.ActionEvent;  // Mengimpor event untuk mendeteksi aksi dari Timer
import java.awt.event.ActionListener; // Mengimpor interface pendengar aksi Timer
import java.awt.event.KeyEvent; // Mengimpor event untuk mendeteksi tombol keyboard
import java.awt.event.KeyListener; // Mengimpor interface pendengar input keyboard
import java.util.ArrayList; // Mengimpor ArrayList untuk menampung daftar objek pipa

public class Logic implements ActionListener, KeyListener { // Kelas Logic mengimplementasikan ActionListener dan KeyListener

    int frameWidth = 360;  // Mendefinisikan variabel untuk lebar jendela game (360 piksel)
    int frameHeight = 640; // Mendefinisikan variabel untuk tinggi jendela game (640 piksel)

    int playerStartPosX = frameWidth / 2; // Menghitung posisi awal horizontal burung agar berada tepat di tengah layar
    int playerStartPosY = frameHeight / 2; // Menghitung posisi awal vertikal burung agar berada tepat di tengah layar
    int playerWidth = 34;  // Lebar gambar burung
    int playerHeight = 24; // Tinggi gambar burung

    int pipeStartPosX = frameWidth; // Pipa mulai muncul dari sisi paling kanan layar
    int pipeStartPosY = 0; // Posisi awal Y pipa atas
    int pipeWidth = 64;    // Lebar standar pipa
    int pipeHeight = 512;  // Tinggi standar pipa

    Timer gameLoop;     // Timer utama untuk menggerakkan game (60 FPS)
    Timer pipesCooldown; // Timer khusus untuk jeda kemunculan pipa baru (setiap 1.5 detik)
    int gravity = 1;    // Besarnya efek gravitasi yang menarik burung ke bawah
    int pipeVelocityX = -2; // Kecepatan gerak pipa ke arah kiri (nilai negatif)

    View view; // Referensi ke kelas View (tampilan antarmuka)
    Image birdImage; // Aset gambar burung
    Player player; // Objek player (burung)

    Image lowerPipeImage; // Atribut untuk gambar pipa atas
    Image upperPipeImage; // Atribut untuk gambar pipa bawah
    ArrayList<Pipe> pipes; // Atribut untuk list penampung pipa

    public int score = 0; // Variabel untuk menyimpan jumlah skor saat ini (dimulai dari 0)
    public boolean gameOver = false; // Status untuk mengecek apakah game sudah berakhir (true/false)

    public Logic() { // Konstruktor yang dijalankan pertama kali saat objek Logic dibuat
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage(); // Memuat file gambar burung dari folder assets
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage(); // Memuat file gambar pipa bawah
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage(); // Memuat file gambar pipa atas

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage); // Membuat objek Player baru
        pipes = new ArrayList<Pipe>(); // Menginisialisasi ArrayList untuk menampung pipa-pipa

        // Membuat Timer cooldown untuk memunculkan pipa baru setiap 1500 ms (1.5 detik) tanpa langsung menyalakannya
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes(); // Memanggil method untuk membuat pipa baru
            }
        });

        gameLoop = new Timer(1000 / 60, this); // Membuat objek Timer game loop (~60 FPS) tanpa langsung menyalakannya
    }

    public void startGame() { // Method khusus untuk memulai game saat tombol Start diklik
        restartGame(); // Mereset posisi player, skor, dan membersihkan pipa lama
        pipesCooldown.start(); // Menyalakan timer pemunculan pipa
        gameLoop.start(); // Menyalakan timer utama game loop
    }

    public void setView(View view) { this.view = view; } // Method setter untuk menghubungkan Logic dengan View
    public Player getPlayer() { return player; } // Method getter untuk mengambil data player
    public ArrayList<Pipe> getPipes() { return pipes; } // Method getter untuk mengambil daftar pipa

    public void placePipes() { // Method untuk membuat pipa baru dengan posisi tinggi acak
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2)); // Menghitung posisi Y acak untuk pipa atas
        int openingSpace = frameHeight / 4; // Ukuran celah kosong untuk dilewati burung

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage); // Membuat pipa atas
        pipes.add(upperPipe); // Memasukkan pipa atas ke list (indeks genap: 0, 2, 4...)

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage); // Membuat pipa bawah
        pipes.add(lowerPipe); // Memasukkan pipa bawah ke list (indeks ganjil: 1, 3, 5...)
    }

    private boolean checkCollision(Player player, Pipe pipe) { // Method untuk mendeteksi tabrakan burung dengan pipa
        boolean collisionX = player.getPosX() < pipe.getPosX() + pipe.getWidth() && player.getPosX() + player.getWidth() > pipe.getPosX();
        boolean collisionY = player.getPosY() < pipe.getPosY() + pipe.getHeight() && player.getPosY() + player.getHeight() > pipe.getPosY();
        return collisionX && collisionY; // Mengembalikan true jika menabrak pada sumbu X dan Y
    }

    public void move() { // Method untuk mengatur perhitungan pergerakan dan fisika game
        if (gameOver) { return; } // Jika game over, hentikan pergerakan

        player.setVelocityY(player.getVelocityY() + gravity); // Menambahkan gravitasi ke kecepatan vertikal player
        player.setPosY(player.getPosY() + player.getVelocityY()); // Mengubah posisi Y burung
        player.setPosY(Math.max(player.getPosY(), 0)); // Membatasi posisi Y agar tidak tembus ke atas layar

        if (player.getPosY() > frameHeight) { // Cek apakah burung jatuh menyentuh dasar bawah layar
            if (!gameOver) {
                SoundEffect.playSound("src/assets/hit.wav"); // Putar suara tabrakan
                SoundEffect.stopBGM(); // Hentikan BGM
            }
            gameOver = true; // Ubah status menjadi game over
        }

        // Perulangan untuk menggerakkan setiap pipa ke kiri dan mengecek tabrakan/skor
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX); // Geser pipa ke kiri

            if (checkCollision(player, pipe)) { // Cek apakah burung menabrak pipa
                if (!gameOver) {
                    SoundEffect.playSound("src/assets/hit.wav"); // Putar suara tabrakan
                    SoundEffect.stopBGM(); // Hentikan BGM
                }
                gameOver = true;
            }

            // Kita hanya memeriksa penambahan skor pada pipa bagian atas saja (indeks genap: i % 2 == 0).
            // Karena pipa atas dan bawah muncul bersamaan, mengecek pipa atas saja sudah cukup mewakili 1 celah.
            if (i % 2 == 0) {
                Pipe upperPipe = pipe; // Mengambil objek pipa atas
                if (!upperPipe.isPassed() && upperPipe.getPosX() + upperPipe.getWidth() < player.getPosX()) {
                    score++; // Tambah skor 1 poin(+1)
                    upperPipe.setPassed(true); // Tandai pipa atas sudah dilewati

                    // Tandai juga pipa bawah pasangannya (indeks i + 1) agar statusnya ikut terkunci
                    if (i + 1 < pipes.size()) {
                        pipes.get(i + 1).setPassed(true);
                    }
                }
            }
        }
    }

    public void restartGame() { // Method untuk mereset permainan ke kondisi awal
        score = 0; // Kembalikan skor ke 0
        gameOver = false; // Ubah status game over menjadi false
        player.setPosY(playerStartPosY); // Kembalikan posisi Y burung ke tengah
        player.setVelocityY(0);  // Reset kecepatan vertikal burung
        pipes.clear(); // Kosongkan seluruh pipa di layar
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Method ActionListener saat Timer berdetak (~60 FPS)
        move(); // Jalankan perhitungan pergerakan
        if (view != null) { view.repaint(); } // Refresh tampilan layar
    }

    @Override
    public void keyTyped(KeyEvent e) {} // Method bawaan KeyListener

    public void keyPressed(KeyEvent e) { // Method saat tombol keyboard ditekan
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) { // Jika tombol Spasi ditekan dan game belum over
            player.setVelocityY(-10); // Dorong burung ke atas
            SoundEffect.playSound("src/assets/jump.wav"); // Putar efek suara lompat
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) { // Jika tombol R ditekan saat game over
            restartGame(); // Reset permainan
            SoundEffect.playBGM("src/assets/bgm.wav"); // Nyalakan kembali musik latar BGM
        }
    }

    public void keyReleased(KeyEvent e) {} // Method bawaan KeyListener
}