package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = null;
        String returnType = null;

        String[] parts = signatureString.split(" ");
        int startIndex = 0;
        if (parts[0].equals("private")||parts[0].equals("public")||parts[0].equals("protected")) {
            accessModifier = parts[0];
            startIndex = 1;
        }
        else returnType = parts[0];
        returnType = parts[startIndex];

        String methodName = parts[startIndex + 1].substring(0, parts[startIndex + 1].indexOf("("));

        int indexOfOpenBracket = signatureString.indexOf("(");
        int indexOfCloseBracket = signatureString.indexOf(")");
        String argumentsString = signatureString.substring(indexOfOpenBracket + 1, indexOfCloseBracket);

        List<MethodSignature.Argument> argumentList = new ArrayList<>();

        if (!argumentsString.isEmpty()) {
            String[] argumentsArr = argumentsString.split(", ");
            for (int i = 0; argumentsArr.length != 0 && i < argumentsArr.length; i++) {
                String[] argParts = argumentsArr[i].split(" ");
                String argType = argParts[0];
                String argName = argParts[1];
                argumentList.add(new MethodSignature.Argument(argType, argName));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, argumentList);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
