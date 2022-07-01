package kyu6;

public class ConvertStringToCamelCase {

    public static String toCamelCase(String s) {

        StringBuilder x = new StringBuilder();
        boolean capitalize = false;
        boolean first = true;

        for (char c : s.toCharArray()) {
            if (capitalize) {
                capitalize = false;
                x.append(Character.toUpperCase(c));
                continue;
            }

            if (c == '-' || c == '_') {
                capitalize = true;
                continue;
            }

            if (first && Character.isUpperCase(c)) {
                first = false;
                x.append(Character.toUpperCase(c));
                continue;
            }

            x.append(c);
        }

        return x.toString();
    }
}
