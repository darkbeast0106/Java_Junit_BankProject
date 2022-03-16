package hu.petrik.bankproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private List<Szamla> szamlaList;
    public Bank(){
        szamlaList = new ArrayList<>();
    }
    // Egy létező számlára pénzt helyez
    public void egyenlegFeltolt(String szamlaszam, long osszeg)
    {
        Szamla sz = szamlaList.stream()
                .filter(szamla -> szamla.szamlaszam.equals(szamlaszam)).findFirst().get();
        long ujEgyenleg = sz.getEgyenleg() + osszeg;
        sz.setEgyenleg(ujEgyenleg);
    }

    // Új számlát nyit a megadott névvel, számlaszámmal
    public void ujSzamla(String nev, String szamlaszam)
    {
        if(szamlaList.stream()
                .anyMatch(szamla -> szamla.szamlaszam.equals(szamlaszam))){
            throw new IllegalArgumentException("A megadott számlaszámmal már létezik számla");
        }
        Szamla sz = new Szamla(nev, szamlaszam);
        szamlaList.add(sz);
    }

    // Két számla között utal.
    // Ha nincs elég pénz a forrás számlán, akkor
    public boolean utal(String honnan, String hova, long osszeg)
    {
        throw new UnsupportedOperationException();
    }

    // Lekérdezi az adott számlán lévő pénzösszeget
    public long egyenleg(String szamlaszam)
    {
        Optional<Szamla> optionalSzamla = szamlaList.stream()
                .filter(szamla -> szamla.szamlaszam.equals(szamlaszam)).findFirst();
        if (optionalSzamla.isEmpty()){
            throw new HibasSzamlaszamException(szamlaszam);
        }
        Szamla sz = optionalSzamla.get();
        return sz.getEgyenleg();
    }

    private class Szamla {
        private String tulajdonos;
        private String szamlaszam;
        private long egyenleg;

        public Szamla(String tulajdonos, String szamlaszam) {
            this.tulajdonos = tulajdonos;
            this.szamlaszam = szamlaszam;
            this.egyenleg = 0;
        }

        public String getTulajdonos() {
            return tulajdonos;
        }

        public void setTulajdonos(String tulajdonos) {
            this.tulajdonos = tulajdonos;
        }

        public String getSzamlaszam() {
            return szamlaszam;
        }

        public long getEgyenleg() {
            return egyenleg;
        }

        public void setEgyenleg(long egyenleg) {
            this.egyenleg = egyenleg;
        }
    }
}
