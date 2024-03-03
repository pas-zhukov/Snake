package ru.pas_zhukov.services;

import java.io.*;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreFileService {
    private static final String SCORE_FILE = "./maxScore.txt";
    public static final Logger logger = LoggerFactory.getLogger(ScoreFileService.class);

    public static void saveMaxScore(Integer score) throws IOException {
        try (FileWriter fileWriter = new FileWriter(getFile(), false)) {
            fileWriter.write(score.toString());
            fileWriter.flush();
        }
    }

    public static int getMaxScore() {
        int maxScore = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFile()))) {
            String score = bufferedReader.readLine();
            try {
                maxScore = Integer.parseInt(score);
            }
            catch (NumberFormatException ex) {
                logger.warn("Can't parse score: " + score);
                return 0;
            }
        }
        catch (IOException ex) {
            logger.warn("Can't read score file");
        }
        return maxScore;
    }

    private static File getFile() throws IOException {
        File scoreFile = new File(SCORE_FILE);
        if (!scoreFile.exists()) {
            logger.warn("Score file doesn't exist, creating new one");
            scoreFile.createNewFile();
        }
        return scoreFile;
    }
}
