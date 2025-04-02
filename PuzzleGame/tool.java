package newPuzzle;
import java.util.ArrayList;
import java.util.Scanner;

public class tool {
    //获取元素x轴坐标
    public static int toFindNumberOfX(int x,int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
            if (x==arr[i][j]){
                return i;
            }
            }
        }
        return -1;
    }

    //获取元素y轴坐标
    public static int toFindNumberOfY(int x,int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (x==arr[i][j]){
                    return j;
                }
            }
        }
        return -1;
    }

    //判断旋转是否合法
    public static boolean judgeExchange(int[][]arr){
        if (toFindNumberOfX(0,arr)==1&&toFindNumberOfY(0,arr)==1){
            System.out.println("旋转不合法");
        return false;
        }
        else return true;
    }
    //旋转工具
    public static int clockwiseTool(int[][]arr){
        int[]k= {10,20,21,22,12,2,1,0};
        int x = toFindNumberOfX(0,arr);
        int y = toFindNumberOfY(0,arr);
        int xy=x*10+y;
        for (int i = 0; i < k.length-1; i++) {
            if (xy==k[i]){
                return k[i+1];
            }
        }
        return k[0];
    }

    //围绕中心0顺时针旋转
    public static void clokwiseExchange(int [][]arr){
        if (judgeExchange(arr)){
        int xy=clockwiseTool(arr);
        int x = xy/10;
        int y = xy%10;
        int Ox = toFindNumberOfX(0,arr);
        int Oy = toFindNumberOfY(0,arr);
        int temp = arr[x][y];
            System.out.println(arr[x][y]+"->0");
            arr[x][y]=arr[Ox][Oy];
            arr[Ox][Oy]=temp;
        }
    }

    //打印数组
    public static void printIt(int s[][]) {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(s[i][j] + "\t");
                if (j == 2) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();
    }

    //输入数组
    public static int[][] creat(){
        //未对输入内容进行约束。
        int a[][]=new int[3][3];
        Scanner input = new Scanner(System.in);
        System.out.println("请输入puzzle");
        //输入
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = input.nextInt();
            }
        }
        return a;
    }

    //判断元素是否在四个角落
    public static boolean isOnTheCross(int x,int[][]arr){
        int xX=toFindNumberOfX(x,arr);
        int xY=toFindNumberOfY(x,arr);
        if (xX==0){
            if (xY==0){return true;}
            else if (xY==2){return true;}
            else return false;
        }else if (xX==2){
            if (xY==0){return true;}
            else if (xY==2){return true;}
            else return false;
        }else return false;
    }

    /*x轴*10+y轴（坐标从0开始），00,01,02
                             10    12
                             20,21,22
                             此为判断顺时针旋转的逻辑(坐标)
                             以顺时针的方式存储代码(坐标)
                             eg:[0][0],[0][1],[0][2],[1][2],[2][2],[2][1],[2][0],[1][0]
                             此为返回的数组下标
    */
    public static ArrayList<Integer> absLine(int[][]arr){
        int[]k= {0,1,2,12,22,21,20,10};
        ArrayList<Integer> arr0 =new ArrayList<>();
        arr0.add(1);
        int temp=1;
        int x = toFindNumberOfX(1,arr);
        int y = toFindNumberOfY(1,arr);
        int xy=x*10+y;
        for (int i = 0; i < k.length; i++) {
            if (xy==k[i]){
                for (int j = 1; j < k.length-i; j++) {
                    int b=k[i+j];
                    int bx = b/10;
                    int by = b%10;
                    arr0.add(arr[bx][by]);
                    temp++;
                }
                for (int j = 0; j < 8-temp; j++) {
                    int b=k[j];
                    int bx = b/10;
                    int by = b%10;
                    arr0.add(arr[bx][by]);
                }
            }
        }
        return arr0;
    }

    //正确的数组
    public static ArrayList<Integer> absLineTrue(int [][]arr){
        int arr0[]={1,2,3,6,8,5,7,4};
        ArrayList<Integer> arrRightNow = absLine(arr);
        arrRightNow.remove((Integer) 0);
        ArrayList<Integer> trueLine =new ArrayList<>();

        for (int i = 0; i < arrRightNow.size(); i++) {
            if (arr0[i]==arrRightNow.get(i)){
            trueLine.add(arr0[i]);
            }else {
                break;
            }
        }
        return trueLine;
    }

    //判断下一个元素是否在对角
    public static boolean judgeCase0(int arr[][]){
        int x = nextOne(arr);
        if (isOnTheCross(x,arr)){
            return true;
        }else {
            return false;
        }
    }

    //判断下一个该还原谁
    public static int nextOne(int arr[][]){
        int arr0[]={1,2,3,6,8,5,7,4};
        ArrayList<Integer> trueLine=tool.absLineTrue(arr);
        int x = arr0[trueLine.size()];
        return x;
    }

    //交换数组元素（与0交换，且与0相邻）
    public static boolean exchangeElements(int x,int[][]arr){

        if (judgeIsExchange(x,arr)){
            int xx = toFindNumberOfX(x,arr);
            int xy = toFindNumberOfY(x,arr);
            int Ox = toFindNumberOfX(0,arr);
            int Oy = toFindNumberOfY(0,arr);
            int temp = arr[xx][xy];
            System.out.println(arr[xx][xy]+"->0");
            arr[xx][xy]=arr[Ox][Oy];
            arr[Ox][Oy]=temp;
            return true;
        }else return false;
    }

    //判断什么时候该交换
    public static boolean judgeIsExchange(int x,int [][]arr){
        int xx = toFindNumberOfX(x,arr);
        int xy = toFindNumberOfY(x,arr);
        int Ox = toFindNumberOfX(0,arr);
        int Oy = toFindNumberOfY(0,arr);
        int subtraction=xx+xy-Ox-Oy;
        if (Math.abs(subtraction)==1) return true;
        else return false;
        }


        //判断顺时针旋转什么时候需要停止
    public static boolean judgeCase00(int x,int arr[][]){
        ArrayList<Integer> trueLine=tool.absLineTrue(arr);
        ArrayList<Integer> absLine=tool.absLine(arr);
        if ((absLine.get(trueLine.size())==0)&&judgeIsExchange(x,arr)){
            return true;
        }else return false;
    }
    //借助无关的元素帮助
    public static int selectOneToHelp(int [][]arr){
        ArrayList<Integer> absLine=tool.absLine(arr);
        ArrayList<Integer> trueLine=tool.absLineTrue(arr);
        int i=0;
        int a=trueLine.size();
        while (isOnTheCross(absLine.get(a+i),arr)){
            i++;
        }
        return absLine.get(a+i);
    }

    //judgeCase0的相反情况，即要还原的元素在对角上。
