package org.example;

/**
 * A class that demonstrates various ways in which CSV values
 * would like when quotes are involved. The quotes could be
 * involved:
 *
 * <ul>
 *     <li>
 *     To escape the values that contain special characters
 *     </li>
 *     <li>
 *     The values itself may contain quotes which need to be escaped
 *     appropriately.
 *     </li>
 * </ul>
 *
 * <a href="https://stackoverflow.com/a/69535500/5614968">Reference</a>
 */
public class CSVQuotes {
    public static String addQuote(
            String pValue) {
        if (pValue == null) {
            return null;
        } else {
            if (pValue.contains("\"")) {
                pValue = pValue.replace("\"", "\"\"");
            }
            if (pValue.contains(",")
                    || pValue.contains("\n")
                    || pValue.contains("'")
                    || pValue.contains("\\")
                    || pValue.contains("\"")) {
                return "\"" + pValue + "\"";
            }
        }
        return pValue;
    }

    public static void main(String[] args) {
        System.out.println("ab\nc" + "|||" + addQuote("ab\nc"));
        System.out.println("a,bc" + "|||" + addQuote("a,bc"));
        System.out.println("a,\"bc" + "|||" + addQuote("a,\"bc"));
        System.out.println("a,\"\"bc" + "|||" + addQuote("a,\"\"bc"));
        System.out.println("\"a,\"\"bc\"" + "|||" + addQuote("\"a,\"\"bc\""));
        System.out.println("\"a,\"\"bc" + "|||" + addQuote("\"a,\"\"bc"));
        System.out.println("a,\"\"bc\"" + "|||" + addQuote("a,\"\"bc\""));
    }
}
