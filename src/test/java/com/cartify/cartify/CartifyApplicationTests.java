package com.cartify.cartify;

import com.cartify.cartify.controller.CartController;
import com.cartify.cartify.controller.ProductController;
import com.cartify.cartify.model.Cart;
import com.cartify.cartify.model.Product;
import com.cartify.cartify.repository.CartRepository;
import com.cartify.cartify.repository.ProductRepository;
import com.cartify.cartify.service.CartService;
import com.cartify.cartify.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CartController.class, ProductController.class})
class CartifyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartRepository cartRepository;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private CartService cartService;

	@MockBean
	private ProductService productService;


	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}



	@Test
	public void testGetAllCarts() throws Exception {
		List<Cart> cartList = new ArrayList<>();
		when(cartRepository.findAll()).thenReturn(cartList);

		mockMvc.perform(MockMvcRequestBuilders.get("/carts"))
				.andExpect(status().isOk());

		verify(cartRepository, times(1)).findAll();
	}

	@Test
	public void testGetCartById() throws Exception {
		Cart cart = new Cart();
		Long cartId = 1L;

		when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

		mockMvc.perform(MockMvcRequestBuilders.get("/carts/{cartId}", cartId))
				.andExpect(status().isOk());

		verify(cartRepository, times(1)).findById(cartId);
	}

	@Test
	public void testCreateCart() throws Exception {
		mockMvc.perform(post("/carts/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"products\": [{\"description\": \"Product for testing\", \"amount\": 19.99}], \"activityTime\": \"2023-08-15T12:00:00\"}"))
				.andExpect(status().isCreated());
		verify(cartRepository, times(1)).save(any(Cart.class));
	}

	@Test
	public void testDeleteCart() throws Exception {
		Long cartId = 1L;

		when(cartRepository.existsById(cartId)).thenReturn(true);

		mockMvc.perform(delete("/carts/{cartId}/delete", cartId))
				.andExpect(status().isOk());

		verify(cartRepository, times(1)).deleteById(cartId);
	}

	@Test
	public void testAddProductToCart() throws Exception {
		Long cartId = 1L;
		Long productId = 1L;

		Cart cart = new Cart();
		Product product = new Product();


		when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		mockMvc.perform(post("/carts/{cartId}/add", cartId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());

	}

	@Test
	public void testGetProducts() throws Exception {
		Product product1 = new Product();
		Product product2 = new Product();
		List<Product> productList = Arrays.asList(product1, product2);

		when(productRepository.findAll()).thenReturn(productList);

		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(productList.size()));

		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void testGetProductById() throws Exception {
		Product product = new Product();
		Long productId = 1L;

		when(productRepository.findById(productId)).thenReturn(Optional.of(product));

		mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", productId))
				.andExpect(status().isOk());

		verify(productRepository, times(1)).findById(productId);
	}

	@Test
	public void testGetProductByIdNOK() throws Exception {
		Long productId = 1L;

		when(productRepository.findById(productId)).thenReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get("/products/{productId}", productId))
				.andExpect(status().isNotFound());

		verify(productRepository, times(1)).findById(productId);
	}

	@Test
	public void testCreateProduct() throws Exception {
		mockMvc.perform(post("/products/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"description\": \"Product for testing\", \"price\": 19.99}"))
				.andExpect(status().isCreated());
		verify(productRepository, times(1)).save(any(Product.class));
	}

	@Test
	public void testCreateProductError() throws Exception {
		when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Failed to save product"));

		String jsonContent = "{\"description\": \"Product for testing\", \"amount\": 10.0}";

		mockMvc.perform(post("/products/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonContent))
				.andExpect(status().isInternalServerError());

		verify(productRepository, times(1)).save(any(Product.class));
	}

}
