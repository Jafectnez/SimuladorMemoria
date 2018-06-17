public class Proceso{
    String etiqueta;
    int tamaño; //en MB

    public Proceso(){
        etiqueta = "";
        tamaño = 0;
    }

    public Proceso(String eti, int tam){
        etiqueta = eti;
        tamaño = tam;
    }

    public String getEtiqueta(){
        return etiqueta;
    }

    public void setEtiqueta(String eti){
        etiqueta = eti;
    }

    public int getTamaño(){
        return tamaño;
    }

    public void setTamaño(int tam){
        tamaño = tam;
    }
}
