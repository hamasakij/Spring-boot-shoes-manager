package com.coltware.spring.response;

import lombok.Data;

@Data
public class CategoryJsonResponse extends JsonResponse {

	/**
     * id
     */
    private Long categoryId;

    /**
     * 名前
     */
    private String categoryName;
}
