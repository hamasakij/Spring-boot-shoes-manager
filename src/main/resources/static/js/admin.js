$(function() {


	// 新規作成ボタン押下時 画面遷移
	$('button.new-entry').click(function() {
		var el = this;
		console.log(el)
		location.href = location.pathname + '/create';
	});

	// カテゴリ新規作成ボタン押下時 モーダル
	$('button.new-entry-category').click(function() {
		var el = this,
			data = $(el).data();

		$('.mini.modal.create-modal')
			.modal({
				//onHideでモーダルが閉じられた後も処理を記述
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');
	});

	// メーカー新規作成ボタン押下時 モーダル
	$('button.new-entry-maker').click(function() {
		var el = this,
			data = $(el).data();

		$('.mini.modal.create-modal')
			.modal({
				//onHideでモーダルが閉じられた後も処理を記述
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');
	});
	// 編集ボタン押下時
	$('button.edit-entry').click(function() {
		var el = this,
			data = $(el).data();
		console.log(el)
		location.href = location.pathname + '/' + data.id + '/detailEdit';
	});
	// 在庫編集ボタン押下時
	$('button.detail-entry').click(function() {
		var el = this,
			data = $(el).data();
		console.log(el)
		location.href = location.pathname + '/' + data.id + '/detail';
	});

	// カテゴリ編集ボタン押下時 モーダル
	$('button.edit-entry-category').click(function() {
		var el = this,
			data = $(el).data(),
			mode = data.mode,
			id = data.id,
			name = data.name;

		$('.mini.modal.edit-modal')
			.modal({
				onShow: function() {
					var el = this,
						$el = $(el),
						$form = $el.find('form.category-create-edit-form'),
						$id = $el.find('input[name=categoryId]'),
						$name = $el.find('input[name=categoryName]'),
						$method = $el.find('input[name=_method]');

					if (mode === 'edit') {
						$form.attr('action', '/master/category/' + id + '/edit');
						$method.val('PUT');
						$id.val(id);
					}

					$name.val(name);
				},
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');
	});

	// メーカー編集ボタン押下時 モーダル
	$('button.edit-entry-maker').click(function() {
		var el = this,
			data = $(el).data(),
			mode = data.mode,
			id = data.id,
			name = data.name;

		$('.mini.modal.edit-modal')
			.modal({
				onShow: function() {
					var el = this,
						$el = $(el),
						$form = $el.find('form.maker-create-edit-form'),
						$id = $el.find('input[name=makerId]'),
						$name = $el.find('input[name=makerName]'),
						$method = $el.find('input[name=_method]');

					if (mode === 'edit') {
						$form.attr('action', '/master/maker/' + id + '/edit');
						$method.val('PUT');
						$id.val(id);
					}

					$name.val(name);
				},
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');
	});


	// 削除ボタン押下時
	$('button.delete-entry').click(function() {
		var el = this,
			data = $(el).data();
		console.log(el)
		$('.mini.modal.delete-modal')
			.attr('data-id', data.id)
			.attr('data-url', data.url)
			.modal('show');

	});

	//
	$('.delete-ok-btn').click(function() {
		var el = this,
			$form = $('.delete-form'),
			data = $('.mini.modal.delete-modal').data(),
			action = location.pathname.replace('/index', '') // /index のときも考慮;

		// 動的にform作成
		// $('<form/>', {action: data.url, method: 'post'})
		//     .append($('<input/>', {type: 'hidden', name: '_method', value: 'DELETE'}))
		//     .append($('<input/>', {type: 'hidden', name: '_token', value: csrf.val()}))
		//     .appendTo(document.body)
		//     .submit();

		// ajax メソッドで同期リクエスト この場合、flashメッセージは出ない
		// $.ajax(data.url, {
		//     async: false,
		//     method: 'POST',
		//     data : {
		//         '_method': 'DELETE',
		//         '_token' : csrf.val()
		//     }
		// }).done(function(data) {
		//     location.href = location.pathname;
		// });

		// form送信
		$form.attr('action', data.url);
		$form.submit();

	});

	// 新規作成画面 保存
	$('.new-entry-save').click(function() {
		var el = this,
			$form = $('.new-create-form');

		if ($form) {
			//$form.attr('action', location.pathname.replace('/create', ''));
			$form.submit();
		}
		return false;
	});



	// カテゴリ 新規作成・編集 OKボタン
	$('div.button.category-create-edit-ok-btn').click(function() {
		var el = this,
			$modal = $('.ui.category-create-modal.edit-modal'),
			$form = $('.category-create-edit-form'),
			data = {},
			action,
			formData;

		action = $form.attr('action');
		// フォームのデータを取得
		formData = $form.serializeArray();

		// [{id="11"},{name: "名前"}] みたいな形を
		// {id="11", name: "名前"} に変換
		$(formData).each(function(index, obj) {
			data[obj.name] = obj.value;
		});

		// ajax
		$.ajax({
			type: 'post',
			url: action,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(data)
		})
			.done(function(res) {
				console.log("処理ができました");
				console.log(res);
				var errors;
				if (res.success) {
					var $successModal = $('.ui.ajax-success-modal'),
						$message;

					if ($successModal.length) {
						$message = $successModal.find('.message');
						$message.text(res.message);

						$successModal.modal({
							onHide: function() {
								location.reload();
							}
						}).modal('show');
					}

				} else {
					errors = res.errors;

					$.each(errors, function(key, val) {
						var $field = $form.find('.field.' + key),
							$pointLbl = $form.find('.ui.pointing.red.basic.label.' + key);
						if ($field.length) {
							$field.addClass('error');
						}

						$pointLbl.text(val);
						$pointLbl.removeClass('hidden');
					});


				}
			})
			.fail(function(res) {
				// エラーメッセージモーダル
				var json = res.responseJSON,
					$errModal = $('.ui.ajax-err-modal'),
					$message;

				if ($errModal.length) {
					$message = $errModal.find('.message');
					$message.text(json.message);

					$errModal.modal({
						onHide: function() {
							location.reload();
						}
					}).modal('show');
				}

			})
			.always(() => {
				$modal.modal('hide');
			});

		return false;
	});

	// メーカー 新規作成・編集 OKボタン
	$('div.button.maker-create-edit-ok-btn').click(function() {
		var el = this,
			$modal = $('.ui.maker-create-modal.edit-modal'),
			$form = $('.maker-create-edit-form'),
			data = {},
			action,
			formData;

		action = $form.attr('action');
		// フォームのデータを取得
		formData = $form.serializeArray();

		// [{id="11"},{name: "名前"}] みたいな形を
		// {id="11", name: "名前"} に変換
		$(formData).each(function(index, obj) {
			data[obj.name] = obj.value;
		});

		// ajax
		$.ajax({
			type: 'post',
			url: action,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(data)
		})
			.done(function(res) {
				console.log("処理ができました");
				console.log(res);
				var errors;
				if (res.success) {
					var $successModal = $('.ui.ajax-success-modal'),
						$message;

					if ($successModal.length) {
						$message = $successModal.find('.message');
						$message.text(res.message);

						$successModal.modal({
							onHide: function() {
								location.reload();
							}
						}).modal('show');
					}

				} else {
					errors = res.errors;

					$.each(errors, function(key, val) {
						var $field = $form.find('.field.' + key),
							$pointLbl = $form.find('.ui.pointing.red.basic.label.' + key);
						if ($field.length) {
							$field.addClass('error');
						}

						$pointLbl.text(val);
						$pointLbl.removeClass('hidden');
					});


				}
			})
			.fail(function(res) {
				// エラーメッセージモーダル
				var json = res.responseJSON,
					$errModal = $('.ui.ajax-err-modal'),
					$message;

				if ($errModal.length) {
					$message = $errModal.find('.message');
					$message.text(json.message);

					$errModal.modal({
						onHide: function() {
							location.reload();
						}
					}).modal('show');
				}

			})
			.always(() => {
				$modal.modal('hide');
			});

		return false;
	});
	// 在庫の出庫ボタン押下時 モーダル
	$('button.order_syukko').click(function() {
		var el = this,
			data = $(el).data(),
			mode = data.mode,
			id = data.id,
			name = data.name;
		ic = data.ic

		$('.modal.syukko-order-modal')
			.modal({
				onShow: function() {
					var el = this,
						$el = $(el),
						$form = $el.find('form.syukko-order-form'),
						$id = $el.find('input[name=productId]'),
						$name = $el.find('input[name=zaiko]'),
						$ic = $el.find('input[name=inventoryCount]'),


						$method = $el.find('input[name=_method]');

					if (mode === 'order') {
						$form.attr('action', '/system/syukko/' + id + '/order/');
						$method.val('PUT');
						$id.val(id);
					}

					$name.val(name);
					$ic.val(ic);


				},
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');

	});
	// 在庫・出庫 OKボタン
	$('div.button.syukko-order-ok-btn').click(function() {
		var el = this,
			$modal = $('.ui.syukko_order_modal'),
			$form = $('.syukko-order-form'),

			data = {},
			action,
			formData;


		action = $form.attr('action');
		// フォームのデータを取得
		formData = $form.serializeArray();
		console.log(formData);
		// [{id="11"},{name: "名前"}] みたいな形を
		// {id="11", name: "名前"} に変換
		$(formData).each(function(index, obj) {
			data[obj.name] = obj.value;

			if (obj.name == "syukkoDate") {
				data[obj.name] = new Date(obj.value);
			}

		});
		console.log(data);
		// ajax
		$.ajax({
			type: 'post',
			url: action,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(data)
		})
			.done(function(res) {
				console.log("処理ができました");
				console.log(res);
				var errors;
				if (res.success) {
					var $successModal = $('.ui.ajax-success-modal'),
						$message;

					if ($successModal.length) {
						$message = $successModal.find('.message');
						$message.text(res.message);

						$successModal.modal({
							onHide: function() {
								location.reload();
							}
						}).modal('show');
					}

				} else {
					errors = res.errors;

					$.each(errors, function(key, val) {
						var $field = $form.find('.field.' + key),
							$pointLbl = $form.find('.ui.pointing.red.basic.label.' + key);
						if ($field.length) {
							$field.addClass('error');
						}

						$pointLbl.text(val);
						$pointLbl.removeClass('hidden');
					});


				}
			})
			.fail(function(res) {
				// エラーメッセージモーダル
				var json = res.responseJSON,
					$errModal = $('.ui.ajax-err-modal'),
					$message;

				if ($errModal.length) {
					$message = $errModal.find('.message');
					$message.text(json.message);

					$errModal.modal({
						onHide: function() {
							location.reload();
						}
					}).modal('show');
				}

			})
			.always(() => {
				$modal.modal('hide');
			});

		return false;
	});

	// 出庫の出庫ボタン押下時 モーダル
	$('button.order_system_syukko').click(function() {


		var el = this,
			data = $(el).data(),
			mode = data.mode,
			id = data.id,
			name = data.name;



		$('.modal.syukko-check-modal')
			.modal({
				onShow: function() {
					var el = this,
						$el = $(el),
						$form = $el.find('form.syukko-order-form'),
						$id = $el.find('input[name=productId]'),
						$name = $el.find('input[name=syukko]'),




						$method = $el.find('input[name=_method]');

					if (mode === 'order') {
						$form.attr('action', '/system/syukko/check/');
						$method.val('PUT');
						$id.val(id);
					}

					$name.val(name);



				},
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');

	});
	// 出庫・出庫 OKボタン
	$('div.button.syukko-check-ok-btn').click(function() {
		var el = this,
			$modal = $('.ui.syukko_check_modal'),
			$form = $('.syukko-order-form'),

			data = {},
			action,
			formData;
		console.log(formData);
		var syukkoFormList = [];



		action = $form.attr('action');
		// フォームのデータを取得
		formData = $form.serializeArray();
		console.log(formData);
		//		// [{id="11"},{name: "名前"}] みたいな形を
		//		// {id="11", name: "名前"} に変換
		//		$(formData).each(function(index, obj) {

		productId = $("input[type=hidden][name=productId]");


		for (var i = 0; i < productId.length; i++) {

			quantity = $("input[type=text][name=quantity]");
			syukkoDate = $("input[type=text][name=syukkoDate]");



			var a = { productId: productId[i].value, quantity: quantity[i].value, syukkoDate: new Date(syukkoDate[i].value) };

			syukkoFormList.push(a);

		};

		console.log(syukkoFormList);

		// ajax
		$.ajax({
			type: 'post',
			url: action,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(syukkoFormList)

		})

			.done(function(res) {
				console.log("処理ができました");
				console.log(res);
				var errors;
				if (res.success) {
					var $successModal = $('.ui.ajax-success-modal'),
						$message;

					if ($successModal.length) {
						$message = $successModal.find('.message');
						$message.text(res.message);

						$successModal.modal({
							onHide: function() {
								location.reload();
							}
						}).modal('show');
					}

				} else {
					errors = res.errors;

					$.each(errors, function(key, val) {
						var $field = $form.find('.field.' + key),
							$pointLbl = $form.find('.ui.pointing.red.basic.label.' + key);
						if ($field.length) {
							$field.addClass('error');
						}

						$pointLbl.text(val);
						$pointLbl.removeClass('hidden');
					});


				}
			})
			.fail(function(res) {
				// エラーメッセージモーダル
				var json = res.responseJSON,
					$errModal = $('.ui.ajax-err-modal'),
					$message;

				if ($errModal.length) {
					$message = $errModal.find('.message');
					$message.text(json.message);

					$errModal.modal({
						onHide: function() {
							location.reload();
						}
					}).modal('show');
				}

			})
			.always(() => {
				$modal.modal('hide');
			});

		return false;
	});

	// 入庫の入庫ボタン押下時 モーダル
	$('button.order_system_nyuuko').click(function() {
		var el = this,
			data = $(el).data(),
			mode = data.mode,
			id = data.id,
			name = data.name;

		$('.modal.nyuuko-check-modal')
			.modal({
				onShow: function() {
					var el = this,
						$el = $(el),
						$form = $el.find('form.nyuuko-order-form'),
						$id = $el.find('input[name=productId]'),
						$name = $el.find('input[name=nyuuko]'),



						$method = $el.find('input[name=_method]');

					if (mode === 'order') {
						$form.attr('action', '/system/nyuuko/check/');
						$method.val('PUT');
						$id.val(id);
					}

					$name.val(name);


				},
				onHide: function() {
					// リセット処理
					placeModalReset(this);
				}
			}).modal('show');

	});
	// 入庫・入庫 OKボタン
	$('div.button.nyuuko-check-ok-btn').click(function() {
		var el = this,
			$modal = $('.ui.nyuuko_check_modal'),
			$form = $('.nyuuko-order-form'),

			data = {},
			action,
			formData;
			var nyuukoFormList = [];


		action = $form.attr('action');
		// フォームのデータを取得
		formData = $form.serializeArray();
		productId = $("input[type=hidden][name=productId]");


		for (var i = 0; i < productId.length; i++) {

			quantity = $("input[type=text][name=quantity]");
			nyuukoDate = $("input[type=text][name=nyuukoDate]");

		console.log(quantity);
		console.log(nyuukoDate);

			var b = { productId: productId[i].value, quantity: quantity[i].value };

			nyuukoFormList.push(b);

		};
		console.log(quantity);
		console.log(nyuukoDate);

		console.log(nyuukoFormList);
		// ajax
		$.ajax({
			type: 'post',
			url: action,
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(nyuukoFormList)
		})
			.done(function(res) {
				console.log("処理ができました");
				console.log(res);
				var errors;
				if (res.success) {
					var $successModal = $('.ui.ajax-success-modal'),
						$message;

					if ($successModal.length) {
						$message = $successModal.find('.message');
						$message.text(res.message);

						$successModal.modal({
							onHide: function() {
								location.reload();
							}
						}).modal('show');
					}

				} else {
					errors = res.errors;

					$.each(errors, function(key, val) {
						var $field = $form.find('.field.' + key),
							$pointLbl = $form.find('.ui.pointing.red.basic.label.' + key);
						if ($field.length) {
							$field.addClass('error');
						}

						$pointLbl.text(val);
						$pointLbl.removeClass('hidden');
					});


				}
			})
			.fail(function(res) {
				// エラーメッセージモーダル
				var json = res.responseJSON,
					$errModal = $('.ui.ajax-err-modal'),
					$message;

				if ($errModal.length) {
					$message = $errModal.find('.message');
					$message.text(json.message);

					$errModal.modal({
						onHide: function() {
							location.reload();
						}
					}).modal('show');
				}

			})
			.always(() => {
				$modal.modal('hide');
			});

		return false;
	});

	// 書籍管理 サムネイルファイルフィールド
	$('input[name=thumbnail_file]').change(function() {
		var el = this,
			$el = $(el),
			$thumbnail = $('input[name=thumbnail]'),
			$img = $('img.thumbnail-img'),
			$addBtn = $('.thumbnail-add'),
			$clearBtn = $('.thumbnail-clear'),
			file,
			url;

		file = el.files.length > 0 ? el.files[0] : null;
		$img.addClass('loading');

		if (location.pathname.indexOf('/create') > 0) {
			url = location.pathname.replace('/create', '/uploadThumbnail');
		} else {
			var retUrl = location.pathname.split('/');
			var ret = [];
			$(retUrl).each(function(i, item) {
				if (retUrl && !item.match(/\d/) && item !== 'edit') {
					ret.push(item);
				}
			});
			url = ret.join('/') + '/uploadThumbnail';
		}

		fileUpload(file, url, function(res) {
			if (res.success) {
				var path = res.filePath + '/' + res.fileName;
				$thumbnail.val(res.fileName);
				$img.attr('src', path);
				$addBtn.removeClass('loading');
				$clearBtn.removeClass('disabled');
			}
		});
	});

	// 書籍管理 サムネイル追加ボタン
	$('.thumbnail-add').click(function() {
		var el = this,
			$el = $(el);

		$el.addClass('loading');
		// ファイルフィールドをクリック
		$('input[name=thumbnail_file]').click();
		return false;
	});

	// 書籍管理 サムネイルクリアボタン
	$('.thumbnail-clear').click(function() {
		var el = this,
			$el = $(el),
			$thumbnail = $('input[name=thumbnail]'),
			$img = $('img.thumbnail-img'),
			fileName;

		$el.addClass('loading');
		$img.addClass('loading');
		fileName = $thumbnail.val();

		fileDelete(fileName, location.pathname.replace('/create', '/clearThumbnail'), function(res) {
			if (res.success) {
				$thumbnail.val('');
				$img.attr('src', location.origin + '/storage/thumbnails/no-image.png');
				$el.addClass('disabled');
				$el.removeClass('loading');
			}
		});

		return false;
	});

	// 使用者管理 csvインポートボタン
	$('.new-entry-emp-csv').click(function() {
		var el = this,
			$el = $(el),
			$fileFeild = $('input[name=emp_csv_file]');

		$el.addClass('disabled');
		$el.addClass('loading');

		// ファイルフィールドをクリック
		$fileFeild.click();
		return false;
	});

	// 書使用者管理 csvファイルフィールド
	$('input[name=emp_csv_file]').change(function() {
		var el = this,
			$el = $(el),
			$addBtn = $('.new-entry-emp-csv'),
			file,
			url,
			$modal,
			$msg;

		file = el.files.length > 0 ? el.files[0] : null;
		if (!file) {
			return;
		}

		url = location.pathname + '/empImportCsv';

		fileUpload(file, url, function(res) {
			if (res.success) {
				$addBtn.removeClass('loading');
				$modal = $('.mini.modal.ajax-err-modal');
				$msg = $modal.find('.message');
				$msg.text(res.message);

			} else {
				$modal = $('.mini.modal.ajax-err-modal');
				$msg = $modal.find('.message');

				$msg.text(res.error);
			}

			$modal.modal({
				onHide: function() {
					location.reload();
				}
			}).modal('show');
		});
	});

});

/**
 * 保管場所のモーダルリセット処理
 * @param el
 */
function placeModalReset(el) {
	var $el = $(el),
		$errLabel = $el.find('.ui.pointing.red.basic.label'),
		$feild = $el.find('.field'),
		$form = $el.find('form.place-create-edit-form'),
		$id = $el.find('input[name=id]'),
		$name = $el.find('input[name=name]'),
		$method = $el.find('input[name=_method]');

	$errLabel.text('');
	$errLabel.addClass('hidden');
	$feild.removeClass('error');

	$form.attr('action', '/admin/place/create');
	$id.val('');
	$name.val('');
	$method.val('POST');
}

/**
 * 非同期通信でのファイルアップロード
 * @param file
 * @param url
 * @param callback
 * @returns {boolean}
 */
function fileUpload(file, url, callback) {
	var $token = $('input[name=_token]'),
		fd = new FormData(),
		xhr;

	if (!file) {
		return false;
	}

	if (file.size === 0) {
		return false;
	}

	fd.append('file', file, file.name);

	if ($token.length) {
		fd.append('_token', $token.val());
	}

	xhr = new XMLHttpRequest();

	xhr.open('POST', url, true);

	xhr.onload = function() {
		if (xhr.status === 200) {
			var res = xhr.response;
			res = $.parseJSON(res);
			callback(res);
		} else {

		}
	};

	xhr.send(fd);
}

/**
 * 非同期通信でのファイル削除
 * @param fileName
 * @param url
 * @param callback
 * @returns {boolean}
 */
function fileDelete(fileName, url, callback) {
	var $token = $('input[name=_token]'),
		fd = new FormData(),
		xhr;

	if (!fileName) {
		return false;
	}

	fd.append('fileName', fileName);

	if ($token.length) {
		fd.append('_token', $token.val());
	}

	xhr = new XMLHttpRequest();

	xhr.open('POST', url, true);

	xhr.onload = function() {
		if (xhr.status === 200) {
			var res = xhr.response;
			res = $.parseJSON(res);
			callback(res);
		} else {

		}
	};

	xhr.send(fd);
}
