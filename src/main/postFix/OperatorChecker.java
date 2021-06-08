package main.postFix;

public class OperatorChecker {

    public static void main(String args[]) {

        System.out.println(compare("(", "-"));

    }

    public static boolean compare(String newV, String oldV) {

        if(newV != null && oldV != null) {

            //Gleichheitsfall
            if(newV.equals(oldV)) {
                return true;
            }
            //positiver fall wenn man drauf packen darf
            else if((newV.equals("+") || newV.equals("-")) && (!newV.equals("+") || !newV.equals("-"))) {
                return true;
            }
        }

        return false;
    }
}
