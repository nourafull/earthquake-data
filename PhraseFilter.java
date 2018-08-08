public class PhraseFilter implements Filter{

    private String where;
    private String phrase;

    //Constructor
    public PhraseFilter(String str1, String str2){
        where = str1;
        phrase = str2;
    }
    public boolean satisfies(QuakeEntry qe) {
        if (where == "start"){
            return qe.getInfo().startsWith(phrase);
        }
        else if (where == "end"){
            return qe.getInfo().endsWith(phrase);
        }
        else if (where == "any") {
            return qe.getInfo().contains(phrase);
        }
        else return false;
    }
}
