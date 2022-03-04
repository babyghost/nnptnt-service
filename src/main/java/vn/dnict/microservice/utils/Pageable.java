package vn.dnict.microservice.utils;

import lombok.Data;

@Data
public final class Pageable {
	private Integer pageSize;
	private Integer totalPage;
	private Integer currentPage;
	private Integer offset;

	
	public Pageable() {}
	public Pageable(Integer pageSize, Integer page, Integer totalRowEntity) {
		if(pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
		
		Integer totalPage = 0;
		if(totalRowEntity != null && totalRowEntity != 0) {
			totalPage = totalRowEntity / pageSize;	
		}
		this.totalPage = totalPage;
		Integer crpage = 0;
		if(page < this.totalPage) {
			crpage = page;
		}
		this.currentPage = crpage;
		Integer os = this.currentPage * this.pageSize;
		
		this.offset = os;
	}
}
