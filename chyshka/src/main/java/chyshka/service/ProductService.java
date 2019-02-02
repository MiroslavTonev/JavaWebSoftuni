package chyshka.service;

import java.util.List;

import chyshka.domain.models.service.ProductServiceModel;

public interface ProductService {
	
	void saveProduct(ProductServiceModel productServiceModel);
	
	List<ProductServiceModel> findAllProduct();
	
	ProductServiceModel findProductByName(String name);
}


