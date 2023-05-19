package scrabble;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieSET;

import java.util.ArrayList;
import java.util.Iterator;

public class AIcur implements ScrabbleAI {

    private GateKeeper gateKeeper;
    private TrieSET trie;
    private ArrayList<Location> hooks;
    private static final boolean[] ALL_TILES = {true, true, true, true, true, true, true};
    private String boardTemp;


    //we are setting our trie up here for eaiser word finding
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
        boardTemp = gateKeeper.toString();
    }

    @Override
    public ScrabbleMove chooseMove() {
        //StdOut.print(gateKeeper.getHand());
        if (gateKeeper.getSquare(Location.CENTER) == Board.DOUBLE_WORD_SCORE) {
            //play bingo word with last tile landing on center
            return firstWord(Location.CENTER);
        }
        //findvalidmoveareas()return an array of locations
        //a locations avilable length is determined by how far a word can go
        // before hitting another placed tile and how many tiles it will be adjacent to
        //if(can play bingo word){
        //play bingo word
        //}
        String oldBoard = boardTemp;
        boardTemp = gateKeeper.toString();
        hooks.addAll(boardchange(oldBoard, boardTemp));
        hooks = hooksCull(hooks);
        return bestWord();
    }
    public ArrayList<Location> validHook(Location l){
        ArrayList<Location> res= new ArrayList<>();
        if(l.neighbor(Location.HORIZONTAL).isOnBoard()&&!isAlphabetical(l.neighbor(Location.HORIZONTAL))){
            res.add(l.neighbor(Location.HORIZONTAL));
        }if(l.antineighbor(Location.HORIZONTAL).isOnBoard()&&!isAlphabetical(l.antineighbor(Location.HORIZONTAL))){
            res.add(l.antineighbor(Location.HORIZONTAL.opposite()));
        }if(l.neighbor(Location.VERTICAL).isOnBoard()&&!isAlphabetical(l.neighbor(Location.VERTICAL))){
            res.add(l.neighbor(Location.VERTICAL));
        }if(l.antineighbor(Location.VERTICAL).isOnBoard()&&!isAlphabetical(l.antineighbor(Location.VERTICAL))){
            res.add(l.antineighbor(Location.VERTICAL));
        }
        return res;
    }
    public ScrabbleMove bestWord() {
        String pattern = "";
        int bestScore = -1;
        PlayWord bestmove = null;
        int score;
        for (int i = 0; i < hooks.size(); i++) {
            for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                for(Location l : validHook(hooks.get(i))) {
                    int dis = getSlot(l, direction.opposite())-1;
                    if (dis > 7) {
                        dis = 7;
                    }
                    for (int j = 0; j < dis; j++) {
                        pattern += ".";
                    }
                    Iterable<String> wordlist = trie.keysThatMatch(pattern);
                    wordlist = cull(wordlist, gateKeeper.getHand());
                    int length = pattern.length();
                    while (!wordlist.iterator().hasNext() && length > 1) {
                        length--;
                        pattern = pattern.substring(0, length);
                        wordlist = trie.keysThatMatch(pattern);
                        wordlist = cull(wordlist, gateKeeper.getHand());
                    }
                    for (String S : wordlist) {
                        try {

                            //because this is the first word, it will always be legal if we only use the tiles in our hand
                            gateKeeper.verifyLegality(S, l, direction.opposite());
                            score = gateKeeper.score(S, l, direction.opposite());
                            if (score > bestScore) {
                                bestScore = score;
                                bestmove = new PlayWord(S, l, direction.opposite());
                            }
                        } catch (IllegalMoveException e) {

                            // It wasn't legal; go on to the next one
                        }
                    }
                    //s=bestMoveForHook(hooks.get(i));
                    //verify legality of move with s
                    //check score of move with s
                    //int score = gateKeeper.score(the move);

                }
            }
        }
        if(bestmove==null){
            return find2();
            //return new ExchangeTiles(ALL_TILES);
        }
       // StdOut.print("\nbest move is trying to be"+bestmove.direction+" "+bestmove.word+" "+bestmove.location);
        return bestmove;
    }

    private ArrayList<Location> hooksCull(ArrayList<Location> hooks) {
        ArrayList<Location> res = hooks;
        for (int i = 0; i < hooks.size(); i++) {
            if(!isValidHook(hooks.get(i))){
                res.remove(i);
            }
        }
        return res;
    }


    public ArrayList<Location> boardchange(String prev, String now){
        //this is finding where the last word was played these locations will be checked for hook validity
        //possibly remove invalid hooks in this method as well
        ArrayList<Location> res = new ArrayList<>();
        for(int i =0;i<240;i++){
            //finds the start of the last played word
            //location of the tile will be row= (int)i/15  col = i%16
            if(prev.charAt(i)!=now.charAt(i)){
                Location start = new Location(((int)i/16),i%16);
                //StdOut.print("\nstart = "+start+prev.charAt(i)+"!="+now.charAt(i));
                if(!res.contains(start)) {
                    res.add(start);
                }
                int j =1;
                if(start.neighbor(Location.HORIZONTAL).isOnBoard()) {
                    Location horizontalcheck = start.neighbor(Location.HORIZONTAL);
                    while(isAlphabetical(horizontalcheck)&&now.charAt(i+j)!='\n'){
                        if(!(res.contains(horizontalcheck))&&prev.charAt(i+j)!=now.charAt(i+j)){
                            res.add(horizontalcheck);
                        }
                        if(horizontalcheck.neighbor(Location.HORIZONTAL).isOnBoard()) {
                            horizontalcheck = horizontalcheck.neighbor(Location.HORIZONTAL);
                            j++;
                        }else{
                           break;
                        }
                    }
                }
                if(start.neighbor(Location.VERTICAL).isOnBoard()){
                    Location vertcheck = start.neighbor(Location.VERTICAL);
                    j=16;
                    while(isAlphabetical(vertcheck)&& i+j<240){
                        if(!(res.contains(vertcheck))&&prev.charAt(i+j)!=now.charAt(i+j)){
                            res.add(vertcheck);
                        }
                        if(vertcheck.neighbor(Location.VERTICAL).isOnBoard()) {
                            vertcheck = vertcheck.neighbor(Location.VERTICAL);
                            j += 16;
                        }else{
                            break;
                        }
                    }
                }
            }

        }
        return res;
    }
    public boolean isValidHook(Location loc){
        ArrayList<Character> hand = gateKeeper.getHand();
        char value = gateKeeper.getSquare(loc);
        ArrayList<Location> neighbors = new ArrayList<Location>();
        neighbors.add(loc.neighbor(Location.HORIZONTAL));
        neighbors.add(loc.neighbor(Location.VERTICAL));
        neighbors.add(loc.antineighbor(Location.HORIZONTAL));
        neighbors.add(loc.antineighbor(Location.VERTICAL));
        if(!isAlphabetical(loc)){
            return false;
        }
        boolean boxed=true;
        for (int i = 0; i < 4; i++) {
            if(neighbors.get(i).isOnBoard()&&!isAlphabetical(neighbors.get(i))){
                boxed=false;
            }
        }
        if(boxed){return false;}
        return true;
    }

    public boolean isLetter(char c){
        return (c>=65&&c<=122);
    }

    public boolean isAlphabetical(Location l){
        return isLetter(gateKeeper.getSquare(l));
    }

    public int getSlot(Location x,Location Direction){
        int res=0;
        Location endBound= new Location(x.getRow(),x.getColumn());
        if(!endBound.neighbor(Direction).isOnBoard()){
            return 0;
        }
        endBound=endBound.neighbor(Direction);
        while(gateKeeper.getSquare(endBound)==' '&&endBound.neighbor(Direction).isOnBoard()){
            res++;
            endBound=endBound.neighbor(Direction);
        }
        return res;
    }
    public int getAntiSlot(Location x,Location Direction){
        int res=0;
        Location endBound= new Location(x.getRow(),x.getColumn());
        endBound=endBound.antineighbor(Direction);
        while(gateKeeper.getSquare(endBound)==' '&&endBound.antineighbor(Direction).isOnBoard()){
            res++;
            endBound=endBound.antineighbor(Direction);
        }
        return res;
    }

    public ScrabbleMove firstWord(Location l){
        //if possible, play the highest scoring word from the center to the double letter space
        String bestWord = "";
        int bestScore = -1;
        ArrayList<Character> hand = gateKeeper.getHand();
        int length = 7;
        String pattern = ".......";
        Iterable<String> wordList= trie.keysThatMatch(pattern);
        wordList=cull(wordList,hand);
        while(!wordList.iterator().hasNext()&&length>1){
            length--;
            pattern=pattern.substring(0,length);
            wordList=trie.keysThatMatch(pattern);
            wordList=cull(wordList,hand);
        }
        if(length<2){
            return new ExchangeTiles(ALL_TILES);//we can still optimize tile exchange
        }
        for (String s:wordList) {
            //because this is the first word, it will always be legal if we only use the tiles in our hand
            int score = gateKeeper.score(s, Location.CENTER, Location.HORIZONTAL);
            if (score > bestScore) {
                bestScore = score;
                bestWord = s;
            }
        }
        //StdOut.print("\nbest word"+bestWord);
        return new PlayWord(bestWord,Location.CENTER, Location.HORIZONTAL);
    }

    /**
     * This method removes all strings in the wordList that cannot be made with the tiles in the player's hand.
     */
    private Iterable<String> cull(Iterable<String> wordList, ArrayList<Character> hand) {
        //i think later on we're going to want to add the hooks to the "hand" here.
        Queue<String> results = new Queue<String>();
        for (String s:wordList) {
            if(hasAll(s,hand)){
                results.enqueue(s);
            }
        }
        return results;
    }


    private boolean hasAny(String str, ArrayList<Character> hand) {
        for (int i = 0; i < str.length(); i++) {
            if(hand.contains(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private boolean hasAll(String str, ArrayList<Character> hand) {
        //i'm using hand.size because we will add the hoods to the "hand" in most turns
        String handStr = "";
        for (int i = 0; i < hand.size(); i++) {
            handStr+=hand.get(i);
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(!handStr.contains(""+c)){
                return false;
            }
            else {
                //remove the character used so that we don't double up on single tiles
                int index=handStr.indexOf(c);
                handStr=handStr.substring(0,index)+handStr.substring(index+1);
            }
        }
        return true;
    }
    public PlayWord bestMoveForHook(Location l){
        ArrayList<Character> hand = gateKeeper.getHand();
        int hordis = getSlot(l.neighbor(Location.VERTICAL),Location.HORIZONTAL);
        int vertdis = getSlot(l.neighbor(Location.HORIZONTAL),Location.VERTICAL);
        int dis;
        Location dir;
        PlayWord bestmove=null;
        String pattern="";
            if(hordis>vertdis){
                dis=hordis;
                dir= Location.HORIZONTAL;
            }else {
                dis = vertdis;
                dir=Location.VERTICAL;
            }
            if(dis>7){
                dis=7;
            }
            for(int i =0;i<dis;i++){
               pattern+=".";
            }
        Iterable<String> wordlist = trie.keysThatMatch(pattern);
            wordlist = cull(wordlist,hand);
            int length = pattern.length();
        while(!wordlist.iterator().hasNext()&&length>1){
            length--;
            pattern=pattern.substring(0,length);
            wordlist=trie.keysThatMatch(pattern);
            wordlist=cull(wordlist,hand);
        }
        if(length<2){
            return null;
        }
        int bestScore=-1;

        for (String s:wordlist) {
            try {
                //because this is the first word, it will always be legal if we only use the tiles in our hand
                gateKeeper.verifyLegality(s, l, dir);
                int score = gateKeeper.score(s, l, dir);
                if (score > bestScore) {
                    bestScore = score;
                    bestmove = new PlayWord(s,l,dir);
                }
            }catch(IllegalMoveException e) {
                // It wasn't legal; go on to the next one
            }
        }
        return bestmove;
    }
    public char[] findTwoletter(Location l){
        String a = ""+gateKeeper.getSquare(l);
       Iterable<String> wordlist = trie.keysThatMatch(a+".");
        ArrayList<Character> hand = gateKeeper.getHand();

       wordlist= cull(wordlist,hand );
       int i =0;
       char[] valid=new char[7];
       for(String word: wordlist){
           valid[i]=word.charAt(1);
         //   hand=hand.remove(hand.indexOf(valid[i]));
        }
return valid;
    }

   /* public String bestWordForHook(Location l){
        ArrayList<Character> hand = gateKeeper.getHand();
        String Board = gateKeeper.toString();
        //String Board =
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
            current=Board.charAt(i*16+horizdistance);
            if(isLetter(current)){
                verts+=current;
            }else{
                verts+=".";
            }
        }
        return null;
    }*/
   public ScrabbleMove find2() {
       ArrayList<Character> hand = gateKeeper.getHand();
       PlayWord bestMove = null;
       int bestScore = -1;
       for (int i = 0; i < hand.size(); i++) {
           for (int j = 0; j < hand.size(); j++) {
               if (i != j){
                   char c = hand.get(i);
                   char a = hand.get(j);
                   if (c == '_') {
                       for(char d: new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}){
                           c=d;
                           if (a =='_'){
                               a ='E';
                           }
                           for (String word : new String[]{c + a+" ",a + c+" ", " " + c+a," "+a + c," "+c,c+" "}) {
                               for (int row = 0; row < Board.WIDTH; row++) {
                                   for (int col = 0; col < Board.WIDTH; col++) {
                                       Location location = new Location(row, col);
                                       for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                           try {
                                               gateKeeper.verifyLegality(word, location, direction);
                                               int score = gateKeeper.score(word, location, direction);
                                               if (score > bestScore) {
                                                   bestScore = score;
                                                   bestMove = new PlayWord(word, location, direction);
                                               }
                                           } catch (IllegalMoveException e) {
                                               // It wasn't legal; go on to the next one
                                           }
                                       }
                                   }
                               }
                           }
                       }
                   }
                   if (a =='_'){
                       a ='E';
                   }
                   for (String word : new String[]{c + a+" ",a + c+" ", " " + c+a," "+a + c," "+c,c+" "}) {
                       for (int row = 0; row < Board.WIDTH; row++) {
                           for (int col = 0; col < Board.WIDTH; col++) {
                               Location location = new Location(row, col);
                               for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                   try {
                                       gateKeeper.verifyLegality(word, location, direction);
                                       int score = gateKeeper.score(word, location, direction);
                                       if (score > bestScore) {
                                           bestScore = score;
                                           bestMove = new PlayWord(word, location, direction);
                                       }
                                   } catch (IllegalMoveException e) {
                                       // It wasn't legal; go on to the next one
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
       if (bestMove != null) {
           return bestMove;
       }
       return new ExchangeTiles(ALL_TILES);
   }
}
//look at placement options for special tiles

//find placement options for board not concerned with special tiles
    //look for open square without letters check proximity around them?
    //