package kangarooSimulator;

import java.util.Random;
import java.util.Scanner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;


public class JumpyGroof extends Application{
    
    static Point[] po ;
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    
    public static int totalPoint;
    public static int[] pointConnected;
    public static int[][] toWhatPoint;
    
    //for drawing weight val
    public static int totalAH=0;
    public static int[] c1,c2,cX,cY,H;
    public static int[][] heightTo;

    public static final int POINT_SIZE = 70;
    private G graph;
    private boolean showTotalWeight;
    private boolean showLineWeight = true;
    boolean isSetUp;
    
    public int[] node,food,space,nodeC;
    public int[][] toNode,height;
    
    Stage window;
    
    //intro
    Label intro;
    Button start;
    Scene scene;
    
    //Total Node
    Label askNode;
    Button enter;
    static TextField nodeVal;
    Scene scene2;
    GridPane grid;
    
    //for table-Node Detail
    TableView<NodeDetail> table;
    static TextField nodeInput,foodInput,spaceInput,connectInput;
    Button insert,next;
    HBox boxTable;
    VBox layoutTable;
    Scene sceneTable;
    
    //for table-NodeHeight from Each Node Detail
    TableView<NodeHeight> table2;
    static TextField nodeToInput,heightInput;
    Button add,exit;
    HBox boxTable2;
    VBox layoutTable2;
    Scene sceneTable2;
    
    Random r=new Random();
    static Scanner sc = new Scanner(System.in);
    static int n,x,c;
    static int[][] A,h;
    static int[] a,f,l,m1,i,p;
    static String[] s;
    
