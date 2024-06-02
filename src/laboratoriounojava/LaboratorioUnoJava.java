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
import java.util.Iterator;

/**
 *
 * @author Admin
 */
public class LaboratorioUnoJava {

    private static HashMap<Integer, HashMap<String, Object>> indexDictionary = new HashMap<>();
    private static ArrayList<HashMap<String, Object>> productList = new ArrayList<>();
    private static HashSet<Integer> registeredIdNumbers = new HashSet<>();
    private static HashSet<Integer> bannedUsers = new HashSet<>();

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
            System.out.println("Seleccione una opcion: ");

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
                if (scanner.hasNextInt()) {
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
                } else {
                    System.out.println("Debe ingresar solo numeros enteros.");
                    scanner.next();
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

        ArrayList<HashMap<String, Object>> cart = new ArrayList<>();
        ArrayList<HashMap<String, Object>> availableProducts = new ArrayList<>();

        if (productList.isEmpty()) {
            System.out.println("No hay productos disponibles. Volviendo al menu principal.");
            return;
        }

        // Filtrar productos disponibles
        Iterator<HashMap<String, Object>> iterator = productList.iterator();
        while (iterator.hasNext()) {
            HashMap<String, Object> product = iterator.next();
            int currentQuantity = (int) product.get("Cantidad");
            if (currentQuantity > 0) {
                availableProducts.add(product);
            }
        }

        System.out.println("Compra de Productos:");
        int confirmIdNumber;
        while (true) {
            System.out.println("Digite su cedula:");

            if (scanner.hasNextInt()) {
                confirmIdNumber = scanner.nextInt();
                scanner.nextLine();

                if (registeredIdNumbers.contains(confirmIdNumber)) {
                    if (bannedUsers.contains(confirmIdNumber)) {
                        System.out.println("Usted ya ha realizado una compra. No puede realizar otra");
                        return;
                    } else {
                        System.out.println("Cedula Confirmada. Bienvenido");
                        break;
                    }
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
            // Mostrar productos disponibles
            for (int i = 0; i < availableProducts.size(); i++) {
                HashMap<String, Object> currentProductInfo = availableProducts.get(i);
                System.out.println((i + 1) + ". " + currentProductInfo.get("Nombre"));
            }
            System.out.println((availableProducts.size() + 1) + ". No comprar mas");

            int selectedOption;
            while (true) {
                System.out.println("Seleccione un producto. Ingrese la opcion correspondiente");
                if (scanner.hasNextInt()) {
                    selectedOption = scanner.nextInt();
                    scanner.nextLine();

                    if (selectedOption >= 1 && selectedOption <= availableProducts.size() + 1) {
                        break;
                    } else {
                        System.out.println("Ingrese una opcion valida. Intentelo de nuevo");
                        scanner.next();
                    }
                } else {
                    System.out.println("Debe ingresar un numero. Intentelo de nuevo.");
                }
            }

            if (selectedOption == availableProducts.size() + 1) {
                System.out.println("Saliendo del menu de compra");
                break;
            } else {
                // Obtener el producto seleccionado
                HashMap<String, Object> selectedProduct = availableProducts.get(selectedOption - 1);
                System.out.println("Ha seleccionado comprar: " + selectedProduct.get("Nombre"));

                int quantityToBuy;
                while (true) {
                    System.out.println("Ingrese la cantidad que desea comprar:");
                    System.out.println("Unidades existentes: " + selectedProduct.get("Cantidad"));

                    if (scanner.hasNextInt()) {
                        quantityToBuy = scanner.nextInt();
                        scanner.nextLine();
                        if (quantityToBuy > 0 && quantityToBuy <= (int) selectedProduct.get("Cantidad")) {

                            boolean found = false;
                            for (HashMap<String, Object> item : cart) {
                                if (item.get("Nombre").equals(selectedProduct.get("Nombre"))
                                        && item.get("Fecha de vencimiento").equals(selectedProduct.get("Fecha de vencimiento"))) {
                                    // Actualizar la cantidad del producto existente
                                    int currentQuantity = (int) item.get("Cantidad");
                                    item.put("Cantidad", currentQuantity + quantityToBuy);
                                    found = true;
                                    break;
                                }
                            }

                            // Si el producto no estaba en el carrito, ser agregado
                            if (!found) {
                                HashMap<String, Object> productToAdd = new HashMap<>(selectedProduct);
                                productToAdd.put("Cantidad", quantityToBuy);
                                cart.add(productToAdd);
                            }

                            // Actualizar la cantidad del producto seleccionado
                            int currentQuantity = (int) selectedProduct.get("Cantidad");
                            selectedProduct.put("Cantidad", currentQuantity - quantityToBuy);
                            System.out.println("Se agrego al carrito: " + quantityToBuy + " " + selectedProduct.get("Nombre") + " / Precio cada unidad: " + selectedProduct.get("Precio") + " / Fecha de vencimiento: " + selectedProduct.get("Fecha de vencimiento"));
                            if ((int) selectedProduct.get("Cantidad") == 0) {
                                availableProducts.remove(selectedProduct);
                            }

                            bannedUsers.add(confirmIdNumber);
                            break;

                        } else {
                            System.out.println("Error: Cantidad invalida. Intentelo de nuevo");
                        }
                    } else {
                        scanner.next();
                        System.out.println("Error: Debe de ingresar solo numeros enteros");
                    }
                }

                // Mostrar productos en el carrito
                System.out.println("Lista de productos en el carrito: ");
                for (int i = 0; i < cart.size(); i++) {
                    HashMap<String, Object> productsInCart = cart.get(i);
                    System.out.println("Producto: " + (i + 1));
                    System.out.println("Nombre: " + productsInCart.get("Nombre"));
                    System.out.println("Precio cada unidad: " + productsInCart.get("Precio"));
                    System.out.println("Cantidad: " + productsInCart.get("Cantidad"));
                    System.out.println("Fecha de vencimiento: " + productsInCart.get("Fecha de vencimiento"));
                    System.out.println();
                }
            }
        }
    }

    public static void reports() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de reportes: ");
            System.out.println("1. Mostrar el o los productos que cada cliente adquirio, el precio total que pagara y la fecha en que lo compro");
            System.out.println("2. Mostrar la cantidad de mujeres y hombres que compraron productos");
            System.out.println("3. Mostrar la lista de los nombres de los productos que no han sido comprados por ningún cliente");
            System.out.println("4. Mostrar el top 3 de los productos más comprados en un rango de fechas, colocando una fecha de inicio y de fin, y mostrar el nombre del producto");
            System.out.println("5. Mostrar la lista de personas que durante su compra cambiaron la cantidad de uno o mas productos");
            System.out.println("6. Volver al menu principal");
            System.out.println("Seleccione una opcion: ");

            int options;
            if (scanner.hasNextInt()) {
                options = scanner.nextInt();
                scanner.nextLine();

                switch (options) {
                    case 1:
                        optionA();
                        break;
                    case 2:
                        optionB();
                        break;
                    case 3:
                        optionC();
                        break;
                    case 4:
                        optionD();
                        break;
                    case 5:
                        optionE();
                        break;
                    case 6:
                        System.out.println("Volviendo al menu principal...");
                        return;
                    default:
                        System.out.println("Error: Debe ingresar una opcion valida.");
                }
            } else {
                scanner.next();
                System.out.println("Error: Debe ingresar solo numeros enteros");
            }
        }
    }

