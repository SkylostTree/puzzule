package newPuzzle;

public class newStart {
    public static void main(String[] args) {
        int arr0[]={1,2,3,6,8,5,7,4};
        int arr[][] = new int[3][3];
        arr= tool.creat();
        tool.printIt(arr);
        tool.struct(arr);
        while (!tool.judegeCase2(arr)) {
            int x= tool.nextOne(arr);
            if (!tool.judgeCase0(arr)) {
                tool.exchangeElements(x, arr);
                while (!tool.judgeCase00(x, arr)) {
                    tool.clokwiseExchange(arr);
                }
                tool.exchangeElements(x, arr);
            } else {
                int a = tool.selectOneToHelp(arr);
                tool.judgeCase1(x, a, arr);
            }
        }
        tool.exchangeElements(5,arr);
        while (!tool.judgeCase3(arr)){
            tool.clokwiseExchange(arr);
        }
    }
}
