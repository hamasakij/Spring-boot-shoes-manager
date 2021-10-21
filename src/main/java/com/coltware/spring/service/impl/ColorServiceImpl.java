package com.coltware.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.model.Color;
import com.coltware.spring.repository.ColorRepository;
import com.coltware.spring.service.ColorService;
@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	
	@Autowired
	private ColorRepository colorRepository;
	/**
	 * カラー一覧取得
	 */
	@Override
	public List<Color> getColors() {
		return colorRepository.findAll();
	}

}
