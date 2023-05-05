import java.util.ArrayList;
import java.util.List;

public class Mängija {
    private String nimi;
    private int elud;
    private int tugevus;
    private int kaitse;
    private int tase = 0; //mängija asukoht
    private int ruum = 0;//mängija asukoht
    private List<String> abivahendid = new ArrayList<>();

    //Mängijale määratud kaart. Mängija ise seda ei näe
    private String[][] kaart = {{"Koll", "Abistaja", "Koll", "Abistaja"},
            {"Koll", "Võlur", "Abistaja"},
            {"Koll", "Võlur", "Abistaja"},
            {"Koll", "Lõks", "Koll", "Abistaja"},
            {"Koll", "Võlur", "Abistaja"},
            {"Abistaja", "Koll", "Koll", "Võlur"},
            {"Koll", "Võlur", "Abistaja"},
            {"Abistaja", "Koll", "Koll", "Võlur"},
            {"Lõks", "Abistaja", "Võlur"},
            {"Koll", "Lõks", "Koll", "Abistaja"},
            {"Lõks", "Abistaja", "Võlur"},
            {"Koll", "Lõks", "Koll", "Abistaja"},
            {"Lõks", "Abistaja", "Võlur"},
            {"Abistaja", "Koll", "Koll", "Võlur"},
            {"Koll", "Lõks", "Koll", "Abistaja"},
            {"Lõks", "Abistaja", "Võlur"},
            {"Koll", "Lõks", "Koll", "Abistaja"},
            {"Abistaja", "Abistaja"},
            {"Boss"},
            {"Lõpp"}};

    //Mängija konstruktor
    public Mängija(String nimi, int elud, int tugevus, int kaitse) {
        this.nimi = nimi;
        this.elud = elud;
        this.tugevus = tugevus;
        this.kaitse = kaitse;
    }

    //mängija getterid ja setterid
    public String getNimi() {
        return nimi;
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

    public void setElud(int elud) {
        this.elud = elud;
    }

    public void setTugevus(int tugevus) {
        this.tugevus = tugevus;
    }

    public void setKaitse(int kaitse) {
        this.kaitse = kaitse;
    }

    public int getTase() {
        return tase;
    }

    public void setTase(int tase) {
        this.tase = tase;
    }

    public String[][] getKaart() {
        return kaart;
    }

    public int getRuum() {
        return ruum;
    }

    public void setRuum(int ruum) {
        this.ruum = ruum;
    }

    public void väljastaAbivahend() {
        for (String abivahend : abivahendid) {
            System.out.println(abivahend);
        }
    }
}
