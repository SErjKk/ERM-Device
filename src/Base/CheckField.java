package Base;

public class CheckField {

    //метод проверки на содержание поля с текстом
    public static Boolean checkThisField(String field, char[] arr) {
        boolean b = false;
        for (int s = 0; s < arr.length; s++) {
            String symbol = String.valueOf(arr[s]);

            if (field.contains(symbol)) {
                b = true;
                break;
            } else
                b = false;
        }
        return b;
    }
}
