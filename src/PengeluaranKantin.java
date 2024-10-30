import tugas.info;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program untuk menghitung total pengeluaran di kantin, termasuk diskon
 * jika pengeluaran melebihi batas tertentu.
 */
public class PengeluaranKantin extends tugas.yoi implements tugas.oke {
    private static final double DISKON_THRESHOLD = 50000;
    private static final double DISKON_RATE = 0.10;
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        inputItems();  // Input data item dari pengguna
        double total = hitungTotal();  // Hitung total pengeluaran
        double diskon = hitungDiskon(total);  // Hitung diskon jika ada
        tampilkanLaporan(total, diskon);  // Tampilkan laporan pengeluaran
        scanner.close();
    }

    /**
     * Metode untuk input item dari pengguna.
     */
    private static void inputItems() {
        String ulang;
        do {
            System.out.print("Masukkan nama item: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan harga item: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();  // Konsumsi karakter newline

            System.out.print("Masukkan kategori (Makanan/Minuman/Lain-lain): ");
            String kategori = scanner.nextLine();

            // Tambahkan item baru ke dalam daftar
            items.add(new Item(new info(nama, harga, kategori)));

            System.out.print("Ingin menambah item lagi? (ya/tidak): ");
            ulang = scanner.nextLine();
        } while (ulang.equalsIgnoreCase("ya"));
    }

    /**
     * Menghitung total pengeluaran.
     *
     * @return Total harga dari semua item.
     */
    private static double hitungTotal() {
        double total = calculateTotal();  // Inisialisasi nilai total
        for (Item item : items) {
            total += item.getHarga();  // Tambahkan harga setiap item
        }
        return total;
    }

    /**
     * Mengembalikan total awal sebagai 0.
     *
     * @return Nilai awal total (0).
     */
    private static double calculateTotal() {
        return 0;
    }

    /**
     * Menghitung diskon berdasarkan total pengeluaran.
     *
     * @param total Total pengeluaran.
     * @return Jumlah diskon jika memenuhi syarat, 0 jika tidak.
     */
    private static double hitungDiskon(double total) {
        return (total >= DISKON_THRESHOLD) ? total * DISKON_RATE : 0;
    }

    /**
     * Menampilkan laporan pengeluaran kepada pengguna.
     *
     * @param total Total pengeluaran tanpa diskon.
     * @param diskon Jumlah diskon yang diterapkan.
     */
    private static void tampilkanLaporan(double total, double diskon) {
        System.out.println("\n==== Laporan Pengeluaran ====");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Pengeluaran: Rp%.2f\n", total);
        if (diskon > 0) {
            System.out.printf("Diskon: Rp%.2f\n", diskon);
        }
        System.out.printf("Total Akhir: Rp%.2f\n", total - diskon);
    }
}

/**
 * Kelas untuk menyimpan data setiap item.
 */
class Item {
    private String nama;
    private double harga;
    private String kategori;

    /**
     * Konstruktor untuk membuat objek {@code Item} menggunakan parameter {@code info}.
     *
     * @param info Objek yang berisi informasi nama, harga, dan kategori item.
     */
    public Item(info info) {
        this.nama = info.nama();
        this.harga = info.harga();
        this.kategori = info.kategori();
    }

    /**
     * Mengembalikan representasi String dari objek item.
     *
     * @return String yang berisi informasi nama, kategori, dan harga item.
     */
    @Override
    public String toString() {
        return String.format("Nama: %s, Kategori: %s, Harga: Rp%.2f",
                nama, kategori, harga);
    }

    /** @return Nama item. */
    public String getNama() {
        return nama;
    }

    /** @return Harga item. */
    public double getHarga() {
        return harga;
    }

    /** @return Kategori item. */
    public String getKategori() {
        return kategori;
    }
}
