public class Main {
    public Proceso colaProcesos[] = new Proceso[10];
    public int espacioMemoria = 4; //en GB
    public Proceso particiones[] = new Proceso[128];
    public int fragmentacion = 0; //en MB
    public int numeracionProcesos = 0;
    public int procesos = 0;

    public Main(){
        inicializar_cola();
        System.out.println("**********Particionamiento Fijo de igual Tamaño***********");
        System.out.println("Memoria de 4 GB");
        System.out.println("Se crearán 128 particiones de 32 MB");
        dividir_memoria();
        espera(1);
        int cont=0;
        while(cont<10){

            Proceso P = cargar_de_cola();
            System.out.println("Se carga el primer proceso en cola : " + P.getEtiqueta() + ", " + P.getTamaño() + " MB");
            ubicar_proceso(P);
            espera(0);
            System.out.println("Fragmentacion interna generada: " + fragmentacion + " MB");
            System.out.println("Procesos en ejecución: ");
            for(int i=0; i<procesos; i++){
                System.out.println(particiones[i].getEtiqueta() + ", " + particiones[i].getTamaño() + " MB");
            }
            espera(1);
            if(procesos == 5) {
                P = particiones[(int) (Math.random() * procesos)];
                System.out.println("El proceso: " + P.getEtiqueta() + " a terminado de ejecutarse");
                eliminar_proceso(P.getEtiqueta());
                espera(1);
            }
            cont++;
        }
    }
    public void espera(int t){
        try{
            if(t == 0) {       //espera corta
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(400);
                    System.out.print(".");
                }
                System.out.println("");
            }else{             //espera larga
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(800);
                    System.out.print(".");
                }
                System.out.println("");
            }
        }catch(Exception e){}
    }

    public void dividir_memoria(){
        for(int i=0; i<128; i++){
            particiones[i] = null;
        }
    }

    public void ubicar_proceso(Proceso P){
        for(int i=0; i<particiones.length; i++){
            if(particiones[i] == null){
                particiones[i] = P;
                fragmentacion = fragmentacion + (32-P.getTamaño());
                procesos++;
                break;
            }
        }
    }

    public void eliminar_proceso(String etiqueta){
        for(int i=0; i<particiones.length; i++){
            if(particiones[i].getEtiqueta().equals(etiqueta)){
                fragmentacion = fragmentacion - particiones[i].getTamaño();
                particiones[i] = null;
                procesos--;
                break;
            }
        }
    }

    public void inicializar_cola(){
        for(int i=0; i<10; i++){
            colaProcesos[i] = new Proceso("P"+numeracionProcesos, (int) (Math.random() * 31) + 1);
            numeracionProcesos++;
        }
    }

    public Proceso cargar_de_cola() {
        if (hay_en_cola()) {
            Proceso P = colaProcesos[0];
            correr_cola();
            return P;
        }

        return null;
    }

    public boolean hay_en_cola(){
        if(colaProcesos[0] != null){
            return true;
        }else{
            return false;
        }
    }

    public void correr_cola() {
        for (int i = 1; i < (colaProcesos.length + 1); i++) {
            try {
                colaProcesos[i - 1] = colaProcesos[i];
            } catch (Exception e) {
            }
        }
    }

    public static void main(String args[]){ new Main();}
}
