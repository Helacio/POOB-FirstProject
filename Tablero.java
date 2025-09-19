public class Tablero {
    private static final int n = 15;
    private int[][] camino;
    private int tamañoCelda = 40; // pixeles

    public Tablero() {
        this.camino = RutaDeSeda.generarEspiral();
    }

    // Crea y pinta la ruta de seda de la longitud deseada
    public void crearRutaDeSeda(int longitud) {
        if (longitud > n * n) {
            longitud = n * n; // límite máximo
        }

        for (int k = 1; k <= longitud; k++) {
            int[] pos = buscarPosicion(k);
            int fila = pos[0];
            int col = pos[1];

            Rectangle celda = new Rectangle();
            celda.changeSize(tamañoCelda, tamañoCelda);
            celda.moveHorizontal(col * tamañoCelda);
            celda.moveVertical(fila * tamañoCelda);
            celda.changeColor("blue");
            celda.makeVisible();
        }
    }

    // Encuentrar la fila y columna de una celda dada
    private int[] buscarPosicion(int k) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (camino[i][j] == k) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
