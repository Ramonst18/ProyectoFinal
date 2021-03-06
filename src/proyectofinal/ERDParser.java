/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author rnavarro
 */
public class ERDParser {
    
    private String direccion;
    
    private ArrayList<String> keysEntidades = new ArrayList<>();
    
    public ArrayList<String> keysEntidades(){
        return keysEntidades;
    }
    
    private ArrayList<String> keysEntidadesDebiles = new ArrayList<>();
    
    private ArrayList<String> relaciones = new ArrayList<>();
    
    public ArrayList<String> getRelaciones(){
        return this.relaciones;
    }
    
    public ArrayList<String> keysEntidadesDebiles(){
        return keysEntidadesDebiles;
    }
    
    public ERDParser(String direccion){
        this.direccion = direccion;
    }
    
    public Hashtable<String,ArrayList> crearHashEntidades(){
        Hashtable<String,ArrayList> hash = new Hashtable<>();
        FileReader fp = null;
        try{
            fp = new FileReader(this.direccion);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
        // Crear el tokenizador del documento JSON  
        JSONTokener tokenizer = new JSONTokener(fp);

        // Objeto principal que contiene todos los componentes
        // del diagrama ERD
        JSONObject JSONDoc = new JSONObject(tokenizer);

        //Obtenet los nombres de los objetos 
        JSONArray names = JSONDoc.names();
        System.out.println(names);

        // Solo recuperar los objetos que describen entidades
        JSONArray entidades = JSONDoc.getJSONArray("entidades");
        System.out.println(entidades);

        Iterator it = entidades.iterator();

        // Procesar cada una de las entidades
        while (it.hasNext()) {
            
            ArrayList<Atributos> atributosEntidades = new ArrayList();
            
            JSONObject entidad = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad, mostrar su nombre
            String entityName = entidad.getString("nombre");
            System.out.println(entityName);
            
            //como es primaria se le añadira un & al name
            this.keysEntidades.add(entityName);
            
            // Para cada entidad, mostrar los atributos
            JSONArray atributos = entidad.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();
            
            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                
                String atributoTipo = atributo.getString("nombre");
                
                Atributos entidadConAtribos = null;
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1://llave primaria
                        System.out.println(" **");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, true);
                        break;
                    case 2://llave forania
                        System.out.println(" *");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, false);
                        entidadConAtribos.setForeign_key(true);
                        break;
                    default://atributo comun
                        System.out.println("");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, false);
                        break;
                }
                
                //agregamos el atributo al array
                atributosEntidades.add(entidadConAtribos);
            }
            
            //agregamos los datos al hash
            hash.put(entityName, atributosEntidades);
        }

        // Solo recuperar los objetos que describen entidades
        JSONArray relations = JSONDoc.getJSONArray("relaciones");

        System.out.println("**** RELACIONES ****");

        it = relations.iterator();

        while (it.hasNext()) {
            JSONObject rel = (JSONObject) it.next();

            //System.out.println( rel );
            //System.out.println( rel.names());
            System.out.println(rel.getString("nombre"));

            JSONArray cards = rel.getJSONArray("cardinalidades");

            int n = cards.length();

            
            String R1 = "";
            String R2 = "";
            for (int i = 0; i < n; i++) {
                JSONObject e1 = cards.getJSONObject(i);

                System.out.printf("\t%s (%s,%s)\n", e1.getString("entidad"),
                        e1.getString("min"),
                        e1.getString("max"));

                //preguntamos por las relaciones
                if (i == 0) {//si es la primera
                    R1 = e1.getString("entidad") + " " + e1.getString("max");
                }else{
                    R2 = e1.getString("entidad") + " " + e1.getString("max");
                }
            }

            //metemos la relacion en el array
            this.relaciones.add(R1 + " " + R2);
        }
        return hash;
    }
    
    public Hashtable<String,ArrayList> crearHashEntidadesDebiles(){
        Hashtable<String,ArrayList> hash = new Hashtable<>();
        FileReader fp = null;
        try{
            fp = new FileReader(this.direccion);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
        // Crear el tokenizador del documento JSON  
        JSONTokener tokenizer = new JSONTokener(fp);

        // Objeto principal que contiene todos los componentes
        // del diagrama ERD
        JSONObject JSONDoc = new JSONObject(tokenizer);

        //Obtenet los nombres de los objetos 
        JSONArray names = JSONDoc.names();
        System.out.println(names);

        //recuperamos las entidades debiles
        JSONArray relationsDebiles = JSONDoc.getJSONArray("debiles");
        
        //buascar fuerte para obtener la entidad relacionada
        for (Object debiles : relationsDebiles) {
            System.out.println(debiles.toString());
        }
        

        Iterator it = relationsDebiles.iterator();

        //sacamos las entidades debiles
        while (it.hasNext()) {
            System.out.println("**** Entidades debiles ****");
            ArrayList<Atributos> atributosEntidades = new ArrayList();
            
            JSONObject entidadDebil = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad debil, mostrar su nombre
            String entityName = entidadDebil.getString("nombre");
            System.out.println(entityName);

            this.keysEntidadesDebiles.add(entityName);
            
            // Para cada entidad debil, mostrar los atributos
            JSONArray atributos = entidadDebil.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();

            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                String atributoTipo = atributo.getString("nombre");
                
                Atributos entidadConAtribos = null;
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1://llave primaria
                        System.out.println(" **");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, true);
                        break;
                    case 2://llave forania
                        System.out.println(" *");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, false);
                        break;
                    default://atributo comun
                        System.out.println("");
                        entidadConAtribos = new Atributos(atributoTipo, null, null, null, false, false);
                        break;
                }
                
                atributosEntidades.add(entidadConAtribos);
            }
            hash.put(entityName, atributosEntidades);

        }
        return hash;
    }
}
