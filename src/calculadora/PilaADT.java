/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*

*/
package calculadora;

/**
 *
 * @author Rodrigo Alejandro Barrera Manjarrez
 */
/*
Pilas LiFo

    pop - sacar dato
    peek -  ver cual es el primero
    push - meter dato

    isEmpty() - me dice si esta vacio o no
Es una estructura que sirve para ordenar o invertir el orden de las cosas.
*/
public interface PilaADT<T> {
    public void push (T elem);
    
    public T pop() throws Exception;
    
    public boolean isEmpty();
    
    public T peek() throws Exception;
    
}

/*
Algoritmo Shunting Yurd
    Así es como funciona las calculadora 

*/
