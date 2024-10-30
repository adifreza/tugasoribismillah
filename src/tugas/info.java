package tugas;

/**
 * Record {@code info} menyimpan informasi item seperti nama, harga, dan kategori.
 * Record adalah struktur data immutabel.
 *
 * @param nama Nama item.
 * @param harga Harga item.
 * @param kategori Kategori item (Makanan/Minuman/Lain-lain).
 */
public record info(String nama, double harga, String kategori) {
}
