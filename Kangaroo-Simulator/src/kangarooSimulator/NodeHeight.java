package kangarooSimulator;

public class NodeHeight {
    private int nodeToID;
    private int heightID;

    
    
    public NodeHeight(){
        
    }
    
    public NodeHeight(int nodeToID,int heightID){
        this.nodeToID=nodeToID;
        this.heightID=heightID;
    }

    public int getNodeToID() {
        return nodeToID;
    }

    public void setNodeToID(int nodeToID) {
        this.nodeToID = nodeToID;
    }

    public int getHeightID() {
        return heightID;
    }

    public void setHeightID(int heightID) {
        this.heightID = heightID;
    }
    
}
