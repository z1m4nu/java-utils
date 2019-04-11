/**
 * 
 */
package org.crossroad.util;

/**
 * @author e.soden
 *
 */
public class PageSize {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float witdh = 0f;
	private float height = 0f;

	/**
	 * @param width
	 * @param height
	 */
	public PageSize(float width, float height) {
		this.witdh = width;
		this.height = height;
	}

	public static PageSize create(PageSizeType type) {
		PageSize size = null;

		switch (type) {
		case A0:
			size = new PageSize(2384, 3370);
			break;
		case A1:
			size = new PageSize(1684, 2384);
			break;
		case A2:
			size = new PageSize(1190, 1684);
			break;
		case A3:
			break;
		case A4:
			break;
		case A5:
			break;
		case A6:
			break;
		case A7:
			break;
		case A8:
			break;
		case A9:
			break;
		case A10:
			break;
		case B0:
			break;
		case B1:
			break;
		case B2:
			break;
		case B3:
			break;
		case B4:
			break;
		case B5:
			break;
		case B6:
			break;
		case B7:
			break;
		case B8:
			break;
		case B9:
			break;
		case B10:
			break;
		case LETTER:
			break;
		case LEGAL:
			break;
		case TABLOID:
			break;
		case LEDGER:
			break;
		case EXECUTIVE:
			break;
		default:
			break;
		}

		return size;
	}

	/**
	 * @return the witdh
	 */
	public float getWitdh() {
		return witdh;
	}

	/**
	 * @param witdh the witdh to set
	 */
	public void setWitdh(float witdh) {
		this.witdh = witdh;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	
}
