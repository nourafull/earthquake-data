public class MinMagFilter implements Filter{

    private double minmag;
    //constructor
    public MinMagFilter(double m){
        minmag = m;
    }
    public boolean satisfies(QuakeEntry qe){

        if (qe.getMagnitude()>= minmag){
            return true;
        }
        else{
            return false;
        }
    }
}
