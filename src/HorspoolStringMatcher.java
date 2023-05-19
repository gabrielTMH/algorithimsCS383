//public class HorspoolStringMatcher {
//    private String pattern;
//    private int[] shiftSet;
//
//    public HorspoolStringMatcher(String s) {
//        this.pattern = s;
//        this.shiftSet = new int[255];
//        for (int i = 0; i < s.length() - 1; i++) {
//            this.shiftSet[(s.charAt(i))] = s.length() - 1 - i;
//        }
//    }
//
//    public int match(String text) {
//        int j;
//        for (int i = 0; i <= text.length() - this.pattern.length(); ) {
//            for (j = this.pattern.length(); j != 0; j--) {
//                int k = j - 1;
//                if (this.pattern.charAt(k) != text.charAt(i + k)) {
//                    i = i + this.shiftSet[this.pattern.charAt(k)] + 1;
//                    break;
//                }
//            }
//            if (j == 0) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public int getShift(char c) {
//        if (this.shiftSet[(c)] <= 0) {
//            return this.pattern.length();
//        } else {
//            return this.shiftSet[(c)];
//        }
//    }
//
//    public static void main(String[] args) {
//        HorspoolStringMatcher a = new HorspoolStringMatcher("plastic");
//        int A = a.match("caaagaaanaaa");
//        System.out.println(A);
//    }
//}



public class HorspoolStringMatcher {

    private String pattern;
    private int[] shiftTable;
    public HorspoolStringMatcher(String pattern) {
        this.pattern = pattern;
        this.shiftTable = new int[128];
        for (int i = 0; i < 128; i++) {
            this.shiftTable[i] = pattern.length();
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            this.shiftTable[pattern.charAt(i)] = pattern.length() - (1 + i);
        }
    }

    public int getShift(char m) {
        return shiftTable[m];
    }

    public int match(String s) {
        int skip = 0;
        while(s.length() - skip >= pattern.length()) {
            if (s.substring(skip).startsWith(pattern)) return skip;
            skip += shiftTable[s.charAt(skip + pattern.length() - 1)];
        }
        return -1;
    }
}
