package kangarooSimulator;

public class Kangaroo extends Map{
    private int pouchlimit;
    private String Gender;
    private int Startingpoint;
    private int foodInPouch=0;
    private int id;

    public Kangaroo(int Startingpoint , String Gender, int pouchlimit, int id ) {
        this.pouchlimit = pouchlimit;
        this.Gender = Gender;
        this.Startingpoint = Startingpoint;
        foodInPouch = 0;
        this.id = id;
    }
   

    Kangaroo(Kangaroo kanggaroo, int currentpoint) {
       this.foodInPouch = kanggaroo.getFoodInPouch();
       this.Gender = kanggaroo.getGender();
       this.pouchlimit = kanggaroo.getPouchlimit();
       this.Startingpoint = currentpoint;
    }

    public int getFoodInPouch() {
        return foodInPouch;
    }

    public void setFoodInPouch(int foodInPouch) {
        this.foodInPouch = foodInPouch+this.foodInPouch;
    }
    
    public int getPouchlimit() {
        return pouchlimit;
    }
    
    public boolean isFull(){
        return foodInPouch>getPouchlimit();
    }

    public void setPouchlimit(int pouchlimit) {
        this.pouchlimit = pouchlimit;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getStartingpoint() {
        return Startingpoint;
    }

    public void setStartingpoint(int Startingpoint) {
        this.Startingpoint = Startingpoint;
    }

    public int getId() {
        return id;
    }
    
    

    public String toString() {
        return Gender+" "+foodInPouch+"/"+pouchlimit;
    }

}
