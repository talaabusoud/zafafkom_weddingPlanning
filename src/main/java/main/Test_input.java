package main;

public class Test_input {
    public static boolean type(String type) {
        type = type.toLowerCase();
        if (type.equals("hall") || type.equals("Studio") || type.equals("dj") || type.equals("food") || type.equals("zaffa") || type.equals("decoration")) {
            return true;
        } else return false;
    }

    public static boolean Name(String name) {
        boolean re = true;
        if (name.length() > 0) {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                    re = false;
                    break;
                }
            }
        }
        return re;
    }

    public static boolean Phone(String phone) {
        boolean re = true;
        if (phone.length() == 10) {
            for (int i = 0; i < phone.length(); i++) {
                if (Character.isLetter(phone.charAt(i))) {
                    re = false;
                    break;
                }
            }
        }else  re = false;
        return re;

    }

    public static boolean Price(String price) {
        boolean re = true;
        if (price.length() > 0) {
            for (int i = 0; i < price.length(); i++) {
                if (Character.isLetter(price.charAt(i))) {
                    re = false;
                    break;
                }
            }
        }
        return re;


    }

    public static boolean imge(String img) {
        if (img.endsWith(".png") || img.endsWith(".jpg")) {
            return true;
        } else {
            return false;
        }
    }
}