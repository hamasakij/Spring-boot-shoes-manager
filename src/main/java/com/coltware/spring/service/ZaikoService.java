package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.ZaikoDto;
import com.coltware.spring.form.ZaikoSearchForm;

public interface ZaikoService {

	public List<ZaikoDto> getInventory(ZaikoSearchForm zaikoSearchForm);
}
