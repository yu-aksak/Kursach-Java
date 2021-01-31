package Client;

public class CheckValid {
    public static boolean checkString(String str){
        String sub1 = "*", sub2 = "/", sub3 = "+", sub4 = "[", sub5 = "]",
                sub6 = "%", sub7 = "#", sub8 = "<", sub9 = ">",
                sub11 = "@", sub12 = "$", sub13 = "-", sub14 = ".", sub15 = "+";
        if (str.contains(sub1) || str.contains(sub2)
                || str.contains(sub3) || str.contains(sub4) || str.contains(sub5)
                || str.contains(sub6) || str.contains(sub7) || str.contains(sub8)
                || str.contains(sub9) || str.contains(sub11) || str.contains(sub12)
                || str.contains(sub13) || str.contains(sub14) || str.contains(sub15))
        {
            return false;
        }
        return true;
    }
}
