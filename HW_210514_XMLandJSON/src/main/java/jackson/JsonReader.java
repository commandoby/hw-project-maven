package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader<T> {
    ObjectMapper mapper;
    private Object Class;


    public JsonReader() {
        mapper = new ObjectMapper();
    }

    public void write (T t, File file) throws IOException {
        mapper.writeValue(file, t);
    }

    public T read (File file, Class<T> tClass) throws IOException {
        return mapper.readValue(file, tClass);
    }
}
