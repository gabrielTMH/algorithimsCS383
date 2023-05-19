package scrabble;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TrieSET;

import java.util.ArrayList;

public class AI implements ScrabbleAI{

    private GateKeeper gateKeeper;
    private TrieSET trie;
    private ArrayList<Location> hooks;

    @Override
    public void setGateKeeper(GateKeeper gateKeeper) {
        this.gateKeeper = gateKeeper;
        In in = new In("words.txt");
        hooks = new ArrayList<>();
        this.trie = new TrieSET();
        while (!in.isEmpty()) {
            String key = in.readString();
            trie.add(key);
        }
    }

    @Override
    public ScrabbleMove chooseMove() {
        if (gateKeeper.getSquare(Location.CENTER) == Board.DOUBLE_WORD_SCORE) {
          //play bingo word with last tile landing on center

        }
        //findvalidmoveareas()return an array of locations
        //a locations avilable length is determined by how far a word can go
        // before hitting another placed tile and how many tiles it will be adjacent to
       //if(can play bingo word){
        //play bingo word
        //}

    }



    public ArrayList<Location> boardchange(String prev, String now){
        //this is finding where the last word was played these locations will be checked for hook validity
        //possibly remove invalid hooks in this method as well
        ArrayList<Location> res = new ArrayList<>();
        for(int i =0;i<240;i++){
            //finds the start of the last played word
            //location of the tile will be row= (int)i/15  col = i%16
            if(prev.charAt(i)!=now.charAt(i)){
                Location start = new Location((int)i/15,i%16);
                res.add(start);
                Location horizontalcheck = start.neighbor(Location.HORIZONTAL);
                Location vertcheck = start.neighbor(Location.VERTICAL);
                int j =1;
                        while(isAlphabetical(horizontalcheck)&&now.charAt(i+j)!='\n'){
                            if(prev.charAt(i+j)!=now.charAt(i+j)){
                                res.add(horizontalcheck);
                            }
                            horizontalcheck=horizontalcheck.neighbor(Location.HORIZONTAL);
                            j++;
                        }
               j=1;
                while(isAlphabetical(vertcheck)&& i+j<240){
                    if(prev.charAt(i+j)!=now.charAt(i+j)){
                        res.add(vertcheck);
                    }
                    vertcheck=vertcheck.neighbor(Location.VERTICAL);
                    j+=16;
                }
            }
        }
        return res;
    }
    public boolean isValidHook(Location loc){
        ArrayList<Character> hand = gateKeeper.getHand();
        char value = gateKeeper.getSquare(loc);
    return false;
    }

    public boolean isLetter(char c){
        return (c>=65&&c<=122);
    }

    public boolean isAlphabetical(Location l){
        return isLetter(gateKeeper.getSquare(l));
    }

    public int getSlot(Location x,Location Direction){
        Location endBound= new Location(x.getRow(),x.getColumn());
        while(gateKeeper.getSquare(endBound)==' '){

        }
    }
    public PlayWord bestWord(Location l){
        ArrayList<Character> hand = gateKeeper.getHand();
        String Board = gateKeeper.toString();
        int horizdistance=l.getRow()-1;
        int vertdistance =l.getColumn()-1;
        char current;
        String hors = "";
        String verts ="";
        for(int i =0;i<15;i++){
            current=Board.charAt(i+vertdistance*16);
            if(isLetter(current)){
                hors+=current;
            }else{
                hors+=".";
            }
        }
        for(int i =0;i<15;i++){
            current=Board.charAt(i+vertdistance*16);
            if(isLetter(current)){
                hors+=current;
            }else{
                hors+=".";
            }
        }

    }

}
//look at placement options for special tiles

//find placement options for board not concerned with special tiles
    //look for open square without letters check proximity around them?
    //