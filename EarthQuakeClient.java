
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO

        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double min,double max) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > min && qe.getDepth() < max) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if (where == "start"){
                if (qe.getInfo().startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where == "end"){
                if (qe.getInfo().endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where == "any"){
                if (qe.getInfo().contains(phrase)) {
                    answer.add(qe);
                }
            }
            else System.out.println("invalid Where value.");
        }
        return answer;
    }
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData,int n,Location myLocation){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);

        for (int j=0; j<n; j++) {
            int minIndex = 0;
            for (int k = 1; k < copy.size(); k++) {
                QuakeEntry current = copy.get(k);
                Location minLocation = copy.get(minIndex).getLocation();
                if (current.getLocation().distanceTo(myLocation) < minLocation.distanceTo(myLocation)) {
                    minIndex = k;
                }
            }
            System.out.println("minIndex found: "+minIndex);
            answer.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return answer;
    }
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }
    }
        public void closeToMe() {
            EarthQuakeParser parser = new EarthQuakeParser();
            String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
            //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
            ArrayList<QuakeEntry> list = parser.read(source);
            System.out.println("# quakes read: " + list.size());

            //Durham, NC
            //Location city = new Location(35.988, -78.907);
            //Bridgeport, CA

            Location city = new Location(38.17, -118.82);
            ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 100*100, city);
            System.out.println("Num of Quakes close to Durham, NC:"+ close.size());
            for (int k=0; k< close.size(); k++) {
                QuakeEntry entry = close.get(k);
                double distanceInMeters = city.distanceTo(entry.getLocation());
                System.out.print(k+1);
                System.out.println("- " + distanceInMeters/1000 + " " + entry.getInfo());
            }
        }
        public void quakesOfDepth(){
            //quakes with depth between -10000.0 and -5000.0
            //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
            String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
            EarthQuakeParser parser = new EarthQuakeParser();
            ArrayList<QuakeEntry> quakeData =parser.read(source);
            ArrayList<QuakeEntry> answer = new ArrayList<>();
            double min = -10000.0;
            double max = -5000.0;
            answer = filterByDepth(quakeData,min,max);
            System.out.println("Num of Quakes with a depth between "+min+" and "+max+" is: "+answer.size());
            for(QuakeEntry qe: answer){
                System.out.println(qe);
            }
        }
    public void quakesByPhrase(){
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeData =parser.read(source);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        answer = filterByPhrase(quakeData,"end","Japan");
        System.out.println("Num of Quakes ending with Japan is: "+answer.size());
        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
    }
    public void closestQuakes(){
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeData =parser.read(source);
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        Location city = new Location(24.7136, 46.6753);
        int n = 20;
        answer = getClosest(quakeData,n,city);
        System.out.println("the "+n+" closest Quakes to my location are:");
        for(QuakeEntry qe: answer){
            System.out.println(qe);
        }
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,depth,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    public void printAllEarthQuakes(){
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeData =parser.read(source);
        System.out.println("Latitude,Longitude,Magnitude,depth,Info");
        for(QuakeEntry qe : quakeData){
            System.out.printf("%4.2f,%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getDepth(),
                    qe.getInfo());
        }
    }
    public void printMinMaxDepth(){
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeData =parser.read(source);
        //find minimum depth
        int mindepth = 0;
        for(int k=1;k<quakeData.size();k++) {

            if (quakeData.get(k).getDepth() < quakeData.get(mindepth).getDepth()){
                mindepth = k;
            }
        }
        System.out.println("minimum depth: "+quakeData.get(mindepth).getDepth() );
        //find maximum depth
        int maxdepth = 0;
        for(int k=1;k<quakeData.size();k++) {

            if (quakeData.get(k).getDepth() > quakeData.get(maxdepth).getDepth()){
                maxdepth = k;
            }
        }
        System.out.println("maximum depth: "+quakeData.get(maxdepth).getDepth() );
    }
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public static void main(String[] args) {

        EarthQuakeClient ec = new EarthQuakeClient();
          //ec.bigQuakes();
          //ec.closeToMe();
          //ec.quakesOfDepth();
          //ec.quakesByPhrase();
          //ec.closestQuakes();
          ec.printMinMaxDepth();
    }
}

