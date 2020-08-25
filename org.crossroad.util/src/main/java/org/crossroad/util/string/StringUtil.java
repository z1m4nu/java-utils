package org.crossroad.util.string;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crossroad.util.db.DataUtil;
import org.crossroad.util.db.IDBData;

public class StringUtil {
	private final static Logger log = LogManager.getLogger(StringUtil.class.getName());
	private StringUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String transform(String template, Map<String, String> data) throws Exception {
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(template);

		while (matcher.find()) {
			String key = matcher.group(1);
			String replace = null;

			replace = data.get(key);

			template = template.replaceAll("\\$\\{" + key + "\\}", replace);

		}

		return template;
	}
	
	public static String computeString(String template, Map<String, IDBData> values, String separator)
			throws Exception {
		String parsedString = null;

		log.debug("Template [" + template + "]");

		parsedString = template.replace("#", separator);

		log.debug("Template with separator [" + parsedString + "]");

		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(parsedString);

		while (matcher.find()) {
			String key = matcher.group(1);
			String replace = null;

			if (key.contains("@")) {
				String[] l = key.split("@");
				replace = DataUtil.formatAsString(values.get(l[0]), l[1]);
			} else {
				replace = values.get(key).getValueAsString();
			}
			parsedString = parsedString.replaceAll("\\$\\{" + key + "\\}", replace);

		}

		log.debug("ParsedString [" + parsedString + "]");
		return parsedString;
	}
	public static final boolean isUTF8(final byte[] pText) {

	    int expectedLength = 0;

	    for (int i = 0; i < pText.length; i++) {
	        if ((pText[i] & 0b10000000) == 0b00000000) {
	            expectedLength = 1;
	        } else if ((pText[i] & 0b11100000) == 0b11000000) {
	            expectedLength = 2;
	        } else if ((pText[i] & 0b11110000) == 0b11100000) {
	            expectedLength = 3;
	        } else if ((pText[i] & 0b11111000) == 0b11110000) {
	            expectedLength = 4;
	        } else if ((pText[i] & 0b11111100) == 0b11111000) {
	            expectedLength = 5;
	        } else if ((pText[i] & 0b11111110) == 0b11111100) {
	            expectedLength = 6;
	        } else {
	            return false;
	        }

	        while (--expectedLength > 0) {
	            if (++i >= pText.length) {
	                return false;
	            }
	            if ((pText[i] & 0b11000000) != 0b10000000) {
	                return false;
	            }
	        }
	    }

	    return true;
	}
}
