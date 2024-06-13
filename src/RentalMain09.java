import java.util.Scanner;

public class RentalMain09 {
    static BarangRental09[] barangRentals = new BarangRental09[5];
    static TransaksiRental09[] transaksiRentals = new TransaksiRental09[100];
    static int jumlahTransaksi = 0;

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        barangRentals[0] = new BarangRental09("S 4567 VV", "Honda Beat", "Motor", 2017, 10000);
        barangRentals[1] = new BarangRental09("N 4515 VS", "Honda Vario", "Motor", 2019, 10000);
        barangRentals[2] = new BarangRental09("N 1453 AA", "Toyota Yaris", "Mobil", 2022, 30000);
        barangRentals[3] = new BarangRental09("AB 4321 A", "Toyota Innova", "Mobil", 2021, 25000);
        barangRentals[4] = new BarangRental09("B 1234 AG", "Toyota Avanza", "Mobil", 2021, 25000);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Peminjaman");
            System.out.println("3. Tampilkan seluruh transaksi");
            System.out.println("4. Urutkan Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");
            int choice = sc1.nextInt();

            switch (choice) {
                case 1:
                    daftarKendaraan();
                    break;
                case 2:
                    peminjaman(sc2);
                    break;
                case 3:
                    tampilkanTransaksi();
                    break;
                case 4:
                    urutTransaksi(sc2);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void daftarKendaraan() {
        System.out.println("==========================");
        System.out.println("Daftar Kendaraan Rental Serba Serbi:");
        System.out.println("==========================");
        System.out.println("Nomor TNKB\tNama Kendaraan\tJenis\tTahun\tBiaya Sewa Perjam");
        for (BarangRental09 br : barangRentals) {
            System.out.printf("%-15s%-20s%-25s%-5d%-5d%n", br.noTNKB, br.namaKendaraan, br.jenisKendaraan, br.tahun, br.biayaSewa);
        }
    }

    public static void peminjaman(Scanner scanner) {
        System.out.print("Masukkan Nama Peminjam: ");
        String namaPeminjam = scanner.nextLine();
        System.out.print("Masukkan Nomor TNKB: ");
        String noTNKB = scanner.nextLine();
        System.out.print("Masukkan Lama Pinjam (jam): ");
        int lamaPinjam = scanner.nextInt();
        System.out.print("Apakah Member (ya/tidak): ");
        String memberStatus = scanner.next();
        boolean isMember = memberStatus.equalsIgnoreCase("ya");

        BarangRental09 br = findBarangByNoTNKB(noTNKB);
        if (br == null) {
            System.out.println("Tidak bisa diproses, kendaraan sudah dipinjam orang!");
            return;
        }

        for (int i = 0; i < jumlahTransaksi; i++) {
            if (transaksiRentals[i].br.noTNKB.equalsIgnoreCase(noTNKB)) {
                System.out.println("Tidak bisa diproses, kendaraan sudah dipinjam orang lain");
                return;
            }
        }

        TransaksiRental09 tr = new TransaksiRental09(namaPeminjam, lamaPinjam, br);
        double totalBiaya = tr.totalBiaya;

        if (isMember) {
            totalBiaya -= 25000;
        }

        if (lamaPinjam > 48 && lamaPinjam <= 78) {
            totalBiaya *= 0.90;
        } else if (lamaPinjam > 78) {
            totalBiaya *= 0.80;
        }

        tr.totalBiaya = totalBiaya;
        transaksiRentals[jumlahTransaksi++] = tr;
        System.out.println("Transaksi berhasil!");
    }

    public static BarangRental09 findBarangByNoTNKB(String noTNKB) {
        for (BarangRental09 br : barangRentals) {
            if (br.noTNKB.equalsIgnoreCase(noTNKB)) {
                return br;
            }
        }
        return null;
    }

    public static void tampilkanTransaksi() {
        System.out.println("Kode\tNo TNKB\tNama Barang\tNama Peminjam\tLama Pinjam\tTotal Biaya");
        double totalPendapatan = 0;
        for (int i = 0; i < jumlahTransaksi; i++) {
            System.out.println(transaksiRentals[i]);
            totalPendapatan += transaksiRentals[i].totalBiaya;
        }
        System.out.println("Total Pendapatan: " + totalPendapatan);
    }

    public static void urutTransaksi(Scanner sc) {
        System.out.print("Urutkan berdasarkan: 1. A-M 2. N-Z: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            traknsaksiDescending();
        } else if (choice == 2) {
            sortTransaksiByNameDescending();
        } else {
            System.out.println("Pilihan tidak valid!");
        }

        tampilkanTransaksi();
    }

    public static void traknsaksiDescending() {
        for (int i = 1; i < jumlahTransaksi; i++) {
            TransaksiRental09 key = transaksiRentals[i];
            int j = i - 1;
            while (j >= 0 && transaksiRentals[j].namaPeminjam.charAt(0) >= 'A' &&
                   transaksiRentals[j].namaPeminjam.charAt(0) <= 'M' &&
                   transaksiRentals[j].br.noTNKB.compareTo(key.br.noTNKB) < 0) {
                transaksiRentals[j + 1] = transaksiRentals[j];
                j = j - 1;
            }
            transaksiRentals[j + 1] = key;
        }
    }

    public static void sortTransaksiByNameDescending() {
        for (int i = 1; i < jumlahTransaksi; i++) {
            TransaksiRental09 key = transaksiRentals[i];
            int j = i - 1;
            while (j >= 0 && transaksiRentals[j].namaPeminjam.charAt(0) >= 'N' &&
                   transaksiRentals[j].namaPeminjam.charAt(0) <= 'Z' &&
                   transaksiRentals[j].namaPeminjam.compareTo(key.namaPeminjam) < 0) {
                transaksiRentals[j + 1] = transaksiRentals[j];
                j = j - 1;
            }
            transaksiRentals[j + 1] = key;
        }
    }
}