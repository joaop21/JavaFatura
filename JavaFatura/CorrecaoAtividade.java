
/**
 * Escreva a descrição da classe CorrecaoAtividade aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.io.Serializable;

public class CorrecaoAtividade implements Serializable{
    private int id_fatura;
    private String antiga_atividade;
    private String nova_atividade;
    
    /////////////////////////////// Construtores
    /**
     * 
     * Construtor vazio de CorrecaoAtividade.
     */
    public CorrecaoAtividade(){
        this.id_fatura = 0;
        this.antiga_atividade = "";
        this.nova_atividade = "";
    }
    
    /**
     * 
     * Construtor parametrizado de CorrecaoAtividade.
     */
    public CorrecaoAtividade(int id, String antiga, String nova){
        this.id_fatura = id;
        this.antiga_atividade = antiga;
        this.nova_atividade = nova;
    }
    
    /** 
     * Construtor de cópia de CorrecaoAtividade.
     */
    public CorrecaoAtividade(CorrecaoAtividade umacorrecao){
        this.id_fatura = umacorrecao.getIdFatura();
        this.antiga_atividade = umacorrecao.getAntigaAtividade();
        this.nova_atividade = umacorrecao.getNovaAtividade();
    }
    
    /**
     * 
     * Método que devolve o ID de uma fatura.
     * 
     * @return ID da fatura.
     */
    public int getIdFatura(){
        return this.id_fatura;
    }
    /**
     * 
     * Método que devolve a antiga atividade.
     * 
     * @return String com a antiga atividade.
     */
    public String getAntigaAtividade(){
        return this.antiga_atividade;
    }
    
    /**
     * 
     * Método que devolve a nova atividade.
     * 
     * @return String com a nova atividade.
     */
    
    public String getNovaAtividade(){
        return this.nova_atividade;
    }
    
    /////////////////////////////// Sets
    
    /**
     * 
     * Método que atualiza o ID da fatura.
     * 
     * @param id Novo ID da fatura.
     */
    public void setIdFatura(int id){
        this.id_fatura = id;
    }
    
    
    /**
     * 
     * Método que atualiza a antiga atividade.
     * 
     * @param antiga Novo valor para a antiga atividade.
     */
    public void setAntigaAtividade(String antiga){
        this.antiga_atividade = antiga;
    }
    
    /**
     * 
     * Método que atualiza a nova atividade.
     * 
     * @param nova Novo valor para a nova atividade.
     */
    
    public void setNovaAtividade(String nova){
        this.nova_atividade = nova;
    }
    
    /////////////////////////////// Métodos Essenciais
    
    /**
     * 
     * Implementação do método de clonagem de CorrecaoAtividade.
     * 
     * @return Objeto do tipo CorrecaoAtividade
     * 
     */
    public CorrecaoAtividade clone() {
        return new CorrecaoAtividade(this);
    }
    
    /**
    * Implementação do método de igualdade entre duas CorrecaoAtividade.
    * 
    * @param o CorrecaoAtividade que é comparada com o recetor
    * @return Booleano True ou False.
    */
    
    public boolean equals (Object o) {
        if (this == o) 
            return true;
            
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        
        CorrecaoAtividade ca = (CorrecaoAtividade) o;
        
        return (this.id_fatura == ca.getIdFatura() &&
                this.antiga_atividade.equals(ca.getAntigaAtividade()) &&
                this.nova_atividade.equals(ca.getNovaAtividade()));
    }
    
     /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto CorrecaoAtividade.
    */
    
    public String toString() {
       StringBuilder sb = new StringBuilder();
       
       sb.append("###################### Correção ######################\n");
       sb.append("ID da fatura: "); sb.append(this.id_fatura + "\n");
       sb.append("Antiga Atividade Económica: "); sb.append(this.antiga_atividade + "\n");
       sb.append("Nova Atividade Económica: "); sb.append(this.nova_atividade + "\n");
       sb.append("######################################################\n\n");
       
       return sb.toString();
    }
}
