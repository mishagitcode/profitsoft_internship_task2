package ua.profitsoft.second_method.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionTwo {
        public static Map<String, Double> toMap(Map<String, Double> map, Violation violation) {
            if(!map.containsKey(violation.getType())){
                map.put(violation.getType(), violation.getFine_amount());
            } else {
                map.put(violation.getType(), map.get(violation.getType()) + violation.getFine_amount());
            }
            return map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }


        public static void jacksonFromJsonToXml() {
            File inputDir = new File("src/main/resources/input/inputJackson");
            Map<String, Double> map = new HashMap<>();
            for (File file: Objects.requireNonNull(inputDir.listFiles())) {
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
                try {
                    Violation v = jsonMapper.readValue(file, Violation.class);
                    XmlMapper xmlMapper = new XmlMapper();
                    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    try {
                        xmlMapper.writeValue(new File("src/main/resources/output/resultJackson.xml"), toMap(map, v));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

