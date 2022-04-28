package app;

import static spark.Spark.*;
import service.ClienteService;

public class Aplicacao {

private static ClienteService clienteService = new ClienteService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/cliente/insert", (request, response) -> clienteService.insert(request, response));

        get("/cliente/:id", (request, response) -> clienteService.get(request, response));
        
        get("/cliente/list/:orderby", (request, response) -> clienteService.getAll(request, response));

        get("/cliente/update/:id", (request, response) -> clienteService.getToUpdate(request, response));
        
        post("/cliente/update/:id", (request, response) -> clienteService.update(request, response));
           
        get("/cliente/delete/:id", (request, response) -> clienteService.delete(request, response));

             
    }

}
