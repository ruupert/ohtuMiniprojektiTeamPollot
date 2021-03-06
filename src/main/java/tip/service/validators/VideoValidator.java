package tip.service.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class VideoValidator {

    public List<String> validate(Tip video) {
        List<String> errors = new ArrayList<>();

        if (validateName(video) == false) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (validateArtist(video) == false) {
            errors.add("Isäntä on joko tyhjä tai max 63 merkkiä");
        }
        if (video.getType() == null || video.getType().equals("video") == false) {
            errors.add("Ei ole video");
        }
//        if (!isValid(video.getDetails().get("date").toString())) {
//            errors.add("not a valid date");
//        }

        if (validateUrl(video) == false) {
            errors.add("url on vääränlainen");
        }

        return errors;
    }

    private boolean validateName(Tip t) {

        if (t.getName().trim().isEmpty()) {
            return false;
        } else if (t.getName().length() > 63) {
            return false;
        }
        return true;
    }

    private boolean validateArtist(Tip t) {
        if (t.getDetails().get("writer") == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue() == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue().length() > 63) {
            return false;
        }
        return true;
    }

    private boolean validateUrl(Tip video) {
        if (video.getDetails().get("url").toString().matches("^(https|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid(String date) {
        // vähän tuplasti tuli tassa tata tsekattua, mutta etuna on, että
        // jos ei regex mätsää niin ei tarvii luoda noita javan muita 
        // palleroita eli säästeliäämpi. 
        if (date.matches("^(?:(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9])|(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9])).[0-9]{4}$")) {
            try {
                DateFormat d = new SimpleDateFormat("MM.dd.yyyy");
                Date parsedDate = d.parse(date);
                if (d.format(parsedDate).equals(date)) {
                    return true;
                } else {
                    return false;
                }
            } catch (ParseException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

}
