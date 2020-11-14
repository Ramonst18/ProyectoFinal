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
    
    private ArrayList<String> keys = new ArrayList<>();
    
    public ArrayList<String> keys(){
        return keys;
    }
    
    public ERDParser(String direccion){
        this.direccion = direccion;
    }
    
    public Hashtable<String,ArrayList> crearHash(){
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
            
            ArrayList<String> atributosEntidades = new ArrayList();
            
            JSONObject entidad = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad, mostrar su nombre
            String entityName = entidad.getString("nombre");
            System.out.println(entityName);
            
            keys.add(entityName);
            
            // Para cada entidad, mostrar los atributos
            JSONArray atributos = entidad.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();

            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                
                String atributoTipo = atributo.getString("nombre");
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1://llave primaria
                        System.out.println(" **");
                        atributoTipo += "**";
                        break;
                    case 2://llave forania
                        System.out.println(" *");
                        atributoTipo += "*";
                        break;
                    default://atributo comun
                        System.out.println("");
                        break;
                }
                
                //agregamos el atributo al array
                atributosEntidades.add(atributoTipo);
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

            for (int i = 0; i < n; i++) {
                JSONObject e1 = cards.getJSONObject(i);

                System.out.printf("\t%s (%s,%s)\n", e1.getString("entidad"),
                        e1.getString("min"),
                        e1.getString("max"));

            }

        }

        //recuperamos las entidades debiles
        JSONArray relationsDebiles = JSONDoc.getJSONArray("debiles");
        
        //buascar fuerte para obtener la entidad relacionada
        for (Object debiles : relationsDebiles) {
            System.out.println(debiles.toString());
        }
        

        it = relationsDebiles.iterator();

        //sacamos las entidades debiles
        while (it.hasNext()) {
            System.out.println("**** Entidades debiles ****");
            ArrayList<String> atributosEntidades = new ArrayList();
            
            JSONObject entidadDebil = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad debil, mostrar su nombre
            String entityName = entidadDebil.getString("nombre");
            System.out.println(entityName);

            keys.add(entityName);
            
            // Para cada entidad debil, mostrar los atributos
            JSONArray atributos = entidadDebil.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();

            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                String atributoTipo = atributo.getString("nombre");
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1:
                        System.out.println(" **");
                        break;
                    case 2:
                        System.out.println(" *");
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                
                atributosEntidades.add(atributoTipo);
            }
            hash.put(entityName, atributosEntidades);

        }
        
        return hash;
    }
    /*
    public static void main(String[] args) throws FileNotFoundException {
        
        Hashtable<String,ArrayList> hash = new Hashtable<>();
        
        FileReader fp = new FileReader("Prueba.json");

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
            
            ArrayList atributosEntidades = new ArrayList();
            
            JSONObject entidad = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad, mostrar su nombre
            String entityName = entidad.getString("nombre");
            System.out.println(entityName);
            
            
            
            // Para cada entidad, mostrar los atributos
            JSONArray atributos = entidad.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();

            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                
                String atributoTipo = atributo.getString("nombre");
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1://llave primaria
                        System.out.println(" **");
                        atributoTipo += "**";
                        break;
                    case 2://llave forania
                        System.out.println(" *");
                        atributoTipo += "*";
                        break;
                    default://atributo comun
                        System.out.println("");
                        break;
                }
                
                //agregamos el atributo al array
                atributosEntidades.add(atributoTipo);
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

            for (int i = 0; i < n; i++) {
                JSONObject e1 = cards.getJSONObject(i);

                System.out.printf("\t%s (%s,%s)\n", e1.getString("entidad"),
                        e1.getString("min"),
                        e1.getString("max"));

            }

        }

        //recuperamos las entidades debiles
        JSONArray relationsDebiles = JSONDoc.getJSONArray("debiles");
        
        //buascar fuerte para obtener la entidad relacionada
        for (Object debiles : relationsDebiles) {
            System.out.println(debiles.toString());
        }
        System.out.println("**** Entidades debiles ****");

        it = relationsDebiles.iterator();

        //sacamos las entidades debiles
        while (it.hasNext()) {
            
            ArrayList atributosEntidades = new ArrayList();
            
            JSONObject entidadDebil = (JSONObject) it.next();

            //names = entidad.names();
            // Para cada entidad debil, mostrar su nombre
            String entityName = entidadDebil.getString("nombre");
            System.out.println(entityName);

            // Para cada entidad debil, mostrar los atributos
            JSONArray atributos = entidadDebil.getJSONArray("atributos");
            Iterator attribIt = atributos.iterator();

            while (attribIt.hasNext()) {
                JSONObject atributo = (JSONObject) attribIt.next();
                System.out.print("\t");
                System.out.print(atributo.getString("nombre"));
                String atributoTipo = atributo.getString("nombre");
                
                //preguntamos por el tipo de atributo
                switch (atributo.getInt("tipo")) {
                    case 1:
                        System.out.println(" **");
                        break;
                    case 2:
                        System.out.println(" *");
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                
                atributosEntidades.add(atributoTipo);
            }
            hash.put(entityName, atributosEntidades);

        }
    
    }*/
}
