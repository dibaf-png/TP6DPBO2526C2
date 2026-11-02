import javax.swing.*; // Mengimpor pustaka Swing untuk komponen GUI (JPanel, JButton)
import java.awt.*;    // Mengimpor pustaka AWT untuk pengaturan warna, font, dan grafik

public class MenuView extends JPanel { // Kelas panel untuk tampilan menu utama game

    public JButton startButton; // Tombol publik untuk memulai permainan
    public JButton exitButton;  // Tombol publik untuk keluar dari aplikasi

    public MenuView() { // Konstruktor kelas MenuView
        setPreferredSize(new Dimension(360, 640)); // Menyesuaikan ukuran panel menu dengan ukuran jendela game
        setBackground(Color.cyan); // Mengatur warna latar belakang panel menu menjadi biru muda (cyan)
        setLayout(null); // Menggunakan layout manual (absolute positioning) agar posisi tombol bisa diatur bebas

        startButton = new JButton("START GAME"); // Inisialisasi tombol Start dengan teks
        startButton.setBounds(80, 260, 200, 50); // Mengatur posisi dan ukuran tombol start
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18)); // Mengatur jenis huruf santai, tebal, ukuran 18
        startButton.setBackground(Color.GREEN); // Mengatur warna latar tombol menjadi hijau
        startButton.setForeground(Color.WHITE); // Mengatur warna teks tombol menjadi putih
        add(startButton); // Menambahkan tombol start ke dalam panel menu

        exitButton = new JButton("EXIT"); // Inisialisasi tombol Exit dengan teks
        exitButton.setBounds(80, 330, 200, 50); // Mengatur posisi dan ukuran tombol exit
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18)); // Mengatur jenis huruf santai, tebal, ukuran 18
        exitButton.setBackground(Color.RED); // Mengatur warna latar tombol menjadi merah
        exitButton.setForeground(Color.WHITE); // Mengatur warna teks tombol menjadi putih
        add(exitButton); // Menambahkan tombol exit ke dalam panel menu
    }

    @Override
    protected void paintComponent(Graphics g) { // Method untuk menggambar komponen visual tambahan di menu
        super.paintComponent(g); // Membersihkan panel dari sisa gambar sebelumnya
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 36)); // Mengatur font judul game menjadi Comic Sans MS
        g.setColor(Color.WHITE); // Mengatur warna teks judul menjadi putih
        g.drawString("FLAPPY BIRD", 55, 150); // Menggambar teks judul game di koordinat tertentu
    }
}