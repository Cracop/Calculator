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
public class Calculadora {
   
    public Calculadora(){
        
    }
    //Checa si los parentesis están pareados de manera correcta
    public boolean checarParentesis(String cadena) throws Exception {
        boolean resp = true;
        String[] datos = cadena.split(" ", cadena.length());
        PilaDatos pilA = new PilaDatos();
        int i = 0;
        while (i < datos.length && resp) {
            if (datos[i].equals("(")) {
                pilA.push(datos[i]);
            }
            if (datos[i].equals(")")) {

                if (!pilA.isEmpty()) {
                    pilA.pop();
                } else {
                    resp = false;
                }
            }

            i++;
        }

        return resp;
    }
    //Da preferencia a los diferentes operadores
    public int darPreferencia(String cad) {
        int valor;
        String pos = cad;
        switch (pos) {
            case "+":
                valor = 1;
                break;

            case "-":
                valor = 1;
                break;

            case "*":
                valor = 2;
                break;

            case "/":
                valor = 2;
                break;

            default:
                valor = 0;
                break;
        }
        return valor;
    }
    //Checa si la preferencia del operador es menor
    public boolean esMenor(String varAux, String varPila) {
        boolean resp = false;
        int varAuxPref, varPilaPref;
        varAuxPref = darPreferencia(varAux);
        varPilaPref = darPreferencia(varPila);
        if (varPilaPref < varAuxPref) {
            resp = true;
        }
        return resp;
    }
    //Añade operadores explicitos donde son implicitos
    public String limpiar(String cad) {
        String resp = "";
        String valor, car;

        for (int i = 0; i < cad.length(); i++) {
            car = String.valueOf(cad.charAt(i));
            String car3 = null;
            String car2 = null;
            valor = determinaSigno(car);
            switch (valor) {
                case "num":
                    if (i < cad.length() - 1) {
                        car3 = String.valueOf(cad.charAt(i + 1));
                        if (determinaSigno(car3).equals("(")) {
                            resp += car + " *";
                        } else {
                            resp += car;
                        }
                    } else {
                        resp += car;
                    }
                    break;
                case "-":
                    if (i < 1) {
                        car3 = String.valueOf(cad.charAt(i + 1));
                        if (determinaSigno(car3).equals("(")) {
                            resp += car + "1 * ";
                        } else {
                            resp += car;
                        }
                    } else {
                        car2 = String.valueOf(cad.charAt(i - 1));
                        if (i < cad.length() - 1) {
                            car3 = String.valueOf(cad.charAt(i + 1));
                        }
                        if (!determinaSigno(car2).equals("num")) {
                            resp += car;
                            if (car3 != null) {
                                if (determinaSigno(car3).equals("(")) {
                                    resp += "1 * ";
                                }
                            } else {
                                resp += car;
                            }
                        } else {
                            resp += " " + car + " ";
                        }
                        car3 = null;

                    }

                    break;
                case ".":
                    if (i < 1) {
                        resp += "0.";
                    } else {
                        car2 = String.valueOf(cad.charAt(i - 1));
                        if (!determinaSigno(car2).equals("num")) {
                            resp += "0.";
                        } else {
                            resp += ".";
                        }
                    }
                    break;
                default:
                    resp += " " + car + " ";
                    break;
            }

        }
        return resp;
    }
    //Determina si el caracter es un operador o un numero
    public String determinaSigno(String cad) {
        String resp;
        switch (cad) {
            case "+":
                resp = "+";
                break;
            case "-":
                resp = "-";
                break;
            case "/":
                resp = "/";
                break;
            case "*":
                resp = "*";
                break;
            case "(":
                resp = "(";
                break;
            case ")":
                resp = ")";
                break;
            case ".":
                resp = ".";
                break;
            default:
                resp = "num";
                break;
        }
        return resp;
    }
    //Pasa de infija a posfija
    public String traducir(String cad) throws Exception {
        String limpio = limpiar(cad);
        String resp = "";
        PilaDatos pilA = new PilaDatos();
        String[] datos = limpio.split(" ");
        String aux1, aux2;

        if (!checarParentesis(cad)) {
            throw new Exception("Syntax Error");
        }
        for (int i = 0; i < datos.length; i++) {
            aux1 = determinaSigno(datos[i]);
            switch (aux1) {
                case "num":
                    resp += datos[i] + ",";
                    break;

                case "(":
                    pilA.push(aux1);
                    break;

                case ")":
                    aux2 = (String) pilA.peek();
                    while (!aux2.equals("(")) {
                        resp += aux2 + ",";
                        pilA.pop();
                        aux2 = (String) pilA.peek();
                    }
                    pilA.pop();
                    break;

                default:
                    if (pilA.isEmpty()) {
                        pilA.push(aux1);
                    } else {
                        aux2 = (String) pilA.peek();
                        while (!pilA.isEmpty() && !esMenor(aux1, aux2) && !aux2.equals("(")) {
                            resp += aux2 + ",";
                            pilA.pop();

                            aux2 = (String) pilA.peek();
                        }
                        pilA.push(aux1);
                    }
                    break;
            }
        }
        while (!pilA.isEmpty()) {
            resp += pilA.pop() + ",";
        }

        return resp;
    }
    //Resuelve la operacion
    public double resolver(String cad) throws Exception {
        PilaDatos pilA = new PilaDatos();
        if(!checarParentesis(cad)){
            throw new Exception("kk");
        }
        String[] datos = traducir(cad).split(",");
        Double resp;
        Double aux1, aux2;
        for (int i = 0; i < datos.length; i++) {
            if (!datos[i].isEmpty()) {
                switch (datos[i]) {
                    case "+":
                        aux1 = (Double) pilA.pop();
                        aux2 = (Double) pilA.pop();
                        resp = aux2 + aux1;
                        pilA.push(resp);
                        break;
                    case "-":
                        aux1 = (Double) pilA.pop();
                        aux2 = (Double) pilA.pop();
                        resp = aux2 - aux1;
                        pilA.push(resp);
                        break;
                    case "*":
                        aux1 = (Double) pilA.pop();
                        aux2 = (Double) pilA.pop();
                        resp = aux2 * aux1;
                        pilA.push(resp);
                        break;
                    case "/":
                        aux1 = (Double) pilA.pop();
                        aux2 = (Double) pilA.pop();
                        if (aux1 == 0) {
                            throw new Exception("Syntax Error");
                        }
                        resp = aux2 / aux1;
                        pilA.push(resp);
                        break;
                    default:
                        pilA.push(Double.parseDouble(datos[i]));
                        break;
                }
            }
        }
        resp = (Double) pilA.pop();
        return resp;
    }
    
    public static void main(String args[]) {
        GUICalculadora gc = new GUICalculadora();
        gc.setVisible(true);
        
    }
}
