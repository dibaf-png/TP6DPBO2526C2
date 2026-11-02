import javax.swing.*; // Mengimpor pustaka Swing untuk membuat komponen panel (JPanel)
import java.awt.*;    // Mengimpor pustaka AWT untuk pengaturan warna, ukuran, dan grafik
import java.util.ArrayList; // Mengimpor ArrayList untuk menangani daftar koleksi pipa

public class View extends JPanel { // Kelas panel tampilan game
    int width = 360;  // Lebar kanvas game
    int height = 640; // Tinggi kanvas game

    private Logic logic; // Referensi ke objek Logic

    public View(Logic logic) { // Konstruktor kelas View
        this.logic = logic;
        setPreferredSize(new Dimension(width, height)); // Mengatur ukuran panel
        setBackground(Color.cyan); // Mengatur warna latar belakang menjadi cyan (biru muda)
        setFocusable(true); // Mengaktifkan fokus agar bisa mendeteksi input keyboard
        addKeyListener(logic); // Menambahkan KeyListener dari objek logic
    }

    @Override
    public void paintComponent(Graphics g) { // Method bawaan JPanel untuk menggambar komponen grafis
        super.paintComponent(g); // Membersihkan frame sebelumnya
        draw(g); // Memanggil method draw
    }

    public void draw(Graphics g) { // Method khusus untuk menggambar seluruh elemen visual ke layar
        Player player = logic.getPlayer(); // Mengambil data player dari Logic
        if (player != null) { // Jika player ada, gambar player ke layar
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes(); // Mengambil daftar pipa dari Logic
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) { // Perulangan untuk menggambar setiap pipa
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 36)); // Menggunakan font Comic Sans MS tebal ukuran 36
        g.setColor(Color.WHITE); // Mengatur warna teks skor menjadi putih
        String scoreStr = String.valueOf(logic.score); // Mengubah nilai skor ke bentuk String
        FontMetrics scoreMetrics = g.getFontMetrics(g.getFont()); // Mengukur lebar teks skor
        int scoreX = (width - scoreMetrics.stringWidth(scoreStr)) / 2; // Menghitung posisi X agar skor persis di tengah
        g.drawString(scoreStr, scoreX, 80); // Menggambar teks skor di koordinat yang simetris

        // Kondisi jika permainan berakhir (Game Over)
        if (logic.gameOver) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Menggunakan font Comic Sans MS ukuran 48
            FontMetrics goMetrics = g.getFontMetrics(g.getFont()); // Mengukur lebar teks "GAME OVER"
            int goX = (width - goMetrics.stringWidth("GAME OVER")) / 2; // Menghitung posisi tengah secara matematis
            g.setColor(Color.RED); // Mengatur warna teks menjadi merah
            g.drawString("GAME OVER", goX, 280); // Menggambar teks Game Over di tengah layar

            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); // Menggunakan font ukuran 20
            FontMetrics restartMetrics = g.getFontMetrics(g.getFont()); // Mengukur lebar teks petunjuk
            String restartText = "Tekan R untuk Restart"; // Teks instruksi restart
            int restartX = (width - restartMetrics.stringWidth(restartText)) / 2; // Menghitung posisi tengah
            g.setColor(Color.WHITE); // Mengatur warna teks menjadi putih
            g.drawString(restartText, restartX, 330); // Menggambar teks petunjuk di bawah tulisan Game Over
        }
    }
}