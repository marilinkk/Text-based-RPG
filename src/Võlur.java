import java.util.Scanner;

public class Võlur implements ToaTegevus {
    private String kirjeldus; //tegelase välimuse ja iseloomu kirjeldus
    private String mõistatus;
    private String[] mõistatused = {"Mis suudab ruumi täita, kuid ei võta ruumi?-valgus",
            "Millel on 13 südant, kuid pole muid elundeid?-kaardipakk",
            "Mis on seest siiruviiruline, pealt kullakarva?-sibul",
            "Mis on auke täis, kuid hoiab siiski vett?-käsn",
            "Mis tuleb enne kasutamist katki teha?-muna",
            "Mis ei saa rääkida, kuid vastab, kui temaga räägitakse?-kaja",
            "Mida rohkem seda on, seda vähem näete. Mis see on?-pimedus",
            "Mida vähem auke selles on, seda katkisem see on.-võrk",
            "Mis läheb üles ja alla, kuid ei liigu?-trepp"};

    private String[] kirjeldused = {"Ruumis tervitab sind pika habeme ja sinakas-lilla keebiga vanamees. Ka piip on tal ees. \n" +
            "Oleks oodanud, et võlur natuke huvitavam ikka.",
            "Ruumis märkad ühte tohutu suurt seent. Otsid kedagi, kuid ei näe ühtegi hingelist. \nÜhel hetkel kuuled ühte piiksuvat häält: 'Siin all, siin all!' \n" +
                    "vaatad alla ja märkad seene peal mitte suuremat kui pöidlakõrgust mehikest. \nLegend räägib, et ta on kõige võimsam võlur siinmaal.",
    "Sisened ruumi ja tunned veidrat lõhna, mis on ühteaegu magus ja kibe. \nMärkad võlurit, kes keedab mingit kahtlast suppi. Ta silmad on sõbralikud ja ta kutsub sind lähemale."};

    //Võluri konstruktor. Genereerib võlurile suvalise kirjelduse ja mõistatuse
    public Võlur(String kirjeldus) {
        this.kirjeldus = genereeriKirjeldus();
        this.mõistatus = mõistatused[(int) (Math.random() * mõistatused.length)];
    }

    public String genereeriKirjeldus() {
        int randIndeks = (int) (Math.random() * kirjeldused.length);
        return kirjeldused[randIndeks];
    }

    @Override
    public void getKirjeldus() {
        System.out.println(kirjeldus);
    }

    public String getMõistatus() {
        return mõistatus;
    }

    //Esitatakse mängijale mõistatus, millele peab vastama 3 korra jooksul
    //Kui vastab õigesti saab edasi minna, kui valesti siis on kaotab 2 elu.
    @Override
    public boolean ruumiTegevus(Mängija mängija) {
        String küsimus = mõistatus.split("-")[0] + " ";
        String vastus = mõistatus.split("-")[1]; //mõistatuse vastus
        System.out.printf("Võlur esitab sulle järgmise küsimuse, millele võid vastust pakkuda 3 korda: \n%s", küsimus);
        Scanner sc = new Scanner(System.in);
        String mängijaVastus;
        int vastuseVõimalused = 3;
        boolean vastasÕigesti = false;
        for (int i = 1; i <= vastuseVõimalused; i++) { //mängija saab kolm korda pakkuda
            System.out.printf("\nSinu %d. pakkumine: ", i);
            mängijaVastus = sc.next().toLowerCase();
            //Kui mängija vastab õigesti, siis tagastab true ja lõpetab tsükli
            if (mängijaVastus.equals(vastus)) {
                System.out.println("Võlur noogutab heakskiitvalt pead.");
                if (i == 1) { //kui mängija vastab esimese korraga õigesti, saab ühe lisaelu
                    System.out.println("Võlurile meeldivad intelligentsed inimesed. Ta joodab sulle võlujooki, mis annab sulle ühe elu juurde.");
                    mängija.setElud(mängija.getElud() + 1);
                }
                vastasÕigesti = true;
                break;
            } else if (vastuseVõimalused - i >0) System.out.printf("Võlur kortsutab kulmu - vastasid vist valesti. Sul on jäänud veel %d katset.", vastuseVõimalused - i);
            if (i == vastuseVõimalused) {
                System.out.println("Võlur vaatab sind pettunud näoga: 'Mis kasu on kangelasest, kes on loll nagu lauajalg?'.\nKaristuseks paneb ta sulle peale loitu, mis võtab ära 2 elu.");
                mängija.setElud(mängija.getElud() - 2);
                if (mängija.getElud() > 0) vastasÕigesti = true;
            }
        }
        if (vastasÕigesti == false) System.out.println("Võluri loits ei olnud küll väga tugev, ent siiski piisav, et su nõrk keha hävitada.\n" +
                "Oleksid vist pidanud koolis rohkem õppima - mäng sai sinu jaoks läbi.");
        return vastasÕigesti;
    }
}
