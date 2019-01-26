/*
 * BaseConverter.java
 * 
 * DESCRIPTION: This is the class that allows the user to convert between different
 * number bases. 
 * 
 * 
 * AUTHOR: Anthony Seo
 */

import java.awt.Color;

public class BaseConverter {
    private BaseConverter() {}

    /*
     * DESCRIPTION: Given a hex digit 0-9A-F, it will convert it to an integer value.
     */
    private static int hexCharToInt(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar == 'A' || hexChar == 'a') {
            return 10;
        } else if (hexChar == 'B' || hexChar == 'b') {
            return 11;
        } else if (hexChar == 'C' || hexChar == 'c') {
            return 12;
        } else if (hexChar == 'D' || hexChar == 'd') {
            return 13;
        } else if (hexChar == 'E' || hexChar == 'e') {
            return 14;
        } else if (hexChar == 'F' || hexChar == 'f') {
            return 15;
        }

        return -1; // Invalid option
    }
    
    /*
     * DESCRIPTION: Given a given an integer 0-15, it will return a hex digit.
     */
    private static char intToHexDigit(int num) {
        if (num >= 0 && num < 10) {
            return (char) (num + 48);
        } else if (num == 10) {
            return 'A';
        } else if (num == 11) {
            return 'B';
        } else if (num == 12) {
            return 'C';
        } else if (num == 13) {
            return 'D';
        } else if (num == 14) {
            return 'E';
        } else if (num == 15) {
            return 'F';
        }

        return '$'; // Invalid option. 
    }

    /*
     * DESCRIPTION: Adds zeros to strings if not enough characters are present.
     * Example: 2 = 10 in binary. After padding: 000.....0010.
     */
    private static String padZeros(String input, int size) {
        while (input.length() < size) {
            input = "0" + input;
        }

        return input;
    }

    public static String intToBinary(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            if ((num & 0x1) == 1) {
                output = "1" + output;
            } else {
                output = "0" + output;
            }
            num >>>= 1;
        }

        return padZeros(output, 32);
    }

    public static String intToOctal(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            int octalDigit = num & 7;
            num >>>= 3;
            output = octalDigit + output;
        }

        return padZeros(output, 11);
    }

    public static String intToHex(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            char hexDigit = intToHexDigit(num & 15);
            
            num >>>= 4;
            output = hexDigit + output;
        }

        return padZeros(output, 8);
    }

    public static String intToString(int num) {
        String result = "";
        while (num != 0) {
            result = (char) (num & 0xFF) + result;
            num >>>= 8;
        }

        return result;
    }

    public static Color intToColor(int num) {
        int alpha = (num >> 24) & 0xFF;
        int red = (num >> 16) & 0xFF;
        int green = (num >> 8) & 0xFF;
        int blue = num & 0xFF;
        
        // return new Color(red, green, blue, alpha);
        return new Color(red, green, blue);
    }

    public static float intToFloat(int num) {
        int mantissa = num & 0x7FFFFF;
        int exponent = (num >> 23) & 0xFF;
        int sign = (num >> 31) & 0x1;

        // Check for Infinity, -Infinity, NaN
        if (exponent == 0xFF) {
            if (mantissa != 0) {
                return Float.NaN;
            } else if (sign == 0 && mantissa == 0) {
                return Float.POSITIVE_INFINITY;
            } else if (sign == 1 && mantissa == 0) {
                return Float.NEGATIVE_INFINITY;
            }
        }

        double result = 0;
        int nthMantissaDigit = -1;
        while (mantissa != 0) {
            if ((mantissa & 0x400000) != 0) {
                result += Math.pow(2, nthMantissaDigit);
            }
            mantissa <<= 1;
            nthMantissaDigit--;
        }
        result += 1;
        
        return (float) (Math.pow(-1, sign) * result * Math.pow(2, exponent - 127));
    }

    public static int binaryToInt(String binaryNum) {
        char[] binaryNumArr = binaryNum.toCharArray();
        int result = 0;
        for (int i = 0; i < binaryNumArr.length; i++) {
            result <<= 1;
            int bitVal = binaryNumArr[i] - '0';
            result = result | bitVal;
        }

        return result;
    }

    public static String binaryToOctal(String binaryNum) {
        int binaryVal = binaryToInt(binaryNum);
        return intToOctal(binaryVal);
    }

    public static String binaryToHex(String binaryNum) {
        int binaryVal = binaryToInt(binaryNum);
        return intToHex(binaryVal);
    }

    public static String binaryToString(String binaryNum) {
        int binaryVal = binaryToInt(binaryNum);
        return intToString(binaryVal);
    }

    public static Color binaryToColor(String binaryNum) {
        int binaryVal = binaryToInt(binaryNum);
        return intToColor(binaryVal);
    }

    public static float binaryToFloat(String binaryNum) {
        int binaryVal = binaryToInt(binaryNum);
        return intToFloat(binaryVal);
    }

    public static int octalToInt(String octNum) {
        char[] octNumArr = octNum.toCharArray();
        int result = 0;
        for (int i = 0; i < octNumArr.length; i++) {
            result <<= 3;
            int charVal = octNumArr[i] - '0';
            result = result | charVal;
        }
        return result;
    }

    public static String octalToBinary(String octNum) {
        int octalIntVal = octalToInt(octNum);
        return intToBinary(octalIntVal);
    }

    public static String octalToHex(String octNum) {
        int octalIntVal = octalToInt(octNum);
        return intToHex(octalIntVal);
    }

    public static String octalToString(String octNum) {
        int octalIntVal = octalToInt(octNum);
        return intToString(octalIntVal);
    }

    public static Color octalToColor(String octNum) {
        int octalIntVal = octalToInt(octNum);
        return intToColor(octalIntVal);
    }

    public static float octalToFloat(String octNum) {
        int octalIntVal = octalToInt(octNum);
        return intToFloat(octalIntVal);
    }

    public static int hexToInt(String hexNum) {
        char[] hexNumArr = hexNum.toCharArray();
        int result = 0;
        for (int i = 0; i < hexNumArr.length; i++) {
            result <<= 4;
            int charVal = hexCharToInt(hexNumArr[i]);
            result = result | charVal;
        }

        return result;
    }

    public static String hexToBinary(String hexNum) {
        int hexIntVal = hexToInt(hexNum);
        return intToBinary(hexIntVal);
    }

    public static String hexToOctal(String hexNum) {
        int hexIntVal = hexToInt(hexNum);
        return intToOctal(hexIntVal);
    }

    public static String hexToString(String hexNum) {
        int hexIntVal = hexToInt(hexNum);
        return intToString(hexIntVal);
    }

    public static Color hexToColor(String hexNum) {
        int hexIntVal = hexToInt(hexNum);
        return intToColor(hexIntVal);
    }

    public static Float hexToFloat(String hexNum) {
        int hexIntVal = hexToInt(hexNum);
        return intToFloat(hexIntVal);
    }

    public static int stringToInt(String strInput) {
        char[] chArray = strInput.toCharArray();
        int result = 0;
        for (char i : chArray) {
            result <<= 8;
            result |= (int) i;
        }

        return result;
    }

    public static String stringToBinary(String strInput) {
        int stringVal = stringToInt(strInput);
        return intToBinary(stringVal);
    }

    public static String stringToOctal(String strInput) {
        int stringVal = stringToInt(strInput);
        return intToOctal(stringVal);
    }

    public static String stringToHex(String strInput) {
        int stringVal = stringToInt(strInput);
        return intToHex(stringVal);
    }

    public static Color stringToColor(String strInput) {
        int stringVal = stringToInt(strInput);
        return intToColor(stringVal);
    }

    public static float stringToFloat(String strInput) {
        int stringVal = stringToInt(strInput);
        return intToFloat(stringVal);
    }

    public static int colorToInt(Color color) {
        int alpha = color.getAlpha();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int result = (alpha << 24) | 0;
        result = (red << 16) | result;
        result = (green << 8) | result;
        result = blue | result;
        return result;
    }

    public static String colorToBinary(Color color) {
        int colorNum = colorToInt(color);
        return intToBinary(colorNum);
    }

    public static String colorToOctal(Color color) {
        int colorNum = colorToInt(color);
        return intToOctal(colorNum);
    }

    public static String colorToHex(Color color) {
        int colorNum = colorToInt(color);
        return intToHex(colorNum);
    }

    public static String colorToString(Color color) {
        int colorNum = colorToInt(color);
        return intToString(colorNum);
    }

    public static float colorToFloat(Color color) {
        int colorNum = colorToInt(color);
        return intToFloat(colorNum);
    }

    public static int floatToInt(float floatInput) {
        String floatBinary = floatToBinary(floatInput);
        return binaryToInt(floatBinary);
    }

    public static String floatToBinary(float floatInput) {
        // Take care of the edge cases in this if block
        if (floatInput == 0) {
            return "00000000000000000000000000000000";
        } else if (floatInput == Float.POSITIVE_INFINITY) {
            return "01111111100000000000000000000000";
        } else if (floatInput == Float.NEGATIVE_INFINITY) {
            return "11111111100000000000000000000000";   
        } else if (floatInput == Float.NaN) {
            return "01111111111111111111111111111111";
        }

        // Get the sign of the float. Multiply float by -1 for calculations later on
        int sign;
        if (floatInput < 0) {
            sign = 1;
            floatInput *= -1;
        } else {
            sign = 0;
        }

        // divide or multiply by two and keep track of the number of times
        // divided or multiplied for the exponent. 
        int powerCount = 0;
        if (floatInput > 2) {
            while (floatInput >= 2) {
                floatInput /= 2;
                powerCount++;
            }
        } else {
            while (floatInput < 1) {
                floatInput *= 2;
                powerCount--;
            }
        }

        floatInput -= 1;
        String mantissa = "";
        for (int i = 0; i < 23; i++) {
            floatInput *= 2;
            if (floatInput >= 1) {
                floatInput -= 1;
                mantissa = mantissa + 1;
            } else {
                mantissa = mantissa + 0;
            }
        }
        
        int exponent = powerCount + 127;
        String expStr = "";
        while (exponent != 0) {
            if ((exponent & 0x1) == 1) {
                expStr = "1" + expStr;
            } else {
                expStr = "0" + expStr;
            }
            exponent >>>= 1;
        }
        // need to pad exponent to ensure we have 8 bits.
        while (expStr.length() < 8) {
            expStr = "0" + expStr;
        }
        String result = sign + expStr + mantissa;

        return result;
    }

    public static String floatToOctal(float floatInput) {
        String floatBinary = floatToBinary(floatInput);
        return binaryToOctal(floatBinary);
    }

    public static String floatToHex(float floatInput) {
        String floatBinary = floatToBinary(floatInput);
        return binaryToHex(floatBinary);
    }

    public static String floatToString(float floatInput) {
        String floatBinary = floatToBinary(floatInput);
        return binaryToString(floatBinary);
    }

    public static Color floatToColor(float floatInput) {
        String floatBinary = floatToBinary(floatInput);
        return binaryToColor(floatBinary);
    }

    // Main function is used to test the methods in this class. 
    public static void main(String[] args) {
        System.out.println("Testing int methods. Number chosen = 1784772193");
        System.out.println(intToBinary(1784772193));
        System.out.println(intToOctal(1784772193));
        System.out.println(intToHex(1784772193));
        System.out.println(intToString(1784772193));
        System.out.println(intToColor(1784772193));
        System.out.println(intToFloat(1784772193));

        System.out.println("\nTesting binary methods. Number chosen = 1101010011000010111011001100001");
        System.out.println(binaryToInt("1101010011000010111011001100001"));
        System.out.println(binaryToOctal("1101010011000010111011001100001"));
        System.out.println(binaryToHex("1101010011000010111011001100001"));
        System.out.println(binaryToString("1101010011000010111011001100001"));
        System.out.println(binaryToColor("1101010011000010111011001100001"));
        System.out.println(binaryToFloat("1101010011000010111011001100001"));


        System.out.println("\nTesting octal methods. Number chosen = 15230273141");
        System.out.println(octalToInt("15230273141"));
        System.out.println(octalToBinary("15230273141"));
        System.out.println(octalToHex("15230273141"));
        System.out.println(octalToString("15230273141"));
        System.out.println(octalToColor("15230273141"));
        System.out.println(octalToFloat("15230273141"));

        System.out.println("\nTesting hex methods. Number chosen = 6A617661");
        System.out.println(hexToInt("6A617661"));
        System.out.println(hexToBinary("6A617661"));
        System.out.println(hexToOctal("6A617661"));
        System.out.println(hexToString("6A617661"));
        System.out.println(hexToColor("6A617661"));
        System.out.println(hexToFloat("6A617661"));


        System.out.println("\nTesting string methods. String to test = java");
        System.out.println(stringToInt("java"));
        System.out.println(stringToBinary("java"));
        System.out.println(stringToOctal("java"));
        System.out.println(stringToHex("java"));
        System.out.println(stringToColor("java"));
        System.out.println(stringToFloat("java"));

        System.out.println("\nTesting color methods. Color to test = 00617661");
        Color testColor = new Color(0x61, 0x76, 0x61);
        System.out.println(colorToInt(testColor));

        System.out.println("\nTesting float methods. Float to test = 6.8141834E25");
        System.out.println(floatToInt((float) 6.8141834E25));
        System.out.println(floatToBinary((float) 6.8141834E25));
        System.out.println(floatToOctal((float) 6.8141834E25));
        System.out.println(floatToHex((float) 6.8141834E25));
        System.out.println(floatToString((float) 6.8141834E25));
        System.out.println(floatToColor((float) 6.8141834E25));


        System.out.println("\n");

        System.out.println(floatToInt((float) -0.000001234));
        System.out.println(floatToBinary((float) -0.000001234));
        System.out.println(floatToHex((float) -0.000001234));
        System.out.println(floatToBinary((float) 0));
        System.out.println(floatToBinary(Float.POSITIVE_INFINITY));
        System.out.println(floatToBinary(Float.NEGATIVE_INFINITY));
        System.out.println(floatToBinary((float) 1E-10));
        System.out.println(intToBinary(-1));

        System.out.println(intToBinary(-1));
        System.out.println(intToOctal(-1));
        System.out.println(intToHex(-1));
        System.out.println(intToString(-1));
        System.out.println(intToColor(-1));
        System.out.println(intToFloat(-1));
    }
}