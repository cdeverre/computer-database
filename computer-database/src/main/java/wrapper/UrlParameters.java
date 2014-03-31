package wrapper;

public class UrlParameters {

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private int currentPage;
	
	private int numberOfPage;
	
	private String OrderByColumns;
	
	private boolean OrderByType;
	
	private String pattern;
	
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	
	public UrlParameters() {
		super();
	}

	public UrlParameters(int currentPage, int numberOfPage,String OrderByColumns, boolean OrderByType,String pattern) {
		this.currentPage=currentPage;
		this.numberOfPage=numberOfPage;
		this.OrderByColumns=OrderByColumns;
		this.OrderByType=OrderByType;
		this.setPattern(pattern);
	}

	
	
	/* *******************************************************/
	/* ***               Methods                         *** */
	/* *******************************************************/
	

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}




	/**
	 * @return the numberOfPage
	 */
	public int getNumberOfPage() {
		return numberOfPage;
	}




	/**
	 * @param numberOfPage the numberOfPage to set
	 */
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}




	/**
	 * @return the orderByColumns
	 */
	public String getOrderByColumns() {
		return OrderByColumns;
	}




	/**
	 * @param orderByColumns the orderByColumns to set
	 */
	public void setOrderByColumns(String orderByColumns) {
		OrderByColumns = orderByColumns;
	}




	/**
	 * @return the orderByType
	 */
	public boolean isOrderByType() {
		return OrderByType;
	}




	/**
	 * @param orderByType the orderByType to set
	 */
	public void setOrderByType(boolean orderByType) {
		OrderByType = orderByType;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
