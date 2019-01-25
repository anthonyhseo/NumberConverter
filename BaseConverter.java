import java.awt.Color;

public class BaseConverter {
    private BaseConverter() {}

    private static int hexCharToInt(char hexChar) {
        if (hexChar == '0') {
            return 0;
        } else if (hexChar == '1') {
            return 1;
        } else if (hexChar == '2') {
            return 2;
        } else if (hexChar == '3') {
            return 3;
        } else if (hexChar == '4') {
            return 4;
        } else if (hexChar == '5') {
            return 5;
        } else if (hexChar == '6') {
            return 6;
        } else if (hexChar == '7') {
            return 7;
        } else if (hexChar == '8') {
            return 8;
        } else if (hexChar == '9') {
            return 9;
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

    public static String intToBinary(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            int remainder = num % 2;
            num /= 2;
            output = remainder + output;
        }

        return output;
    }

    public static String intToOctal(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            int octalDigit = num & 7;
            num >>= 3;
            output = octalDigit + output;
        }

        return output;
    }

    public static String intToHex(int num) {
        if (num == 0) return "0";

        String output = "";
        while (num != 0) {
            char hexDigit = intToHexDigit(num & 15);
            
            num >>= 4;
            output = hexDigit + output;
        }

        return output;
    }

    public static String intToString(int num) {
        String result = "";
        while (num != 0) {
            result = (char) (num & 0xFF) + result;
            num >>= 8;
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
        for (int i = binaryNumArr.length - 1; i >= 0; i--) {
            int bitVal = binaryNumArr[i] - '0';
            result += bitVal * Math.pow(2, binaryNumArr.length - 1 - i);
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
        for (int i = octNumArr.length - 1; i >= 0; i--) {
            int charVal = octNumArr[i] - '0';
            result += charVal * Math.pow(8, octNumArr.length - 1 - i);
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
        for (int i = hexNumArr.length - 1; i >= 0; i--) {
            int charVal = hexCharToInt(hexNumArr[i]);
            result += charVal * Math.pow(16, hexNumArr.length - 1 - i);
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
        String expStr = intToBinary(exponent);
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
    }
}