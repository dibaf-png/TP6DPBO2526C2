# JANJI
Saya Farah Diba Nur Malinda dengan NIM 2502083 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan 
Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

---

# Fitur Utama

Terdapat beberapa fitur yang ada dalam program game ini, yaitu:
* **Main Menu Interaktif (`CardLayout`)**: Dilengkapi tombol **START GAME** (memulai timer & BGM) dan **EXIT** (keluar aplikasi).
* **Fisika & Kontrol**: Burung ditarik gravitasi dan dapat melompat menggunakan tombol **Spasi** disertai efek suara.
* **Skor Akurat (+1)**: Perhitungan skor presisi dibatasi pada pipa atas (`i % 2 == 0`) tanpa penambahan ganda.
* **Game Over & Restart ("R")**: Deteksi tabrakan otomatis menghentikan permainan; tekan tombol **"R"** untuk langsung mengulang dari awal.
* **Antarmuka Simetris**: Teks skor dan layar *Game Over* menggunakan font **Comic Sans MS** yang diposisikan otomatis di tengah.
* **Bonus Audio (+20)**: Musik latar belakang (*BGM*) *looping* dan *sound effect* dinamis untuk lompat serta tabrakan.

---

# Struktur Class

Pada program ini terdapat **7 class utama** yang saling mendukung, yaitu:
1. **`App.java` (Kelas Utama / Entry Point)**
   * **Fungsi:** Menjadi titik awal jalannya program (`main method`). Kelas ini bertanggung jawab mengatur ukuran jendela utama (`JFrame`), menerapkan tata letak tumpukan panel menggunakan `CardLayout`, serta mendaftarkan halaman menu dan halaman game. Di sinilah aksi tombol *Start* dan *Exit* dihubungkan.

2. **`MenuView.java` (Antarmuka Menu Utama)**
   * **Fungsi:** Mengatur tampilan antarmuka awal saat game pertama kali dibuka. Kelas ini membuat panel menu yang bersih dan menyediakannya dengan dua tombol interaktif, yaitu tombol untuk memulai permainan (**START GAME**) dan tombol untuk keluar dari aplikasi (**EXIT**).

3. **`View.java` (Kanvas Grafis & Visual)**
   * **Fungsi:** Bertindak sebagai area render grafis (`JPanel`). Kelas ini bertugas menggambar seluruh komponen visual permainan ke layar—seperti gambar latar, burung, pipa atas dan bawah, skor, hingga tampilan teks *Game Over*—secara presisi dan simetris menggunakan `FontMetrics` dan font *Comic Sans MS*.

4. **`Logic.java` (Pusat Logika & Aturan Permainan)**
   * **Fungsi:** Menjadi otak dari seluruh jalannya game. Kelas ini mengatur hukum fisika gravitasi, pergerakan pipa secara konstan, pemanggilan timer pengulangan (*game loop*), pengecekan tabrakan (*collision detection*) antara burung dan pipa/tanah, serta manajemen skor akurat (+1 per celah).

5. **`Player.java` (Model Objek Burung)**
   * **Fungsi:** Kelas model data yang merepresentasikan objek burung. Menyimpan informasi penting seperti koordinat posisi (`x`, `y`), ukuran dimensi (`width`, `height`), serta kecepatan vertikal (*velocity*) saat burung melompat atau ditarik gravitasi.

6. **`Pipe.java` (Model Objek Pipa Rintangan)**
   * **Fungsi:** Kelas model data untuk merepresentasikan objek pipa (baik pipa atas maupun pipa bawah). Menyimpan atribut posisi horizontal dan vertikal pipa, kecepatan gerak, gambar pipa, serta status penanda apakah pipa tersebut sudah berhasil dilewati oleh burung (`passed`).

7. **`SoundEffect.java` (Utilitas Audio)**
   * **Fungsi:** Kelas pendukung yang dirancang untuk menangani pemuatan dan pemutaran berkas audio berformat `.wav`. Digunakan secara mandiri untuk memutar musik latar belakang (*BGM*) secara *looping* serta memainkan efek suara (*sound effect*) saat burung melompat atau mengalami tabrakan.

---

# Alur Program

1. **Inisialisasi & Menu Utama (`App.java` & `MenuView.java`)**: 
   * Program dijalankan melalui `App.java` yang menyiapkan jendela utama (`JFrame`) berukuran 360x640 piksel menggunakan `CardLayout`. Halaman awal menampilkan menu utama dengan tombol **START GAME** dan **EXIT**.
2. **Memulai Permainan (`startGame()`)**: 
   * Saat tombol **START GAME** diklik, tampilan beralih ke panel game (`View.java`), fokus keyboard diaktifkan, musik latar (*BGM*) diputar berulang (*looping*), dan timer permainan mulai berjalan bersamaan dengan kemunculan pipa.
3. **Mekanika & Fisika Permainan (`Logic.java` & `View.java`)**: 
   * Burung terus ditarik gravitasi ke bawah. Pemain menekan **Spasi** untuk melompat ke atas, sementara pipa bergerak konstan dari kanan ke kiri layar.
4. **Penghitungan Skor Akurat**: 
   * Skor bertambah tepat **+1** setiap kali burung berhasil melewati celah pipa atas (`i % 2 == 0`) secara konsisten.
5. **Kondisi Game Over & Restart ("R")**: 
   * Permainan berhenti otomatis jika terjadi tabrakan (dengan pipa atau tanah) disertai efek suara *hit*. Pemain cukup menekan tombol **"R"** untuk me-restart permainan secara instan.

---

# Dokumentasi Gameplay

<video src="https://github.com/user-attachments/assets/27c45077-6004-4706-87e0-b3696afe4d13" controls autoplay loop muted width="360">
  Your browser does not support the video tag.
</video>





