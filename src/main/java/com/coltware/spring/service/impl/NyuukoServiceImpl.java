package com.coltware.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
	public List<Nyuuko> doInsert(List<NyuukoForm> nyuukoFormList) {
		List<Nyuuko> resultList = new ArrayList<Nyuuko>();

		for (NyuukoForm form : nyuukoFormList) {
			Nyuuko nyuuko = new Nyuuko();
			BeanUtils.copyProperties(form, nyuuko);
		}

		logger.debug("取得{}",nyuukoFormList);
		logger.debug("取得{}",resultList);
		
		return nyuukoRepository.saveAll(resultList);
	}

}
