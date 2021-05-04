package kangarooSimulator;

public class NodeDetail {
    
    private int nodeID;
    private int foodID;
    private int spaceID;
    private int connectID;
    
    public NodeDetail(){
        
    }
    
    public NodeDetail(int nodeID,int foodID,int spaceID,int connectID){
        this.nodeID=nodeID;
        this.foodID=foodID;
        this.spaceID=spaceID;
        this.connectID=connectID;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    public int getConnectID() {
        return connectID;
    }

    public void setConnectID(int connectID) {
        this.connectID = connectID;
    }
    
    
}
