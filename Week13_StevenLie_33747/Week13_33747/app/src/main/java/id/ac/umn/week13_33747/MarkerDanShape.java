package id.ac.umn.week13_33747;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MarkerDanShape {
    private String jenis = "Marker";
    private String keterangan = "";
    private double radius = 0.0;
    private List<LatLng> titiks = new ArrayList<LatLng>();
    private Object objekPeta = null;

    public MarkerDanShape(String jenis, String keterangan, double radius, List<LatLng> titiks) {
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.radius = radius;
        this.titiks = new ArrayList<LatLng>();
        this.titiks.addAll(titiks);
    }

    public String getJenis() {
        return jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public List<LatLng> getTitiks() {
        return titiks;
    }
    public int getJumlahTitiks(){
        return this.titiks.size();
    }
    public void setTitiks(List<LatLng> titiks) {
        this.titiks = titiks;
    }

    public Object getObjekPeta() {
        return objekPeta;
    }

    public void setObjekPeta(Object objekPeta) {
        this.objekPeta = objekPeta;
    }
}
