package service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coltware.spring.SpringBootShoesManagerApplication;
import com.coltware.spring.model.Product;
import com.coltware.spring.repository.ProductRepository;
import com.coltware.spring.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootShoesManagerApplication.class)
class ProdcutServiceTest {

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductService productService;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(productService);;
	}
	
	@Test
	public void getProsuctTest() {
		
		Mockito.doReturn(resultList).when(productRepository).getProduct();
		List<Product> list = productRepository.findAll();
		assertThat(list.get(0).getProductId(),is(1));
		
	}

}
