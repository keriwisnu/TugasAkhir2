/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tugasakhir;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 *
 * @author Keri Wisnu
 */
public class PosTag {
    private MaxentTagger tagger;
    
    public MaxentTagger getTagger() {
        return tagger;
    }
    
    public PosTag(){
        tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
    }
    
    public String[] tagPOS(String[] sentences){
        for(int i=0; i<sentences.length; i++){
            sentences[i] = tagger.tagString(sentences[i]);
        }
        return sentences;
    }
    
    public String tagPOS(String sentence){
        return tagger.tagString(sentence);
    }
    
    public String[] removeNoise(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\bnt_NN\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bll_NN\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bnt_NNS\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bll_NNS\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bve_NN\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bve_NNS\\b", "");
            sentences[i] = sentences[i].replaceAll("\\bam_NN\\b", "");
            sentences[i] = sentences[i].replaceAll("\\s\\s*", " ");
            sentences[i] = sentences[i].replaceAll("^\\s", "");
        }
        return sentences;
    }
    
    public String[] removeAdjectives(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_JJ\\b", "");
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_JJ[A-Z]\\b", "");
        }
        return sentences;
    }
    
    public String[] removeCardinalNumber(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_CD\\b", "");
        }
        return sentences;
    }
    
    public String[] removeConCor(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_CC\\b", "");
        }
        return sentences;
    }
    
     public String[] removeList(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_LS\\b", "");
        }
        return sentences;
    }
     
     public String[] removeVerb(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_VB[A-Z]\\b", "");
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_VB\\b", "");
        }
        return sentences;
    }
    
     public String[] removeAdverb(String[] sentences){
        for(int i=0;i<sentences.length;i++){
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_RB\\b", "");
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_RP\\b", "");
            sentences[i] = sentences[i].replaceAll("\\b[a-z0-9]*_RB[RS]\\b", "");
        }
        return sentences;
    }
}
