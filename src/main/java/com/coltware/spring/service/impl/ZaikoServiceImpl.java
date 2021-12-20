package com.coltware.spring.service.impl;

import static com.coltware.spring.specification.ZaikoSpecification.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.coltware.spring.dto.ZaikoDto;
import com.coltware.spring.form.ZaikoSearchForm;
import com.coltware.spring.model.Zaiko;
import com.coltware.spring.repository.ZaikoRepository;
import com.coltware.spring.service.ZaikoService;

@Service
@Transactional
public class ZaikoServiceImpl implements ZaikoService {

	@Autowired
	private ZaikoRepository zaikoRepository;

	/**
	 * 在庫数取得
	 */
	@Override
	public List<ZaikoDto> getInventory(ZaikoSearchForm zaikoSearchForm) {

		Long categoryId = zaikoSearchForm.getCategoryId();
		Long makerId = zaikoSearchForm.getMakerId();
		Long colorId = zaikoSearchForm.getColorId();
		Long sizeId = zaikoSearchForm.getSizeId();
		Long productCode = zaikoSearchForm.getProductCode();
		Long minPrice = zaikoSearchForm.getMinPrice();
		Long maxPrice = zaikoSearchForm.getMaxPrice();
		String productName = zaikoSearchForm.getProductName();
		Boolean deleted = zaikoSearchForm.getDeleted();

		List<Zaiko> list = zaikoRepository.findAll(Specification.where(categoryIdContains(categoryId))
				.and(makerIdContains(makerId)).and(colorIdContains(colorId)).and(sizeIdContains(sizeId))
				.and(codeContains(productCode)).and(minPriceContains(minPrice)).and(maxPriceContains(maxPrice))
				.and(nameContains(productName)).and(deletedContains(deleted)));
		
		List<ZaikoDto> resultList = new ArrayList<ZaikoDto>();
		for (Zaiko zaiko : list) {
			ZaikoDto dto = new ZaikoDto();
			BeanUtils.copyProperties(zaiko, dto);
			dto.setZaikoInfo(zaiko);
			resultList.add(dto);
		}
		return resultList;
	}

}
