import java.util.Scanner;

/**
 * VeritabaniIslemleri
 *
 * @author Şafak Taşkın
 * @since 1.0.0
 */
public class App {

    public static void main(String[] args) {
        VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();
        MenuTasarimi menuTasarimi = new MenuTasarimi();
        Scanner scanner = new Scanner(System.in);
        menuTasarimi.menuOlustur();
        int secim = scanner.nextInt();
        boolean isBaglantiHazir = veritabaniIslemleri.baglantiyiKontrolEt();
        if (!isBaglantiHazir) {
            System.out.println("Bağlantı problemi var. Lütfen kontrol edin.");
        } else if(secim == 1){
            Scanner scan = new Scanner(System.in);
            veritabaniIslemleri.kahramanlariListele();

            System.out.print("Kahraman adını giriniz: ");
            String adi = scan.nextLine();

            System.out.print("Kahraman soyadını giriniz: ");
            String soyadi = scan.nextLine();

            Hero hero = new Hero();
            hero.setAdi(adi);
            hero.setSoyadi(soyadi);
            veritabaniIslemleri.kahramanEkle(hero);
        } else if(secim == 2){
            veritabaniIslemleri.kahramanlariListele();
            Scanner klavyedenAlinanDeger = new Scanner(System.in);

            System.out.print("Karakterin ID'sini girin: ");
            int heroId = klavyedenAlinanDeger.nextInt();

            System.out.println("Film adı giriniz: ");
            String filmAdi = klavyedenAlinanDeger.next();

            System.out.println("Bütçe tutarını giriniz: ");
            float budget = klavyedenAlinanDeger.nextFloat();

            veritabaniIslemleri.filmEkle(filmAdi, budget, heroId);

        } else if(secim == 3){
            veritabaniIslemleri.kahramanlarinKazanclari();
        }else
            System.out.println("Rakam girişini doğru yapınız !");
        }

    }




