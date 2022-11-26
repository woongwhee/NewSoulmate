package tk.newsoulmate.domain.vo.response;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class GsonDateFormate implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat dateFormat;
    public GsonDateFormate(){
        dateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new Date(dateFormat.parse(json.getAsString()).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateFormat.format(src.getTime()));
    }
}
