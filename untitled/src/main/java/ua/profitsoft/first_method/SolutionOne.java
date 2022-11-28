package ua.profitsoft.first_method;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.profitsoft.second_method.model.SolutionTwo.jacksonFromJsonToXml;

public class SolutionOne {
    public static void main(String[] args) {
        jacksonFromJsonToXml();
    }
        static final String inputPath = "src/main/resources/input/inputPersons.xml";
        static final String outPath = "src/main/resources/output/outputPersons.xml";


        public static String parsingXmlAttributes(String input) {
            Pattern pattern1 = Pattern.compile("\\\"\\W+\\\"");
            Matcher matcher1 = pattern1.matcher(input);
            String resName = "\"";
            int i = 0;
            while (matcher1.find()) {
                String name = matcher1.group();
                name = name.replaceAll("\"", "");
                resName += name;
                if(i == 0) {
                    resName += " ";
                    i++;
                }
            }
            resName += "\"";
            input = input.replaceAll("surname\\W+\"", "");
            return input.replaceAll("\\\"\\W+\\\"", resName);
        }


        public static boolean isXmlElementNotClosed(String input) {
            return !input.endsWith("/>");
        }

        public static boolean isPersonXmlElement(String input) { return input.trim().startsWith("<person"); }

        //MAIN METHOD
        public static void changeAttributeNameAndSurnameInCopiedFile(String path) throws IOException {
            try(BufferedReader reader = new BufferedReader(new FileReader(inputPath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outPath))) {
                while (reader.ready()) {

                    String xmlElement = reader.readLine();

                    if (isPersonXmlElement(xmlElement)) {
                        while(isXmlElementNotClosed(xmlElement)) {
                            xmlElement += "\n" + reader.readLine();
                        }
                        xmlElement = parsingXmlAttributes(xmlElement);
                    }

                    writer.write(xmlElement);
                    writer.newLine();
                }

            } catch (IOException exp) {
                throw new RuntimeException(exp.getMessage());
            }

        }
    }

