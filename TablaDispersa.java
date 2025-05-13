
public class TablaDispersa {

    private Tarea[] tabla;
    private int numElementos;
    private double factorCarga;
    private static final int TAMANIO = 101;
    

    public TablaDispersa() {
        this.tabla = new Tarea[TAMANIO];
        this.numElementos = 0;
        this.factorCarga = 0.0;
    }

    public boolean insertar(Tarea t) {
        if (t == null || numElementos >= TAMANIO)
            return false;

        int posicion = calcularPosicion(t.getId());
        int i = 0;

        while (tabla[posicion] != null && !tabla[posicion].getId().equals(t.getId())) {//equals compara el contenido de los objetos
            i++;
            posicion = resolverColision(posicion, i);
        }

        tabla[posicion] = t;
        numElementos++;
        calcularFactorCarga();
        return true;
    }

    public Tarea buscar(String id) {
        int posicion = calcularPosicion(id);
        int i = 0;

        while (tabla[posicion] != null && i < TAMANIO) {
            if (tabla[posicion].getId().equals(id))
                return tabla[posicion];
            i++;
            posicion = resolverColision(posicion, i);
        }

        return null;
    }

    public boolean eliminar(String id) {
        int posicion = calcularPosicion(id);
        int i = 0;

        while (tabla[posicion] != null && i < TAMANIO) {
            if (tabla[posicion].getId().equals(id)) {
                tabla[posicion].setEsAlta(false);
                numElementos--;
                calcularFactorCarga();
                return true;
            }
            i++;
            posicion = resolverColision(posicion, i);
        }

        return false;
    }

    public int calcularPosicion(String id) {
        double A = 0.6180339887; 
        double valor = obtenerValorNumerico(id);
        double producto = valor * A;
        double decimal = producto - Math.floor(producto);
        return (int) (decimal * TAMANIO);
    }

    private double obtenerValorNumerico(String id) {
        long valor = 0;
        for (int i = 0; i < id.length(); i++) {
            valor += id.charAt(i) * Math.pow(31, i); //31 es un número primo comunmente usado en tablas hash
            //charAt(i) devuelve el carácter en una posición específica de una cadena.
        }
        return valor;
    }

    public int resolverColision(int posicionInicial, int i) {
        return (posicionInicial + i * i) % TAMANIO;
    }

    public double calcularFactorCarga() {
        this.factorCarga = (double) numElementos / TAMANIO;
        return this.factorCarga;
    }

    public void mostrarTareas() {
        System.out.println("TAREAS ACTIVAS:");
        for (Tarea t : tabla) {
            if (t != null && t.getEsAlta()) {
                System.out.println(t);
            }
        }
    }

    
}