    public static void main(String[] args) throws InvocationTargetException, InterruptedException , NumberFormatException{
        
        int [] id;
        
        launch(args);
        
        System.out.print("Number of points: ");
        
        n=sc.nextInt();
        a=new int[n+1];
        f=new int[n+1];
        l=new int[n+1];
        m1=new int[n+1];
        A=new int[n+1][n+1];
        h=new int[n+1][n+1];
        heightTo=new int[n+1][n+1];
        if(n>0){
            System.out.println("a f l m");
            for(int j=1;j<=n;j++){
                System.out.print("...");
                a[j]=sc.nextInt();
                f[j]=sc.nextInt();
                l[j]=sc.nextInt();
                m1[j]=sc.nextInt();
                if(m1[j]>0){
                    System.out.println("A h");
                    for(int k=1;k<=m1[j];k++){
                        System.out.print(":");
                        A[k][a[j]]=sc.nextInt();
                        h[k][a[j]]=sc.nextInt();
                        heightTo[a[j]][A[k][a[j]]]=h[k][a[j]];
                        totalAH++;
                    }
                    
                }
            }
        }
        c1 = new int[totalAH];
        c2 = new int[totalAH];
        H = new int[totalAH];
        cX = new int[totalAH];
        cY = new int[totalAH];
        System.out.println("");
        System.out.print("Number of kangaroo(s): ");
        x=sc.nextInt();
        i=new int[x+1];
        p=new int[x+1];
        s=new String[x+1];
        System.out.println("i s p");
        for(int j=1;j<=x;j++){
            i[j]=sc.nextInt();
            s[j]=sc.next();
            p[j]=sc.nextInt();
        }
        
        
        System.out.println("");
        System.out.print("Kangaroos needed to form a colony: ");
        c=sc.nextInt();
        
        
        //points
        po = new  Point[n+1];
        for(int j=1;j<=n;j++){
            po[j]=new Point(a[j],f[j],l[j],m1[j],c);
        }
        for(int v=1;v<=n;v++){
                if(m1[v]>0){
                    for(int d=1;d<=m1[v];d++){
                        po[v].addPath(A[d][a[v]], h[d][a[v]]);
                    }
                }
            }
        id =new int[x+1];
        for(int o=1;o<=x;o++){
            id[o] = o;
            po[i[o]].addKangaroo(p[o], s[o], id[o]);
        }
        for(int j=1;j<=n;j++){
            System.out.println(po[j].display());
        }
        
        foodDistribution(); //distribute early
     
        
         for(int j=1;j<=n;j++){
            System.out.println(po[j].display());
        }
         
         
        int[] cantMove = new int[x+1];//kanggaroo cant move,so set here
        int io=0;//temporary i[o]/place
        int turn = 0;// turn kanggaroo in a point
        while (true) {
            for(int o=1;o<=x;o++){
                for (int j = 0; j < po[i[o]].getCountK(); j++) {
                    if (po[i[o]].getKanggaroo(j).getId() == id[o]) {
                       turn = j;
                    }
                }
                System.out.println("kang "+o);
                if (!po[i[o]].hasColony()) {
                    int remove=0;
                        if (po[i[o]].getKanggaroo(turn).getGender().equalsIgnoreCase("M")) {
                            if (po[i[o]].getCountK()!=0) {
                                if(m1[i[o]]>0){
                                    int [] rank = new int[m1[i[o]]];
                                    
                                    for (int j = 0; j < m1[i[o]]; j++) {
                                            rank[j] = j;
                                    }
                                    for (int j = 0; j < m1[i[o]]; j++) {
                                        for (int k = 0; k < m1[i[o]]; k++) {
                                            if (po[po[i[o]].getPath(j).getPointID()].getPointFood()<po[po[i[o]].getPath(k).getPointID()].getPointFood()) {
                                                int temp = rank[j];
                                                rank[j] = rank[k];
                                                rank[k] = temp;
                                            }
                                        }
                                    }
                                    
                                    for(int d=0;d< m1[i[o]];d++){// loop satu2 path
                                        int food2 = po[po[i[o]].getPath(rank[d]).getPointID()].getPointFood();
                                        int obs = po[i[o]].getPath(rank[d]).getObstacle();
                                        int fpouch =  po[i[o]].getKanggaroo(turn).getFoodInPouch();
                                        int needed =  (obs + fpouch/2);
                                        int remain =remain(fpouch,needed,po[i[o]].getPath(rank[d]).getPointID(),turn,i[o]);
                                        if (remain >= 0) {
                                            if (food(po[i[o]].getPointFood(), food2)) {
                                                if (po[po[i[o]].getPath(rank[d]).getPointID()].hasColony()){
                                                    if (canEnterColony(po[i[o]].getPath(rank[d]).getPointID(), remain)) {
                                                        cantMove[o] =0;
                                                        move(i[o], turn, needed, po[i[o]].getPath(rank[d]).getPointID());
                                                        System.out.println("move because can afford colony");
                                                        remove++;//note that kanggaroo has been remove from point
                                                        io = po[i[o]].getPath(rank[d]).getPointID();
                                                        break;
                                                    }
                                                    else {
                                                        cantMove[o] =1;
                                                        System.out.println("kang "+ o+"cant move because cant enter colony after got many food there");
                                                        break;
                                                    }
                                                }
                                                else{
                                                    cantMove[o] =0;
                                                    move(i[o], turn, needed, po[i[o]].getPath(rank[d]).getPointID());
                                                    System.out.println("move because food and no colony");
                                                    remove++;
                                                    io = po[i[o]].getPath(rank[d]).getPointID();
                                                    break;
                                                }
                                            }
                                            else if (po[i[o]].getPointFood() == food2 ) {//equal food so compare female
                                                if(po[i[o]].getFemale()< po[po[i[o]].getPath(rank[d]).getPointID()].getFemale()){
                                                    if (po[po[i[o]].getPath(rank[d]).getPointID()].hasColony()) {
                                                        if (canEnterColony(po[i[o]].getPath(rank[d]).getPointID(), remain)) {
                                                            cantMove[o] =0;
                                                            move(i[o], turn, needed, po[i[o]].getPath(rank[d]).getPointID());
                                                            System.out.println("move because can afford colony and female");
                                                            remove++;
                                                            io = po[i[o]].getPath(rank[d]).getPointID();
                                                            break;
                                                        }
                                                        else {
                                                            cantMove[o] =1;
                                                            System.out.println("kang "+ o+"cant move");
                                                            break;
                                                        }
                                                    }
                                                    else{
                                                        cantMove[o] =0;
                                                        move(i[o], turn, needed, po[i[o]].getPath(rank[d]).getPointID());
                                                        System.out.println("move because female");
                                                        remove++;
                                                        io = po[i[o]].getPath(rank[d]).getPointID();
                                                        break;
                                                    }
                                                 }
                                            }
                                            else {
                                                cantMove[o] =1;
                                                System.out.println("kang "+ o+"cant move");
                                                break; //most outer loop
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("kang "+ o+"cant move because "+o+" female");
                            cantMove[o] =1;
                        } 
                        
                        if (remove == 1) {
                            i[o]= io;
                            System.out.println("new io = " +i[o]);
                        }
                     
                }
                else{
                    System.out.println("has colony at "+po[i[o]].getPointID());
                    System.out.println("kang "+ o+" cant move because has colony");
                    cantMove[o]=1;
                }
            }
            int count=0;
            for (int j = 1; j <= x; j++) {
                if (cantMove[j] == 1) {
                    count++;
                }
            }
            System.out.println("count for break = "+count);
            if (count == x) {
                break;
            }
        }
        for(int j=1;j<=n;j++){
            System.out.println(po[j].display());
        }
        
        int countColony = 0;
        int remaining = x;
        for (int j = 1; j <= n; j++) {
            if (po[j].hasColony()) {
                remaining-=po[j].getCountK();
                countColony ++;
            }
        }
        int countRemain = 0;
        Kangaroo [] remainingKang = new Kangaroo[remaining] ;
        for (int j = 1; j <= n; j++) {
            if (!po[j].hasColony()) {
                for (int k = 0; k < po[j].getCountK(); k++) {
                    remainingKang[countRemain] = po[j].getKanggaroo(k);
                    countRemain++;
                }
            }
        }
        System.out.println("Number of colonies: "+ countColony);
        for (int j = 1; j <= n; j++) {
            if (po[j].hasColony()) {
            System.out.println("Point "+j+" has colony and contain "+po[j].getCountK()+" kanggaroo");
            }
        }
        System.out.println("Number of remaining kangaroos :" + remaining);
        for(int j=0;j<countRemain;j++){
            System.out.println(remainingKang[j].getStartingpoint()+" "+remainingKang[j]);
        }
        
        final JumpyGroof gd = new JumpyGroof();
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {    
                gd.setUpGraph(n, m1, A);
                gd.display();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Kangaroo Simulation");
        
        /*
        FOR INTRODUCTION ONLY!!!
        */
        intro = new Label("Welcome to the Kangaroo Simulation!");
        
        start = new Button("Start");
        start.setOnAction(e -> {
            window.setScene(scene2);
            
        });
        
        VBox box = new VBox();
        box.getChildren().addAll(intro,start);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        scene = new Scene(box,300,300);
        
        window.setScene(scene);
        
        /*
        FOR FILLING TOTAL NODE ONLY!!!
        */
        
        askNode = new Label("How many Node:");
        GridPane.setConstraints(askNode, 0, 0);
        
        nodeVal = new TextField();
        //n = (T)Integer.parseInt(nodeVal);
        GridPane.setConstraints(nodeVal, 0, 1);
        
        enter = new Button("Enter");
        GridPane.setConstraints(enter, 3, 2);
        enter.setOnAction(e -> window.setScene(sceneTable));
        
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.getChildren().addAll(askNode,nodeVal,enter);
        
        scene2 = new Scene(grid,300,100);
        
        
        /*
        TABLE FOR FILLING NODE DETAIL ONLY
        */
        //node column
        TableColumn<NodeDetail,Integer> nodeCol = new TableColumn<>("Node ID");
        nodeCol.setMinWidth(200);
        nodeCol.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
        
        //food column
        TableColumn<NodeDetail,Integer> foodCol = new TableColumn<>("Total Food");
        foodCol.setMinWidth(200);
        foodCol.setCellValueFactory(new PropertyValueFactory<>("foodID"));
        
        //space column
        TableColumn<NodeDetail,Integer> spaceCol = new TableColumn<>("Total Capacity");
        spaceCol.setMinWidth(200);
        spaceCol.setCellValueFactory(new PropertyValueFactory<>("spaceID"));
        
        //connect column
        TableColumn<NodeDetail,Integer> connectCol = new TableColumn<>("Node Connected");
        connectCol.setMinWidth(200);
        connectCol.setCellValueFactory(new PropertyValueFactory<>("connectID"));
        
        //TextField for node
        nodeInput = new TextField();
        nodeInput.setPromptText("Node ID");
        
        //TextField for food
        foodInput = new TextField();
        foodInput.setPromptText("Total Food");
        
        
        //TextField for space
        spaceInput = new TextField();
        spaceInput.setPromptText("Max Space");
        
        //TextField for connectID
        connectInput = new TextField();
        connectInput.setPromptText("Node Connected");
        
        //insert button
        insert = new Button("INSERT");
        insert.setOnAction(e ->{
            addItem();
            tableNoHeight.display("Enter Node and Height Input", "To Which Node ID", "Height between Node");
        });
        
        
        //next button
        next = new Button("NEXT");
        next.setOnAction(e -> {
            
        });
        
        //HBox
        boxTable = new HBox();
        boxTable.setPadding(new Insets(10,10,10,10));
        boxTable.setSpacing(10);
        boxTable.getChildren().addAll(nodeInput,foodInput,spaceInput,connectInput,insert,next);

        table = new TableView<>();
        table.setItems(getDetail());
        table.getColumns().addAll(nodeCol,foodCol,spaceCol,connectCol);

        layoutTable = new VBox(20);
        layoutTable.getChildren().addAll(table,boxTable);
        
        sceneTable = new Scene(layoutTable,800,500);
        
        /*
        
        */

        window.show();
        
    }
    
    
    
    public static boolean food(int food1,int food2){
        return  (food1 < food2);
    }
    
    public static boolean pathHasColony(int path){
        return (po[path].hasColony());
            
    }
   
    
    public static boolean canEnterColony(int path, int remain){
        int count=0;
        if (po[path].hasColony()) {
            count++;
            System.out.println("has colony");
            if (remain >= po[path].getCountK() ) {
               return true; 
            }
        }
        if (count==0) {
            System.out.println("no colony");
        }else
            System.out.println("cant share with colony");
        return false;
    }
    
    public static int remain(int inPouch, int needed,int path,int kangTurnInPoint, int curPoint){
       
        int foodLeft = po[path].getPointFood();
        int limit = po[curPoint].getKanggaroo(kangTurnInPoint).getPouchlimit();
        while (foodLeft != 0 && needed != 0) {            
            needed--;
            foodLeft--;
        }
        
        if (needed != 0) {
            while (needed!=0) {                
                needed--;
                inPouch--;
            }
        }
        return inPouch;
    }
    public static void foodDistribution(){
        for(int v=1;v<=po.length-1;v++){//food 
          while (po[v].getPointFood()!=0) {
                int count = 0;//check semua dh ambik.
                for (int j = 0; j < po[v].getCountK(); j++) {
                         if (po[v].getKanggaroo(j).getFoodInPouch()>=po[v].getKanggaroo(j).getPouchlimit()) {
                                count++;
                            } else{
                                po[v].getKanggaroo(j).setFoodInPouch(1);
                                po[v].setPointFood(po[v].getPointFood()-1);
                             }
                   
                }
                
                if (count == po[v].getCountK()) {
                    break;
                }
            }
        }
    }
    
    public static void move(int currentPoint,int curKangInPoint, int needed, int path){
        po[currentPoint].getKanggaroo(curKangInPoint).setFoodInPouch(-needed);
        po[path].addKangaroo(po[currentPoint].getKanggaroo(curKangInPoint),path);
        po[currentPoint].removeKanggaroo(curKangInPoint);
        foodDistribution();
        System.out.println("move from "+po[currentPoint].getPointID()+" move to "+path);
       
    }
    
    public JumpyGroof() {
        randomGraph();	
    }

    public void randomGraph() {
        graph = G.randomGraph(20);
    }

    public void setUpGraph() {
        
        int controlx=400,controly=400;
        
        graph = new G();
        int tempX,tempY;
        int count=0;
        
        for(int i=1;i<=totalPoint;i++){
            tempX=r.nextInt(controlx+200);
            tempY=r.nextInt(controly+200);
            graph.addVertex(tempX, tempY);
            cX[count]=tempX;
            cY[count]=tempY;
            count++;
        }
        for(int i=1;i<=totalPoint;i++){
            for(int j=1;j<=pointConnected[i];j++){
                int destination;
                destination = toWhatPoint[j][i];
                graph.connectVertices((i-1), (destination-1));
            }
        }
        isSetUp = true;
    }
    
    public void setUpGraph(int a, int[] b, int[][] c){
        
        
        totalPoint=a;
        pointConnected=b;
        toWhatPoint=c;
        int controlx=400,controly=400;
        int tempX,tempY;
        int count=0;
        graph = new G();
        for(int i=1;i<=totalPoint;i++){
            tempX=r.nextInt(controlx+200);
            tempY=r.nextInt(controlx+200);
            graph.addVertex(tempX, tempY);
            cX[count]=tempX;
            cY[count]=tempY;
            count++;
        }
        int count2=0;
        for(int i=1;i<=totalPoint;i++){
            for(int j=1;j<=pointConnected[i];j++){
                int destination;
                destination = toWhatPoint[j][i];
                graph.connectVertices((i-1), (destination-1));
                c1[count2]=(i-1);
                c2[count2]=(destination-1);
                H[count2]=heightTo[i][destination];
                count2++;
                
            }
        }
        isSetUp = true;
    }

    public void display() {
        JFrame frame = new JFrame("Kangaroo");
        final GraphPanel panel = new GraphPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new SwingWorker<Void, Void>() {

                    protected Void doInBackground() throws Exception {
                        if (isSetUp) 
                            setUpGraph();
                        else 
                            randomGraph();
                        return null;
                    }

                    protected void done() {
                        showTotalWeight = false;
                        panel.repaint();
                    }

                }.execute();
            }
        });

        JCheckBox lineWeight = new JCheckBox("Rearrange Kangaroo Position", true);
        lineWeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showLineWeight ^= true;
                showTotalWeight ^= true;
                panel.repaint();
            }
        });
        

        panel.add(new JLabel(new String(new char[10]).replace("\0", " ")));
        panel.add(reset);
        panel.add(lineWeight);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    class GraphPanel extends JPanel {
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                G.Position vertexPosition = graph.getPosition(i);
                g.setColor(new java.awt.Color(204,204,0));
                g.fillOval(vertexPosition.getX() - POINT_SIZE / 2, vertexPosition.getY() - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
                g.setColor(Color.BLACK);
                g.setFont(new java.awt.Font("Tahoma",1,10));
                
                int ax=0;
                int ay=0;
                for(int j=0;j<po[i+1].CountK-po[i+1].getFemale();j++){
                    ax=r.nextInt(POINT_SIZE-10);
                    ay=r.nextInt(POINT_SIZE-10);
                    g.setColor(Color.BLUE);
                    g.fillOval(vertexPosition.getX() + POINT_SIZE / 2 - ax - 20, vertexPosition.getY()+ POINT_SIZE / 2 - ay - 20, 7, 7);
                }
                for(int j=0;j<po[i+1].getFemale();j++){
                    ax=r.nextInt(POINT_SIZE-10);
                    ax=r.nextInt(POINT_SIZE-10);
                    g.setColor(Color.RED);
                    g.fillOval(vertexPosition.getX() + POINT_SIZE / 2 - ax - 20, vertexPosition.getY()+ POINT_SIZE / 2 - ay - 20, 7, 7);
                }
                g.setColor(Color.BLACK);
                
                g.drawString("Point:"+String.valueOf(po[i+1].getPointID()), vertexPosition.getX()+35, vertexPosition.getY()-POINT_SIZE/2);
                
                g.drawString("Food:"+String.valueOf(po[i+1].getPointFood()), vertexPosition.getX()+35, vertexPosition.getY()-POINT_SIZE/2 + 30);
                
                g.drawString(Integer.toString(i+1), vertexPosition.getX(), vertexPosition.getY() - POINT_SIZE / 2);
                
                
            }
            g.setColor(Color.red);
            List<G.Edge> edges = graph.getEdges();
            for (G.Edge edge : edges) {
                G.Position p1 = graph.getPosition(edge.getFromVertex());
                G.Position p2 = graph.getPosition(edge.getToVertex());
                int x1 = p1.getX();
                int y1 = p1.getY();
                int x2 = p2.getX();
                int y2 = p2.getY();
                g.drawLine(x1, y1, x2, y2);
                

                G graph = new G();
                int centreX;
                int centreY;
                
                for(int i=0;i<totalAH;i++){
                    int X1=cX[c1[i]];
                    int Y1=cY[c1[i]];

                    int X2=cX[c2[i]];
                    int Y2=cY[c2[i]];
                    String weight = Double.toString(H[i]);
                    
                    if (X1 > X2)
                        centreX = X2 + ((X1 - X2) / 2);
                    else
                        centreX = X1 + ((X2 - X1) / 2);
                    if (Y1 > Y2)
                        centreY = Y2 + ((Y1 - Y2) / 2);
                    else
                        centreY = Y1 + ((Y2 - Y1) / 2);

                    g.drawChars(weight.toCharArray(), 0, weight.indexOf("."), centreX, centreY);
                }   
            }

        }
    }
    
    public ObservableList<NodeDetail> getDetail(){
        ObservableList<NodeDetail> products = FXCollections.observableArrayList();
        
        return products;
    }
    
    public ObservableList<NodeHeight> getNodeHeightDetail(){
        ObservableList<NodeHeight> products2 = FXCollections.observableArrayList();
        
        return products2;
    }
    
    //addItem method
    public void addItem(){
        NodeDetail detail = new NodeDetail();
        detail.setNodeID(Integer.parseInt(nodeInput.getText()));
        detail.setFoodID(Integer.parseInt(foodInput.getText()));
        detail.setSpaceID(Integer.parseInt(spaceInput.getText()));
        detail.setConnectID(Integer.parseInt(connectInput.getText()));
        table.getItems().add(detail);
        nodeInput.clear();
        foodInput.clear();
        spaceInput.clear();
        connectInput.clear();
    }
    
    
}
