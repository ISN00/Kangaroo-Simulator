package kangarooSimulator;

import java.util.Arrays;

public class Point extends Map{
    protected int pointLimit;
    protected int pointPathCount = 0;
    protected int pointFood;
    protected int CountK = 0;
    protected int path;
    Kangaroo[] k;
    Path[] pa;
    int formColony;
    
    
    
    public Point(int pointID ,int pointFood, int pointLimit, int path, int formColony) {
        this.pointID = pointID;
        this.pointLimit = pointLimit;
        this.path = path;
        this.pointFood = pointFood;
        k = new Kangaroo[pointLimit];
        pa = new Path[path];
        this.formColony = formColony; 
        CountK = 0;
    }
    
    public Kangaroo getKanggaroo(int i){
       return k[i];
        
    }
    public kangarooSimulator.Path getPath(int i){
       return pa[i];
        
    }
    public int getPathObs(int i){
       return pa[i].getObstacle();
        
    }
    
    public boolean hasColony(){
        return CountK >=formColony;
    }
    
    public void addPath(int to,int obstacle){
        pa[pointPathCount]=new Path(to,obstacle);
        pointPathCount++;
    }
    
    public void addKangaroo(int PouchSize, String Gender, int id){
        k[CountK] = new Kangaroo(pointID,Gender,PouchSize,id);
        CountK++;
    }
  
    public void removeKangaroo(int PouchSize, String Gender, int id){
        k[CountK] = new Kangaroo(pointID,Gender,PouchSize,id);
        CountK++;
        
    }
    
    public boolean isFull(){
        return CountK>=pointLimit;
    }

    public int getPointLimit() {
        return pointLimit;
    }

    public void setPointLimit(int pointLimit) {
        this.pointLimit = pointLimit;
    }

    public int getpointPathCount() {
        return pointPathCount;
    }

    public void setpointPathCount(int pointPathCount) {
        this.pointPathCount = pointPathCount;
    }

    public int getPointFood() {
        return pointFood;
    }

    public void setPointFood(int pointFood) {
        this.pointFood = pointFood;
    }

    public int getCountK() {
        return CountK;
    }

    public void setCountK(int CountK) {
        this.CountK = CountK;
    }

    public int getPointID() {
        return pointID;
    }

    public void setPointID(int pointID) {
        this.pointID = pointID;
    }
    
    public boolean hasFood(){
         if (pointFood!=0) {
            return true;
        }
         return false;
    }
    
    public boolean hasPath(){
        if(pointPathCount>0)
            return true;
        return false;
    }
    
    public String display(){
        return "PointID: "+pointID+"\n\tPoint Space:"+CountK+"/"+pointLimit+"\n\t\tKangaroo: "+Arrays.toString(k)+"\n\tPoint Food: "+pointFood+"\n\tPoint Path: "+pointPathCount+"\n\t\tPath: "+Arrays.toString(pa);
    }

    public void addKangaroo(Kangaroo kanggaroo, int startingPoint) {
          k[CountK] = new Kangaroo(kanggaroo, startingPoint);
            CountK++;
    }
    public void removeKanggaroo(int index){
        while(k[index]!=null){
        k[index] = k[index+1];
        k[index+1]=null;
        index++;
        }
        CountK--;
        System.out.println(CountK+" countk");
    }
    
    public int getFemale(){
        int count = 0;
        for (int i = 0; i < CountK; i++) {
            if (k[i].getGender().equalsIgnoreCase("F")) {
                count++;
            }
        }
        return count;
    }
    
}
