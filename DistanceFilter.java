public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;
    //constructor
    public DistanceFilter(double lat,double lon,double dist){
        loc = new Location(lat,lon);
        maxDist = dist;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (qe.getLocation().distanceTo(loc)>= maxDist){
            return false;
        }
        else{
            return true;
        }
    }
}
