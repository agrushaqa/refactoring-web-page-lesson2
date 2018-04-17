import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;

public class JsonReader {
    JSONObject jsonObject;

    JsonReader(String FileName)
    {
        String FileContent = readAllBytesJava(FileName);
        JSONParser jsonParser = new JSONParser();
        try{
            jsonObject = (JSONObject) jsonParser.parse(FileContent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String readAllBytesJava(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }


    public String get(String Parameter)
    {
        if(jsonObject.containsKey(Parameter)==false)
            throw new InvalidParameterException(Parameter+" is absent");
        return jsonObject.get(Parameter).toString();
    }
}
