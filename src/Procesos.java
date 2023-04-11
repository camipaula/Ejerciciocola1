public class Procesos {
private String id;
private int cedula;
private int tiempo;


public Procesos(String id,int cedula, int tiempo){
    this.id=id;
    this.cedula=cedula;
    this.tiempo=tiempo;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setId(int cedula) {
        this.cedula = cedula;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Numero de proceso: "+ id+"\ncedula: "+cedula+"\ntiempo: "+ tiempo ;
    }
}
