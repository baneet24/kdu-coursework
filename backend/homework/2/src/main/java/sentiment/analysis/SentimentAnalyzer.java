package sentiment.analysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);

    public static void main(String[] args) {
        String review = "Sorry OG, but you just lost some loyal customers. horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
        String[][] featureSet = {{"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"seambiance", "arvice", "management", "waiter", "waitress", "bartender", "staff", "server"}};
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureSetResult = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);

        printBook(featureSetResult);
    }

    public static void printBook(int[] featureSetResult){
        for (int i = 0; i < featureSetResult.length; i++) {
            logger.info(String.valueOf(featureSetResult[i]));
        }
    }


    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] opinions = new int[featureSet.length];
        int opt = 0;

        for (int i = 0; i < featureSet.length; i++) {
            String[] features = featureSet[i];
            for (String feature : features) {
                opt= getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                if(opt!=0) {
                    opinions[i] = opt;
                }
            }
        }

        return opinions;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int wasPhrasePatternResult = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        if (wasPhrasePatternResult != 0) {
            return wasPhrasePatternResult;
        }

        return checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        //Pattern is {feature} was {opinion}
        String sentence = feature + " was ";
        String featureOpinion;
        for (String posWord : posOpinionWords) {
            featureOpinion = sentence + posWord;
            if (review.contains(featureOpinion)) {
                return 1;
            }
        }
        for (String negWord : negOpinionWords) {
            featureOpinion = sentence + negWord;
            if (review.contains(featureOpinion)) {
                return -1;
            }
        }
        return 0;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        //Pattern is {opinion} {feature}
        String[] sentences = review.split("\\.");
        String featureAndOpinion;

        for(String sentence: sentences) {
            for (String positiveWord : posOpinionWords) {
                featureAndOpinion = positiveWord + " " + feature;
                if (sentence.contains(featureAndOpinion)) {
                    return 1;
                }
            }
            for (String negativeWord : negOpinionWords) {
                featureAndOpinion = negativeWord + " " + feature;
                if (sentence.contains(featureAndOpinion)) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
