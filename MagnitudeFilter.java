public class MagnitudeFilter implements Filter{

    private double minmag;
    private double maxmag;
    public MagnitudeFilter(double min, double max){
        minmag = min;
        maxmag = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (qe.getMagnitude()>= minmag && qe.getMagnitude()<=maxmag){
            return true;
        }
        else{
            return false;
        }
    }
}
