/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Keri Wisnu
 */
public class Dataset {

    StanfordLemmatizer lemma = new StanfordLemmatizer();
    StopwordRemoval stop = new StopwordRemoval();
    PosTag pos = new PosTag();

    String kalimat;
    String kalimatLemma;
    ArrayList<String> lemmaKumpulans = new ArrayList<>();
    String kalimatPos;
    String overall;

    /**
     * @param args the command line arguments
     */
    static ArrayList<ArrayList<String>> bukaFile(String namafile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(namafile));

        ArrayList<ArrayList<String>> datas = new ArrayList<>();

        //Set the delimiter used in file
        scanner.useDelimiter(";");

        ArrayList<String> data = new ArrayList<>();
        while (scanner.hasNext()) {
            String baca = scanner.next();
            if (baca.contains("\n")) {
                datas.add(data);
                data = new ArrayList<>();
            } else {
                data.add(baca);
            }
        }

        //Do not forget to close the scanner  
        scanner.close();
        return datas;
    }

    public void saveLemma(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("target.txt")) {
            for (String str : lemmaKumpulans) {
                writer.write(str);
                writer.write("\n");
            }
        }
    }

    public void preprocessing(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<String>> datas = Dataset.bukaFile("gg5.csv");

        for (int i = 0; i < datas.size(); i++) {
            kalimat = stop.removeStopword(datas.get(i).get(3));
            kalimatLemma = lemma.lemmatizeSentence(kalimat);
            lemmaKumpulans.add(kalimatLemma);
            kalimatPos = pos.tagPOS(kalimatLemma);
            overall = datas.get(i).get(4);
//            System.out.println(i + 1);
//            System.out.println("Overall : " + overall);
//            System.out.println("Asli : " + datas.get(i).get(3));
//            System.out.println("Kalimat : " + kalimat);
//            System.out.println("Kalimat Lemma : " + kalimatLemma);
//            System.out.println("Post Tag : " + kalimatPos);
        }
    }
}
