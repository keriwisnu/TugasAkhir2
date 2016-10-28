/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 *
 * @author Ega
 */
public class StanfordLemmatizer {
    protected StanfordCoreNLP pipeline;

    public StanfordLemmatizer() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        /*
         * This is a pipeline that takes in a string and returns various analyzed linguistic forms. 
         * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator), 
         * and then other sequence model style annotation can be used to add things like lemmas, 
         * POS tags, and named entities. These are returned as a list of CoreLabels. 
         * Other analysis components build and store parse trees, dependency graphs, etc. 
         * 
         * This class is designed to apply multiple Annotators to an Annotation. 
         * The idea is that you first build up the pipeline by adding Annotators, 
         * and then you take the objects you wish to annotate and pass them in and 
         * get in return a fully annotated object.
         * 
         *  StanfordCoreNLP loads a lot of models, so you probably
         *  only want to do this once per execution
         */
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String lemmatizeSentence(String sentenceText)
    {
        List<String> lemmas = new LinkedList<>();
        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(sentenceText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
            }
        }
        String result = "";
        for (int i = 0; i < lemmas.size(); i++) {
            if (i==lemmas.size()-1 && !lemmas.get(i).equals("")) {
                result += lemmas.get(i);
            }else if (!lemmas.get(i).equals("")) {
                result += lemmas.get(i) + " ";
            }
        }
        return result;
    }
    
    public String[] lemmatizeDocument(String[] listSentences){
        String[] result = new String[listSentences.length];
        for (int i = 0; i < listSentences.length; i++) {
            result[i] = lemmatizeSentence(listSentences[i]);
        }
        return result;
    }

}
