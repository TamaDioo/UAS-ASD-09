public class TransaksiRental09 {
    int kodeTransaksi, lamaPinjam;
    String namaPeminjam;
    double totalBiaya;
    BarangRental09 br;

    public TransaksiRental09(int kodeTransaksi, String namaPeminjam, int lamaPinjam, double totalBiaya, BarangRental09 br) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.totalBiaya = totalBiaya;
        this.br = br;
    }

    public TransaksiRental09(String namaPeminjam, int lamaPinjam, BarangRental09 br) {
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.br = br;
    }
}