package com.coltware.spring.response;

import lombok.Data;

@Data
public class MakerJsonResponse extends JsonResponse{

	/**
	 * メーカーId
	 */
	private Long makerId;
	
	/**
	 * メーカー名
	 */
	private String makerName;
}
