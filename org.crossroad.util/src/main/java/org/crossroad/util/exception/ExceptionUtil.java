/**
 * 
 */
package org.crossroad.util.exception;

import org.apache.commons.io.output.TeeOutputStream;

import com.sun.mail.iap.Argument;

/**
 * @author e.soden
 *
 */
public final class ExceptionUtil {

	/**
	 * 
	 */
	private ExceptionUtil() {
		
	}
	
	
	public static String exceptionToString(Throwable t)
	{
		StringBuffer buffer = new StringBuffer();
		
		String s = t.getClass().getName();
        String message = t.getLocalizedMessage();
        buffer.append((message != null) ? (s + ": " + message) : s).append("\n");
        
		for(StackTraceElement trace:t.getStackTrace())
		{
			
			buffer.append("\tat ").append(trace.toString()).append("\n");
		}
		
		for (Throwable suppressed:t.getSuppressed())
		{
			buffer.append(exceptionToString(suppressed)).append("\n");
		}
		
		Throwable cause = t.getCause();
		if (cause != null)
		{
			buffer.append(exceptionToString(cause)).append("\n");
		}
		return buffer.toString();
	}

}
