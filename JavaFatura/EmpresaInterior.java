
/**
 * Escreva a descrição da classe EmpresaInterior aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.ArrayList;
import java.util.List;

public class EmpresaInterior extends ContEmpresa implements ReducaoImposto{
    
    private String concelhoInterior;
    private double coeficiente;
    
    /**
     * 
     * Construtor vazio de EmpresaInterior
     */
    public EmpresaInterior(){
        super();
        this.concelhoInterior = "";
        this.coeficiente = 0;
    }
    
    /**
     * 
     * Construtor parametrizado de EmpresaInterior.
     */
    public EmpresaInterior(int nnif, String nemail, String nnome, String nmorada, 
        String npassword, List<String> ncodigos,double neuros, 
        String nconcelho, double ncoeficiente){
            super(nnif,nemail,nnome,nmorada,npassword,ncodigos,neuros);
            this.concelhoInterior = nconcelho;
            this.coeficiente = ncoeficiente;
    }
    
    /**
     * 
     * Construtor de cópia de EmpresaInterior.
     * 
     * @return EmpresaInterior
     */
    public EmpresaInterior(EmpresaInterior umEmpresaInterior){
        super(umEmpresaInterior.getNif(), umEmpresaInterior.getEmail(),
              umEmpresaInterior.getNome(), umEmpresaInterior.getMorada(), 
              umEmpresaInterior.getPassword(), umEmpresaInterior.getCodigos(),
              umEmpresaInterior.getEuros());
    }
    
    /**
     * 
     * Método que devolve o Concelho Interior.
     * 
     * @return String Concelho Interior
     */
    public String getConcelhoInterior(){
        return this.concelhoInterior;
    }
    
    /**
     * 
     * Método que devolve o coeficiente.
     * 
     * @return Valor.
     */
    public double getCoeficiente(){
        return this.coeficiente;
    }
    
    /** 
     * Método que atualiza o concelho interior.
     * 
     * @param nConcelhoInterior Novo concelho interior.
     * 
     */
    public void setConcelhoInterior(String nConcelhoInterior){
        this.concelhoInterior = nConcelhoInterior;
    }
    
    /**
     * 
     * Método que atualiza o coeficiente.
     * 
     * @param nCoeficiente Novo coeficiente.
     */
    public void setCoeficiente(double nCoeficiente){
        this.coeficiente = nCoeficiente;
    }
    
    /**
     * Método que calcula o valor a reduzir de imposto.
     * 
     * @return Valor.
     */
    public double reducaoImposto(){
        return this.coeficiente;
    }
}
