package hu.suxy.nicknamegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NicknameGenerator {

    private List<LetterType> config;
    private List<String> vowels;
    private List<String> consonant;
    private List<String> dontCare;

    public NicknameGenerator() {
        this.consonant = Arrays.asList(
                "B", "C", "CS", "D", "DZ",
                "DZS", "F", "G", "GY", "H",
                "J", "K", "L", "LY", "M",
                "N", "NY", "P", "Q", "R",
                "S", "SZ", "T", "TY", "V",
                "W", "X", "Y", "Z", "ZS");
        this.vowels = Arrays.asList(
                "A", "Á",
                "E", "É",
                "I", "Í",
                "O", "Ó", "Ö", "Ő",
                "U", "Ú", "Ü", "Ű");
        this.dontCare = new ArrayList<>();
        this.dontCare.addAll(consonant);
        this.dontCare.addAll(vowels);
        this.config = new ArrayList<>();
        this.config.add(LetterType.DONT_CARE);
        this.config.add(LetterType.DONT_CARE);
        this.config.add(LetterType.DONT_CARE);
        this.config.add(LetterType.DONT_CARE);
    }

    public String generateNickname() {
        StringBuilder sb = new StringBuilder();
        for (LetterType letterType : config) {
            switch (letterType) {
                case DONT_CARE:
                    sb.append(randomFrom(dontCare));
                    break;
                case VOWEL:
                    sb.append(randomFrom(vowels));
                    break;
                case CONSONANT:
                    sb.append(randomFrom(consonant));
                    break;
            }
        }
        return sb.toString();
    }

    public List<LetterType> getConfig() {
        return config;
    }

    public void setConfig(List<LetterType> config) {
        this.config = config;
    }

    private String randomFrom(List<String> letterList) {
        return letterList.get((int) (Math.random() * letterList.size()));
    }
}
