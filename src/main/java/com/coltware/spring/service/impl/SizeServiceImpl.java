package com.coltware.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.model.Size;
import com.coltware.spring.repository.SizeRepository;
import com.coltware.spring.service.SizeService;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;
	/**
	 * サイズ一覧取得
	 */
	@Override
	public List<Size> getSizes() {
		return sizeRepository.findAll();
	}

}
