package kangarooSimulator;

import java.util.ArrayList;

public class Graph<V extends Comparable<V>, E>{
    private GraphNode head;
    
    public Graph(){
        head = null;
    }
    
    public boolean isEmpty(){
        return(head==null);
    }
    
    public int getSize(){
        int count = 0;
        GraphNode currentNode = head;
        while(currentNode != null){
            currentNode = currentNode.getVerticeLink();
            count++;
        }
        return count;
    }
    
    public void clear(){
        head = null;
    }
    
    public void showGraph(){
        GraphNode currentNode = head;
        while(currentNode != null){
            System.out.println(currentNode.toString());
            Edge edgeNode = (Edge) currentNode.getEdgeLink();
            while(edgeNode != null){
                System.out.println(edgeNode.toString());
                edgeNode = edgeNode.getEdgeLink();
            }
            System.out.println("");
            currentNode = currentNode.getVerticeLink();
        }
    }
    
    public GraphNode hasVertice(V a){
        GraphNode currentNode = head;
        if(isEmpty()){
            return null;
        }
        else{
            while(currentNode!=null){
                if(a.compareTo((V) currentNode.getVertice())==0){
                    return currentNode;
                }
                currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }
    
    public void addVertice(V a){
        GraphNode newNode = new GraphNode (a, null);
        GraphNode currentNode = head;
        if(head==null){
            head = newNode;
        }
        else{
            while(currentNode.getVerticeLink()!=null)
                currentNode = currentNode.getVerticeLink();
                
            currentNode.setVerticeLink(newNode);
        }
        
    }
    
    public boolean addEdge(V from, V to, E weight){
        if (hasVertice(from)==null||hasVertice(to)==null)
            return false;
        else{
            GraphNode currentNode = head;
            while(currentNode!=null){
                if(from.compareTo((V)currentNode.getVertice())==0){
                    GraphNode temp = hasVertice(to);
                    Edge newNode = new Edge(temp,weight,null);
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    if (edgeNode==null)
                        currentNode.setEdgeLink(newNode);
                    else{
                        while(edgeNode.getEdgeLink()!=null)
                            edgeNode = edgeNode.getEdgeLink();
                        edgeNode.setEdgeLink(newNode);
                    }
                    return true;
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public boolean deleteEdge(V from,V to){
        if(!isEdge(from,to))
            return false;
        else{
            GraphNode currentNode = head;
            while(currentNode!=null){
                if(from.compareTo((V)currentNode.getVertice())==0){
                    GraphNode temp = hasVertice(to);
                    Edge previousNode = null;
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null){
                        if(edgeNode.getVerticeLink()==temp){
                            if(previousNode==null)
                                currentNode.setEdgeLink(edgeNode.getEdgeLink());
                            else
                                previousNode.setEdgeLink(edgeNode.getEdgeLink());
                            return true;
                        }
                        else{
                            previousNode = edgeNode;
                            edgeNode = edgeNode.getEdgeLink();
                        }
                    }
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public boolean isEdge(V from,V to){
        if(hasVertice(from)==null||hasVertice(to)==null)
            return false;
        else{
            GraphNode currentNode = head;
            while(currentNode!=null){
                if(from.compareTo((V)currentNode.getVertice())==0){
                    GraphNode temp = hasVertice(to);
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    if(edgeNode==null)
                        return false;
                    else{
                        while(edgeNode!=null){
                            if(edgeNode.getVerticeLink()==temp)
                                return true;
                            edgeNode = edgeNode.getEdgeLink();
                        }
                    }
                }
                else currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public E getWeight(V from,V to){
        if(isEdge(from,to)){
            GraphNode currentNode = head;
            while(currentNode!=null){
                if(from.compareTo((V)currentNode.getVertice())==0){
                    GraphNode temp = hasVertice(to);
                    Edge edgeNode=(Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null){
                        if (edgeNode.getVerticeLink()==temp)
                            return(E) edgeNode.getWeight();
                        edgeNode=edgeNode.getEdgeLink();
                    }
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }
    
    public ArrayList getAdjacent(V a){
        ArrayList<V> array = new ArrayList();
        if(hasVertice(a)!=null){
            GraphNode currentNode = head;
            while(currentNode!=null){
                if(a.compareTo((V) currentNode.getVertice())==0){
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null){
                        array.add((V)edgeNode.getVerticeLink().getVertice());
                        edgeNode = edgeNode.getEdgeLink();
                    }
                    break;
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return array;
    }
}
