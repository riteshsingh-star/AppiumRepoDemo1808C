package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final String TestData_Path="src/test/resources/testData/";

    public static List<Map<String,String>> getJsonArrayFromJsonFile(String filePath){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(
                    new File(TestData_Path+filePath),
                    new TypeReference<List<Map<String, String>>>(){

                    }
            );
        }
        catch (Exception e){
            throw new RuntimeException("Failed to read json file "+filePath,e);
        }
    }
}
