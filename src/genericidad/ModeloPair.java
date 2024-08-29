/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package genericidad;

/**
 *
 * @author Andrés
 */
public class ModeloPair {
    
    public class Pair<T, U> {

    private T first;
    private U second;

    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    
    public T getFirst() {
        return first;
    }

    
    public U getSecond() {
        return second;
    }

    
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
}
