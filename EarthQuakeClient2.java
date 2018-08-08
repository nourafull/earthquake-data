import java.util.ArrayList;

public class EarthQuakeClient2 {
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            if (f.satisfies(qe)){
                answer.add(qe);
            }
        }
        return answer;
    }
    public void filterMinMag(){

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Filter minmag = new MinMagFilter(4.0);
        ArrayList<QuakeEntry> mmlist = filter(list,minmag);
        System.out.println("result of MinMag filter:"+mmlist.size());
        for (QuakeEntry qe: mmlist){

            System.out.printf("%4.2f,%s\n",
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    public void filterMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Filter magnitude = new MagnitudeFilter(3,4);
        ArrayList<QuakeEntry> magnitudeList = filter(list,magnitude);
        System.out.println("result of Magnitude filter:" + magnitudeList.size());
        for (QuakeEntry qe: magnitudeList){

            System.out.printf("%4.2f,%s\n",
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }
    public void filterDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Filter depth = new DepthFilter(-150000,-100000);
        ArrayList<QuakeEntry> depthList = filter(list,depth);
        System.out.println("result of depth filter:" + depthList.size());
        for (QuakeEntry qe: depthList){

            System.out.printf("%4.2f,%s\n",
                    qe.getDepth(),
                    qe.getInfo());
        }
    }
    public void filterDistance(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Filter distance = new DistanceFilter(36.7783,119.4179,8000*400);
        ArrayList<QuakeEntry> distanceList = filter(list,distance);
        System.out.println("result of distance filter:" + distanceList.size());
        for (QuakeEntry qe: distanceList){
            System.out.printf("%s\n", qe.getInfo());
        }
    }
    public  void filterPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Filter phrase = new PhraseFilter("any","Japan");
        ArrayList<QuakeEntry> phraseList = filter(list,phrase);
        System.out.println("result of phrase filter:" + phraseList.size());
        for (QuakeEntry qe: phraseList){
            System.out.printf("%s\n", qe.getInfo());
        }
    }
    public void MagnitudeDepthFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "C:/Users/noura/Desktop/self-learning/earthquake_project/src/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        //Magnitude
        Filter magnitude = new MagnitudeFilter(4.0,5.0);
        ArrayList<QuakeEntry> magnitudeList = filter(list,magnitude);

        //Depth
        Filter depth = new DepthFilter(-35000,-12000);
        ArrayList<QuakeEntry> MagDepthList = filter(magnitudeList,depth);
        System.out.println("result of magnitude filter:" + MagDepthList.size());
        for (QuakeEntry qe: MagDepthList){
            System.out.printf("%4.2f, %4.2f, %s\n", qe.getMagnitude(),qe.getDepth(), qe.getInfo());
        }
    }
    public static void main(String[] args) {
        EarthQuakeClient2 ec = new EarthQuakeClient2();
        //ec.filterMagnitude();
        //ec.filterDepth();
        //ec.filterDistance();
        //ec.filterPhrase();
        ec.MagnitudeDepthFilter();
    }
}
