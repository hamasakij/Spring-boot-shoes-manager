package com.coltware.spring.service.impl;

import static com.coltware.spring.specification.MakerSpecifications.*;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.exception.NotFoundException;
import com.coltware.spring.form.MakerForm;
import com.coltware.spring.form.MakerSearchForm;
import com.coltware.spring.model.Maker;
import com.coltware.spring.repository.MakerRepository;
import com.coltware.spring.service.MakerService;

@Service
@Transactional
public class MakerServiceImpl implements MakerService {

	@Autowired
	private MakerRepository makerRepository;
	
	/**
	 * メーカーのプルダウンメニュー取得
	 */
	@Override
	public List<Maker> getMakers(){
		return makerRepository.findAll();
	}
	/**
	 * メーカー一覧取得
	 */
	@Override
	public List<Maker> getMakers(MakerSearchForm makerSearchForm) {
		
		Long makerId = makerSearchForm.getMakerId();
		String makerName = makerSearchForm.getMakerName();
		Boolean deleted = makerSearchForm.getDeleted();
		
		return makerRepository.findAll(Specification.where(makerIdContains(makerId))
														.or(makerNameContains(makerName))
														.and(deletedContains(deleted)));
	}
	/**
	 * メーカーの作成
	 */
	@Override
	public Maker doInsert(MakerForm makerForm) {
		Maker maker = new Maker();
		BeanUtils.copyProperties(makerForm, maker);
		return makerRepository.save(maker);
	}
	
	@Override
	public MakerForm getMaker(Long makerId) {
		if (makerId == null) {
			throw new NotFoundException("指定したidがありません。");
		}
		Maker maker = makerRepository.getById(makerId);
		if (maker == null) {
			throw new NotFoundException("対象のレコードが見つかりません。");
		}
		MakerForm makerForm = new MakerForm();

		BeanUtils.copyProperties(maker, makerForm);
		return makerForm;
	}
	
	/**
	 * メーカーを更新
	 */
	@Override
	public Maker editMaker(MakerForm makerForm) {
		Maker maker = new Maker();
		if(makerForm == null) {
			throw new NotFoundException("対象のレコードが見つかりません");
		}
		BeanUtils.copyProperties(makerForm, maker);
		return makerRepository.save(maker);
	}
	
	/**
	 * Idをキーにメーカーを削除
	 */
	@Override
	public Maker deletedMaker(Long makerId) {
		Maker maker = makerRepository.getById(makerId);
		
		maker.setDeleted(true);
		return makerRepository.save(maker);
	}
	
	
	

	
	
}
