package com.coltware.spring.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.coltware.spring.form.valid.ValidGroup1;
import com.coltware.spring.form.valid.ValidGroup2;

import lombok.Data;

@Data
public class MakerForm {

	
	/**
	 * メーカーId
	 */
	private Long makerId;
	
	/**
	 * メーカー名
	 */
	@NotBlank(message="メーカー名を入力してください",groups = ValidGroup1.class)
	@Size(min=1, max=20, groups = ValidGroup2.class)
	private String makerName;
	
}