    public static void optionA() {
        Date validateDueDate = new Date();
        // bannedId es el valor 208790566, 205340065, 208769056, y bannedUsers es la variable que se itera
        // Con lo cual todos los valores de bannedUsers son obtenidos por indexDictionary 
        System.out.println("Productos adquiridos por cada cliente:");
        for (Integer bannedId : bannedUsers) {
            //El ID se utiliza como clave para buscar en el indexDictionary, que contiene información
            //detallada de cada cliente. Nombre. Cedula. Genero. Etc y toda esta informacion es almacenada
            //en el diccionario local currentClientInfo hasta se itera nuevamente y se procese datos nuevos.
            HashMap<String, Object> currentClientInfo = indexDictionary.get(bannedId);
            System.out.println("Cliente:" + currentClientInfo.get("Nombre") + "Cedula: " + currentClientInfo.get("Cedula"));

            int totalPayment = 0;
            ArrayList<HashMap<String, Object>> customerPurchases = new ArrayList<>();
            for (HashMap<String, Object> product : productList) {
                int quantityBought = (int) product.get("Cantidad");
                if (quantityBought > 0) {
                    HashMap<String, Object> productPurchase = new HashMap<>();
                    productPurchase.put("Nombre", product.get("Nombre"));
                    productPurchase.put("Precio", product.get("Precio"));
                    productPurchase.put("Cantidad", quantityBought);
                    productPurchase.put("Fecha de vencimiento", product.get("Fecha de vencimiento"));
                    customerPurchases.add(productPurchase);

                    totalPayment += ((int) product.get("Precio") * quantityBought);
                }

            }
            System.out.println("Productos Adquiridos: ");
            for (HashMap<String, Object> purchase : customerPurchases) {
                System.out.println("- Producto: " + purchase.get("Nombre"));
                System.out.println("  Precio unitario: " + purchase.get("Precio"));
                System.out.println("  Cantidad: " + purchase.get("Cantidad"));
                System.out.println("  Fecha de vencimiento: " + purchase.get("Fecha de vencimiento"));

                Date expirationDate = (Date) purchase.get("Fecha de vencimiento");
                if (expirationDate.before(validateDueDate)) {
                    System.out.println("¡Este producto ya vencio!");
                }
            }
            System.out.println("Precio total de la compra: " + totalPayment);

            System.out.println("Fecha de la compra: " + validateDueDate);

            System.out.println(); // Espacio entre clientes
        }
    }

    public static void optionB() {
        int woman = 0;
        int man = 0;
        // Tipo de danto que se almacena, Elemento, : separar ambos 
        //personInfo variable local que es cada "elemento" del diccionario .values obtiene toda los datos
        for (HashMap<String, Object> personInfo : indexDictionary.values()) {
            String gender = (String) personInfo.get("Genero");
            if (gender.equals("Femenino")) {
                woman++;
            } else if (gender.equals("Masculino")) {
                man++;
            }
        }
        System.out.println("Cantidad de mujeres que compraron almenos 1 producto: " + woman);
        System.out.println("Cantidad de hombres que compraron almenos 1 producto: " + man);
    }

    public static void optionC() {

    }

    public static void optionD() {

    }

    public static void optionE() {

    }

}
