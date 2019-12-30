package com.example.yemektarifleriapp;

public class Tarif {
    private  String yemek_isim,yemek_tur,yemek_icerik,yemek_paylasan,yemek_hazir;

    public Tarif(String yemek_isim, String yemek_tur, String yemek_icerik, String yemek_paylasan, String yemek_hazir) {
        this.yemek_isim = yemek_isim;
        this.yemek_tur = yemek_tur;
        this.yemek_icerik = yemek_icerik;
        this.yemek_paylasan = yemek_paylasan;
        this.yemek_hazir = yemek_hazir;
    }

    public String getYemek_isim() {
        return yemek_isim;
    }

    public void setYemek_isim(String yemek_isim) {
        this.yemek_isim = yemek_isim;
    }

    public String getYemek_tur() {
        return yemek_tur;
    }

    public void setYemek_tur(String yemek_tur) {
        this.yemek_tur = yemek_tur;
    }

    public String getYemek_icerik() {
        return yemek_icerik;
    }

    public void setYemek_icerik(String yemek_icerik) {
        this.yemek_icerik = yemek_icerik;
    }

    public String getYemek_paylasan() {
        return yemek_paylasan;
    }

    public void setYemek_paylasan(String yemek_paylasan) {
        this.yemek_paylasan = yemek_paylasan;
    }

    public String getYemek_hazir() {
        return yemek_hazir;
    }

    public void setYemek_hazir(String yemek_hazir) {
        this.yemek_hazir = yemek_hazir;
    }
}
