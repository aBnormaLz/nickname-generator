package hu.suxy.nicknamegenerator;

import java.util.Arrays;
import java.util.List;

class NicknameGenerator {

    private List<String> vowels;
    private List<String> consonant;

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
    }

    public String generateNickname() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomFrom(consonant));
        sb.append(randomFrom(vowels));
        sb.append(randomFrom(consonant));
        sb.append(randomFrom(vowels));
        return sb.toString();
    }

    private String randomFrom(List<String> letterList) {
        return letterList.get((int) (Math.random() * letterList.size()));
    }
}
