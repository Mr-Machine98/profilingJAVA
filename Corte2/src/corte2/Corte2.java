/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corte2;

import java.util.Random;

/*
* Corte 2
*
* Métricas de software
*
* Autores: JUAN CAMILO MAMIAN RUIZ, ALDEMAR BERNAL HERNANDEZ
*
* UNIVERSIDAD LIBRE
*
 */
public class Corte2 {

    static Random Azar = new Random();

    /**
     * @param args the command line arguments
     */
    //Método Main
    public static void main(String[] args) {
        // TODO code application logic here
        int numPruebas = 10;

        for (int numElemento = 80000; numElemento <= 80000; numElemento += 1000) {
            MitoOrdena(numElemento, numPruebas);
        }
    }

    //Todos los algoritmos de ordenamiento
    public static void MitoOrdena(int Limite, int TotalPruebas) {

        //Arreglos 
        int[] numerosA = new int[Limite];
        int[] numerosB = new int[Limite];

        long TPShell = 0, TPIns = 0, TPSel = 0, TPBur = 0, TPQuick = 0;
        long valor = 0;
        long inicio = 0, fin = 0;

        //Para disminuir oscilaciones en el tiempo, se hacen
        //N pruebas con cada grupo de pruebas
        for (int prueba = 1; prueba <= 1; prueba++) {

            LlenaAzar(numerosA);

            //Ordenación por método Shell
            System.arraycopy(numerosA, 0, numerosB, 0, numerosA.length);
            inicio = System.currentTimeMillis();
            Shell(numerosB);
            fin = System.currentTimeMillis();
            TPShell += fin - inicio;
            valor += numerosB[0];

            //Reset
            inicio = 0;
            fin = 0;

            //Ordenación por método Inserción
            System.arraycopy(numerosA, 0, numerosB, 0, numerosA.length);
            inicio = System.currentTimeMillis();
            Insercion(numerosB);
            fin = System.currentTimeMillis();
            TPIns += fin - inicio;
            valor += numerosB[0];

            //Reset
            inicio = 0;
            fin = 0;

            //Ordenación por método Selección
            System.arraycopy(numerosA, 0, numerosB, 0, numerosA.length);
            inicio = System.currentTimeMillis();
            Seleccion(numerosB);
            fin = System.currentTimeMillis();
            TPSel += fin - inicio;
            valor += numerosB[0];

            //Reset
            inicio = 0;
            fin = 0;

            //Ordenación por método Burbuja
            System.arraycopy(numerosA, 0, numerosB, 0, numerosA.length);
            inicio = System.currentTimeMillis();
            Burbuja(numerosB);
            fin = System.currentTimeMillis();
            TPBur += fin - inicio;
            valor += numerosB[0];

            //Reset
            inicio = 0;
            fin = 0;

            //Ordenación por método QuickSort
            System.arraycopy(numerosA, 0, numerosB, 0, numerosA.length);
            inicio = System.currentTimeMillis();
            QuickSort(numerosB, 0, numerosB.length - 1);
            fin = System.currentTimeMillis();
            TPQuick += fin - inicio;
            valor += numerosB[0];
            
            
        }

        double TS = (double) TPShell;
        double TI = (double) TPIns;
        double TL = (double) TPSel;
        double TB = (double) TPBur;
        double TQ = (double) TPQuick;

        String CSV = "#Datos: " + Integer.toString(Limite) + " Shell: " + Double.toString(TS) + " Insert: " + Double.toString(TI) + " Select: " + Double.toString(TL) + " Burb: " + Double.toString(TB) + " Quick: " + Double.toString(TQ);
        CSV = CSV.replace('.', ',');
        System.out.print(CSV);
        System.out.println(" val: " + Long.toString(valor));
    }

    //Llena el arreglo unidimensional con valores aleatorios
    public static void LlenaAzar(int[] numerosA) {
        for (int cont = 0; cont < numerosA.length; cont++) {
            numerosA[cont] = Azar.nextInt(10000);
        }
    }

    //Ordenamientos Insert
    public static void Insercion(int[] arreglo) {
        int tmp;
        int j;
        for (int i = 1; i < arreglo.length; i++) {
            tmp = arreglo[i];
            for (j = i - 1; j >= 0 && arreglo[j] > tmp; j--) {
                arreglo[j + 1] = arreglo[j];
            }
            arreglo[j + 1] = tmp;
        }
    }

    //Ordenamiento por Selección
    public static void Seleccion(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j] < arreglo[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int aux = arreglo[i];
                arreglo[i] = arreglo[min];
                arreglo[min] = aux;
            }
        }
    }

    //Ordenamiento por Burbuja
    public static void Burbuja(int[] Arreglo) {
        int n = Arreglo.length;
        int tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (Arreglo[j] > Arreglo[j + 1]) {
                    tmp = Arreglo[j];
                    Arreglo[j] = Arreglo[j + 1];
                    Arreglo[j + 1] = tmp;
                }
            }
        }
    }

    //Ordenamiento por Shell
    public static void Shell(int[] arreglo) {
        int N = arreglo.length;
        int incremento = N;
        do {
            incremento = incremento / 2;
            for (int k = 0; k < incremento; k++) {
                for (int i = incremento + k; i < N; i += incremento) {
                    int j = i;
                    while (j - incremento >= 0 && arreglo[j] < arreglo[j - incremento]) {
                        int tmp = arreglo[j];
                        arreglo[j] = arreglo[j - incremento];
                        arreglo[j - incremento] = tmp;
                        j -= incremento;
                    }
                }
            }
        } while (incremento > 1);
    }

    //Ordenación por QuickSort
    public static void QuickSort(int[] vector, int primero, int ultimo) {
        int i, j, central;
        double pivote;
        central = (primero + ultimo) / 2;
        pivote = vector[central];
        i = primero;
        j = ultimo;
        do {
            while (vector[i] < pivote) {
                i++;
            }
            while (vector[j] > pivote) {
                j--;
            }
            if (i <= j) {
                int temp;
                temp = vector[i];
                vector[i] = vector[j];
                vector[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (primero < j) {
            QuickSort(vector, primero, j);
        }
        if (i < ultimo) {
            QuickSort(vector, i, ultimo);
        }
    }

}
