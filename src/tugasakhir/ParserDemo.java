/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.util.Collection;
import java.util.List;
import java.io.StringReader;
import java.io.FileWriter;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;

class ParserDemo {

    MaxentTagger tagger = new MaxentTagger("E://Java_Project//Library//stanford-postagger-2015-12-09//models//english-left3words-distsim.tagger");
    ArrayList<String> hasilEks = new ArrayList<String>();
    ArrayList<String> hasilPars = new ArrayList<String>();
    Dataset data;

    /**
     * The main method demonstrates the easiest way to load a parser. Simply
     * call loadModel and specify the path of a serialized grammar model, which
     * can be a file, a resource on the classpath, or even a URL. For example,
     * this demonstrates loading a grammar from the models jar file, which you
     * therefore need to include on the classpath for ParserDemo to work.
     *
     * Usage: {@code java ParserDemo [[model] textFile]} e.g.: java ParserDemo
     * edu/stanford/nlp/models/lexparser/chineseFactored.ser.gz
     * data/chinese-onesent-utf8.txt
     *
     */
//  public static void main (String[] args) {
    public ParserDemo(Dataset data) {
        this.data = data;
    } // static methods only

    public ArrayList<String> getHasil() {
        return hasilEks;
    }
    
    public ArrayList<String> getHasilPars() {
        return hasilPars;
    }

    public void main(String[] args) {
        String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
//    if (args.length > 0) {
//      parserModel = args[0];
//    }
        LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);

//    if (args.length == 0) {
        demoAPI(lp);
//    } else {
//      String textFile = (args.length > 1) ? args[1] : args[0];
//      demoDP(lp, textFile);
//    }
    }

    /**
     * demoDP demonstrates turning a file into tokens and then parse trees. Note
     * that the trees are printed by calling pennPrint on the Tree object. It is
     * also possible to pass a PrintWriter to pennPrint if you want to capture
     * the output. This code will work with any supported language.
     */
//  public static void demoDP(LexicalizedParser lp, String filename) { //tidak digunakan
//    // This option shows loading, sentence-segmenting and tokenizing
//    // a file using DocumentPreprocessor.
//    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
//    GrammaticalStructureFactory gsf = null;
//    if (tlp.supportsGrammaticalStructures()) {
//      gsf = tlp.grammaticalStructureFactory();
//    }
//    // You could also create a tokenizer here (as below) and pass it
//    // to DocumentPreprocessor
//    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
//      Tree parse = lp.apply(sentence);
//      parse.pennPrint();
//      System.out.println();
//
//      if (gsf != null) {
//        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
//        Collection tdl = gs.typedDependenciesCCprocessed();
//        System.out.println(tdl);
//        System.out.println();
//      }
//    }
//  }
    /**
     * demoAPI demonstrates other ways of calling the parser with already
     * tokenized text, or in some cases, raw text that needs to be tokenized as
     * a single sentence. Output is handled with a TreePrint object. Note that
     * the options used when creating the TreePrint can determine what results
     * to print out. Once again, one can capture the output by passing a
     * PrintWriter to TreePrint.printTree. This code is for English.
     */
//  public static void demoAPI(LexicalizedParser lp) {
    public void demoAPI(LexicalizedParser lp) {
        // This option shows parsing a list of correctly tokenized words
        String[] sent = {"This", "is", "an", "easy", "sentence", "."};
        List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
        Tree parse = lp.apply(rawWords);
//    parse.pennPrint();
//    System.out.println();

        // This option shows loading and using an explicit tokenizer
//    String sent2 = "Works great. Instructions were straightforward and easy to follow. Buying a second one just in case I need it in the future.";
//    for (int i = 0; i < data.lemmaKumpulans.size(); i++){
        for (int i = 0; i < 50; i++) {
            String sent2 = data.lemmaKumpulans.get(i);
            TokenizerFactory<CoreLabel> tokenizerFactory
                    = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
            Tokenizer<CoreLabel> tok
                    = tokenizerFactory.getTokenizer(new StringReader(sent2));
            List<CoreLabel> rawWords2 = tok.tokenize();
            parse = lp.apply(rawWords2);

            TreebankLanguagePack tlp = lp.treebankLanguagePack(); // PennTreebankLanguagePack for English
            GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
            List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
            String hasilDiAtas = tdl.toString();
            hasilPars.add(hasilDiAtas);
            //    System.out.println(tdl);
            //    System.out.println();
//            int count = 0;
            for (Tree tree : parse) {
                String np = "";
                String kandidat ;
                if (tree.label().value().equals("NP")) {
                    if (tree.getLeaves().size() == 1) {
                        kandidat = tree.getChildrenAsList().toString();
//                    System.out.println("kandidat np: "+kandidat);
                        if (kandidat.matches("(.*)NN(.*)")) {
//                        System.out.println("NP: "+subtree.getChildrenAsList());
                            np = tree.getLeaves().toString().replaceAll("[^A-Za-z]", "");
//                        System.out.println(np);
                            //simpan
                            hasilEks.add(kandidat + " kata : " + np);
                        }
                    } else if (tree.getLeaves().size() == 2) {
                        //menggabungkan kata
                        for (int x = 0; x < tree.getLeaves().size(); x++) {
                            //System.out.println(subtree.getLeaves().get(x).toString());
                            np = np + tree.getLeaves().get(x).toString() + " ";
                        }
                        kandidat = tree.getChildrenAsList().toString();
//                    System.out.println("kandidat np: "+kandidat);
                        //hanya mengambil NP yang mempunyai opini
                        if (kandidat.matches("(.*)NN(.*)") == true) {
                            if ((kandidat.matches("(.*)JJ(.*)") == true) || (kandidat.matches("(.*)RB(.*)") == true)) {
//                        System.out.println("NP: "+subtree.getChildrenAsList());
                                np = np.trim().replaceAll("\\s", " ");
                                String opini = "";
                                //simpan NP sebagai aspek
                                String kandidatNP = kandidat + " kata : " + np;
//                        System.out.println(np);

                                //pencarian opini
                                String tag = tagger.tagString(np);
                                String[] tag_split = tag.split(" ");
                                int j = 0;
                                for (int z = 0; z <= 1; z++) {
                                    j++;
                                    String tag_input = tag_split[z].toString();
                                    //System.out.println(tag_input);
                                    if ((tag_split[z].matches("(.*)JJ(.*)") == true) || (tag_split[z].matches("(.*)RB(.*)") == true)) {
                                        String[] kanOpini = tag_input.split("_");
                                        opini = kanOpini[0].toString();
                                        //System.out.println("opini: "+opini);

                                        //cek kedekatan opini
//                                kelas = pmi.getKedekatanOpini(opini);
//                                p.setKelas(kelas);
                                    }
                                    //    if((tag_input.matches("(.*)JJ(.*)")==true)||(tag_input.matches("(.*)RB(.*)")==true)){
//                                System.out.println(tag);
//                            }
                                }
                                hasilEks.add(kandidatNP + " opini : " + opini);
                                
                            }
                        }
                    }
                }
            }
            // You can also use a TreePrint object to print trees and dependencies

        }
//        System.out.println(hasilPars.size());
    }

}
