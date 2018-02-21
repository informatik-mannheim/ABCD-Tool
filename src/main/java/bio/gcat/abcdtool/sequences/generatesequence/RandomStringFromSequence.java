package bio.gcat.abcdtool.sequences.generatesequence;

public class RandomStringFromSequence implements RandomString {


    /**
     * creates a random sequence from a sequence, adding chunks to each other until the desired length is reached
     * @param sequence
     * @param length  length of the desired sequence
     * @param chunkSize  the chunks taken from the original sequence
     * @return
     */
    public String randomString(String sequence,int length,int chunkSize) {

        String temp="";
        for (int i = 0; i < length/chunkSize; i++) {
            int pos = (int) (Math.random() * sequence.length());
            temp += sequence.substring(pos, pos + chunkSize);

        }
        return temp;
    }

    @Override
    public String randomString(int length) {
        String sequence = new RandomStringGenerator().randomString(length);
        sequence= randomString(sequence,sequence.length(),20);
        return sequence;
    }
}
