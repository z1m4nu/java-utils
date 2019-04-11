/**
 * 
 */
package org.crossroad.util.exception;

/**
 * @author e.soden
 *
 */
public class ExceptionTest {

	/**
	 * 
	 */
	public ExceptionTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ExceptionTest test = new ExceptionTest();
			test.test();
		} catch(Exception e)
		{
			System.out.println(ExceptionUtil.exceptionToString(e));
		}
	}

	
	public void test() throws Exception {
		try {
			try {
				throw new IllegalArgumentException("TEST");
			} catch (Exception e)
			{
				throw new ConfigurationException(e);
			}
		} catch(Exception e)
		{
			throw new Exception(e);
		}
	}
}
