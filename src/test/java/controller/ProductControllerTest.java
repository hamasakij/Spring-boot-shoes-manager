package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coltware.spring.SpringBootShoesManagerApplication;
import com.coltware.spring.controller.ProductController;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.form.ProductSearchForm;
import com.coltware.spring.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootShoesManagerApplication.class)
class ProductControllerTest {

	private MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@Autowired
	ProductController productController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	// 一覧表示
	@Test
	public void indexTest() throws Exception {
		mockMvc.perform(get("/master/product")).andExpect(status().isOk())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーション(price)
	@Test
	public void indexSearchValidationPriceTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setMinPrice((long) -9000);
		form.setMaxPrice((long) -10000);
		form.setProductCode((long) 1);
		form.setProductName("a");
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーション(productName)
	@Test
	public void indexSearchValidationNameTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setMinPrice((long) 9000);
		form.setMaxPrice((long) 10000);
		form.setProductCode((long) 1);
		form.setProductName("aaaaaaaaaaaaaaaaaaaaaa");
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーションエラー無し(カテゴリだけ)
	@Test
	public void indexSearchCategoryTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setCategoryId((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(カラーだけ)
	@Test
	public void indexSearchColorTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setColorId((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(メーカーだけ)
	@Test
	public void indexSearchMakerTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setMakerId((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(サイズだけ)
	@Test
	public void indexSearchSizeTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setSizeId((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(最低金額だけ)
	@Test
	public void indexSearchMinPriceTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setMinPrice((long) 9000);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(最高金額だけ)
	@Test
	public void indexSearchMaxPriceTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setPrice((long) 9000);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors());
	}

	// 検索条件バリデーションエラー無し(商品コードだけ)
	@Test
	public void indexSearchCodeTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setProductCode((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーションエラー無し(商品名だけ)
	@Test
	public void indexSearchNameTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setProductName("aaaaaaa");
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーションエラー無し(プルダウンメニューの項目だけ)
	@Test
	public void indexSearchMenuTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーションエラー無し(テキストボックスの項目だけ)
	@Test
	public void indexSearchTextBoxTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setMinPrice((long) 9000);
		form.setMaxPrice((long) 10000);
		form.setProductCode((long) 1);
		form.setProductName("aaaaaaaaaa");
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 検索条件バリデーションエラー無し(すべての検索項目)
	@Test
	public void indexSearchTest() throws Exception {
		ProductSearchForm form = new ProductSearchForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setMinPrice((long) 9000);
		form.setMaxPrice((long) 10000);
		form.setProductCode((long) 1);
		form.setProductName("aaaaaaaa");
		mockMvc.perform(get("/master/product").flashAttr("productSearchForm", form)).andExpect(model().hasNoErrors())
				.andExpect(view().name("master/product/index"));
	}

	// 新規商品追加画面の表示
	@Test
	public void insertTest() throws Exception {
		mockMvc.perform(get("/master/product/create")).andExpect(status().isOk())
				.andExpect(view().name("master/product/create"));
	}

	// 商品詳細を表示
	@Test
	public void detailTest() throws Exception {

		mockMvc.perform(get("/master/product/1/detailEdit")).andExpect(status().isOk())
				.andExpect(view().name("master/product/detailEdit"));
	}

	// 新規登録した商品の生成バリデーションエラー(productName)
	@Test
	public void createValidationErrorNameTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 1);
		form.setProductCode((long) 1);
		form.setProductName("a");

		mockMvc.perform(post("/master/product/create").flashAttr("productForm", form)).andExpect(model().hasErrors())
				.andExpect(model().attribute("productForm", form)).andExpect(view().name("master/product/create"));
	}

	// 新規登録した商品の生成バリデーションエラー(price)
	@Test
	public void createValidationErrorPriceTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) -11);
		form.setProductCode((long) 1);
		form.setProductName("aaa");

		mockMvc.perform(post("/master/product/create").flashAttr("productForm", form)).andExpect(model().hasErrors())
				.andExpect(model().attribute("productForm", form)).andExpect(view().name("master/product/create"));
	}

	// 新規登録した商品の生成バリデーションエラー(productCode)
	@Test
	public void createValidationErrorCodeTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 11);
		form.setProductCode(null);
		form.setProductName("aaa");

		mockMvc.perform(post("/master/product/create").flashAttr("productForm", form)).andExpect(model().hasErrors())
				.andExpect(model().attribute("productForm", form)).andExpect(view().name("master/product/create"));
	}

	// 新規登録した商品の生成(成功)
	@Test
	public void createSuccessTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 1);
		form.setProductCode((long) 1);
		form.setProductName("aaa");

		mockMvc.perform(post("/master/product/create").flashAttr("productForm", form)).andExpect(model().hasNoErrors())
				.andExpect(model().attribute("productForm", form)).andExpect(view().name("redirect:/master/product"));

	}

	// 商品の詳細情報の編集バリデーションエラー(price)
	@Test
	public void detailEditValidationErrorPriceTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) -100);
		form.setProductCode((long) 1);
		form.setProductName("aaaa");

		mockMvc.perform(post("/master/product/detailEdit").flashAttr("productForm", form))
				.andExpect(model().hasErrors()).andExpect(model().attribute("productForm", form))
				.andExpect(view().name("master/product/detailEdit"));
	}

	// 商品の詳細情報の編集バリデーションエラー(code)
	@Test
	public void detailEditValidationErrorCodeTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 1);
		form.setProductCode(null);
		form.setProductName("aaaa");

		mockMvc.perform(post("/master/product/detailEdit").flashAttr("productForm", form))
				.andExpect(model().hasErrors()).andExpect(model().attribute("productForm", form))
				.andExpect(view().name("master/product/detailEdit"));
	}

	// 商品の詳細情報の編集バリデーションエラー(name)
	@Test
	public void detailEditValidationErrorNameTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setCategoryId((long) 1);
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 1);
		form.setProductCode((long) 1);
		form.setProductName("a");

		mockMvc.perform(post("/master/product/detailEdit").flashAttr("productForm", form))
				.andExpect(model().hasErrors()).andExpect(model().attribute("productForm", form))
				.andExpect(view().name("master/product/detailEdit"));
	}

	// 商品の詳細情報の編集バリデーションエラーなし
	@Test
	public void detailEditSuccessTest() throws Exception {
		ProductForm form = new ProductForm();
		form.setColorId((long) 1);
		form.setMakerId((long) 1);
		form.setSizeId((long) 1);
		form.setPrice((long) 1);
		form.setProductCode((long) 1);
		form.setProductName("aaaaa");

		mockMvc.perform(post("/master/product/detailEdit").flashAttr("productForm", form))
				.andExpect(model().hasNoErrors()).andExpect(model().attribute("productForm", form))
				.andExpect(view().name("redirect:/master/product"));
	}

//	 商品の削除
	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(post("/master/product/1/delete").flashAttr("productId", 1))
				.andExpect(view().name("redirect:/master/product"));
	}

}
