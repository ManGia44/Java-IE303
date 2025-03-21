import java.util.*;
import java.io.*;

public class bai4 {

    public static Map<String, Integer> vocab = new HashMap<String, Integer>();
    public static Map<String, Integer> corpus = new HashMap<String, Integer>();
    public static Map<String, Integer> pairCorpus = new HashMap<String, Integer>();
    public static Double[] probs;
    public static Double[][] conditionalProbs;

    public static void readFile() {
        try {
            Vector<String> lines = new Vector<String>();
            File file = new File("./UIT-ViOCD (1).txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lines.addElement(line);
            }
            fileScanner.close();

            for (String line : lines) {
                // remove line break \n, \r and tab \t
                line = line.replace("\n", "").replace("\r", "").replace("\t", "");
                // remove all leading spaces
                line = line.replaceAll("^\\s+", "");
                // remove all ending spaces
                line = line.replaceAll("\\s+$", "");
                // lowering
                line = line.toLowerCase();

                // collecting words
                int wordId = vocab.size();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (corpus.containsKey(word)) {
                        corpus.put(word, corpus.get(word) + 1);
                    } else {
                        vocab.put(word, wordId);
                        corpus.put(word, 1);
                        wordId++;
                    }
                }

                // collecting pairs of words
                for (int i = 0; i < words.length - 1; i++) {
                    String words_ij = words[i] + "_" + words[i + 1];
                    if (pairCorpus.containsKey(words_ij)) {
                        pairCorpus.put(words_ij, pairCorpus.get(words_ij) + 1);
                    } else {
                        pairCorpus.put(words_ij, 1);
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!");
            fileNotFoundException.printStackTrace();
        }
    }

    public static void constructSingleProb() {
        // determine the total number of words in the dataset
        int totalWords = 0;
        for (Map.Entry<String, Integer> item : corpus.entrySet()) {
            totalWords += item.getValue();
        }

        // calculating the probability of each word
        probs = new Double[vocab.size()];
        for (Map.Entry<String, Integer> item : corpus.entrySet()) {
            String word = item.getKey();
            Integer wordCount = corpus.get(word);

            Integer wordId = vocab.get(word);

            // determining the P(w)
            probs[wordId] = (double) wordCount / totalWords;
        }
    }

    public static void constructConditionalProb() {
        // calculating the probability of each pair of words
        conditionalProbs = new Double[vocab.size()][vocab.size()];
        for (Map.Entry<String, Integer> item_i : corpus.entrySet()) {
            for (Map.Entry<String, Integer> item_j : corpus.entrySet()) {
                // get the information of word i
                String word_i = item_i.getKey();
                Integer wordId_i = vocab.get(word_i);

                // get the information of word j
                String word_j = item_j.getKey();
                Integer wordId_j = vocab.get(word_j);

                // determining the P(w_j | w_i)
                String wordKey_ij = word_i + "_" + word_j;
                if (pairCorpus.containsKey(wordKey_ij)) {
                    Integer wordCount_ij = pairCorpus.get(wordKey_ij);
                    conditionalProbs[wordId_i][wordId_j] = (double) wordCount_ij / corpus.get(word_i);
                } else {
                    conditionalProbs[wordId_i][wordId_j] = 1e-20;
                }
            }
        }
    }

    public static void training() {
        constructSingleProb();
        constructConditionalProb();
    }

    public static Vector<String> inferring(String w0) {
        Vector<String> words = new Vector<String>();
        words.add(w0);
        if (!vocab.containsKey(w0)) {
            System.out.println("Từ '" + w0 + "' không có trong từ điển!");
            return words;
        }

        Integer w0Idx = vocab.get(w0);
        for (int t = 1; t <= 4; t++) { // changed to 4 to have a max of 5 words
            // determine the word that gives the highest probability P(w0, w1, ..., wt)
            String maxWord = "";
            double maxProb = 0.0;
            int w1Idx = 0;
            for (Map.Entry<String, Integer> item : vocab.entrySet()) {
                w1Idx = item.getValue();
                double prob = conditionalProbs[w0Idx][w1Idx];
                if (prob > maxProb) {
                    maxWord = item.getKey();
                    maxProb = prob;
                }
            }
            if (maxWord.isEmpty() || maxProb == 1e-20) {
                break; // No next word found or probability is too low
            }
            words.add(maxWord);
            w0Idx = vocab.get(maxWord);
        }

        return words;
    }

    public static void main(String[] args) throws Exception {
        readFile();
        training();
        Vector<String> predicted_words = inferring("ngu");
        String sentence = String.join(" ", predicted_words);
        System.out.println(sentence);
    }
}