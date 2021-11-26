package com.coltware.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.dto.NyuukoDto;
import com.coltware.spring.form.NyuukoForm;
import com.coltware.spring.model.Nyuuko;
import com.coltware.spring.model.Product;
import com.coltware.spring.repository.NyuukoRepository;
import com.coltware.spring.repository.ProductRepository;
import com.coltware.spring.service.NyuukoService;

@Service
@Transactional
public class NyuukoServiceImpl implements NyuukoService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private NyuukoRepository nyuukoRepository;

	/**
	 * 商品情報を取得
	 */
	@Override
	public List<NyuukoDto> getInventory() {

		List<Product> list = productRepository.findAll();
		List<NyuukoDto> resultList = new ArrayList<NyuukoDto>();

		for (Product product : list) {
			NyuukoDto dto = new NyuukoDto();
			
			BeanUtils.copyProperties(product, dto);
			dto.setProductAllInfo(product);
			resultList.add(dto);
		}
		return resultList;
	}

	/**
	 * 入庫処理
	 */
	@Override
	public Nyuuko doInsert(NyuukoForm nyuukoForm) {
		Nyuuko nyuuko = new Nyuuko();
		//同じプロパティ同士のコピー
		BeanUtils.copyProperties(nyuuko, nyuukoForm);
		return nyuukoRepository.save(nyuuko);
	}
	
	

}
