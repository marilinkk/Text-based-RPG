public class Abistaja implements ToaTegevus {
    private String kirjeldus; //koopa loomiseks
    private String[] abivahendid = {"ravijook", "mõõk", "kaart", "kilp"};
    private String abivahend;

    //ravijook (+1 HP), mõõk (+1 strength), soovitus (mis järgmise taseme ruumides on), kilp (+1 defence)

    public Abistaja(String kirjeldus) {
        this.kirjeldus = kirjeldus;
        this.abivahend = genereeriAbivahend(); //genereerib abilisele suvalise abivahendi
    }

    public String genereeriAbivahend() {
        int randIndeks = (int) (Math.random() * abivahendid.length);
        return abivahendid[randIndeks];
    }

    @Override
    public void getKirjeldus() {
        System.out.println(kirjeldus);
    }

    public String getAbivahend() {
        return abivahend;
    }

    //Annab mängijale abivahendi
    @Override
    public boolean ruumiTegevus(Mängija mängija) {
        switch (abivahend) {
            case "ravijook":
                mängija.setElud(mängija.getElud() + 1);
                System.out.println("Ruumis elab ravitseja, kes on sinust sisse võetud. Ta ravib su haavad. \nMängija elud on nüüd: " + mängija.getElud());
                break;
            case "mõõk":
                mängija.setTugevus(mängija.getTugevus() + 2);
                System.out.println("Abistajale meeldivad su lood vaprusest. Ta kingib sulle mõõga, mis on tema perekonnas olnud generatsioone. \nMängija tugevus on nüüd: " + mängija.getTugevus());
                break;
            case "kilp":
                mängija.setKaitse(mängija.getKaitse() + 2);
                System.out.println("Abistaja hindab su julgust, kuid kingib sulle siiski oma kilbi. \nMängija kaitse on nüüd: " + mängija.getKaitse());
                break;
            case "kaart":
                System.out.println("Lahke abistaja annab sulle kaardi, millel on osad kohad kulunud. \nNäha on vaid järgmise taseme ruumid:");

                for (int i = 0; i < mängija.getKaart()[mängija.getTase() + 1].length; i++) {
                    System.out.printf("%d. %s\n", i+1, mängija.getKaart()[mängija.getTase() + 1][i]);
                }
                break;
            default:
                System.out.println("Mängijale ei antud midagi");
        }
        return true;
    }
}
