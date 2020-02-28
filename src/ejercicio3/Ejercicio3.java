package ejercicio3;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ejercicio3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numDirecciones;
        String dir;

        //Solicitamos el numero de direcciones que quiere guardar
        System.out.print("Cuantas direcciones quieres almacenar: ");
        numDirecciones = sc.nextInt();
        sc.nextLine();//bug de lectura

        //Generamos una lista con las direcciones introducidas por el usuario
        ArrayList<String> listaDirecciones = new ArrayList<String>();
        System.out.println("--Introduce direccion--\n" +
                          "Ejemplo: https://www.github.com");
        for (int i = 0; i < numDirecciones; i++){
            System.out.print("Direccion " + (i+1) + ": ");
            dir = sc.nextLine();
            listaDirecciones.add(dir); //Añadimos a la lista las direcciones
        }

        //Añadimos el path donde iran los archivos procesados, corrigiendo las separaciones entre carpetas. Y generamos una carpeta si la incluye.
        System.out.println("En que path te gustaria guardar los archivos resultantes( Ejemplo -> C:/Users/PC-Casa/Desktop/misArchivosWeb ): ");
        String path = sc.nextLine();
        path = path.replace("/", File.separator);
        File generarCarpeta = new File(path);
        generarCarpeta.mkdir();

        //Llamamos al executor service para que se encargue de gestionar los hilos.
        ExecutorService executorService = Executors.newFixedThreadPool(numDirecciones);
        //Añadimos tantos hilos como direcciones haya introducido el usuario
        for(String direccion : listaDirecciones) {
            Runnable r = new URL_Peticion(direccion, path);
            executorService.execute(r);
        }
        //Una vez acabamos cerramos el executor service
        executorService.shutdown();
    }

}