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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LaboratorioUnoJava {

    private static HashMap<Integer, HashMap<String, Object>> indexDictionary = new HashMap<>();
    private static ArrayList<HashMap<String, Object>> productList = new ArrayList<>();
    private static HashSet<Integer> registeredIdNumbers = new HashSet<>();
    private static HashSet<Integer> bannedUsers = new HashSet<>();
    private static ArrayList<HashMap<String, Object>> userHistory = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> ifUserChangeQuantityIndex = new ArrayList<>();

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
                        System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 2 letra");
                    }
                } else {
                    System.out.println("Error: El nombre del producto debe empezar con mayuscula(NO SE PERMITEN 2 MAYUSCULAS EN 1 PALABRA) y debe contener almenos mas de 2 letra");
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

                boolean productExists = false;
                for (HashMap<String, Object> product : productList) {
                    String existingProductName = (String) product.get("Nombre");
                    if (existingProductName.equalsIgnoreCase(productName)) {
                        productExists = true;
                        break;
                    }
                }

                if (productExists) {
                    System.out.println("Error: El producto ya esta registrado!");
                } else {
                    break;
                }

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
            productInfo.put("Fecha de vencimiento", productDueDate);

            productList.add(productInfo);

            System.out.println("Productos Registrados: ");
            for (int i = 0; i < productList.size(); i++) {
                HashMap<String, Object> currentProductInfo = productList.get(i);
                System.out.println("Producto " + (i + 1) + ":");
                System.out.println("Nombre: " + currentProductInfo.get("Nombre"));
                System.out.println("Precio c/u: " + currentProductInfo.get("Precio"));
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

        String userName = "";
        for (HashMap<String, Object> userInfo : indexDictionary.values()) {
            if ((int) userInfo.get("Cedula") == confirmIdNumber) {
                userName = (String) userInfo.get("Nombre");
                break;
            }
        }

        while (true) {
            System.out.println("Menu de Productos:");
            // Mostrar productos disponibles
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

                    }
                } else {
                    System.out.println("Debe ingresar un numero. Intentelo de nuevo.");
                    scanner.next();
                }
            }

            if (selectedOption == productList.size() + 1) {
                System.out.println("Saliendo del menu de compra");
                break;
            } else {
                // Obtener el producto seleccionado
                HashMap<String, Object> selectedProduct = productList.get(selectedOption - 1);
                System.out.println("Ha seleccionado comprar: " + selectedProduct.get("Nombre"));

                int quantityToBuy;
                boolean quantityModified = false;
                while (true) {
                    System.out.println("Ingrese la cantidad que desea comprar:");

                    if (scanner.hasNextInt()) {
                        quantityToBuy = scanner.nextInt();
                        scanner.nextLine();
                        if (quantityToBuy > 0) {
                            boolean found = false;
                            for (HashMap<String, Object> item : cart) {
                                if (item.get("Nombre").equals(selectedProduct.get("Nombre"))
                                        && item.get("Fecha de vencimiento").equals(selectedProduct.get("Fecha de vencimiento"))) {
                                    // Actualizar la cantidad del producto existente
                                    int currentQuantity = (int) item.get("Cantidad");
                                    if (currentQuantity != quantityToBuy) {
                                        item.put("Cantidad", quantityToBuy);
                                        quantityModified = true;
                                    }

                                    found = true;
                                    if (quantityModified == true) {
                                        HashMap<String, Object> verifyQuantityModified = new HashMap<>();
                                        verifyQuantityModified.put("Nombre", userName);
                                        verifyQuantityModified.put("Cedula", confirmIdNumber);
                                        ifUserChangeQuantityIndex.add(verifyQuantityModified);
                                    }
                                }
                            }
                            // Si el producto no estaba en el carrito, sera agregado
                            if (!found) {
                                HashMap<String, Object> productToAdd = new HashMap<>(selectedProduct);
                                productToAdd.put("Cantidad", quantityToBuy);
                                cart.add(productToAdd);
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
                HashMap<String, Object> purchaseInfo = new HashMap<>();
                purchaseInfo.put("Cedula", confirmIdNumber);
                purchaseInfo.put("Nombre", selectedProduct.get("Nombre"));
                purchaseInfo.put("Cantidad", quantityToBuy);
                purchaseInfo.put("Precio total", quantityToBuy * (int) selectedProduct.get("Precio"));
                purchaseInfo.put("Fecha de vencimiento", selectedProduct.get("Fecha de vencimiento"));
                purchaseInfo.put("Fecha de compra", new Date());
                userHistory.add(purchaseInfo);

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
            System.out.println("3. Mostrar la lista de los nombres de los productos que no han sido comprados por ningun cliente");
            System.out.println("4. Mostrar el top 3 de los productos mas comprados en un rango de fechas, colocando una fecha de inicio y de fin, y mostrar el nombre del producto");
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

        // This HashMap groups the purchases by the client's ID number
        HashMap<Integer, HashMap<String, Object>> clientPurchases = new HashMap<>();

        // Iterate over the purchase history and group the information by client ID
        for (HashMap<String, Object> purchaseInfo : userHistory) {
            int idNumber = (int) purchaseInfo.get("Cedula");
            String productName = (String) purchaseInfo.get("Nombre");
            int quantity = (int) purchaseInfo.get("Cantidad");
            int totalPrice = (int) purchaseInfo.get("Precio total");
            Date purchaseDate = (Date) purchaseInfo.get("Fecha de compra");
            Date expirationDate = (Date) purchaseInfo.get("Fecha de vencimiento");

            // If the client doesn't exist in the HashMap, add them
            if (!clientPurchases.containsKey(idNumber)) {
                clientPurchases.put(idNumber, new HashMap<>());
            }

            // Get the HashMap for this client's purchases
            HashMap<String, Object> purchaseDetails = clientPurchases.get(idNumber);

            // Add or update the purchase information for this product
            if (purchaseDetails.containsKey(productName)) {
                // Update the existing purchase information
                HashMap<String, Object> existingPurchase = (HashMap<String, Object>) purchaseDetails.get(productName);
                int existingQuantity = (int) existingPurchase.get("Cantidad");
                int existingTotalPrice = (int) existingPurchase.get("Precio total");
                existingPurchase.put("Cantidad", existingQuantity + quantity);
                existingPurchase.put("Precio total", existingTotalPrice + totalPrice);
            } else {
                // Add a new purchase entry
                HashMap<String, Object> newPurchase = new HashMap<>();
                newPurchase.put("Nombre", productName);
                newPurchase.put("Cantidad", quantity);
                newPurchase.put("Precio total", totalPrice);
                newPurchase.put("Fecha de compra", purchaseDate);
                newPurchase.put("Fecha de vencimiento", expirationDate);
                purchaseDetails.put(productName, newPurchase);
            }
        }

        // Iterate over the client purchases and print the information
        for (int idNumber : clientPurchases.keySet()) {
            System.out.println("Cedula: " + idNumber);
            HashMap<String, Object> purchaseDetails = clientPurchases.get(idNumber);
            int totalAmount = 0;

            for (Map.Entry<String, Object> entry : purchaseDetails.entrySet()) {
                HashMap<String, Object> purchaseInfo = (HashMap<String, Object>) entry.getValue();
                String productName = (String) purchaseInfo.get("Nombre");
                int quantity = (int) purchaseInfo.get("Cantidad");
                int totalPrice = (int) purchaseInfo.get("Precio total");
                Date purchaseDate = (Date) purchaseInfo.get("Fecha de compra");
                Date expirationDate = (Date) purchaseInfo.get("Fecha de vencimiento");

                System.out.println("Nombre del producto: " + productName);
                System.out.println("Cantidad: " + quantity);
                System.out.println("Precio total: " + totalPrice);
                System.out.println("Fecha de compra: " + purchaseDate);
                System.out.println("Fecha de vencimiento: " + expirationDate);

                if (expirationDate.before(validateDueDate)) {
                    System.out.println("Este producto ya vencio!");
                }

                System.out.println();
                totalAmount += totalPrice;
            }

            System.out.println("Total a pagar: " + totalAmount);
            System.out.println("---------------------------------------------------");
        }
    }

    public static void optionB() {
        int woman = 0;
        int man = 0;
        // Tipo de danto que se almacena, Elemento, : separar ambos 
        //personInfo variable local que es cada "elemento" del diccionario .values obtiene toda los datos
        for (HashMap<String, Object> personInfo : indexDictionary.values()) {
            int idNumber = (int) personInfo.get("Cedula");

            // Esto verifica si el cliente ha realizado al menos una compra
            boolean hasPurchased = false;
            for (HashMap<String, Object> purchase : userHistory) {
                if ((int) purchase.get("Cedula") == idNumber) {
                    hasPurchased = true;
                    break;
                }
            }

            if (hasPurchased) {
                String gender = (String) personInfo.get("Genero");
                if (gender.equals("Femenino")) {
                    woman++;
                } else if (gender.equals("Masculino")) {
                    man++;
                }
            }
        }
        System.out.println("Cantidad de mujeres que compraron almenos 1 producto: " + woman);
        System.out.println("Cantidad de hombres que compraron almenos 1 producto: " + man);
    }

    public static void optionC() {
        HashSet<String> purchasedProducts = new HashSet<>();

        // Obtener los nombres de los productos comprados
        for (HashMap<String, Object> productInfo : userHistory) {
            String productName = (String) productInfo.get("Nombre");
            purchasedProducts.add(productName);
        }

        // Obtener los nombres de todos los productos
        HashSet<String> allProducts = new HashSet<>();
        for (HashMap<String, Object> productInfo : productList) {
            String productName = (String) productInfo.get("Nombre");
            allProducts.add(productName);
        }

        // Calcular los productos que no han sido comprados
        HashSet<String> nonPurchasedProducts = new HashSet<>(allProducts);
        nonPurchasedProducts.removeAll(purchasedProducts);

        // Imprimir la lista de productos no comprados
        System.out.println("Lista de productos no comprados:");
        for (String productName : nonPurchasedProducts) {
            System.out.println(productName);
        }
    }

    public static void optionD() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar las fechas de inicio y fin del rango
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate, endDate;

        try {
            System.out.println("Ingrese la fecha de inicio del rango (formato: dd/MM/yyyy): ");
            startDate = dateFormat.parse(scanner.nextLine());

            System.out.println("Ingrese la fecha de fin del rango (formato: dd/MM/yyyy): ");
            endDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto.");
            return;
        }

        // Diccionario para almacenar la cantidad total comprada de cada producto
        HashMap<String, Integer> productSales = new HashMap<>();

        // Iterar sobre el historial de compras y contar la cantidad de productos comprados en el rango de fechas
        for (HashMap<String, Object> purchase : userHistory) {
            Date purchaseDate = (Date) purchase.get("Fecha de compra");

            // Verificar si la fecha de compra esta dentro del rango especificado
            if (purchaseDate.after(startDate) && purchaseDate.before(endDate)) {
                String productName = (String) purchase.get("Nombre");
                int quantity = (int) purchase.get("Cantidad");

                // Actualizar el contador de ventas para este producto
                productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
            }
        }

        // Ordenar el diccionario por valores (la cantidad comprada) en orden descendente
        List<Map.Entry<String, Integer>> sortedSales = new ArrayList<>(productSales.entrySet());
        sortedSales.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Top 3 de productos mas comprados en el rango de fechas:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedSales) {
            System.out.println(entry.getKey());
            count++;
            if (count == 3) {
                break;
            }
        }
    }

    public static void optionE() {
        System.out.println("Lista de personas que cambiaron la cantidad de productos durante su compra: ");

        if (ifUserChangeQuantityIndex.isEmpty()) {
            System.out.println("No hay personas que hayan cambiado la cantidad del producto durante su compra: ");
            return;
        }
        HashSet<Integer> printedIds = new HashSet<>();

        for (HashMap<String, Object> userInfo : ifUserChangeQuantityIndex) {
            int idNumber = (int) userInfo.get("Cedula");
            String name = (String) userInfo.get("Nombre");

            if (!printedIds.contains(idNumber)) {
                System.out.println("------------------------------------------------");
                System.out.println("Cedula: " + idNumber);
                System.out.println("Nombre: " + name);
                System.out.println("------------------------------------------------");
                printedIds.add(idNumber);
            }
        }
    }
}
