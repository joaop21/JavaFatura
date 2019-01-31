
/**
 * Escreva a descrição da classe AtividadeEconomica aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class AtividadeEcon implements java.io.Serializable{
    private String atividade;
    private double coeficiente;
    
    /**
    * Construtor vazio de AtividadeEconomica.
    */
    public AtividadeEcon(){
        this.atividade = "";
        this.coeficiente = 0;
    }

    /**
    * Construtor parametrizado de AtividadeEconomica.
    */
    public AtividadeEcon(String ativ, double coe){
        this.atividade = ativ;
        this.coeficiente = coe;
    }

    /**
    * Construtor de cópia de AtividadeEconomica.
    */
    public AtividadeEcon(AtividadeEcon ativ){
        this.atividade = ativ.getAtividade();
        this.coeficiente = ativ.getCoeficiente();
    }

    //////////////////////////////// GETS
    /**
    * Método que devolve o nome da atividade económica.
    * 
    * @return Nome da atividade.
    */
    public String getAtividade() {
        return this.atividade;
    }

    /**
    * Método que devolve a Informação sobre o coeficiente associado a uma atividade económica.
    * 
    * @return Coeficiente associado a uma atividade.
    */
    public double getCoeficiente() {
        return this.coeficiente;
    }

    ///////////////////////////////// SETS
    /**
    * Atualiza a Informação sobre o coeficiente de duma dada atividade.
    * 
    * @param ncoeficiente Novo coeficiente da atividade.
    */
    public void setCoeficiente(double ncoeficiente) {
        this.coeficiente = ncoeficiente;
    }

    //////////////////////////////// Métodos essenciais

    /**
    * Método de clonagem de uma atividade económica.
    * 
    * @return Objeto do tipo AtividadeEcon.
    */
    public AtividadeEcon clone() {
        return new AtividadeEcon(this);
    }

    /**
    * Implementação do método de igualdade entre duas atividades económicas.
    * 
    * @param o AtividadeaEcon que é comparado com o recetor
    * @return Booleano True ou False.
    */
    public boolean equals (Object o) {
        if (this == o) 
            return true;
            
        if ((o==null) || (this.getClass() != o.getClass())) 
            return false;
        
        AtividadeEcon c = (AtividadeEcon) o;
        
        return (this.atividade.equals(c.getAtividade()) &&
                this.coeficiente == c.getCoeficiente());
    }

    /**
    * Implementação do método toString.
    * 
    * @return Uma String com informação textual do objeto AtividadeEcon.
    */
    public String toString() {
       StringBuilder sb = new StringBuilder();
       
       sb.append("Atividade: "); sb.append(this.atividade);
       sb.append(", Coeficiente: "); sb.append(this.coeficiente);
       
       return sb.toString();
    }
    
    
}
