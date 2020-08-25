package org.crossroad.util.pdf;

public class PDFUtil {

	private PDFUtil() {
		// TODO Auto-generated constructor stub
	}



	/*
	 * For instance, if you want to create a page with the size of a 6-1/4
	 * Commercial Envelope, then you need to create a rectangle that measures 6 by
	 * 3.5 inch. The measurement system in PDF doesn't use inches, but user units.
	 * By default, 1 user unit = 1 point, and 1 inch = 72 points.
	 */
	public static float inchToPDFSize(float inch) {
		return (inch * 28.3f);
	}

}
