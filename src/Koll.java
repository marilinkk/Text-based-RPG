import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Koll implements ToaTegevus {
    private String kolliNimi; //koopa loomiseks
    private String kirjeldus;
    private int elud;
    private int tugevus;
    private int kaitse;
    private String[] kirjeldused = {"Ruumis olev koll võrdlemisi väike - mitte suurem kui koer,\nkuid on näha, et tema küünised on pikad ning teravad. \nTa karvastik on räsitud ja silmad jõllitavad sind metsistunult. \n" +
            "Ta uriseb ja vaatamata oma suurusele tekitab sinus kõhedust",
            "Sa sisened ruumi, mis on täis ämblikuvõrke. Sa vaatad üles ja näed seal hiiglaslikku ämblikku!\nTa on vähemalt 8 meetrit suur ning tema 8 silma pole ka just kuigi sõbralkud. \nMõtled endamisi, kas see on karma selle eest, " +
                    "kui tapsid selle väikse ämbliku oma vannitoas.",
            "Märkad ruumis lihakäntsaka kallal järavat libahunti, kes on vähemalt 2 meetrit pikk.\nTa vaatab su poole ja uriseb kurjalt. Tõmbad mõõga vaikselt tupest välja ja ta sööstab rünnakule!",
            "Ruumis näed nurgas magamas väikest valget jänest.\nSinu kolistamine äratab ta üles. Jänesenahast roomab välja aga mitmemeetrine ussilaadne olevus, \nkellel pole küll silmi, kuid on mitu rida teravaid hambaid" +
                    "\nning kes liigub ääretult kiiresti. Võib-olla see jänes ei maganudki?",
            "Uus ruum tundub seest tühi. \nVaatad ringi ning näed vaid eelnevate kangelaste skelette nurgas lebamas. \nKuni siis ühel hetkel... tunned enda ümber jahedat õhku ja keegi sosistab su nime."};

    //Kolli konstruktor
    public Koll(String kolliNimi, int elud, int tugevus, int kaitse) {
        this.kolliNimi = kolliNimi;
        this.elud = elud;
        this.tugevus = tugevus;
        this.kaitse = kaitse;
        this.kirjeldus = genereeriKirjeldus(); //kollile genereeritakse koopaloomes suvaline kirjeldus
    }

    public String genereeriKirjeldus() {
        if (kolliNimi == "BOSS") { //ainult Bossil on kindel kirjeldus
            return "\"Sisened ruumi ning sinu poole sööstab tohutusuur draakon!\\nVeel enne rünnakut jõuad korraks talle pilgu peale heita - ta soomused on metallmustad ning kohati lilla helgiga. \\nTema kuldkollased silmad on täis viha ja enesekindlust.";
        }
        int randIndeks = (int) (Math.random() * kirjeldused.length);
        return kirjeldused[randIndeks];
    }

    /*
    Võitluse meetodi üldidee. Võitluse iga tsükli alguses genereeritakse nii kollile kui ka mängijale tugevus ja kaitse
    vahemikust 1...nendeMaxKaitse/Tugevus. Ehk kui mängija tugevus on näiteks 10, siis tema löögitugevus võib olla suvaline väärtus 1...10
    Sellest lahutatakse Kollile samamoodi suvaliselt genereeritud kaitse (jällegi lubatud vahemikust) ning see vahe määrab ära, kui palju
    koll päriselt saab kannatada. Kui mängija ründetugevus on 7, aga kolli kaitse on 6, siis tegelikult teeb mängija viga ainult 1 eluühikut.
    See põhimõte käib mõlematpidi - nii kollile kui ka mängijale. Kolli maxTugevus ja maxKaitse genereeritakse suvaliselt koopa loomisel
     */
    public boolean ruumiTegevus(Mängija mängija) {
        int käik = 1;
        String paus;
        Scanner sc = new Scanner(System.in);

        System.out.printf("Mängija elud: %d, tugevus: %d, kaitse: %d", mängija.getElud(), mängija.getTugevus(), mängija.getKaitse());
        System.out.printf("\nKoll elud: %d, tugevus: %d, kaitse: %d\n", elud, tugevus, kaitse);
        while (this.elud > 0 || mängija.getElud() > 0) {
            int rünnakKoll = (int) (Math.random() * (tugevus) + 1);
            int rünnakMängija = (int) (Math.random() * (mängija.getTugevus()) + 1);
            int kaitseKoll = (int) (Math.random() * (kaitse) + 1);
            int kaitseMängija = (int) (Math.random() * (mängija.getKaitse()) + 1);
            System.out.printf("\nKäik %d. Koll ründab mängijat! ", käik);
            //Koll ründab,mängija kaitseb
            if (rünnakKoll > kaitseMängija) {
                int rünnakKollTugevus = rünnakKoll - kaitseMängija;
                switch (rünnakKollTugevus) {
                    case 0, 1:
                        System.out.println("\nKoll asub rünnakule, ent komistab kivi otsa ja ainult vaevu riivab sind. Rünnaku tugevus: " + rünnakKollTugevus);
                        break;
                    case 2, 3:
                        System.out.println("\nKoll saab sulle küll pihta, kuid hommikuvõimlemine jäi tegemata ja rünnak ei olnud tugev. Rünnaku tugevus: " + rünnakKollTugevus);
                        break;
                    default:
                        System.out.println("\nKriitiline rünnak! Rünnaku tugevus: "  + rünnakKollTugevus);
                        break;
                }
                mängija.setElud(mängija.getElud() - rünnakKollTugevus); //Uuendame mängija elusid
            } else System.out.println(" Rünnak ebaõnnestus!");
            System.out.print("Kolli käik lõppes\n");
            if (mängija.getElud() <= 0) {
                System.out.println("Sa ei olnud enda parimas vormis ning kahjuks said surma. Paremat edu järgmises elus.");
                break;
            }

            System.out.printf("Mängija elud: %d, Kolli elud: %d\n", mängija.getElud(), elud);

            //Paus käigu vahel -> vajuta "ENTER" et edasi minna
            System.out.println("Edasi minemiseks vajuta 'ENTER'");
            paus = sc.nextLine();

            //Mängija ründab, koll kaitseb
            System.out.printf("Käik %d. Mängija ründab kolli!", käik);
            if (rünnakMängija > kaitseKoll) {
                int rünnakMängijaTugevus = rünnakMängija - kaitseKoll;
                switch (rünnakMängijaTugevus) {
                    case 0, 1:
                        System.out.println("\nAsud rünnakule, kuid sinu tähelepanu hajutab ruumis olev sääsk ning sihid hoopis teda. Rünnakutugevus: " + rünnakMängijaTugevus);
                        break;
                    case 2, 3:
                        System.out.println("\nKäsi vääratab. Saad kollile küll pihta, ent ei tee palju viga. Rünnakutugevus: " + rünnakMängijaTugevus);
                        break;
                    default:
                        System.out.println("\nKõik tähed joondusid selleks üheks hetkeks ning teed kriitiliselt viga! Rünnakutegevus: " + rünnakMängijaTugevus);
                        break;
                }
                elud = elud - rünnakMängijaTugevus; //Uuendame kolli elusid
            } else System.out.println(" Rünnak ebaõnnestus!");
            System.out.print("Mängija käik lõppes\n");
            if (elud <= 0) {
                System.out.println("Koll langeb su rünnaku peale elutuna maha. Ta ei olnud sulle mingi vastane.");
                break;
            }
            System.out.printf("Mängija elud: %d, Kolli elud: %d\n", mängija.getElud(), elud);
            System.out.println("Edasi minemiseks vajuta 'ENTER'");
            paus = sc.nextLine(); //Paus peale mängija käiku -> vajuta "ENTER" et edasi minna
            käik++;
        }
        return mängija.getElud() > 0;
    }

    public void setElud(int elud) {
        this.elud = elud;
    }

    public String getKolliNimi() {
        return kolliNimi;
    }

    @Override
    public void getKirjeldus() {
        System.out.println(kirjeldus);
    }

    public int getElud() {
        return elud;
    }

    public int getTugevus() {
        return tugevus;
    }

    public int getKaitse() {
        return kaitse;
    }

}
