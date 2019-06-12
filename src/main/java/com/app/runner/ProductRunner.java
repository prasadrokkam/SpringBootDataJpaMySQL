package com.app.runner;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repo.ProductRepository;

@Component
public class ProductRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Product p = new Product();
		
		
		/*
		 * p.setProdId(101); p.setProdCode("pizza"); p.setProdCost(3.3);
		 * 
		 * Product pob = repo.save(p);
		 * 
		 * repo.save(new Product(102,"burger",4.3)); repo.save(new
		 * Product(103,"pastry",4.3)); repo.save(new Product(104,"cake",5.3));
		 */		

		//update all
		repo.save(new Product(104,"choclate",1.1));
		
		//bulk insert
		List<Product> list = Arrays.asList(
				new Product(105,"orange",6.1),
				new Product(106,"lemon",2.4),
				new Product(106,"butterscotch",8.5),
				new Product(107,"vennila",1.5),
				new Product(108,"strowbary",0.5)
				);
		
		repo.saveAll(list);
		
		//fetch one row
		Optional<Product> fid = repo.findById(100);
		if(fid.isPresent()) {  //avoid null pointer exception
				Product p1 = fid.get();
				System.out.println(p1.getProdCode());
		}else {
			System.out.println("Row not found");
		}
		
		
		//fetch all rows
		List<Product> list1 = repo.findAll();
		list1.forEach(System.out::println);
		
		System.out.println("-----------------");
		
		for (Product p1: list1) {
			System.out.println(p1.getProdId()+"  "+p1.getProdCode()+"  "+p1.getProdCost());
		}
	
		//delete all rows
		//repo.deleteAll();
		repo.deleteAllInBatch();
	}

}
