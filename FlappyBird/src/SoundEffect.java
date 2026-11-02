import javax.sound.sampled.AudioInputStream; // Mengimpor pustaka untuk membaca stream file audio
import javax.sound.sampled.AudioSystem;     // Mengimpor sistem audio Java untuk mengambil file suara
import javax.sound.sampled.Clip;            // Mengimpor Clip untuk memutar klip audio
import java.io.File;   // Mengimpor File untuk membaca path file suara di folder proyek

public class SoundEffect {
    private static Clip bgmClip; // Variabel statis untuk menampung klip musik latar (BGM)

    // Method untuk memutar efek suara singkat (seperti lompat atau tabrakan)
    public static void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath); // Membaca file suara dari path yang diberikan
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); // Mengubah file ke format audio stream
            Clip clip = AudioSystem.getClip(); // Membuat objek clip audio baru
            clip.open(audioIn); // Membuka stream audio ke dalam clip
            clip.start(); // Memulai pemutaran suara sebanyak satu kali
        }
        catch (Exception e) {
            System.out.println("Gagal memutar suara: " + e.getMessage()); // Menangkap pesan error jika file gagal dimuat
        }
    }

    // Method untuk memutar musik latar belakang (BGM) secara berputar terus-menerus (looping)
    public static void playBGM(String bgmFilePath) {
        try {
            stopBGM(); // Menghentikan BGM yang sedang berjalan sebelumnya agar tidak tumpang tindih
            File bgmFile = new File(bgmFilePath); // Membaca file BGM dari folder assets
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bgmFile); // Mengubah file ke format audio stream
            bgmClip = AudioSystem.getClip(); // Membuat objek clip untuk BGM
            bgmClip.open(audioIn); // Membuka stream audio BGM
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY); // Mengatur agar musik berputar berulang-ulang tanpa henti
            bgmClip.start(); // Memulai pemutaran musik latar
        }
        catch (Exception e) {
            System.out.println("Gagal memutar BGM: " + e.getMessage()); // Menangkap pesan error jika BGM gagal dimuat
        }
    }

    // Method untuk menghentikan musik latar saat pemain kalah atau kembali ke menu
    public static void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) { // Mengecek apakah klip BGM sedang aktif berjalan
            bgmClip.stop(); // Menghentikan pemutaran BGM
            bgmClip.close(); // Menutup klip audio untuk membersihkan memori
        }
    }
}