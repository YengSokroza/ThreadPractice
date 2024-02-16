public class SentencePrinter {
    public static void main(String[] args) {
        String sentence1 = "Welcome to CSTAD";
        String sentence2 = "Focus on the step in front of you not the whole staircase.";
        String sentence3 = "download";
        String sentence4 = "Downloading";
        String sentence5 = "100%  Complete Successfully";

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                printSign(true, sentence1);
                printSentence(sentence1);
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                printSign(true, sentence2);
                printSentence(sentence2);
            }
        });
        Thread thread3 = new Thread(() -> {
            synchronized (lock) {
                printSentence(sentence4);
                printSign(false, sentence4);
                printSentence(sentence5);

            }
        });

        try {
            thread1.start();
            thread1.join();

            thread2.start();
            thread2.join();

            thread3.start();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void printSign(boolean sign, String sentence) {
        int sentenceLength = sentence.length();
        if (sign) {
            String asterisk = "*".repeat(sentenceLength);
            System.out.println(asterisk);
        } else {
            String point = ".".repeat(sentenceLength);
            if (sentence.equalsIgnoreCase("Downloading")) {
                System.out.print(point);
            }
        }
    }

    static void printSentence(String sentence) {
        for (char c : sentence.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!sentence.equalsIgnoreCase("Downloading")) {
            System.out.println();
        }

    }
}

