package projet.wrapper;

public class PageWrapper {

	/* *******************************************************/
	/* ***               Attributs                       *** */
	/* *******************************************************/
	
	private int currentPage;
	
	private String orderByColumns;
	
	private boolean orderByType;
	
	private String pattern;
	
	
	
	/* *******************************************************/
	/* ***               Constructors                    *** */
	/* *******************************************************/
	
	
	public PageWrapper() {
		super();
	}

	public PageWrapper(String currentPageString,String orderByColumns, String orderByTypeString,String pattern) {
		int currentPage=1;
		if(currentPageString!=null && currentPageString.matches("\\d+")) {
			currentPage=Integer.parseInt(currentPageString);
			currentPage=Math.max(currentPage, 1);
		} 
		this.currentPage=currentPage;
		if (orderByColumns==null) {
			this.orderByColumns="name";
		} else {
			this.orderByColumns=orderByColumns;
		}
		this.orderByType=true;
		if (orderByTypeString!=null && ("true".equals(orderByTypeString) || "false".equals(orderByTypeString))){
			this.orderByType=Boolean.parseBoolean(orderByTypeString);
		}
		this.pattern=pattern;
		
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
	 * @return the orderByColumns
	 */
	public String getOrderByColumns() {
		return orderByColumns;
	}




	/**
	 * @param orderByColumns the orderByColumns to set
	 */
	public void setOrderByColumns(String orderByColumns) {
		this.orderByColumns = orderByColumns;
	}




	/**
	 * @return the orderByType
	 */
	public boolean getOrderByType() {
		return orderByType;
	}




	/**
	 * @param orderByType the orderByType to set
	 */
	public void setOrderByType(boolean orderByType) {
		this.orderByType = orderByType;
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
