import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    //Selles meetodis loome koopa etteantud kaardi põhjal.
    //Koopa loomine läbi meetodi võimaldab meil kaarti soovi korral lihtsamini muuta või näiteks
    //tekitada mitu eri koobast
    //kaart ise on 2D massiiv, kus peamassiivi elemendid on tasemed ja alammassiivi elemendid ruumid
    public static ToaTegevus[][] koopaLoomine(String[][] kaart) {
        ToaTegevus[][] ruumid = new ToaTegevus[kaart.length][];
        for (int i = 0; i < kaart.length; i++) { //käime läbi koopa tasemed
            ruumid[i] = new ToaTegevus[kaart[i].length];
            for (int j = 0; j < kaart[i].length; j++) { //käime läbi toa
                if (Objects.equals(kaart[i][j], "Koll")) {
                    ToaTegevus koll = new Koll("Koll", (int) (Math.random() * 10 + 1),
                            (int) (Math.random() * 5 + 1), (int) (Math.random() * 5));
                    ruumid[i][j] = koll;
                } else if (Objects.equals(kaart[i][j], "Abistaja")) {
                    ToaTegevus abistaja = new Abistaja("Abistaja");
                    ruumid[i][j] = abistaja;
                } else if (Objects.equals(kaart[i][j], "Võlur")) {
                    ToaTegevus võlur = new Võlur("Võlur");
                    ruumid[i][j] = võlur;
                } else if (Objects.equals(kaart[i][j], "Boss")) {
                    ToaTegevus koll = new Koll("BOSS", (int) (Math.random() * 6 + 10),
                            (int) (Math.random() * 10 + 5), (int) (Math.random() * 10 + 1));
                    ruumid[i][j] = koll;
                } else if (Objects.equals(kaart[i][j], "Lõks")) {
                    ToaTegevus lõks = new Lõks((int) (Math.random() * 3));
                    ruumid[i][j] = lõks;
                }
            }
        }
        return ruumid;
    }


    public static void main(String[] args) {
        //sissejuhatav tekst
        System.out.println("Tere tulemast seiklema koobastes Zipo! \nSinu eesmärk on jõuda koopast välja elusalt. \n" +
                "Selleks pead Sa võitlema kollidega ning lahendama võlurite mõistatusi. Mõnikord võid kohata abistavaid tegelasi, kes toetavad Su eesmärki ning" +
                " annavad Sulle abistavaid vahendeid. ");
        Scanner sc = new Scanner(System.in);

        //küsime mängija nime ning loome Mängija objekti
        System.out.println("Palun sisesta oma mängija nimi: ");
        String mängijaNimi = sc.next();
        Mängija mängija = new Mängija(mängijaNimi, 10, 7, 3);

        //loome mängija jaoks koopa ja väljastame tema algandmed
        ToaTegevus[][] ruumid = koopaLoomine(mängija.getKaart());
        System.out.printf("Mängija info: Nimi: %s, Elud: %d, Tugevus: %d, Kaitse %d\n", mängija.getNimi(),
                mängija.getElud(), mängija.getTugevus(), mängija.getKaitse());

        boolean kasJätkata = true; //selle abil kontrollime, kas mängija on veel elus ja mäng jätkab
        while (kasJätkata) {
            System.out.println("Palun vali ruumi nr: 1..." + mängija.getKaart()[mängija.getTase()].length + "." + " Kui soovid mängu lõpetada, vali 0.");
            int ruumiNr = sc.nextInt();
            if (ruumiNr == 0) { //kui mängija soovib mängu lõpetada
                System.out.println("Aitäh mängimast!");
                break;
            }

            else if (ruumiNr > mängija.getKaart()[mängija.getTase()].length || ruumiNr < 0) {
                System.out.println("Näpp vääratas? Sellist ruumi ei eksisteeri. Palun proovi uuesti");
                continue; //kui mängija valib ruumi, mida ei eksisteeri selles tasemes
            }

            //vastasel juhul hakkab mäng
            int tase = mängija.getTase();
            String ruumiNimi = mängija.getKaart()[tase][ruumiNr - 1];
            ToaTegevus ruum = ruumid[tase][ruumiNr - 1];
            switch (ruumiNimi) {
                case "Koll":
                    ruum.getKirjeldus();
                    System.out.println("\nAlgab võitlus kolliga!");
                    kasJätkata = ruum.ruumiTegevus(mängija);
                    break;
                case "Võlur":
                    System.out.println("\nSisenesite võluri ruumi");
                    ruum.getKirjeldus();
                    kasJätkata = ruum.ruumiTegevus(mängija);
                    break;
                case "Abistaja":
                    System.out.println("\nKohtusite abistava tegelasega");
                    kasJätkata = ruum.ruumiTegevus(mängija);
                    break;
                case "Lõks":
                    System.out.println("\nSattusite lõksu");
                    ruum.getKirjeldus();
                    kasJätkata = ruum.ruumiTegevus(mängija);
                    break;
                case "Boss":
                    System.out.println("\nJõudsite bossini");
                    ruum.getKirjeldus();
                    kasJätkata = ruum.ruumiTegevus(mängija);
                    break;
                case "Lõpp":
                    System.out.println("Jõudsite koopa viimasesse ruumi - valgus juba paistab! Veel enne aga märkate \n" +
                            "ruumi servas midagi helkimas. See on kott, mis tundub olevat täis väärtmetalli ja kalliskive! \n" +
                            "Võtate selle endage kaasa. 'Nüüd ma ei pea enam vähemalt kolm kuud nuudleid sööma!' mõtlete endamisi.");
                    System.out.println(ruum);
                    break;
                default:
                    System.out.println("Sattusite tundmatusse ruumi");
                    System.out.println(ruum);
                    continue;
            }
            if (ruumiNimi.equals("Lõpp")) {
                System.out.println("Olete jõudnud lõppu! Pääsesite koopast eluga!");
                break;
            }
            mängija.setTase(mängija.getTase() + 1);

            System.out.printf("Mängija info: Nimi: %s, Elud: %d, Tugevus: %d, Kaitse %d\n", mängija.getNimi(),
                    mängija.getElud(), mängija.getTugevus(), mängija.getKaitse());
            System.out.println("_______________________________________________________________________________________");
        }
    }
}





