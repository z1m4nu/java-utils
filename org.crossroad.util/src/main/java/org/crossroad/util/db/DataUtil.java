/**
 * 
 */
package org.crossroad.util.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author e.soden
 *
 */
public class DataUtil {
	private static final Logger log = LogManager.getLogger(DataUtil.class.getName());

	/**
	 * 
	 */
	private DataUtil() {
		// TODO Auto-generated constructor stub
	}

	public static Object convertFromString(DBType type, String value, String format) throws Exception {
		Object obj = null;

		switch (type) {
		case BIGINTEGER:
			value = value.trim();
			value = (!value.equalsIgnoreCase("?")) ? value : "0";
			if (format != null) {
				NumberFormat instance = NumberFormat.getNumberInstance();
				((DecimalFormat) instance).applyPattern(format);
				Number nm = instance.parse(value);
				obj = BigInteger.valueOf(nm.longValue());
			} else {
				obj = BigInteger.valueOf(Long.valueOf(value));
			}
			break;
		case DATE:
			value = value.trim();
			SimpleDateFormat dtFormat = new SimpleDateFormat(format);
			value = (!value.equalsIgnoreCase("?")) ? value : dtFormat.format(new Date());
			obj = dtFormat.parse(value);
			break;
		case FLOAT:
			value = value.trim();
			value = (!value.equalsIgnoreCase("?")) ? value : "0";
			obj = Float.valueOf(value);
			break;
		case INTEGER:
			value = value.trim();
			value = (!value.equalsIgnoreCase("?")) ? value : "0";
			obj = Integer.valueOf((value == null) ? "0" : value);
			break;
		case LONG:
			value = value.trim();
			value = (!value.equalsIgnoreCase("?")) ? value : "0";
			if (format != null) {
				NumberFormat instance = NumberFormat.getNumberInstance();
				((DecimalFormat) instance).applyPattern(format);
				Number nm = instance.parse(value);
				obj = nm.longValue();
			} else {
				obj = Long.valueOf(value);
			}
			break;
		case STRING:
			obj = value.trim();
			break;
		case UNKNOWN:
		default:
			throw new Exception("Unable to convert type [" + type + "]");
		}

		return obj;
	}

	public static String formatAsString(IDBData value, String format) throws Exception {
		String result = null;

		switch (value.getType()) {
		case BIGINTEGER:
			if (format != null) {
				NumberFormat fmt = NumberFormat.getNumberInstance();
				((DecimalFormat) fmt).applyPattern(format);
				result = fmt.format((BigInteger) value.getData());
			} else {
				result = value.getData().toString();
			}
			break;
		case FLOAT:
			if (format != null) {
				NumberFormat fmt = NumberFormat.getNumberInstance();
				((DecimalFormat) fmt).applyPattern(format);
				result = fmt.format((Float) value.getData());
			} else {
				result = value.getData().toString();
			}
			break;
		case INTEGER:
			if (format != null) {
				NumberFormat fmt = NumberFormat.getNumberInstance();
				((DecimalFormat) fmt).applyPattern(format);
				result = fmt.format((Integer) value.getData());
			} else {
				result = value.getData().toString();
			}
			break;
		case LONG:
			if (format != null) {
				NumberFormat fmt = NumberFormat.getNumberInstance();
				((DecimalFormat) fmt).applyPattern(format);
				result = fmt.format((Long) value.getData());
			} else {
				result = value.getData().toString();
			}
			break;
		case STRING:
			result = value.getData().toString();
			break;
		case DATE:
			SimpleDateFormat sFormat = null;
			
			if ("INSTANT".equals(format)) {
				sFormat  =new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
				sFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			} else {
				sFormat = new SimpleDateFormat(format);				
			}
			
			if (sFormat != null)
			{
				Date dt = null;
				if (value.getData() instanceof Date)
				{
					dt = (Date) value.getData();
				} else {
					SimpleDateFormat fromString = new SimpleDateFormat("yyyyMMdd");
					dt = fromString.parse(value.getData().toString());
				}
				result = sFormat.format(dt);
			}
			break;
		default:
			throw new Exception("Unable to proceed with specified type for [" + value.getName() + "]");
		}

		return result;
	}

	public static Map<String, IDBData> deSerialize(String file) {
		Map<String, IDBData> datas = null;
		FileInputStream stream = null;
		ObjectInputStream oStream = null;
		BufferedInputStream bStream = null;
		try {
			stream = new FileInputStream(file);
			bStream = new BufferedInputStream(stream);
			oStream = new ObjectInputStream(bStream);
			datas = (Map<String, IDBData>) oStream.readObject();

			oStream.reset();
		} catch (Exception e) {
			log.error("Unable to serialize IDBData", e);
		} finally {
			if (oStream != null) {
				try {
					oStream.close();
				} catch (IOException e) {
					log.error("Unable to close objectOutputStream", e);
				}
			}

			if (bStream != null) {
				try {
					bStream.close();
				} catch (IOException e) {
					log.error("Unable to close objectOutputStream", e);
				}
			}

			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error("Unable to close objectOutputStream", e);
				}
			}
		}

		return datas;
	}
}
