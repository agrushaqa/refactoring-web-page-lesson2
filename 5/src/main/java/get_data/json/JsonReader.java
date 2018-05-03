package get_data.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

public class JsonReader {
    private JSONObject jsonObject;

    public JsonReader(String fileName)
    {
        String fileContent = readAllBytesJava(fileName);
        JSONParser jsonParser = new JSONParser();
        try{
            jsonObject = (JSONObject) jsonParser.parse(fileContent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String readAllBytesJava(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes(Paths.get(filePath)) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }


    public String get(String parameter)
    {
        if(jsonObject.containsKey(parameter) == false)
            throw new InvalidParameterException(parameter + " is absent");
        return jsonObject.get(parameter).toString();
    }
}
