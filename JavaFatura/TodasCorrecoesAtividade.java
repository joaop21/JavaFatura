
/**
 * Escreva a descrição da classe TodasCorrecoesAtividade aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TodasCorrecoesAtividade implements java.io.Serializable{
    private Map<Integer,CorrecaoAtividade> correcoes;
    
    private static final long serialVersionUID = 4L;
    
    /////////////////////////////// Construtores
    /**
     * Construtor vazio de TodasCorrecoesAtividade
     */
    public TodasCorrecoesAtividade(){
        this.correcoes = new HashMap<>();
    }
    
    /**
     * 
     * Construtor parametrizado de TodasCorrecoesAtividade
     */
    public TodasCorrecoesAtividade(Map<Integer,CorrecaoAtividade> correcoes){
        this.correcoes = correcoes;
    }
    
    /**
     * 
     * Construtor de cópia de TodasCorrecoesAtividade
     */
    public TodasCorrecoesAtividade(TodasCorrecoesAtividade correcoes){
        this.correcoes = correcoes.getCorrecoes();
    }
    
    /**
     * Método que devolve uma correção de atividade.
     * 
     * @param id ID da fatura.
     */
    
  
    public CorrecaoAtividade getCorrecaoAtividade(int id){
        for(CorrecaoAtividade c : this.correcoes.values()){
            if(c.getIdFatura() == id) return c.clone();
        }
        return null;
    }
    
    /**
     * Método que devolve um map com o ID da fatura e a correção feita.
     * 
     * @return Map com ID da fatura e respetiva correção de atividade.
     */
    
    public Map<Integer,CorrecaoAtividade> getCorrecoes(){
        return this.correcoes.values().stream().
        collect(Collectors.toMap((c) -> c.getIdFatura(),(c) -> c.clone()));
    }
    
    /////////////////////////////// Métodos Essenciais
    
    /**
     * 
     * Implementação do método de clone de TodasCorrecoesAtividade.
     * 
     */
    public TodasCorrecoesAtividade clone() {
     return new TodasCorrecoesAtividade(this); 
    }
    
    /**
     * 
     * * Implementação do método de igualdade entre duas classes TodasCorrecoesAtividade.
    * 
    * @param o TodasCorrecoesAtividade que é comparada com o recetor
    * @return Booleano True ou False.
     */
    
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        TodasCorrecoesAtividade c = (TodasCorrecoesAtividade) o;
        
        return this.correcoes.equals(c.getCorrecoes());
    }  
    
     /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto TodasCorrecoesAtividade.
    */
    
    public String toString() {
     StringBuffer sb = new StringBuffer();
     
     for (CorrecaoAtividade c: this.correcoes.values())
        sb.append(c.toString() + "\n");
     
     return sb.toString(); 
    }
    
    /////////////////////////////// Métodos
    
    /**
     * Método que adiciona uma correção de atividade à lista.
     * 
     * @param ca Correção de atividade a adicionar.
     */
    public void addCorrecaoAtividade(CorrecaoAtividade ca){
        this.correcoes.put(ca.getIdFatura(), ca.clone());
    }
    /**
     * 
     * Método que verifica se existe uma chave.
     * 
     * @param key Chave a procurar.
     */
    public boolean containsKey_(Integer key){
       if(this.correcoes.containsKey(key)) return true; 
       else return false;
    }
    
    /**
     * 
     * Método que verifica se existe uma CorrecaoAtividade.
     * 
     * @param value COrreção de Atividade a procurar.
     */
    
    public boolean containsValue_(CorrecaoAtividade value){
       if(this.correcoes.containsValue(value)) return true; 
       else return false;
    }
    
    /**
     * Método que carrega o estado de TodasCorrecoesAtividade.
     * 
     * @param nomeFicheiro Nome do ficheiro que contém o estado gravado.
     * 
     * @return Estrutura TodasCorrecoesAtividade carregada.
     */
    
    public  static TodasCorrecoesAtividade loadTCA(String nomeFicheiro) throws FileNotFoundException,IOException,
                                                     ClassNotFoundException {
       FileInputStream f = new FileInputStream(nomeFicheiro);
       ObjectInputStream o = new ObjectInputStream(f);
       
       TodasCorrecoesAtividade tca = (TodasCorrecoesAtividade) o.readObject();
       
       o.close();
       return tca;                                    
    }
   
    /**
     * 
     * Método que guarda o estado atual de TodasCorrecoesAtividade.
     * 
     * @param nomeFicheiro Nome do ficheiro onde irá ser gravado o estado.
     *
     */
    
    public void guardaTCA (String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
}