public static void judgeCase1(int x,int a,int arr[][]){
        exchangeElements(a,arr);
//        printIt(arr);
        if (judgeCase10(x,a,arr)){
            while (!judgeCase00(a,arr)){
             clokwiseExchange(arr);
//             printIt(arr);
            }
            exchangeElements(a,arr);
//            printIt(arr);
        }else {
            while (!judgeCase11(x,a,arr)){
            clokwiseExchange(arr);
//            printIt(arr);
            }
            exchangeElements(a,arr);
//            printIt(arr);
        }
}
//判断什么时候顺时针旋转停下，且保证x不在对角。
public static boolean judgeCase11(int x,int a,int arr[][]){
    if (!isOnTheCross(x,arr)){
        if (judgeCase00(a,arr)){
            return true;
        }
        if (judgeCase12(a,arr)){
            return true;
        }
    }else {return false;}
    return false;
}
public static boolean judgeCase12(int x,int arr[][]){
    ArrayList<Integer> trueLine=tool.absLineTrue(arr);
    ArrayList<Integer> absLine=tool.absLine(arr);
    if (absLine.size()>=trueLine.size()+2){
        if ((absLine.get(trueLine.size()+1)==0)&&judgeIsExchange(x,arr)){
            return true;
        }
    }
        return false;
}
    //judgeCase0的相反情况，即要还原的元素在对角上,且让工具元素交换完之后，元素就神奇的被还原了
    public static boolean judgeCase10(int x,int a,int arr[][]){
    ArrayList<Integer> trueLine=tool.absLineTrue(arr);
    if (trueLine.contains(x)){
        return true;
    }else {
        return false;
    }
    }
    public static boolean judegeCase2(int arr[][]){
        int arr0[]={1,2,3,6,8,5,7,4};
        ArrayList<Integer> trueLine=absLineTrue(arr);
        if (trueLine.size()==arr0.length){
            return true;
        }
        else return false;
    }
    public static boolean judgeCase3(int arr[][]){
        int arr0[][]={{1,2,3},{4,5,6},{7,8,0}};
        for (int i = 0; i < arr0.length; i++) {
            for (int j = 0; j < arr0[0].length; j++) {
                if (arr[i][j]!=arr0[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    //如果用户输入时，0不在[1][1]处，还原至[1][1]处
    public static void struct(int arr[][]) {
        int bx = tool.toFindNumberOfX(0,arr);
        int by = tool.toFindNumberOfY(0,arr);
        int temp0;
        int temp1;
        if (by==0||by==2){
            System.out.println(arr[bx][1]+"->0");
            temp0=arr[bx][1];
            arr[bx][by]=temp0;
            arr[bx][1]=0;
//            printIt(arr);
        }
        bx = tool.toFindNumberOfX(0,arr);
        by = tool.toFindNumberOfY(0,arr);
        if (bx==0||bx==2){
            System.out.println(arr[1][1]+"->0");
            temp0=arr[1][1];
            arr[bx][by]=temp0;
            arr[1][1]=0;
//            printIt(arr);
        }
    }
}
