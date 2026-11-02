import javax.swing.*; // Mengimpor pustaka Swing untuk membuat JFrame dan JPanel
import java.awt.*;    // Mengimpor AWT untuk CardLayout
import java.awt.event.ActionEvent;  // Mengimpor event aksi tombol
import java.awt.event.ActionListener; // Mengimpor listener aksi tombol

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlappyBird"); // Membuat jendela utama game dengan judul "FlappyBird"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Mengatur agar program berhenti total saat jendela ditutup
        frame.setSize(360, 640); // Menentukan lebar (360) dan tinggi (640) jendela game
        frame.setLocationRelativeTo(null); // Menempatkan posisi jendela tepat di tengah layar monitor
        frame.setResizable(false); // Mencegah ukuran jendela agar tidak bisa diubah-ubah oleh pengguna

        CardLayout cardLayout = new CardLayout(); // Membuat pengatur tata letak tumpukan panel (CardLayout)
        JPanel containerPanel = new JPanel(cardLayout); // Membuat panel penampung utama yang menerapkan CardLayout

        MenuView menuView = new MenuView(); // Membuat objek tampilan menu utama
        Logic logika = new Logic(); // Membuat objek logika pengatur permainan
        View tampilan = new View(logika); // Membuat objek tampilan kanvas game dengan membawa data logika

        logika.setView(tampilan); // Menghubungkan balik objek logika ke tampilan agar bisa melakukan repaint

        containerPanel.add(menuView, "MENU"); // Mendaftarkan halaman menu ke container dengan nama kunci "MENU"
        containerPanel.add(tampilan, "GAME"); // Mendaftarkan halaman game ke container dengan nama kunci "GAME"

        // Memberikan fungsi aksi ketika tombol START diklik oleh pengguna
        menuView.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "GAME"); // Berpindah tampilan dari halaman MENU ke halaman GAME
                tampilan.requestFocusInWindow(); // Memfokuskan keyboard ke area game agar tombol spasi langsung berfungsi
                logika.startGame(); // Memulai timer game loop dan kemunculan pipa saat tombol diklik
                SoundEffect.playBGM("src/assets/bgm.wav"); // Memulai pemutaran musik latar secara looping saat game dimulai
            }
        });

        // Memberikan fungsi aksi ketika tombol EXIT diklik oleh pengguna
        menuView.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Menutup aplikasi secara total
            }
        });

        frame.add(containerPanel); // Memasukkan containerPanel ke dalam JFrame utama
        cardLayout.show(containerPanel, "MENU"); // Menampilkan halaman MENU terlebih dahulu saat aplikasi pertama kali dibuka
        frame.setVisible(true); // Menampilkan jendela game ke layar pengguna
    }
}