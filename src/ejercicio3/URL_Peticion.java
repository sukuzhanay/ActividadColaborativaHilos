package ejercicio3;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class URL_Peticion implements Runnable{

    private String direccion;
    private String path;

    public URL_Peticion(String direccion, String path) {
        this.direccion=direccion;
        this.path=path;
    }

    //Implementamos el metodo run con las tareas que queremos que haga
    @Override
    public void run() {
        procesarDireccion();
    }

    //Metodo para procesar el contenido de la pagina web y almacenarlo en una direccion indicada
    public void procesarDireccion(){
        try {
            System.out.println("Procesando direccion " + Thread.currentThread().getName());
            //Generamos un archivo mas limpio con terminacion html
            String nombreArchivo = direccion.substring(direccion.indexOf('.')+1, direccion.indexOf(".com"));
            path = path.replace(" ", "");
            String fullPath = this.path + File.separator + nombreArchivo + ".html";

            //Leemos el contenido del html de la pagina web y lo guardamos en la direccion indicada anteriormente
            InputStream in = new URL(this.direccion).openStream();
            Files.copy(in, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Terminado de procesar " + Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}//Checked
