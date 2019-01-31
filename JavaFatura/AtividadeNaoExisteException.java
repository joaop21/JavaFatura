
/**
 * Escreva a descrição da classe AtividadeNaoExisteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class AtividadeNaoExisteException extends Exception{
    
    public AtividadeNaoExisteException(){
        super();
    }
    
    public AtividadeNaoExisteException(String cod){
        super(cod);
    }
}
