package com.mazhar.inventory;

import com.mazhar.inventory.model.Inventory;
import com.mazhar.inventory.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){
		return args -> {
			Inventory inv1 = new Inventory();
			inv1.setSkuCode("Iphone12");
			inv1.setQuantity(20);

			Inventory inv2 = new Inventory();
			inv2.setSkuCode("Iphone14");
			inv2.setQuantity(0);
			inventoryRepo.save(inv1);
			inventoryRepo.save(inv2);
		};
	}

}
