package org.crossroad.util.pdf;


public enum PDFType {
	PDF, PDFA1A, PDFA1B, PDFA2A, PDFA2B, PDFA3A, PDFA3B, PDFA3U;
	
	public static PDFType fromString(String value)
	{
		return PDFType.valueOf(value.toUpperCase());
	}
}
