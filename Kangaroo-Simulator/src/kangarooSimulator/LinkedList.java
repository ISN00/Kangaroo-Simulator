package kangarooSimulator;

import java.util.Random;

public class LinkedList<T extends Comparable<T>> {
    Random r=new Random();
    private ListNode head;
    
    public LinkedList(){
        head = null;
    }
    
    public int length() {
        int count=0;
        ListNode currentNode = head;
        while (currentNode != null){
            currentNode = currentNode.getLink();
            count++;
        }
        return count;
    }
    
    public void clear() {
        head=null;
    }
    
    public boolean isEmpty() {
        return (head==null);
    }
    
    public void showList() {
        ListNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.toString());
            currentNode = currentNode.getLink();
        }
    }
    
    //insert at the back
    public void addNode(T a) {
        ListNode newNode = new ListNode(a , null);
        ListNode currentNode = head;
        if(head==null) {
            head = newNode;
        }
        else {
            while (currentNode.getLink() != null)
                currentNode = currentNode.getLink();
            currentNode.setLink(newNode);
        }
    }
    //q2
    public void addNumber (){
        ListNode<T> currentNode = head;
        Double d;
        while(currentNode != null){
            d=(Double) currentNode.getData();
            if(d>20.0){
                d+=10.5;
                currentNode.setData((T) d);
            }
            currentNode = currentNode.getLink();
        }
    }
    //q2end
    
    //delete from the back
    public void deleteNode() {
        ListNode currentNode = head;
        ListNode previousNode = head;
        if(head != null) {
            if (currentNode.getLink() == null)
                head=null;
            else {
                while (currentNode.getLink() != null) {
                    previousNode =currentNode;
                    currentNode = currentNode.getLink();
                }
                previousNode.setLink(null);
            }
        }
        else
            System.out.println("The list is empty.");
    }
    
    //add front node
    public void addFrontNode(T a) {
        head = new ListNode(a, head);
    }
    
    //delete front
    public void deleteFrontNode() {
        if (head != null)
            head = head.getLink();
        else
            System.out.println("The list is empty.");
    }
    
    public boolean contains(T t) {
        boolean found = false;
        ListNode currentNode = head;
        while (currentNode != null) {
            if (t.compareTo( (T) currentNode.getData()) == 0) {
                found = true;
                break;
            }
            currentNode = currentNode.getLink();
        }
        return found;
    }
    
    //add node by position
    public void addNodeByPosition(T a,int index){
        if(index==0)
            addFrontNode(a);
        else if (index==length())
            addNode(a);
        else if(index>length())
            System.out.println("invalid index. No node inserted");
        else{
            ListNode newNode = new ListNode (a, null);
            ListNode currentNode = head;
            for(int i=1;i<index;i++)
                currentNode = currentNode.getLink();
            ListNode tempNote = currentNode.getLink();
            currentNode.setLink(newNode);
            newNode.setLink(tempNote);
        }
    }
    
    //delete node byposition
    public void deleteNodeByPosition(int index){
        if(index==0)
            deleteFrontNode();
        else if(index==length()-1)
            deleteNode();
        else if(index>=length())
            System.out.println("Invalid index. No node deleted");
        else{
            ListNode currentNode = head;
            for(int i=1;i<index;i++)
                currentNode=currentNode.getLink();
            ListNode tempNode = currentNode.getLink();
            currentNode.setLink(tempNode.getLink());
            tempNode=null;
        }
    }
    
    //update/retrive
    //set
    public void setFront(T t){
        if(head != null)
            head.setData(t);
        else
            System.out.println("Invalid update. List is empty.");
    }
    
    public void setBack(T t){
        if(head != null){
            ListNode currentNode = head;
            while(currentNode.getLink() != null)
                currentNode = currentNode.getLink();
            currentNode.setData(t);
        }
        else
            System.out.println("Invalid update. List is empty.");
    }
    
    public void set(T t,int index){
        if(index==0){
            setFront(t);
        }
        else if(index==length()-1)
            setBack(t);
        else if(index>=length())
            System.out.println("Invalid index. Updated failed.");
        else {
            ListNode currentNode = head;
            for(int i=1;i<=index;i++)
                currentNode = currentNode.getLink();
            currentNode.setData(t);
        }
    }
    
    //get
    public T getFront(){
        if(head != null)
            return (T) head.getData();
        else
            return null;
    }
    
    public T getBack(){
        if(head != null){
            ListNode currentNode = head;
            while(currentNode.getLink() != null)
                currentNode = currentNode.getLink();
            return (T) currentNode.getData();
        }
        else
            return null;
    }
    
    public T get(int index){
        if(index==0)
            return getFront();
        else if(index==length()-1)
            return getBack();
        else if(index>=length())
            return null;
        else{
            ListNode currentNode = head;
            for(int i=1;i<=index;i++)
                currentNode = currentNode.getLink();
            return (T) currentNode.getData();
        }
    }
}
