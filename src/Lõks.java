public class Lõks implements ToaTegevus {
    private int vigastab;
    private String kirjeldus; //Lisada mingi hetk
    private String[] kirjeldused = {"Sisened ruumi ja see täitub paksu tossuga, millel on kibe lõhn. Vist mürk.",
            "Niipea, kui astud uksest sisse, kaob pind su jalge alt. Ruumis laiutab suur auk",
            "Sinu poole tuhisevad mõlemalt poolt nooled!",
            "Kuuled kriginat ja vaatad üles. Sinu poole on langemas suur kivi!",
            "Ülevalt näed langemas suurt võrku, millest välja saada tundub võimatu."};

    public Lõks(int vigastab) {
        this.vigastab = vigastab; //vigastuse tugevus 1-3 genereeritakse koopa loomisel suvaliselt
        this.kirjeldus = genereeriKirjeldus();
    }

    public String genereeriKirjeldus() {
        int randIndeks = (int) (Math.random() * kirjeldused.length);
        return kirjeldused[randIndeks];
    }

    //kontrollime, kas lõks vigastas mängijat ja kas mängija jäi ellu
    @Override
    public boolean ruumiTegevus(Mängija mängija) {
        mängija.setElud(mängija.getElud() - vigastab);
        if (vigastab == 0) {
            System.out.println("Õnneks märkasid lõksu varakult ja ei saanud vigastada!\n");
            return true;
        } else {
            if (mängija.getElud() > 0) {
                System.out.printf("Said viga! Kaotasid %d elu, kuid jäid ellu!\n", vigastab);
                return true;
            } else {
                System.out.println("Langesid lõksu ohvriks ja said surma!\n");
                return false;
            }
        }
    }

    @Override
    public void getKirjeldus() {
        System.out.println(kirjeldus);
    }
}
