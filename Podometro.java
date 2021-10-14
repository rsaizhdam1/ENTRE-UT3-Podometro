/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author  Rub�n Saiz 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;    
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        if (queSexo == HOMBRE|| queSexo == MUJER) {
            sexo = queSexo;
            altura = queAltura;
            if (sexo == HOMBRE){
                longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
            }
            else{
                longitudZancada = Math.floor(altura * ZANCADA_MUJER);
            }
        }
        
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        //Saca �nicamente la hora de inicio
        int soloHoraInicio = (int) Math.floor(horaInicio / 100);
        //Pasa las horas a minutos y lo suma junto a los minutos de la hora de inicio
        int minutosInicio = (soloHoraInicio * 60) + horaInicio % 100;
        //Saca �nicamente la hora de fin
        int soloHoraFin = (int) Math.floor(horaFin / 100);
        //Pasa las horas a minutos y lo suma junto a los minutos de la hora de fin
        int minutosFin = (soloHoraFin * 60) + horaFin % 100;
        //Resta los minutos de fin y de inicio para sacar los minutos que dura el paseo.
        tiempo += minutosFin - minutosInicio;
        totalDistanciaSemana += (pasos * longitudZancada) / 100000;
        switch (dia){
            case 1: 
                totalPasosLaborables += pasos;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada) / 100000; 
                break;
            case 7: 
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada) / 100000;
                break;
            default:
                break;
        }
        if (horaInicio >= 2100) {
            caminatasNoche++;
        }
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuraci�n del pod�metro\n*********************************");
        System.out.println("Altura: " + altura / 100 + " mtos\n" + "Sexo: " + sexo +"\nLongitud zancada: " + 
        longitudZancada / 100 + " mtos");
        
        
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadisticas() {    
        System.out.println("Estad�sticas\n*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("");
        System.out.println("N� pasos d�as laborables: " + totalPasosLaborables);
        System.out.println("N� pasos S�BADO: " + totalPasosSabado);
        System.out.println("N� pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("N� caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: " + (int) Math.floor(tiempo / 60) + "h. y " + tiempo % 60 + "m.");
        System.out.println("D�a/s con m�s pasos caminados: " + diaMayorNumeroPasos());
    } 
    
    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String resultado = "";
        if (totalPasosLaborables >= totalPasosSabado && totalPasosLaborables >= totalPasosDomingo) {
            resultado += "LABORABLES ";
        }
        if (totalPasosSabado >= totalPasosLaborables && totalPasosSabado >= totalPasosDomingo) {
            resultado += "S�BADOS ";
        }
        if (totalPasosDomingo >= totalPasosLaborables && totalPasosDomingo >= totalPasosSabado) {
            resultado += "DOMINGO ";
        }
        return resultado;
    }
    
    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
