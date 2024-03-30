package main;

public class TestInput {
    public static boolean type(String type) {
        type = type.toLowerCase();
        return type.equals("hall") || type.equals("studio") || type.equals("dj")
                || type.equals("food") || type.equals("zaffa") || type.equals("decoration");
    }


    public static boolean isValidName(String name) {
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

    public static boolean isValidPhone(String phone) {
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

    public static boolean isValidPrice(String price) {
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
        return img.endsWith(".png") || img.endsWith(".jpg");

    }
}