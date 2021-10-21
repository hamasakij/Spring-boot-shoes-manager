package com.coltware.spring.service.impl;

import static com.coltware.spring.specification.ProductSpecifications.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.form.ProductSearchForm;
import com.coltware.spring.model.Product;
import com.coltware.spring.repository.ProductRepository;
import com.coltware.spring.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductRepository productRepository;

	/**
	 * 商品一覧取得 IDや名前などをリストにし、1つにまとめる カテゴリなどID表示のものを名前で表示する
	 */
	@Override
	public List<ProductDto> getProducts(ProductSearchForm productSearchForm) {
		//検索条件
		Long categoryId = productSearchForm.getCategoryId();
		Long makerId = productSearchForm.getMakerId();
		Long colorId = productSearchForm.getColorId();
		Long sizeId = productSearchForm.getSizeId();
		Long productCode = productSearchForm.getProductCode();
		String productName = productSearchForm.getProductName();
		Long minPrice = productSearchForm.getMinPrice();
		Long maxPrice = productSearchForm.getMaxPrice();
		Boolean deleted = productSearchForm.getDeleted();
		
		// productRepositoty.findAllで取得した情報をdtoにコピーする
		List<Product> list = productRepository.findAll(Specification.where(categoryIdContains(categoryId))
																	.or(makerIdContains(makerId))
																	.and(colorIdContains(colorId))
																	.and(sizeIdContains(sizeId))
																	.and(codeContains(productCode))
																	.and(nameContains(productName))
																	.and(minPriceContains(minPrice))
																	.and(maxPriceContains(maxPrice))
																	.and(deletedContains(deleted))
																	);

		List<ProductDto> resultList = new ArrayList<ProductDto>();
		for (Product product : list) {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(product, dto);
			dto.setProductInfo(product);

			resultList.add(dto);
			
		}

		return resultList;
	}

	/**
	 * 商品の作成
	 */
	@Override
	public Product doInsert(ProductForm productForm) {
		Product product = new Product();
		// 同じプロパティ同士コピー
		BeanUtils.copyProperties(productForm, product);
		return productRepository.save(product);
	}

	/**
	 * 商品の情報を取得
	 */
	@Override
	public ProductForm getDetail(Long productId) {
		Product product = productRepository.getById(productId);
		ProductForm productForm = new ProductForm();

		BeanUtils.copyProperties(product, productForm);
		return productForm;
	}

	/**
	 * 商品の詳細情報を更新
	 */
	@Override
	public Product detailUpdate(ProductForm productForm) {
		Product product = new Product();
		// 同じプロパティをコピー
		BeanUtils.copyProperties(productForm, product);
		return productRepository.save(product);
	}

	/**
	 * idをキーに商品を削除
	 */
	@Override
	public Product deleteProduct(Long productId ) {

		Product product = productRepository.getById(productId);
		product.setDeleted(true);
		logger.debug("削除できました{}", product);
		return productRepository.save(product);
	}

}
