import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Feladat {
    //Az ArrayList egy dinamikus tömb, ami azt jelenti, hogy a mérete futás közben növelhető és csökkenthető. 
    //Ez ideális a fájlok beolvasásához, ahol nem tudjuk előre a beolvasandó adatok mennyiségét.
    ArrayList<Jarmu> jarmuLista = new ArrayList<>(); //Létrehoz egy új ArrayList objektumot a jarmuLista változó számára.
    ArrayList<Jarmu> opel_lista = new ArrayList<>(); //Létrehoz egy új ArrayList objektumot az opel_lista változó számára.

    public void readFile () {
        try {
            tryReadFile();
        } catch (FileNotFoundException e){
            System.err.println("Hiba! A fájl nem található!" + e.getMessage());
        }
    }

    public void tryReadFile() throws FileNotFoundException{
        File file = new File("jarmuvek_opel.csv"); //Létrehoz egy File objektumot a jarmuvek_opel.csv fájlhoz.
        Scanner sc = new Scanner(file, "utf-8"); //Létrehoz egy Scanner objektumot a fájl beolvasásához UTF-8 kódolással.
        String elsoSor = sc.nextLine(); //beolvassa az első sort, de nem próbálja meg átalakítani egész számmá
        while(sc.hasNext()){  /*Addig ismétli a ciklust, amíg van beolvasható adat a fájlban. A Scanner objektum hasNext() metódusa.
            Ellenőrzi, hogy van-e még beolvasható adat a Scanner objektumban.*/
            String line = sc.nextLine(); //Beolvassa a fájl következő sorát.
            String[] lineArray = line.split(":"); //Szétválasztja a sort : karakterek alapján, és tömbbe rendezi az elemeket.
            Jarmu jarmu = new Jarmu(); //Létrehoz egy új Jarmu objektumot.
            jarmu.az = Integer.parseInt(lineArray[0]); //Beállítja a jarmu objektum az attribútumát az lineArray[0] értékére, átalakítva egész számmá.
            jarmu.rendszam = lineArray[1];  // Beállítja a jarmu objektum rendszam attribútumát az lineArray[1] értékére.
            jarmu.marka = lineArray[2];  //Beállítja a jarmu objektum marka attribútumát az lineArray[2] értékére.
            jarmu.urtartalom = Integer.parseInt(lineArray[3]);  //Beállítja a jarmu objektum urtartalom attribútumát az lineArray[3] értékére, átalakítva egész számmá.
            jarmu.ar = Double.parseDouble(lineArray[4]);  //Beállítja a jarmu objektum ar attribútumát az lineArray[4] értékére, átalakítva lebegőpontos számmá.
            jarmuLista.add(jarmu);  // Hozzáadja a jarmu objektumot a jarmuLista-hoz.
        } 
        sc.close();  //Bezárja a Scanner objektumot.
    }
    
    public void kivonat() { //Ez a metódus deklarációja. A metódus neve kivonat, visszatérési értéke void, azaz nem ad vissza értéket.
        for(Jarmu jarmu : jarmuLista){ // Ez egy for ciklus, amely bejárja a jarmuLista listát. 
            //A ciklus minden iterációjában a jarmu változó a lista aktuális elemére fog hivatkozni.
            if(jarmu.marka.equals("Opel")){ //Ez egy feltételes utasítás, amely ellenőrzi, hogy a jarmu objektum 
                //marka attribútuma "Opel" értéket tartalmaz-e. Ha igen, akkor a ciklus törzse végrehajtódik.
                opel_lista.add(jarmu);//Ez a sor hozzáadja a jarmu objektumot az opel_lista listához.
            }
        }
    }
    
    public void fajlbaIr() {
        try {
            tryfajlbaIr(); // meghívja a tryfajlbaIr() metódust
        } catch (IOException e) {
            System.err.println("Hiba! A fájl nem írható!" +e.getMessage());
        }
    }

    public void tryfajlbaIr() throws IOException{
        FileWriter fw = new FileWriter("onlyopel.csv"); /*Létrehoz egy FileWriter objektumot a "onlyopel.csv" fájl nevével. 
        A FileWriter objektumot a fájlba íráshoz használjuk. */
        for(Jarmu jarmu : opel_lista){ //Végigiterál az opel_lista listán a for ciklus segítségével.
            fw.write(jarmu.az.toString()); /*A jarmu.azonosito attribútum értékét átalakítja String értékké a toString() 
            metódus segítségével, majd kiírja a fájlba a fw.write() metódussal. */
            fw.write(":"); //Egy kettőpontot (":") ír a fájlba a fw.write() metódussal.
            fw.write(jarmu.rendszam); 
            fw.write(":");
            fw.write(jarmu.marka);
            fw.write(":");
            fw.write(jarmu.urtartalom.toString());
            fw.write(":");
            fw.write(jarmu.ar.toString());
            fw.write("\n"); //Sorvégés karaktert ("\n") ír a fájlba a fw.write() metódussal, ezzel új sorba lépve.
        }
        fw.close(); //Bezárja a FileWriter objektumot a fw.close() metódussal. Így biztosítjuk az adatok megfelelő rögzítését a fájlban.
        
    }
}
