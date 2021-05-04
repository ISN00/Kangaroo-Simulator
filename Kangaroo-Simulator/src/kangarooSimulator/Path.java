package kangarooSimulator;

public class Path extends Map{
    private int Obstacle;

    public int getObstacle() {
        return Obstacle;
    }

    public void setObstacle(int Obstacle) {
        this.Obstacle = Obstacle;
    }

    public int getPointID() {
        return pointID;
    }

    public void setPointID(int pointID) {
        this.pointID = pointID;
    }

    public Path(int pointID,int Obstacle) {
        this.Obstacle = Obstacle;
        this.pointID = pointID;
    }
    
    public void isCrossable(){
        
    }

    @Override
    public String toString() {
        return "-("+Obstacle+")->"+pointID;
    }
    
}
