/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author Rodrigo Alejandro Barrera Manjarrez
 */
public class PilaDatos <T> implements PilaADT<T>{

    T[] datos;
    int contador=0;
    
    public PilaDatos(){
        datos = (T[]) new Object[1];
        //contador=0;
        
    }

    public void push(T elem) {
        if(contador<datos.length){
            datos[contador]=elem;
            contador++;
        }else{
            expand();
            datos[contador]=elem;
            contador++;
        }
    }

    @Override
    public T pop() throws Exception {
        T aux=null;
        if(isEmpty()!=true){
        contador--;
        aux=datos[contador];
        datos[contador]=null;
        
    }
        //contador;
        
        return aux;
    }

    @Override
    public boolean isEmpty() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean flag=true;
        if(contador!=0){
            flag=false;
        }
        return flag;
    }

    @Override
    public T peek() throws Exception {
        T aux=null;
        if(isEmpty()!=true){
            aux=datos[contador-1];
        }
        return aux;        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void expand(){
        T[] nuevo = (T[]) new Object[datos.length*2];
        for(int i=0;i<contador;i++){
            nuevo[i] = datos[i];
        }
        datos=nuevo;
    }

    int pop8() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
