public class WordFind {

    public static int find(String text, String pattern){

        for(int i =0; i<text.length()-pattern.length();i++){
            if(text.substring(i,(pattern.length()+i)).equals(pattern)){
                return i;
            }

        }
        return -1;
    }

    public static int BMHFind(String text,String pattern){
        return 0;
    }
    public static int GetShift(){
        return 0;
    }
}
