package com.pahlsoft.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.pahlsoft.model.Product;
 
@Path("/json/product")
public class JSONService {
 
	@GET
	@Path("/get")
	@Produces("text/html")
	public String getProductInJSON() {
		String output = "No Product Specified.";
		return output;
	}
	
	@GET
	@Path("/get/{param}")
	@Produces("application/json")
	public List<Product> getProductInJSON(@PathParam("param") int productId) {
		List<Product> pList = new ArrayList<Product>();
		
		if (productId==1) {
			Product product1 = new Product();
			product1.setName("iPad 3");
			product1.setQty(999);
			pList.add(product1);
		
		} else if (productId==2) {
			Product product2 = new Product();
			product2.setName("iPad 3");
			product2.setQty(999);
			pList.add(product2);
			
		} else {
			Product product = new Product();
			product.setName("no product specified");
			product.setQty(000);
			pList.add(product);
		  
		}
		
		return pList; 
 
	}
 
	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {
 
		String result = "Product created : " + product;
		return Response.status(201).entity(result).build();
 
	}
 
}