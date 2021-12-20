package com.coltware.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coltware.spring.dto.SyukkoDto;
import com.coltware.spring.form.SyukkoForm;
import com.coltware.spring.model.Syukko;
import com.coltware.spring.model.Zaiko;
import com.coltware.spring.repository.SyukkoRepository;
import com.coltware.spring.repository.ZaikoRepository;
import com.coltware.spring.service.SyukkoService;

@Service
@Transactional
public class SyukkoServiceImpl implements SyukkoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ZaikoRepository zaikoRepository;
	@Autowired
	private SyukkoRepository syukkoRepository;

	@Override
	public List<SyukkoDto> getInventory() {
		List<Zaiko> list = zaikoRepository.findAll();

		List<SyukkoDto> resultList = new ArrayList<SyukkoDto>();
		for (Zaiko zaiko : list) {
			SyukkoDto dto = new SyukkoDto();
			BeanUtils.copyProperties(zaiko, dto);
			dto.setProductAllInfo(zaiko);
			resultList.add(dto);
		}

		return resultList;
	}

	@Override
	public Syukko doInsert(SyukkoForm syukkoForm) {
		Syukko syukko = new Syukko();

		BeanUtils.copyProperties(syukkoForm, syukko);

		logger.debug("取得できました{}", syukkoForm);
		logger.debug("取得できました{}", syukko);

		return syukkoRepository.save(syukko);
	}

	@Override
	public List<Syukko> doInsert(List<SyukkoForm> syukkoFormList) {

		List<Syukko> resultList = new ArrayList<Syukko>();
		

		for(SyukkoForm form : syukkoFormList) {
			Syukko syukko = new Syukko();
			
			BeanUtils.copyProperties(form,syukko);
			
			resultList.add(syukko);
		}
		
		

		logger.debug("取得できました{}", syukkoFormList);
		logger.debug("取得できました{}", resultList);

		return syukkoRepository.saveAll(resultList);
	}

}
