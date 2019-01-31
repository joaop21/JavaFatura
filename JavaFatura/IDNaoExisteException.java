
/**
 * Escreva a descrição da classe AtividadeNaoExisteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class IDNaoExisteException extends Exception{
    
    public IDNaoExisteException(){
        super();
    }
    
    public IDNaoExisteException(String cod){
        super(cod);
    }
}
