public class App {
    public static void main(String[] args) throws Exception { 
        Feladat fel = new Feladat(); //meghívja a Feladat osztály readFile metódusát.
        fel.readFile();
        fel.kivonat();
        fel.fajlbaIr();

    }
}
