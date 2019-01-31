
/**
 * Escreva a descrição da classe TodosConcelhos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.lang.Iterable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Collections;

public class TodosConcelhos implements java.io.Serializable{
    private List<Concelho> concelhos;
    
    private static final long serialVersionUID = 5L;
   
    //////////////////////////////// Métodos Essenciais
    // Construtor vazio
    public TodosConcelhos() {
        this.concelhos = new ArrayList<>();
    }
    
    // Construtor parametrizado
    public TodosConcelhos(List<Concelho> novosConcelhos) {
        setConcelhos(novosConcelhos);
    }
    
    // Construtor de cópia
    public TodosConcelhos(TodosConcelhos todos) {
        this.concelhos = todos.getConcelhos();
    }
    
    //////////////////////////////// Gets
    public List<Concelho> getConcelhos() {
      return this.concelhos.stream().map(Concelho::clone).collect(Collectors.toList());        
    }
    
    public Concelho getConcelho(String nome){
        for(Concelho c : this.concelhos){
            if(c.getNome() == nome) return c.clone();
        }
        return null;
    }
    
    
    //////////////////////////////// Sets
    public void setConcelhos(List<Concelho> todos) {
        this.concelhos = new ArrayList<>();
        for(Concelho concelho : todos) {
            this.concelhos.add(concelho.clone());
        }
    }
    
    
    //////////////////////////////// Métodos Essenciais
    public TodosConcelhos clone() {
        return new TodosConcelhos(this);
    }
    
    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;
        TodosConcelhos e = (TodosConcelhos)o;
        return this.concelhos.equals(e.getConcelhos()); 
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.concelhos.toString());
        return sb.toString();
    }
    
        //////////////////////////////// Métodos
    /**
     * Método que adiciona uma Concelho c a TodosConcelhos.
     * 
     * @param c Concelho.
     */

    public void addConcelho(Concelho c){ // não precisa exceção
        this.concelhos.add(c.clone());
    }
    
    public boolean contains_(Concelho c){
       if(this.concelhos.contains(c)) return true; 
       else return false;
    }
    
    public static TodosConcelhos loadTC(String nomeFicheiro) throws FileNotFoundException,IOException,
                                                     ClassNotFoundException {
       FileInputStream f = new FileInputStream(nomeFicheiro);
       ObjectInputStream o = new ObjectInputStream(f);
       
       TodosConcelhos tc = (TodosConcelhos) o.readObject();
       o.close();
       return tc;                                    
    }
    
    /**
     * Método que guarda o estado de TodosConcelhos num ficheiro.
     * 
     * @param nomeFicheiro Nome do ficheiro onde vai ser guardado.
     * 
     * 
     */
    
    public void guardaTC (String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
}
    