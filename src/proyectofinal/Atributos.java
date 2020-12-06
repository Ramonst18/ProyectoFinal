/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author Ramon
 */
public class Atributos {
    private String name;
    private String data_type;
    private String length;
    private String precision;
    private boolean not_null;
    private boolean primary_key;
    private boolean Foreign_key;
    
    public Atributos(String name,String data_type,String length,String precision,boolean not_null,boolean primary_key){
        this.name = name;
        this.data_type = data_type;
        this.length = length;
        this.precision = precision;
        this.not_null = not_null;
        this.primary_key = primary_key;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public boolean isNot_null() {
        return not_null;
    }

    public void setNot_null(boolean not_null) {
        this.not_null = not_null;
    }

    public boolean isPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(boolean primary_key) {
        this.primary_key = primary_key;
    }

    public boolean isForeign_key() {
        return Foreign_key;
    }

    public void setForeign_key(boolean Foreign_key) {
        this.Foreign_key = Foreign_key;
    }
    
    
}
