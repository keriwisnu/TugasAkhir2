/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

/**
 *
 * @author Keri Wisnu
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class TugasAkhir {
//ArrayList<String> kalimat = new ArrayList<String>();
//ArrayList<String> kalimatLemma = new ArrayList();
//ArrayList<String> kalimatPos = new ArrayList();
//String a = "ayam";

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Dataset data = new Dataset();
        data.preprocessing(args);
//         data.saveLemma(args);
        ParserDemo parser = new ParserDemo(data);
        parser.main(args);
        Extraction ext = new Extraction(parser, data);
        ext.extraction();
//        System.out.println("Jumlah hasil = " + ext.gethasil().size());
        for (int i = 0; i < ext.gethasil().size(); i++) {
            for (int j = 0; j < ext.gethasil().get(i).size(); j++) {
                System.out.println(ext.gethasil().get(i).get(j));
            }
        }
//        StanfordLemmatizer lemma = new StanfordLemmatizer();
//        StopwordRemoval stop = new StopwordRemoval();
//        PosTag pos = new PosTag();
////        PotongText pt = new PotongText();
//        String kalimat;
//        String kalimatLemma;
//        String kalimatPos;
//        String overall;
////        kalimat.add(a);
////        pt.potong();
////        pt.ambilKalimat();
//        
////        for (int i = 0; i < pt.jadiDoang.length; i++) {
////        kalimat = stop.removeStopword(pt.jadiDoang[i]);
////            System.out.println(kalimat(i));
////        }
//        ArrayList<ArrayList<String>> datas = Dataset.bukaFile("gg.csv");
//        
//        for (int i = 0; i < datas.size(); i++) {
//            kalimat = stop.removeStopword(datas.get(i).get(3));
//            kalimatLemma = lemma.lemmatizeSentence(kalimat);
//            kalimatPos = pos.tagPOS(kalimatLemma);
//            overall = datas.get(i).get(3);
//            System.out.println(i+1);
//            System.out.println("Overall : "+overall);
//            System.out.println("Asli : "+datas.get(i).get(3));
//            System.out.println("Kalimat : "+kalimat);
//            System.out.println("Kalimat Lemma : "+kalimatLemma);
//            System.out.println("Post Text : "+kalimatPos);
//        }

//        kalimat = stop.removeStopword(pt.jadiDoang[0]);
//        kalimatLemma = lemma.lemmatizeSentence(kalimat);
//        kalimatPos = pos.tagPOS(kalimatLemma);
//        System.out.println(pt.jadiDoang[0]);
//        System.out.println(kalimat);
//        System.out.println(kalimatLemma);
//        System.out.println(kalimatPos);
    }
}
