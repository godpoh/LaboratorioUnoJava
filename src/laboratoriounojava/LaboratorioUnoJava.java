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
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class LaboratorioUnoJava {

    private static HashMap<Integer, HashMap<String, Object>> indexDictionary = new HashMap<>();
    private static ArrayList<HashMap<String, Object>> productList = new ArrayList<>();
    private static HashSet<Integer> registeredIdNumbers = new HashSet<>();

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
                    if (registeredIdNumbers.contains(idNumber)) {
                        System.out.println("Error: Esta cedula ya ha sido registrada.");
                        continue;
                    } else {
                        registeredIdNumbers.add(idNumber);
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
                if (name.matches("([A-Z][a-z]*)(\\s[A-Z][a-z]*)*")) {
                    if (name.length() > 2) {
                        break;
                    } else {
                        System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 1 letra");
                    }
                } else {
                    System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 1 letra");
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
        while (true) {
            System.out.println("Registro de productos: ");

            String productName;
            while (true) {
                System.out.println("Ingrese el nombre del producto");

                productName = scanner.nextLine();
                if (productName.matches("([A-Z][a-z]*)(\\s[A-Z][a-z]*)*")) {
                    if (productName.length() > 1) {
                        break;
                    } else {
                        System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 1 letra");
                    }
                } else {
                    System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 1 letra");
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

            int productQuantity = 0;
            while (true) {
                System.out.println("Registro de productos:");
                System.out.println("Recuerde que si ingresa 10 " + productName + " debe colocarle fecha de vencimiento a cada uno: ");
                System.out.println("Cantidad de productos ingresados: " + productQuantity);
                System.out.println("1. Agregar 1 " + productName + " al registro");
                System.out.println("2. Agregar 2 " + productName + " al registro");
                System.out.println("3. Agregar 3 " + productName + " al registro");
                System.out.println("4. Agregar 5 " + productName + " al registro");
                System.out.println("5. Agregar 10 " + productName + " al registro");
                System.out.println("6. Acabar de ingresar productos");

                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            productQuantity += 1;
                            break;
                        case 2:
                            productQuantity += 2;
                            break;
                        case 3:
                            productQuantity += 3;
                            break;
                        case 4:
                            productQuantity += 5;
                            break;
                        case 5:
                            productQuantity += 10;
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Error: Ingrese una opcion valida.");
                            continue;
                    }
                    if (option == 6) {
                        if (productQuantity > 0) {
                            break;
                        } else {

                            System.out.println("Debe de almenos ingresar 1 producto existente");
                        }
                    }
                } else {
                    scanner.nextLine();
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
                } catch (ParseException eVariableAsProductDueDate) {
                    System.out.println("Error: Formato de fecha incorrecto. Intentelo de nuevo.");
                }
            }
            HashMap<String, Object> productInfo = new HashMap<>();

            productInfo.put("Nombre", productName);
            productInfo.put("Precio", productPrice);
            productInfo.put("Cantidad", productQuantity);
            productInfo.put("Fecha de vencimiento", productDueDate);

            productList.add(productInfo);

            System.out.println("Productos Registrados: ");
            for (int i = 0; i < productList.size(); i++) {
                HashMap<String, Object> currentProductInfo = productList.get(i);
                System.out.println("Producto " + (i + 1) + ":");
                System.out.println("Nombre: " + currentProductInfo.get("Nombre"));
                System.out.println("Precio c/u: " + currentProductInfo.get("Precio"));
                System.out.println("Cantidad: " + currentProductInfo.get("Cantidad"));
                System.out.println("Fecha de Vencimiento: " + currentProductInfo.get("Fecha de vencimiento"));
                System.out.println();
            }
            while (true) {
                System.out.println("Desea agregar otro producto?");
                System.out.println("Si ingresa otra opcion que no sean las opciones disponibles sera redigirido al menu principal");
                System.out.println("1. Si");
                System.out.println("2. No");

                int option;
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Error: Debe ingresar solo numeros enteros.");
                    scanner.next();
                    continue;
                }
                if (option != 1) {
                    System.out.println("Volviendo al menu principal");
                    return;
                } else {
                    break;
                }
            }
        }
    }

    public static void searchNBuyOfProducts() {
        Scanner scanner = new Scanner(System.in);

        if (productList.isEmpty()) {
            System.out.println("No hay productos disponibles. Volviendo al menu principal.");
            return;
        }
        System.out.println("Compra de Productos:");
        int confirmIdNumber;
        while (true) {
            System.out.println("Digite su cedula:");

            if (scanner.hasNextInt()) {
                confirmIdNumber = scanner.nextInt();
                scanner.nextLine();

                if (registeredIdNumbers.contains(confirmIdNumber)) {
                    System.out.println("Cedula Confirmada. Bienvenido");
                    break;
                } else {
                    System.out.println("Cedula NO existente. Redirigiendo al menu principal.");
                    return;
                }
            } else {
                scanner.next();
                System.out.println("Error: Debe ingresar solo digitos. Intentelo de nuevo");
            }
        }

        while (true) {
            System.out.println("Menu de Productos:");
            // Lo que esto hace es agregar el producto a consola(1. Pan 2. No comprar mas)
            // Se guarda la lista dentro de otro diccionario para que sea mas comodo utilizarlo
            for (int i = 0; i < productList.size(); i++) {
                HashMap<String, Object> currentProductInfo = productList.get(i);
                System.out.println((i + 1) + ". " + currentProductInfo.get("Nombre"));
            }
            System.out.println((productList.size() + 1) + ". No comprar mas");

            int selectedOption;
            while (true) {
                System.out.println("Seleccione un producto. Ingrese la opcion correspondiente");
                if (scanner.hasNextInt()) {
                    selectedOption = scanner.nextInt();
                    scanner.nextLine();

                    if (selectedOption >= 1 && selectedOption <= productList.size() + 1) {
                        break;
                    } else {
                        System.out.println("Ingrese una opcion valida. Intentelo de nuevo");
                        scanner.next();
                    }
                } else {
                    System.out.println("Debe ingresar un numero. Intentelo de nuevo.");
                }
            }
            if (selectedOption == productList.size() + 1) {
                System.out.println("Saliendo del menu de compra");
                break;
            } else {
                // Se crea una variable local para hacer que la lista sea mas manejable
                // Si ingreso la 3. Pan en realidad seria 2. Porque las listas empiezan en 0
                // Por eso se le resta el -1
                HashMap<String, Object> selectedProduct = productList.get((selectedOption - 1));
                // Se utiliza la variable local para no estar colocando -1 y acceder directamente al 
                //indice correcto
                System.out.println("Ha seleccionado comprar: " + selectedProduct.get("Nombre"));

                int quantityToBuy;
                while (true) {
                    System.out.println("Ingrese la cantidad que desea comprar:");
                    System.out.println("Unidades existentes:" + selectedProduct.get("Cantidad"));

                    if (scanner.hasNextInt()) {
                        quantityToBuy = scanner.nextInt();
                        scanner.nextLine();
                        if (quantityToBuy > 0  && quantityToBuy <= (int)selectedProduct.get("Cantidad")) {
                            int currentQuantity = (int) selectedProduct.get("Cantidad");
                            selectedProduct.put("Cantidad", currentQuantity - quantityToBuy);
                            System.out.println("Compra existosa de" + quantityToBuy + " " + selectedProduct.get("Nombre"));
                            break;
                        } else {
                            System.out.println("Error: Cantidad invalida. Intentelo de nuevo");
                        }
                    } else {
                        scanner.next();
                        System.out.println("Error: Debe de ingresar solo numeros enteros");
                    }
                }

            }
        }

    }

    public static void reports() {

    }

}
