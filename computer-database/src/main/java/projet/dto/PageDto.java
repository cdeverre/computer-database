package projet.dto;

public class PageDto {

	private String currentPage;
	
	
	private String OrderByColumns;
	
	private String OrderByType;
	
	private String pattern;
	
	public PageDto(String currentPage, String numberOfPage,String OrderByColumns, String OrderByType,String pattern) {
		this.currentPage=currentPage;
		this.OrderByColumns=OrderByColumns;
		this.OrderByType=OrderByType;
		this.pattern=pattern;
	}
	
}
