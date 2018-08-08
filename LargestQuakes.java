import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> largestQuakes = getLargest(list,10);

//        int max = indexOfLargest(list);
//        System.out.println("largest quake index: "+max);
//        System.out.println(list.get(max));

        System.out.println("Largest quakes: "+largestQuakes.size());
        for(QuakeEntry qe: largestQuakes){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }
    public int indexOfLargest(ArrayList<QuakeEntry> list){
        int max = 0;
        for (int k=1; k<list.size(); k++){
            QuakeEntry current = list.get(k);
            if ( current.getMagnitude() > list.get(max).getMagnitude())
                max = k;
        }
        return max;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> list,int num){

        ArrayList<QuakeEntry> answer = new ArrayList<>();
        ArrayList<QuakeEntry> copy = new ArrayList<>(list);
        for(int i=0; i<num; i++){
            int max = indexOfLargest(copy);
            answer.add(copy.get(max));
            copy.remove(max);
        }
        return answer;
    }
    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();

    }
}
