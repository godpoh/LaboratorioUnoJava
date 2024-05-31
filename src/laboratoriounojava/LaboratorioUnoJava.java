/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratoriounojava;

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class LaboratorioUnoJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Registro de personas.");
            System.out.println("2. Registro de Productos.");
            System.out.println("3. Busqueda y Compra de Productos.");
            System.out.println("4. Reportes.");
            System.out.println("5. Salir");

            if (scanner.hasNextInt()) {
                int optionMenu = scanner.nextInt();
                switch (optionMenu) {
                    case 1:
                        registrationOfPeople();
                        break;
                    case 2:
                        registrationOfProducts();
                        break;
                    case 3:
                        searchNBuyOfProducts();
                        break;
                    case 4:
                        reports();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        System.exit(0);
                    default:
                        System.out.println("Error: Ingrese una opcion valida. Intentelo de nuevo.");
                }
            } else {
                scanner.next();
                System.out.println("Error: No se permite ingresar letras. Intentelo de nuevo.");
            }
        }
    }

    public static void registrationOfPeople() {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, HashMap<String, Object>> indexDictionary = new HashMap<>();
        HashSet<Integer> registedIdNumbers = new HashSet<>();
        System.out.println("Registro de Personas: ");

        while (true) {
            int idNumber;

            while (true) {
                System.out.println("Ingrese su cedula: ");
                if (scanner.hasNextInt()) {
                    idNumber = scanner.nextInt();
                    String idString = String.valueOf(idNumber);
                    if (idString.length() != 9) {
                        System.out.println("Error: La cedula debe de tener 9 numeros.");
                        continue;
                    }
                    if (registedIdNumbers.contains(idNumber)) {
                        System.out.println("Error: Esta cedula ya ha sido registrada.");
                        continue;
                    } else {
                        registedIdNumbers.add(idNumber);
                    }
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Error: Ingrese un numero valido y longevidad de 9 numeros.");
                    scanner.next();
                }
            }

            String name;
            while (true) {
                System.out.println("Ingrese su nombre: ");
                name = scanner.nextLine();
                if (name.matches("[A-Z][a-zA-Z ]*")) {
                    if (name.length() > 2) {
                        break;
                    } else {
                        System.out.println("Error: El nombre debe de iniciar con mayuscula y debe contener mas de 2 letras.");
                    }
                } else {
                    System.out.println("Error: El nombre debe de iniciar con mayuscula y debe contener mas de 2 letras.");
                }
            }

            String gender;
            while (true) {
                System.out.println("Ingrese su genero: ");
                System.out.println("1. Masculino");
                System.out.println("2. Femenino");

                if (scanner.hasNextInt()) {
                    int optionsGender = scanner.nextInt();
                    scanner.nextLine();
                    if (optionsGender == 1) {
                        gender = "Masculino";
                        break;
                    } else if (optionsGender == 2) {
                        gender = "Femenino";
                        break;
                    } else {
                        System.out.println("Ingrese una opcion valida. Intentelo de nuevo");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Error: Ingrese un numero valido.");
                }
            }
            int age;
            while (true) {
                System.out.println("Ingrese su edad:");
                age = scanner.nextInt();
                scanner.nextLine();

                if (age < 6) {
                    System.out.println("No tienes la edad suficiente para registrarte.");
                    continue;
                } else if (age > 130) {
                    System.out.println("No puede ingresar una edad mayor a 130");
                    continue;
                } else {
                    break;
                }
            }

            HashMap<String, Object> personInfo = new HashMap<>();
            personInfo.put("Cedula", idNumber);
            personInfo.put("Nombre", name);
            personInfo.put("Genero", gender);
            personInfo.put("Edad", age);

            int nextIndex = indexDictionary.size() + 1;

            indexDictionary.put(nextIndex, personInfo);

            System.out.println("Informacion registrada:");
            for (int i = 1; i <= indexDictionary.size(); i++) {
                HashMap<String, Object> currentPersonInfo = indexDictionary.get(i);
                System.out.println("Persona " + i + ":");
                System.out.println("Cedula: " + currentPersonInfo.get("Cedula"));
                System.out.println("Nombre: " + currentPersonInfo.get("Nombre"));
                System.out.println("Genero: " + currentPersonInfo.get("Genero"));
                System.out.println("Edad: " + currentPersonInfo.get("Edad"));
                System.out.println();
            }

            while (true) {
                System.out.println("Desea agregar otra persona?: ");
                System.out.println("Importante! Si digita otro numero que no sea 1 o 2 sera devuelto al menu principal!");
                System.out.println("1. Si");
                System.out.println("2. No");

                if (scanner.hasNextInt()) {
                    int continueInput = scanner.nextInt();
                    if (continueInput != 1) {
                        System.out.println("Volviendo al menu principal");
                        return;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Error: Debe ingresar una opcion valida");
                    scanner.next();
                }
            }
        }
    }

    public static void registrationOfProducts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Registro de productos: ");
        String productName;
        while (true) {
            System.out.println("Ingrese el nombre del producto");

            productName = scanner.nextLine();
            if (productName.matches("[A-Z][a-zA-Z ]*")) {
                if (productName.length() > 1) {
                    break;
                } else {
                    System.out.println("Error: El nombre del producto debe empezar con mayuscula y debe contener almenos mas de 1 letra");
                }
            } else {
                System.out.println("Error: El nombre del producto debe empezar con mayuscula y debe contener almenos mas de 1 letra.");
                continue;
            }
        }
        int productPrice;
        while (true) {
            System.out.println("Defina el precio de cada unidad del producto");

            if (scanner.hasNextInt()) {
                productPrice = scanner.nextInt();
                scanner.nextLine();
                if (productPrice > 0) {
                    break;
                } else {
                    System.out.println("Error: El precio del producto debe ser mayor a 0");
                }
            } else {
                System.out.println("Error: Digite solo numeros. Intentelo de nuevo.");
                scanner.next();
            }
        }
        int productQuantity;
        int productQuantityPlus = 0;
        while (true) {
            System.out.println("Registro de productos:");
            System.out.println("Recuerde que si ingresa 10 panes debe colocarle fecha de vencimiento a cada uno: ");
            System.out.println("Cantidad de productos ingresados: " + productQuantityPlus);
            System.out.println("1. Agregar 1 " + productName + " al registro");
            System.out.println("2. Agregar 2 " + productName + " al registro");
            System.out.println("3. Agregar 3 " + productName + " al registro");
            System.out.println("4. Agregar 5 " + productName + " al registro");
            System.out.println("5. Agregar 10 " + productName + " al registro");
            System.out.println("6. Acabar de ingresar productos");

            if (scanner.hasNextInt()) {
                productQuantity = scanner.nextInt();
                scanner.nextLine();
                if (productQuantity == 1) {
                    productQuantityPlus++;
                    continue;
                } else if (productQuantity == 2) {
                    productQuantityPlus += 2;
                    continue;
                } else if (productQuantity == 3) {
                    productQuantityPlus += 3;
                    continue;
                } else if (productQuantity == 4) {
                    productQuantityPlus += 5;
                    continue;
                } else if (productQuantity == 5) {
                    productQuantityPlus += 10;
                    continue;
                } else if (productQuantity == 6) {
                    break;
                } else {
                    System.out.println("Error: Ingrese una de las opciones validas. Intentelo de nuevo");
                    continue;
                }
            } else {
                scanner.next();
                System.out.println("Error: Debe ingresar solo numeros enteros.");
            }
        }
        Date productDueDate = null;
        while (true) {
            System.out.println("Ingrese la fecha de vencimiento del(los) productos (formato: Dia/Mes/Year)");
            String inputDate = scanner.nextLine();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            
            try {
                productDueDate = dateFormat.parse(inputDate);
                break;
            } catch (ParseException eVariableAsProductDueDate){
                System.out.println("Error: Formato de fecha incorrecto. Intentelo de nuevo.");
            }
        }
    
    }
    public static void searchNBuyOfProducts() {

    }

    public static void reports() {

    }

}
