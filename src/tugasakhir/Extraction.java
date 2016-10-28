/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;

/**
 *
 * @author Keriwisnu
 */
public class Extraction {

    MaxentTagger tagger = new MaxentTagger("E://Java_Project//Library//stanford-postagger-2015-12-09//models//english-left3words-distsim.tagger");
    ArrayList<String> hasilPars = new ArrayList<String>();
    ArrayList<ArrayList<String>> hasilExt = new ArrayList<ArrayList<String>>();
    Dataset data;

    public Extraction(ParserDemo parser, Dataset data) {
        hasilPars = parser.getHasilPars();
        this.data = data;
    }

    public ArrayList<ArrayList<String>> gethasil() {
        return hasilExt;
    }

    public void extraction() {
//        int count = 0;
        for (int i = 0; i < hasilPars.size(); i++) {
//            count++;
            ArrayList<String> he = new ArrayList<String>();

            //ambil kalimat hanya untuk ditampilkan
            //System.out.println(i+". "+sentence);
            //ambil hasil parsing
            String sent = hasilPars.get(i);
            //System.out.println(RecordData.get(i).getStopWord());
//            System.out.println("Parsing: " + sent);

            //System.out.print("Fitur: ");
            //jika hasil parsing mengandung amod atau nsubj
            if ((sent.toString().matches("(.*)(amod|nsubj)(.*)")) == true) {
//                System.out.println(count);
                String[] p = sent.split("\\), "); //tokenisasi setiap hasil parser
                for (int a = 0; a < p.length; a++) {
                    String[] str = p[a].toString().split("\\(");
                    String ss = str[0]; //pemisahan tag parser dengan isinya

                    if (ss.matches("amod") == true) {
                        String parse = str[1].replaceAll("[^A-Za-z]", " ");
                        String parse_tag = tagger.tagString(parse);

                        //rule
                        String x = parse_tag;
                        String[] split = x.split(" ");
                        if ((split.length == 2) && (split[0].matches("(.*)NN") == true) && (split[1].matches("(.*)JJ") == true)) {
                            String pe = "";
//                        System.out.println(ss);
//                        System.out.println(parse_tag);
                            //aspek
                            String aa = split[0]; //aspek fitur
                            //memisah tag
                            String[] aa1 = aa.split("_");
                            String aspek = aa1[0];
                            pe += " kata : " + aspek;
                            //System.out.println("aspek: " + aspek);
                            //opini
                            String ab = split[1]; //opini
                            //memisah tag
                            String[] ab1 = ab.split("_");
                            String opini = ab1[0];
                            pe += " opini : " + opini;

                            //save tag parser and pos tag
                            pe += " " + ss + "(" + parse + ")";
//                        System.out.println("parser: "+pe.getTagParser());
                            pe += "(" + split[0] + ", " + split[1] + ")";
//                        System.out.println("pos tag: "+pe.getTagPOS());
                            he.add(pe);
                        } else if ((split[0].matches("(.*)VB") == true) && (split[1].matches("(.*)RB") == true)) {
                            String pe = "";
//                        System.out.println(ss);
//                        System.out.println(parse_tag);
                            //aspek
                            String aa = split[0]; //aspek fitur
                            //memisah tag
                            String[] aa1 = aa.split("_");
                            String aspek = aa1[0];
                            pe += " kata : " + aspek;
                            //System.out.println("aspek: " + aspek);
                            //opini
                            String ab = split[1];
                            //memisah tag
                            String[] ab1 = ab.split("_");
                            String opini = ab1[0];
                            pe += " opini : " + opini;

                            //save tag parser and pos tag
                            pe += " " + ss + "(" + parse + ")";
//                        System.out.println("parser: "+pe.getTagParser());
                            pe += "(" + split[0] + ", " + split[1] + ")";
//                        System.out.println("pos tag: "+pe.getTagPOS());
                            he.add(pe);
                        }

                    } else if (ss.matches("nsubj") == true) {
                        //System.out.println(ss);
                        String parse = str[1].replaceAll("[^A-Za-z]", " ");
                        String parse_tag = tagger.tagString(parse);
                        String x = parse_tag;
                        String[] split = x.split(" ");
                        //rule
                        if ((split[0].matches("(.*)NN")) && (split[1].matches("(.*)JJ") == true)) {
                            String pe = "";
                            String aa = split[0]; //aspek fitur
                            //memisah tag
                            //aspek
                            String[] aa1 = aa.split("_");
                            String aspek = aa1[0];
                            pe += " kata : " + aspek;
                            //System.out.println("aspek: " + aspek);
                            //opini
                            String ab = split[1]; //opini
                            //memisah tag
                            String[] ab1 = ab.split("_");
                            String opini = ab1[0];
                            pe += " opini : " + opini;

                            //save tag parser and pos tag
                            pe += " " + ss + "(" + parse + ")";
//                        System.out.println("parser: "+pe.getTagParser());
                            pe += "(" + split[0] + ", " + split[1] + ")";
//                        System.out.println("pos tag: "+pe.getTagPOS());
                            he.add(pe);
                        }
                    }
                }
            }
            hasilExt.add(he);
        }
    }
}
